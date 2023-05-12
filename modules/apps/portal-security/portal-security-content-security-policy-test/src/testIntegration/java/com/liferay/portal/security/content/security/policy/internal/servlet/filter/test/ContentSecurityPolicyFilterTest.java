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

package com.liferay.portal.security.content.security.policy.internal.servlet.filter.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.configuration.test.util.CompanyConfigurationTemporarySwapper;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.content.security.policy.internal.constants.ContentSecurityPolicyConstants;
import com.liferay.portal.test.rule.FeatureFlags;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Olivér Kecskeméty
 */
@FeatureFlags("LPS-134060")
@RunWith(Arquillian.class)
public class ContentSecurityPolicyFilterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testWhenConfigDisabledFilterShouldBeDisabled()
		throws Exception {

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper = _configureContentSecurityPolicy(
					false, "")) {

			HttpURLConnection httpClient = _executePortalCall();

			Map<String, List<String>> headerFields =
				httpClient.getHeaderFields();

			Assert.assertFalse(
				headerFields.containsKey(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER));
		}
	}

	@Test
	public void testWhenConfigEnabledAndPolicyEmptyCspHeaderShouldNotBeAdded()
		throws Exception {

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper = _configureContentSecurityPolicy(
					true, "")) {

			HttpURLConnection httpClient = _executePortalCall();

			Map<String, List<String>> headerFields =
				httpClient.getHeaderFields();

			Assert.assertFalse(
				headerFields.containsKey(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER));
		}
	}

	@Test
	public void testWhenConfigEnabledAndPolicyEmptyNonceShouldNotBeAddedToTags()
		throws Exception {

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper = _configureContentSecurityPolicy(
					true, "")) {

			HttpURLConnection httpClient = _executePortalCall();

			Map<String, List<String>> headerFields =
				httpClient.getHeaderFields();

			Assert.assertFalse(
				headerFields.containsKey(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER));

			String responseBody = _getResponseBody(httpClient);

			Assert.assertFalse(responseBody.contains("nonce="));
		}
	}

	@Test
	public void testWhenConfiguredCorrectlyCspHeaderShouldBeAdded()
		throws Exception {

		String cspPolicy = "default-src 'self';";

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper = _configureContentSecurityPolicy(
					true, cspPolicy)) {

			HttpURLConnection httpClient = _executePortalCall();

			Map<String, List<String>> headerFields =
				httpClient.getHeaderFields();

			Assert.assertTrue(
				headerFields.containsKey(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER));

			Assert.assertEquals(
				httpClient.getHeaderField(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER),
				cspPolicy);
		}

		_configureContentSecurityPolicy(true, cspPolicy);
	}

	@Test
	public void testWhenConfiguredCorrectlyNonceShouldBeGeneratedToBodyAndHeader()
		throws Exception {

		String cspPolicy =
			"default-src 'self'; script-src 'self' '[nonceToken]'; style-src " +
				"'self' '[nonceToken]'";

		try (CompanyConfigurationTemporarySwapper
				configurationTemporarySwapper = _configureContentSecurityPolicy(
					true, cspPolicy)) {

			HttpURLConnection httpClient = _executePortalCall();

			Map<String, List<String>> headerFields =
				httpClient.getHeaderFields();

			Assert.assertTrue(
				headerFields.containsKey(
					ContentSecurityPolicyConstants.
						CONTENT_SECURITY_POLICY_HEADER));

			String contentSecurityPolicyHeaderValue = httpClient.getHeaderField(
				ContentSecurityPolicyConstants.CONTENT_SECURITY_POLICY_HEADER);

			Assert.assertNotNull(contentSecurityPolicyHeaderValue);

			int nonceStartPos = contentSecurityPolicyHeaderValue.indexOf(
				ContentSecurityPolicyConstants.
					CONTENT_SECURITY_POLICY_NONCE_HEADER_PREFIX);

			int noncePrefixLength =
				ContentSecurityPolicyConstants.
					CONTENT_SECURITY_POLICY_NONCE_HEADER_PREFIX.length();

			int nonceRandomStartPos = nonceStartPos + noncePrefixLength;

			String nonce = contentSecurityPolicyHeaderValue.substring(
				nonceRandomStartPos, nonceRandomStartPos + 24);

			String substitutedCspPolicy = StringUtil.replace(
				cspPolicy,
				ContentSecurityPolicyConstants.
					CONTENT_SECURITY_POLICY_NONCE_TOKEN,
				ContentSecurityPolicyConstants.
					CONTENT_SECURITY_POLICY_NONCE_HEADER_PREFIX + nonce);

			Assert.assertEquals(
				contentSecurityPolicyHeaderValue, substitutedCspPolicy);

			String responseBody = _getResponseBody(httpClient);

			Assert.assertTrue(
				responseBody.contains("<link nonce='" + nonce + "'"));

			Assert.assertTrue(
				responseBody.contains("<script nonce='" + nonce + "'"));

			Assert.assertTrue(
				responseBody.contains("<style nonce='" + nonce + "'"));
		}
	}

	private CompanyConfigurationTemporarySwapper
			_configureContentSecurityPolicy(boolean enabled, String policy)
		throws Exception {

		return new CompanyConfigurationTemporarySwapper(
			TestPropsValues.getCompanyId(),
			"com.liferay.portal.security.content.security.policy.internal." +
				"configuration.ContentSecurityPolicyConfiguration",
			HashMapDictionaryBuilder.<String, Object>put(
				"enabled", enabled
			).put(
				"policy", policy
			).build(),
			SettingsFactoryUtil.getSettingsFactory());
	}

	private HttpURLConnection _executePortalCall() throws IOException {
		URL url = new URL("http://localhost:8080/web/guest");

		HttpURLConnection httpClient = (HttpURLConnection)url.openConnection();

		httpClient.setRequestMethod("GET");

		return httpClient;
	}

	private String _getResponseBody(HttpURLConnection httpClient)
		throws IOException {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(httpClient.getInputStream()))) {

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		}

		return sb.toString();
	}

}