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

package com.liferay.headless.admin.user.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.admin.user.client.dto.v1_0.Organization;
import com.liferay.headless.admin.user.client.http.HttpInvoker;
import com.liferay.headless.admin.user.client.pagination.Page;
import com.liferay.headless.admin.user.client.pagination.Pagination;
import com.liferay.headless.admin.user.client.resource.v1_0.OrganizationResource;
import com.liferay.headless.admin.user.client.serdes.v1_0.OrganizationSerDes;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.net.URL;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseOrganizationResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();
		testLocale = LocaleUtil.getDefault();

		_resourceURL = new URL(
			"http://localhost:8080/o/headless-admin-user/v1.0");
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
			}
		};

		Organization organization1 = randomOrganization();

		String json = objectMapper.writeValueAsString(organization1);

		Organization organization2 = OrganizationSerDes.toDTO(json);

		Assert.assertTrue(equals(organization1, organization2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
			}
		};

		Organization organization = randomOrganization();

		String json1 = objectMapper.writeValueAsString(organization);
		String json2 = OrganizationSerDes.toJSON(organization);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testGetOrganizationsPage() throws Exception {
		Organization organization1 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		Organization organization2 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		Page<Organization> page = OrganizationResource.getOrganizationsPage(
			null, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(organization1, organization2),
			(List<Organization>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetOrganizationsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Organization organization1 = randomOrganization();

		organization1 = testGetOrganizationsPage_addOrganization(organization1);

		for (EntityField entityField : entityFields) {
			Page<Organization> page = OrganizationResource.getOrganizationsPage(
				null, getFilterString(entityField, "between", organization1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(organization1),
				(List<Organization>)page.getItems());
		}
	}

	@Test
	public void testGetOrganizationsPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Organization organization1 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Organization organization2 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		for (EntityField entityField : entityFields) {
			Page<Organization> page = OrganizationResource.getOrganizationsPage(
				null, getFilterString(entityField, "eq", organization1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(organization1),
				(List<Organization>)page.getItems());
		}
	}

	@Test
	public void testGetOrganizationsPageWithPagination() throws Exception {
		Organization organization1 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		Organization organization2 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		Organization organization3 = testGetOrganizationsPage_addOrganization(
			randomOrganization());

		Page<Organization> page1 = OrganizationResource.getOrganizationsPage(
			null, null, Pagination.of(1, 2), null);

		List<Organization> organizations1 =
			(List<Organization>)page1.getItems();

		Assert.assertEquals(
			organizations1.toString(), 2, organizations1.size());

		Page<Organization> page2 = OrganizationResource.getOrganizationsPage(
			null, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<Organization> organizations2 =
			(List<Organization>)page2.getItems();

		Assert.assertEquals(
			organizations2.toString(), 1, organizations2.size());

		assertEqualsIgnoringOrder(
			Arrays.asList(organization1, organization2, organization3),
			new ArrayList<Organization>() {
				{
					addAll(organizations1);
					addAll(organizations2);
				}
			});
	}

	@Test
	public void testGetOrganizationsPageWithSortDateTime() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Organization organization1 = randomOrganization();
		Organization organization2 = randomOrganization();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(
				organization1, entityField.getName(),
				DateUtils.addMinutes(new Date(), -2));
		}

		organization1 = testGetOrganizationsPage_addOrganization(organization1);

		organization2 = testGetOrganizationsPage_addOrganization(organization2);

		for (EntityField entityField : entityFields) {
			Page<Organization> ascPage =
				OrganizationResource.getOrganizationsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(organization1, organization2),
				(List<Organization>)ascPage.getItems());

			Page<Organization> descPage =
				OrganizationResource.getOrganizationsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(organization2, organization1),
				(List<Organization>)descPage.getItems());
		}
	}

	@Test
	public void testGetOrganizationsPageWithSortString() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Organization organization1 = randomOrganization();
		Organization organization2 = randomOrganization();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(organization1, entityField.getName(), "Aaa");
			BeanUtils.setProperty(organization2, entityField.getName(), "Bbb");
		}

		organization1 = testGetOrganizationsPage_addOrganization(organization1);

		organization2 = testGetOrganizationsPage_addOrganization(organization2);

		for (EntityField entityField : entityFields) {
			Page<Organization> ascPage =
				OrganizationResource.getOrganizationsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(organization1, organization2),
				(List<Organization>)ascPage.getItems());

			Page<Organization> descPage =
				OrganizationResource.getOrganizationsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(organization2, organization1),
				(List<Organization>)descPage.getItems());
		}
	}

	protected Organization testGetOrganizationsPage_addOrganization(
			Organization organization)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Page<Organization> invokeGetOrganizationsPage(
			String search, String filterString,
			com.liferay.portal.vulcan.pagination.Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location = _resourceURL + _toPath("/organizations");

		if (search != null) {
			location = HttpUtil.addParameter(location, "search", search);
		}

		if (filterString != null) {
			location = HttpUtil.addParameter(location, "filter", filterString);
		}

		if (pagination != null) {
			location = HttpUtil.addParameter(
				location, "page", pagination.getPage());
			location = HttpUtil.addParameter(
				location, "pageSize", pagination.getPageSize());
		}

		if (sortString != null) {
			location = HttpUtil.addParameter(location, "sort", sortString);
		}

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		return Page.of(string, OrganizationSerDes::toDTO);
	}

	protected Http.Response invokeGetOrganizationsPageResponse(
			String search, String filterString,
			com.liferay.portal.vulcan.pagination.Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location = _resourceURL + _toPath("/organizations");

		if (search != null) {
			location = HttpUtil.addParameter(location, "search", search);
		}

		if (filterString != null) {
			location = HttpUtil.addParameter(location, "filter", filterString);
		}

		if (pagination != null) {
			location = HttpUtil.addParameter(
				location, "page", pagination.getPage());
			location = HttpUtil.addParameter(
				location, "pageSize", pagination.getPageSize());
		}

		if (sortString != null) {
			location = HttpUtil.addParameter(location, "sort", sortString);
		}

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testGetOrganization() throws Exception {
		Organization postOrganization = testGetOrganization_addOrganization();

		Organization getOrganization = OrganizationResource.getOrganization(
			postOrganization.getId());

		assertEquals(postOrganization, getOrganization);
		assertValid(getOrganization);
	}

	protected Organization testGetOrganization_addOrganization()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Organization invokeGetOrganization(Long organizationId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath("/organizations/{organizationId}", organizationId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		try {
			return OrganizationSerDes.toDTO(string);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to process HTTP response: " + string, e);
			}

			throw e;
		}
	}

	protected Http.Response invokeGetOrganizationResponse(Long organizationId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath("/organizations/{organizationId}", organizationId);

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testGetOrganizationOrganizationsPage() throws Exception {
		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();
		Long irrelevantParentOrganizationId =
			testGetOrganizationOrganizationsPage_getIrrelevantParentOrganizationId();

		if ((irrelevantParentOrganizationId != null)) {
			Organization irrelevantOrganization =
				testGetOrganizationOrganizationsPage_addOrganization(
					irrelevantParentOrganizationId,
					randomIrrelevantOrganization());

			Page<Organization> page =
				OrganizationResource.getOrganizationOrganizationsPage(
					irrelevantParentOrganizationId, null, null,
					Pagination.of(1, 2), null);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantOrganization),
				(List<Organization>)page.getItems());
			assertValid(page);
		}

		Organization organization1 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		Organization organization2 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		Page<Organization> page =
			OrganizationResource.getOrganizationOrganizationsPage(
				parentOrganizationId, null, null, Pagination.of(1, 2), null);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(organization1, organization2),
			(List<Organization>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetOrganizationOrganizationsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();

		Organization organization1 = randomOrganization();

		organization1 = testGetOrganizationOrganizationsPage_addOrganization(
			parentOrganizationId, organization1);

		for (EntityField entityField : entityFields) {
			Page<Organization> page =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null,
					getFilterString(entityField, "between", organization1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(organization1),
				(List<Organization>)page.getItems());
		}
	}

	@Test
	public void testGetOrganizationOrganizationsPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();

		Organization organization1 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Organization organization2 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		for (EntityField entityField : entityFields) {
			Page<Organization> page =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null,
					getFilterString(entityField, "eq", organization1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(organization1),
				(List<Organization>)page.getItems());
		}
	}

	@Test
	public void testGetOrganizationOrganizationsPageWithPagination()
		throws Exception {

		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();

		Organization organization1 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		Organization organization2 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		Organization organization3 =
			testGetOrganizationOrganizationsPage_addOrganization(
				parentOrganizationId, randomOrganization());

		Page<Organization> page1 =
			OrganizationResource.getOrganizationOrganizationsPage(
				parentOrganizationId, null, null, Pagination.of(1, 2), null);

		List<Organization> organizations1 =
			(List<Organization>)page1.getItems();

		Assert.assertEquals(
			organizations1.toString(), 2, organizations1.size());

		Page<Organization> page2 =
			OrganizationResource.getOrganizationOrganizationsPage(
				parentOrganizationId, null, null, Pagination.of(2, 2), null);

		Assert.assertEquals(3, page2.getTotalCount());

		List<Organization> organizations2 =
			(List<Organization>)page2.getItems();

		Assert.assertEquals(
			organizations2.toString(), 1, organizations2.size());

		assertEqualsIgnoringOrder(
			Arrays.asList(organization1, organization2, organization3),
			new ArrayList<Organization>() {
				{
					addAll(organizations1);
					addAll(organizations2);
				}
			});
	}

	@Test
	public void testGetOrganizationOrganizationsPageWithSortDateTime()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();

		Organization organization1 = randomOrganization();
		Organization organization2 = randomOrganization();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(
				organization1, entityField.getName(),
				DateUtils.addMinutes(new Date(), -2));
		}

		organization1 = testGetOrganizationOrganizationsPage_addOrganization(
			parentOrganizationId, organization1);

		organization2 = testGetOrganizationOrganizationsPage_addOrganization(
			parentOrganizationId, organization2);

		for (EntityField entityField : entityFields) {
			Page<Organization> ascPage =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(organization1, organization2),
				(List<Organization>)ascPage.getItems());

			Page<Organization> descPage =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(organization2, organization1),
				(List<Organization>)descPage.getItems());
		}
	}

	@Test
	public void testGetOrganizationOrganizationsPageWithSortString()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Long parentOrganizationId =
			testGetOrganizationOrganizationsPage_getParentOrganizationId();

		Organization organization1 = randomOrganization();
		Organization organization2 = randomOrganization();

		for (EntityField entityField : entityFields) {
			BeanUtils.setProperty(organization1, entityField.getName(), "Aaa");
			BeanUtils.setProperty(organization2, entityField.getName(), "Bbb");
		}

		organization1 = testGetOrganizationOrganizationsPage_addOrganization(
			parentOrganizationId, organization1);

		organization2 = testGetOrganizationOrganizationsPage_addOrganization(
			parentOrganizationId, organization2);

		for (EntityField entityField : entityFields) {
			Page<Organization> ascPage =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(organization1, organization2),
				(List<Organization>)ascPage.getItems());

			Page<Organization> descPage =
				OrganizationResource.getOrganizationOrganizationsPage(
					parentOrganizationId, null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(organization2, organization1),
				(List<Organization>)descPage.getItems());
		}
	}

	protected Organization testGetOrganizationOrganizationsPage_addOrganization(
			Long parentOrganizationId, Organization organization)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetOrganizationOrganizationsPage_getParentOrganizationId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetOrganizationOrganizationsPage_getIrrelevantParentOrganizationId()
		throws Exception {

		return null;
	}

	protected Page<Organization> invokeGetOrganizationOrganizationsPage(
			Long parentOrganizationId, String search, String filterString,
			com.liferay.portal.vulcan.pagination.Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/organizations/{parentOrganizationId}/organizations",
					parentOrganizationId);

		if (search != null) {
			location = HttpUtil.addParameter(location, "search", search);
		}

		if (filterString != null) {
			location = HttpUtil.addParameter(location, "filter", filterString);
		}

		if (pagination != null) {
			location = HttpUtil.addParameter(
				location, "page", pagination.getPage());
			location = HttpUtil.addParameter(
				location, "pageSize", pagination.getPageSize());
		}

		if (sortString != null) {
			location = HttpUtil.addParameter(location, "sort", sortString);
		}

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		return Page.of(string, OrganizationSerDes::toDTO);
	}

	protected Http.Response invokeGetOrganizationOrganizationsPageResponse(
			Long parentOrganizationId, String search, String filterString,
			com.liferay.portal.vulcan.pagination.Pagination pagination,
			String sortString)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/organizations/{parentOrganizationId}/organizations",
					parentOrganizationId);

		if (search != null) {
			location = HttpUtil.addParameter(location, "search", search);
		}

		if (filterString != null) {
			location = HttpUtil.addParameter(location, "filter", filterString);
		}

		if (pagination != null) {
			location = HttpUtil.addParameter(
				location, "page", pagination.getPage());
			location = HttpUtil.addParameter(
				location, "pageSize", pagination.getPageSize());
		}

		if (sortString != null) {
			location = HttpUtil.addParameter(location, "sort", sortString);
		}

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		Organization organization1, Organization organization2) {

		Assert.assertTrue(
			organization1 + " does not equal " + organization2,
			equals(organization1, organization2));
	}

	protected void assertEquals(
		List<Organization> organizations1, List<Organization> organizations2) {

		Assert.assertEquals(organizations1.size(), organizations2.size());

		for (int i = 0; i < organizations1.size(); i++) {
			Organization organization1 = organizations1.get(i);
			Organization organization2 = organizations2.get(i);

			assertEquals(organization1, organization2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Organization> organizations1, List<Organization> organizations2) {

		Assert.assertEquals(organizations1.size(), organizations2.size());

		for (Organization organization1 : organizations1) {
			boolean contains = false;

			for (Organization organization2 : organizations2) {
				if (equals(organization1, organization2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				organizations2 + " does not contain " + organization1,
				contains);
		}
	}

	protected void assertValid(Organization organization) {
		boolean valid = true;

		if (organization.getDateCreated() == null) {
			valid = false;
		}

		if (organization.getDateModified() == null) {
			valid = false;
		}

		if (organization.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("comment", additionalAssertFieldName)) {
				if (organization.getComment() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"contactInformation", additionalAssertFieldName)) {

				if (organization.getContactInformation() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("image", additionalAssertFieldName)) {
				if (organization.getImage() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("keywords", additionalAssertFieldName)) {
				if (organization.getKeywords() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("location", additionalAssertFieldName)) {
				if (organization.getLocation() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (organization.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfOrganizations", additionalAssertFieldName)) {

				if (organization.getNumberOfOrganizations() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"parentOrganization", additionalAssertFieldName)) {

				if (organization.getParentOrganization() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("services", additionalAssertFieldName)) {
				if (organization.getServices() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Organization> page) {
		boolean valid = false;

		Collection<Organization> organizations = page.getItems();

		int size = organizations.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		Organization organization1, Organization organization2) {

		if (organization1 == organization2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("comment", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getComment(),
						organization2.getComment())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"contactInformation", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						organization1.getContactInformation(),
						organization2.getContactInformation())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getDateCreated(),
						organization2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getDateModified(),
						organization2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getId(), organization2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("image", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getImage(), organization2.getImage())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("keywords", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getKeywords(),
						organization2.getKeywords())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("location", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getLocation(),
						organization2.getLocation())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getName(), organization2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"numberOfOrganizations", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						organization1.getNumberOfOrganizations(),
						organization2.getNumberOfOrganizations())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"parentOrganization", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						organization1.getParentOrganization(),
						organization2.getParentOrganization())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("services", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						organization1.getServices(),
						organization2.getServices())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected Collection<EntityField> getEntityFields() throws Exception {
		if (!(_organizationResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_organizationResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField -> Objects.equals(entityField.getType(), type)
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Organization organization) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("comment")) {
			sb.append("'");
			sb.append(String.valueOf(organization.getComment()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("contactInformation")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							organization.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							organization.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(organization.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							organization.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							organization.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(organization.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("image")) {
			sb.append("'");
			sb.append(String.valueOf(organization.getImage()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("keywords")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("location")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(organization.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("numberOfOrganizations")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("parentOrganization")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("services")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected Organization randomOrganization() throws Exception {
		return new Organization() {
			{
				comment = RandomTestUtil.randomString();
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				id = RandomTestUtil.randomLong();
				image = RandomTestUtil.randomString();
				name = RandomTestUtil.randomString();
			}
		};
	}

	protected Organization randomIrrelevantOrganization() throws Exception {
		Organization randomIrrelevantOrganization = randomOrganization();

		return randomIrrelevantOrganization;
	}

	protected Organization randomPatchOrganization() throws Exception {
		return randomOrganization();
	}

	protected Group irrelevantGroup;
	protected String testContentType = "application/json";
	protected Group testGroup;
	protected Locale testLocale;
	protected String testUserNameAndPassword = "test@liferay.com:test";

	private Http.Options _createHttpOptions() {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");
		options.addHeader(
			"Accept-Language", LocaleUtil.toW3cLanguageId(testLocale));

		String encodedTestUserNameAndPassword = Base64.encode(
			testUserNameAndPassword.getBytes());

		options.addHeader(
			"Authorization", "Basic " + encodedTestUserNameAndPassword);

		options.addHeader("Content-Type", testContentType);

		return options;
	}

	private String _toJSON(Map<String, String> map) {
		if (map == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		Set<Map.Entry<String, String>> set = map.entrySet();

		Iterator<Map.Entry<String, String>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();

			sb.append("\"" + entry.getKey() + "\": ");

			if (entry.getValue() == null) {
				sb.append("null");
			}
			else {
				sb.append("\"" + entry.getValue() + "\"");
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private String _toPath(String template, Object... values) {
		if (ArrayUtil.isEmpty(values)) {
			return template;
		}

		for (int i = 0; i < values.length; i++) {
			template = template.replaceFirst(
				"\\{.*?\\}", String.valueOf(values[i]));
		}

		return template;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseOrganizationResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.admin.user.resource.v1_0.OrganizationResource
		_organizationResource;

	private URL _resourceURL;

}