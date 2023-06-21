/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.internal.upgrade.v2_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Gustavo Lima
 */
public class SXPBlueprintUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeSXPBlueprintOptionsPortlets();

		_upgradeSearchBarPortlets();
	}

	private long _getSXPBlueprintId(String largeValue) {
		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
				StringBundler.concat(
					StringPool.OPEN_BRACKET, largeValue,
					StringPool.CLOSE_BRACKET));

			long sxpBlueprintId = 0;

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				JSONObject attributesJSONObject = jsonObject.getJSONObject(
					"attributes");

				if ((attributesJSONObject != null) &&
					attributesJSONObject.has("sxpBlueprintId")) {

					sxpBlueprintId = attributesJSONObject.getLong(
						"sxpBlueprintId");
				}
			}

			return sxpBlueprintId;
		}
		catch (JSONException jsonException) {
			throw new SystemException(jsonException);
		}
	}

	private void _upgradeLargeValue(
		String largeValue, String portletPreferencesIdQuoted,
		ResultSet resultSet) {

		try {
			if (resultSet.next()) {
				String newLargeValue = StringUtil.replace(
					largeValue,
					StringBundler.concat(
						StringUtil.quote("sxpBlueprintId", "\""), ":",
						_getSXPBlueprintId(largeValue)),
					StringBundler.concat(
						StringUtil.quote(
							"sxpBlueprintExternalReferenceCode", "\""),
						":",
						StringUtil.quote(
							resultSet.getString("externalReferenceCode"),
							"\"")));

				PreparedStatement preparedStatement =
					connection.prepareStatement(
						StringBundler.concat(
							"update PortletPreferenceValue set largeValue = ",
							StringUtil.quote(newLargeValue),
							" where portletPreferencesId = ",
							portletPreferencesIdQuoted, " and name = ",
							StringUtil.quote(
								"suggestionsContributorConfigurations")));

				preparedStatement.executeUpdate();
			}
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}
	}

	private void _upgradeSearchBarPortlet(
		String portletPreferenceIdQuoted, ResultSet resultSet) {

		try {
			while (resultSet.next()) {
				String name = resultSet.getString("name");

				if (name.equals("suggestionsContributorConfigurations")) {
					String largeValue = resultSet.getString("largeValue");

					PreparedStatement preparedStatement =
						connection.prepareStatement(
							StringBundler.concat(
								"select externalReferenceCode from ",
								"SXPBlueprint where sxpBlueprintId = ",
								_getSXPBlueprintId(largeValue)));

					_upgradeLargeValue(
						largeValue, portletPreferenceIdQuoted,
						preparedStatement.executeQuery());
				}
			}
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}
	}

	private void _upgradeSearchBarPortlets() {
		String portletIdQuoted = StringUtil.quote(
			"%com_liferay_portal_search_web_search_bar_portlet_" +
				"SearchBarPortlet_INSTANCE_%");

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select portletPreferencesId from PortletPreferences ",
					"where portletId like ", portletIdQuoted))) {

			ResultSet resultSet = preparedStatement1.executeQuery();

			while (resultSet.next()) {
				String portletPreferencesIdQuoted = StringUtil.quote(
					resultSet.getString("portletPreferencesId"));

				try (PreparedStatement preparedStatement2 =
						connection.prepareStatement(
							StringBundler.concat(
								"select name, largeValue from ",
								"PortletPreferenceValue where ",
								"portletPreferencesId = ",
								portletPreferencesIdQuoted))) {

					_upgradeSearchBarPortlet(
						portletPreferencesIdQuoted,
						preparedStatement2.executeQuery());
				}
			}
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}
	}

	private void _upgradeSmallValueAndName(
			String portletPreferencesIdQuoted, ResultSet resultSet)
		throws SQLException {

		if (resultSet.next()) {
			PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"update PortletPreferenceValue set smallValue = ",
					StringUtil.quote(
						resultSet.getString("externalReferenceCode")),
					" where portletPreferencesId = ",
					portletPreferencesIdQuoted, " and name = ",
					StringUtil.quote("sxpBlueprintId")));

			preparedStatement1.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(
				StringBundler.concat(
					"update PortletPreferenceValue  set name = ",
					StringUtil.quote("sxpBlueprintExternalReferenceCode"),
					" where portletPreferencesId = ",
					portletPreferencesIdQuoted, " and name = ",
					StringUtil.quote("sxpBlueprintId")));

			preparedStatement2.executeUpdate();
		}
	}

	private void _upgradeSXPBlueprintOptionsPortlet(
			String portletPreferencesIdQuoted, ResultSet resultSet)
		throws SQLException {

		while (resultSet.next()) {
			String name = resultSet.getString("name");

			if (name.equals("sxpBlueprintId")) {
				PreparedStatement preparedStatement =
					connection.prepareStatement(
						StringBundler.concat(
							"select externalReferenceCode from SXPBlueprint ",
							"where sxpBlueprintId = ",
							StringUtil.quote(
								resultSet.getString("smallValue"))));

				_upgradeSmallValueAndName(
					portletPreferencesIdQuoted,
					preparedStatement.executeQuery());
			}
		}
	}

	private void _upgradeSXPBlueprintOptionsPortlets() {
		String portletIdQuoted = StringUtil.quote(
			"%com_liferay_search_experiences_web_internal_blueprint_options_" +
				"portlet_SXPBlueprintOptionsPortlet_INSTANCE_%");

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select portletPreferencesId from PortletPreferences ",
					"where portletId like ", portletIdQuoted))) {

			ResultSet resultSet = preparedStatement1.executeQuery();

			while (resultSet.next()) {
				String portletPreferencesIdQuoted = StringUtil.quote(
					resultSet.getString("portletPreferencesId"));

				try (PreparedStatement preparedStatement2 =
						connection.prepareStatement(
							StringBundler.concat(
								"select name, smallValue from ",
								"PortletPreferenceValue where ",
								"portletPreferencesId = ",
								portletPreferencesIdQuoted))) {

					_upgradeSXPBlueprintOptionsPortlet(
						portletPreferencesIdQuoted,
						preparedStatement2.executeQuery());
				}
			}
		}
		catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

}