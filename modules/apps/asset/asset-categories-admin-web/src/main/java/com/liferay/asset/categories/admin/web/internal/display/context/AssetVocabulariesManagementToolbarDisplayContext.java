/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juergen Kappler
 */
public class AssetVocabulariesManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public AssetVocabulariesManagementToolbarDisplayContext(
			HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			AssetCategoriesDisplayContext assetCategoriesDisplayContext)
		throws PortalException {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			assetCategoriesDisplayContext.getVocabulariesSearchContainer());

		_assetCategoriesDisplayContext = assetCategoriesDisplayContext;
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setKeywords(
			StringPool.BLANK
		).buildString();
	}

	@Override
	public String getSearchContainerId() {
		return "assetVocabularies";
	}

	@Override
	protected String getDisplayStyle() {
		return _assetCategoriesDisplayContext.getDisplayStyle();
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"create-date"};
	}

	private final AssetCategoriesDisplayContext _assetCategoriesDisplayContext;

}