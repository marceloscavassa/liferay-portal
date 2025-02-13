/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.json.web.service.web.internal;

import com.liferay.petra.memory.DeleteFileFinalizeAction;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.test.FinalizeManagerUtil;
import com.liferay.portal.kernel.test.GCUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.upload.UploadServletRequestImpl;
import com.liferay.portal.util.PortalImpl;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Igor Spasic
 * @author Raymond Augé
 */
public class JSONWebServiceServiceActionTest
	extends BaseJSONWebServiceTestCase {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		initPortalServices();

		Class<?> clazz = JSONWebServiceServiceAction.class;

		PortalClassLoaderUtil.setClassLoader(clazz.getClassLoader());

		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(new PortalImpl());

		_jsonWebServiceServiceAction = new JSONWebServiceServiceAction();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		PortalClassLoaderUtil.setClassLoader(null);
	}

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			new JSONWebServiceActionsManagerUtil(),
			"_jsonWebServiceActionsManager",
			new JSONWebServiceActionsManagerImpl());
	}

	@After
	public void tearDown() throws InterruptedException {
		GCUtil.gc(true);

		FinalizeManagerUtil.drainPendingFinalizeActions();
	}

	@Test
	public void testInvokerNullCall() throws Exception {
		registerActionClass(FooService.class);

		String json = toJSON(
			LinkedHashMapBuilder.<String, Object>put(
				"/foo/null-return", new LinkedHashMap<>()
			).build());

		MockHttpServletRequest mockHttpServletRequest =
			createInvokerHttpServletRequest(json);

		Assert.assertEquals(
			"{}",
			_jsonWebServiceServiceAction.getJSON(
				mockHttpServletRequest, new MockHttpServletResponse()));
	}

	@Test
	public void testInvokerSimpleCall() throws Exception {
		registerActionClass(FooService.class);

		Map<String, Object> params = new LinkedHashMap<>();

		Map<String, Object> map = LinkedHashMapBuilder.<String, Object>put(
			"/foo/hello-world", params
		).build();

		params.put("userId", 173);
		params.put("worldName", "Jupiter");

		String json = toJSON(map);

		MockHttpServletRequest mockHttpServletRequest =
			createInvokerHttpServletRequest(json);

		Assert.assertEquals(
			"\"Welcome 173 to Jupiter\"",
			_jsonWebServiceServiceAction.getJSON(
				mockHttpServletRequest, new MockHttpServletResponse()));
	}

	@Test
	public void testMultipartRequest() throws Exception {
		registerActionClass(FooService.class);

		Map<String, FileItem[]> fileParams =
			HashMapBuilder.<String, FileItem[]>put(
				"fileName", new FileItem[] {_createFileItem("aaa")}
			).build();

		HttpServletRequest httpServletRequest = new UploadServletRequestImpl(
			createHttpRequest("/foo/add-file"), fileParams, null) {

			@Override
			public String getFileName(String name) {
				return "test";
			}

			@Override
			public Map<String, FileItem[]> getMultipartParameterMap() {
				return fileParams;
			}

		};

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			httpServletRequest);

		Assert.assertNotNull(jsonWebServiceAction);
	}

	@Test
	public void testMultipartRequestFilesUpload() throws Exception {
		registerActionClass(FooService.class);

		HttpServletRequest httpServletRequest = new UploadServletRequestImpl(
			createHttpRequest("/foo/upload-files"),
			HashMapBuilder.<String, FileItem[]>put(
				"firstFile", new FileItem[] {_createFileItem("aaa")}
			).put(
				"otherFiles",
				new FileItem[] {_createFileItem("bbb"), _createFileItem("ccc")}
			).build(),
			null);

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			httpServletRequest);

		Assert.assertNotNull(jsonWebServiceAction);

		Object result = jsonWebServiceAction.invoke();

		Assert.assertNotNull(result);

		Assert.assertEquals("aaabbbccc", result.toString());
	}

	@Test
	public void testServletContextInvoker1() throws Exception {
		testServletContextInvoker("somectx", true, "/foo/hello-world");
	}

	@Test
	public void testServletContextInvoker2() throws Exception {
		testServletContextInvoker("somectx", false, "/somectx.foo/hello-world");
	}

	@Test
	public void testServletContextRequestParams1() throws Exception {
		testServletContextRequestParams("somectx", true, "/foo/hello-world");
	}

	@Test
	public void testServletContextRequestParams2() throws Exception {
		testServletContextRequestParams(
			"somectx", false, "/somectx.foo/hello-world");
	}

	@Test
	public void testServletContextURL1() throws Exception {
		testServletContextURL(
			"somectx", true, "/foo/hello-world/user-id/173/world-name/Jupiter");
	}

	@Test
	public void testServletContextURL2() throws Exception {
		testServletContextURL(
			"somectx", false,
			"/somectx.foo/hello-world/user-id/173/world-name/Jupiter");
	}

	protected MockHttpServletRequest createInvokerHttpServletRequest(
		String content) {

		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setContent(content.getBytes());
		mockHttpServletRequest.setMethod(HttpMethods.POST);
		mockHttpServletRequest.setRemoteUser("root");

		return mockHttpServletRequest;
	}

	protected void testServletContextInvoker(
			String contextName, boolean setContextPath, String query)
		throws Exception {

		registerActionClass(FooService.class, contextName);

		Map<String, Object> params = new LinkedHashMap<>();

		Map<String, Object> map = LinkedHashMapBuilder.<String, Object>put(
			query, params
		).build();

		params.put("userId", 173);
		params.put("worldName", "Jupiter");

		String json = toJSON(map);

		MockHttpServletRequest mockHttpServletRequest =
			createInvokerHttpServletRequest(json);

		if (setContextPath) {
			setServletContext(mockHttpServletRequest, contextName);
		}

		Assert.assertEquals(
			"\"Welcome 173 to Jupiter\"",
			_jsonWebServiceServiceAction.getJSON(
				mockHttpServletRequest, new MockHttpServletResponse()));
	}

	protected void testServletContextRequestParams(
			String contextName, boolean setContextPath, String request)
		throws Exception {

		registerActionClass(FooService.class, contextName);

		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			request);

		mockHttpServletRequest.setParameter("userId", "173");
		mockHttpServletRequest.setParameter("worldName", "Jupiter");

		mockHttpServletRequest.setMethod(HttpMethods.GET);

		if (setContextPath) {
			setServletContext(mockHttpServletRequest, contextName);
		}

		Assert.assertEquals(
			"\"Welcome 173 to Jupiter\"",
			_jsonWebServiceServiceAction.getJSON(
				mockHttpServletRequest, new MockHttpServletResponse()));
	}

	protected void testServletContextURL(
			String contextName, boolean setContextPath, String request)
		throws Exception {

		registerActionClass(FooService.class, contextName);

		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			request);

		mockHttpServletRequest.setMethod(HttpMethods.GET);

		if (setContextPath) {
			setServletContext(mockHttpServletRequest, contextName);
		}

		Assert.assertEquals(
			"\"Welcome 173 to Jupiter\"",
			_jsonWebServiceServiceAction.getJSON(
				mockHttpServletRequest, new MockHttpServletResponse()));
	}

	private FileItem _createFileItem(String content) throws Exception {
		Path tempFilePath = Files.createTempFile(null, null);

		Files.write(tempFilePath, content.getBytes());

		File tempFile = tempFilePath.toFile();

		FinalizeManager.register(
			tempFile, new DeleteFileFinalizeAction(tempFile.getAbsolutePath()),
			FinalizeManager.PHANTOM_REFERENCE_FACTORY);

		return ProxyUtil.newDelegateProxyInstance(
			FileItem.class.getClassLoader(), FileItem.class,
			new Object() {

				public File getStoreLocation() {
					return tempFile;
				}

				public boolean isInMemory() {
					return false;
				}

			},
			null);
	}

	private static JSONWebServiceServiceAction _jsonWebServiceServiceAction;

}