/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.dashboard.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Ferrari
 */
@ExtendedObjectClassDefinition(
	category = "users",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.commerce.dashboard.web.internal.configuration.CommerceDashboardForecastPortletInstanceConfiguration",
	localization = "content/Language",
	name = "commerce-dashboard-forecast-portlet-instance-configuration-name"
)
public interface CommerceDashboardForecastPortletInstanceConfiguration {

	@Meta.AD(name = "asset-category-ids", required = false)
	public String assetCategoryIds();

}