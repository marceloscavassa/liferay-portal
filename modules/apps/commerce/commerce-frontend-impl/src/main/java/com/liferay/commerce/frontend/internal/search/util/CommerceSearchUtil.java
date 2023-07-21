/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.search.util;

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceSearchUtil.class)
public class CommerceSearchUtil {

	public String getAccountManagementFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getAccountManagementLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	public String getCatalogFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getCatalogLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	public String getOrdersFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getOrdersLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	public String getSearchFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getSearchLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	private Layout _getAccountManagementLayout(
			long groupId, boolean privateLayout)
		throws PortalException {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/accounts");

		if (layout != null) {
			return layout;
		}

		long plid = _portal.getPlidFromPortletId(
			groupId, AccountPortletKeys.ACCOUNT_ENTRIES_MANAGEMENT);

		if (plid > 0) {
			layout = _layoutLocalService.fetchLayout(plid);
		}

		return layout;
	}

	private Layout _getCatalogLayout(long groupId, boolean privateLayout)
		throws PortalException {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/catalog");

		if (layout != null) {
			return layout;
		}

		long plid = _portal.getPlidFromPortletId(
			groupId, CPPortletKeys.CP_SEARCH_RESULTS);

		if (plid > 0) {
			layout = _layoutLocalService.fetchLayout(plid);
		}

		return layout;
	}

	private Layout _getOrdersLayout(long groupId, boolean privateLayout)
		throws PortalException {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/cart");

		if (layout == null) {
			layout = _layoutLocalService.fetchLayoutByFriendlyURL(
				groupId, privateLayout, "/orders");
		}

		if (layout != null) {
			return layout;
		}

		long plid = _portal.getPlidFromPortletId(
			groupId, CommercePortletKeys.COMMERCE_CART_CONTENT);

		if (plid <= 0) {
			plid = _portal.getPlidFromPortletId(
				groupId, CommercePortletKeys.COMMERCE_ORDER_CONTENT);
		}

		if (plid > 0) {
			layout = _layoutLocalService.fetchLayout(plid);
		}

		return layout;
	}

	private Layout _getSearchLayout(long groupId, boolean privateLayout) {
		return _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/search");
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}