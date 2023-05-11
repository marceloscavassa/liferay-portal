/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.content.security.policy.internal.servlet.filter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.security.content.security.policy.configuration.ContentSecurityPolicyConfiguration;
import com.liferay.portal.security.content.security.policy.constants.ContentSecurityPolicyConstants;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Olivér Kecskeméty
 */
public class ContentSecurityPolicyFilterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws PortalException {
		MockitoAnnotations.initMocks(this);

		PropsUtil.setProps(_props);

		Mockito.when(
			_props.get("feature.flag.LPS-134060")
		).thenReturn(
			"true"
		);

		Mockito.when(
			_portal.getScopeGroupId(Mockito.any(HttpServletRequest.class))
		).thenReturn(
			0L
		);

		ReflectionTestUtil.setFieldValue(
			_contentSecurityPolicyFilter, "_configurationProvider",
			_configurationProvider);

		ReflectionTestUtil.setFieldValue(
			_contentSecurityPolicyFilter, "_portal", _portal);
	}

	@Test
	public void testWhenConfigDisabledFilterShouldBeDisabled()
		throws Exception {

		_configureContentSecurityPolicy(false, "");
		Assert.assertFalse(
			_contentSecurityPolicyFilter.isFilterEnabled(
				_mockHttpServletRequest, _mockHttpServletResponse));
	}

	@Test
	public void testWhenConfigEnabledAndPolicyEmptyCspHeaderShouldNotBeAdded()
		throws Exception {

		_configureContentSecurityPolicy(true, "");
		Assert.assertTrue(
			_contentSecurityPolicyFilter.isFilterEnabled(
				_mockHttpServletRequest, _mockHttpServletResponse));

		_contentSecurityPolicyFilter.processFilter(
			_mockHttpServletRequest, _mockHttpServletResponse,
			_mockFilterChain);
		Assert.assertFalse(
			_mockHttpServletResponse.containsHeader(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER));
	}

	@Test
	public void testWhenConfigEnabledAndPolicyEmptyNonceShouldNotBeAddedToTags()
		throws Exception {

		PrintWriter printWriter = _mockHttpServletResponse.getWriter();

		printWriter.write(_TEST_HTML_BODY);
		printWriter.close();

		_configureContentSecurityPolicy(true, "");

		Assert.assertTrue(
			_contentSecurityPolicyFilter.isFilterEnabled(
				_mockHttpServletRequest, _mockHttpServletResponse));

		_contentSecurityPolicyFilter.processFilter(
			_mockHttpServletRequest, _mockHttpServletResponse,
			_mockFilterChain);

		String httpServletResponseContent =
			_mockHttpServletResponse.getContentAsString();

		Assert.assertFalse(httpServletResponseContent.contains("nonce"));
	}

	@Test
	public void testWhenConfiguredCorrectlyCspHeaderShouldBeAdded()
		throws Exception {

		String cspPolicy = "default-src 'self';";

		_configureContentSecurityPolicy(true, cspPolicy);

		Assert.assertTrue(
			_contentSecurityPolicyFilter.isFilterEnabled(
				_mockHttpServletRequest, _mockHttpServletResponse));

		_contentSecurityPolicyFilter.processFilter(
			_mockHttpServletRequest, _mockHttpServletResponse,
			_mockFilterChain);

		Assert.assertTrue(
			_mockHttpServletResponse.containsHeader(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER));

		Assert.assertEquals(
			_mockHttpServletResponse.getHeader(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER),
			cspPolicy);
	}

	@Test
	public void testWhenConfiguredCorrectlyNonceShouldBeGeneratedToBodyAndHeader()
		throws Exception {

		PrintWriter printWriter = _mockHttpServletResponse.getWriter();

		printWriter.write(_TEST_HTML_BODY);
		printWriter.close();

		_mockHttpServletResponse.setContentLength(_TEST_HTML_BODY.length());

		_configureContentSecurityPolicy(
			true,
			"default-src 'self'; script-src 'self' '[nonceToken]'; style-src " +
				"'self' '[nonceToken]'");

		Assert.assertTrue(
			_contentSecurityPolicyFilter.isFilterEnabled(
				_mockHttpServletRequest, _mockHttpServletResponse));

		_contentSecurityPolicyFilter.processFilter(
			_mockHttpServletRequest, _mockHttpServletResponse,
			_mockFilterChain);

		Assert.assertTrue(
			_mockHttpServletResponse.containsHeader(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER));

		String contentSecurityPolicyHeaderValue =
			_mockHttpServletResponse.getHeader(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER);

		Assert.assertNotNull(contentSecurityPolicyHeaderValue);

		String nonce = contentSecurityPolicyHeaderValue.substring(45, 69);

		Assert.assertTrue(
			contentSecurityPolicyHeaderValue.contains(
				"script-src 'self' 'nonce-" + nonce));

		Assert.assertTrue(
			contentSecurityPolicyHeaderValue.contains(
				"style-src 'self' 'nonce-" + nonce));

		String httpServletResponseContent =
			_mockHttpServletResponse.getContentAsString();

		Assert.assertTrue(
			httpServletResponseContent.contains("<link nonce=" + nonce));

		Assert.assertTrue(
			httpServletResponseContent.contains("<script nonce=" + nonce));

		Assert.assertTrue(
			httpServletResponseContent.contains("<style nonce=" + nonce));
	}

	private void _configureContentSecurityPolicy(boolean enabled, String policy)
		throws Exception {

		Mockito.when(
			_configurationProvider.getCompanyConfiguration(
				Mockito.eq(ContentSecurityPolicyConfiguration.class),
				Mockito.anyLong())
		).thenReturn(
			new ContentSecurityPolicyConfiguration() {

				@Override
				public boolean enabled() {
					return enabled;
				}

				@Override
				public String policy() {
					return policy;
				}

				@Override
				public String[] excludedURIPaths() {
					return new String[0];
				}

			}
		);
	}

	private static final String _TEST_HTML_BODY = StringBundler.concat(
		"<!DOCTYPE html><head><title>Test HTML Document</title><meta ",
		"charset=\"utf-8\"><meta name=\"description\" content=\"A blank HTML ",
		"document for testing purposes.\"><link rel=\"stylesheet\" href=\"",
		"test.css\"></link><script src=\"test.js\"></script><style>body {",
		"background-color: lightblue;}</style></head><body><h1>A Sample HTML ",
		"Document (Test File)</h1></body></html>");

	@Mock
	private ConfigurationProvider _configurationProvider;

	private final ContentSecurityPolicyFilter _contentSecurityPolicyFilter =
		new ContentSecurityPolicyFilter();
	private final MockFilterChain _mockFilterChain = new MockFilterChain();
	private final MockHttpServletRequest _mockHttpServletRequest =
		new MockHttpServletRequest();
	private final MockHttpServletResponse _mockHttpServletResponse =
		new MockHttpServletResponse();

	@Mock
	private Portal _portal;

	@Mock
	private Props _props;

}