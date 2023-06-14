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

package com.liferay.headless.builder.test.validation.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.builder.internal.constants.HeadlessBuilderConstants;
import com.liferay.headless.builder.test.util.APIApplicationTestUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio Jiménez del Coso
 */
@RunWith(Arquillian.class)
public class HeadlessBuilderObjectValidationTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws PortalException {
		_apiApplicationObjectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					HeadlessBuilderConstants.API_APPLICATION_ERC,
					CompanyThreadLocal.getCompanyId());
	}

	@Test(expected = ModelListenerException.class)
	public void testInvalidBaseURLPathAPIApplication() throws Exception {
		APIApplicationTestUtil.addAPIApplicationEntry(
			_apiApplicationObjectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"applicationStatus", "draft"
			).put(
				"baseURL",
				RandomTestUtil.randomString() + StringPool.FORWARD_SLASH
			).put(
				"title", RandomTestUtil.randomString()
			).build());
	}

	@Test
	public void testValidBaseURLPathAPIApplication() throws Exception {
		ObjectEntry apiApplication =
			APIApplicationTestUtil.addAPIApplicationEntry(
				_apiApplicationObjectDefinition,
				HashMapBuilder.<String, Serializable>put(
					"applicationStatus", "draft"
				).put(
					"baseURL", RandomTestUtil.randomString()
				).put(
					"title", RandomTestUtil.randomString()
				).build());

		Assert.assertNotNull(apiApplication);
	}

	private ObjectDefinition _apiApplicationObjectDefinition;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

}