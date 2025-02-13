/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.builder.model.listener.test;

import com.liferay.headless.builder.test.BaseTestCase;
import com.liferay.headless.builder.util.APIApplicationTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.test.util.HTTPTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.test.rule.FeatureFlags;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergio Jiménez del Coso
 */
@FeatureFlags({"LPS-167253", "LPS-184413", "LPS-186757"})
public class APIApplicationPublisherObjectEntryModelListenerTest
	extends BaseTestCase {

	@Test
	public void test() throws Exception {
		String baseURL = RandomTestUtil.randomString();

		JSONObject jsonObject = HTTPTestUtil.invokeToJSONObject(
			JSONUtil.put(
				"applicationStatus", "published"
			).put(
				"baseURL", baseURL
			).put(
				"externalReferenceCode", _ERC_1
			).put(
				"title", RandomTestUtil.randomString()
			).toString(),
			"headless-builder/applications", Http.Method.POST);

		Assert.assertEquals(
			0,
			jsonObject.getJSONObject(
				"status"
			).get(
				"code"
			));

		APIApplicationTestUtil.assertDeployedAPIApplication(baseURL);

		HTTPTestUtil.invokeToJSONObject(
			JSONUtil.put(
				"applicationStatus", "unpublished"
			).toString(),
			"headless-builder/applications/by-external-reference-code/" +
				_ERC_1,
			Http.Method.PATCH);

		APIApplicationTestUtil.assertNotDeployedAPIApplication(baseURL);

		baseURL = RandomTestUtil.randomString();

		HTTPTestUtil.invokeToJSONObject(
			JSONUtil.put(
				"applicationStatus", "published"
			).put(
				"baseURL", baseURL
			).put(
				"externalReferenceCode", _ERC_2
			).put(
				"title", RandomTestUtil.randomString()
			).toString(),
			"headless-builder/applications", Http.Method.POST);

		APIApplicationTestUtil.assertDeployedAPIApplication(baseURL);

		HTTPTestUtil.invokeToJSONObject(
			null,
			"headless-builder/applications/by-external-reference-code/" +
				_ERC_2,
			Http.Method.DELETE);

		APIApplicationTestUtil.assertNotDeployedAPIApplication(baseURL);

		baseURL = RandomTestUtil.randomString();

		HTTPTestUtil.invokeToJSONObject(
			JSONUtil.put(
				"apiApplicationToAPIEndpoints",
				JSONUtil.put(
					JSONUtil.put(
						"description", "description"
					).put(
						"externalReferenceCode", RandomTestUtil.randomString()
					).put(
						"httpMethod", "get"
					).put(
						"name", "name"
					).put(
						"path",
						StringPool.FORWARD_SLASH + RandomTestUtil.randomString()
					).put(
						"scope", "company"
					))
			).put(
				"apiApplicationToAPISchemas",
				JSONUtil.put(
					JSONUtil.put(
						"apiSchemaToAPIProperties",
						JSONUtil.put(
							JSONUtil.put(
								"description", "description"
							).put(
								"name", "name"
							).put(
								"objectFieldERC", "APPLICATION_STATUS"
							))
					).put(
						"description", "description"
					).put(
						"externalReferenceCode", RandomTestUtil.randomString()
					).put(
						"mainObjectDefinitionERC", "L_API_APPLICATION"
					).put(
						"name", "name"
					))
			).put(
				"applicationStatus", "published"
			).put(
				"baseURL", baseURL
			).put(
				"externalReferenceCode", RandomTestUtil.randomString()
			).put(
				"title", RandomTestUtil.randomString()
			).toString(),
			"headless-builder/applications", Http.Method.POST);

		APIApplicationTestUtil.assertDeployedAPIApplication(baseURL);
	}

	private static final String _ERC_1 = RandomTestUtil.randomString();

	private static final String _ERC_2 = RandomTestUtil.randomString();

}