/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.control.menu.web.internal;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.constants.LayoutDisplayPageWebKeys;
import com.liferay.layout.security.permission.resource.LayoutContentModelResourcePermission;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.product.navigation.control.menu.BaseProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.io.IOException;
import java.io.Writer;

import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS,
		"product.navigation.control.menu.entry.order:Integer=100"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class LayoutHeaderProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry {

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

	@Override
	public String getURL(HttpServletRequest httpServletRequest) {
		return null;
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		Writer writer = httpServletResponse.getWriter();

		StringBundler sb = new StringBundler(22);

		sb.append("<div class=\"");
		sb.append(_getCssClass(httpServletRequest));
		sb.append("\"><span class=\"align-items-center ");
		sb.append("control-menu-level-1-heading d-flex mr-1\" ");
		sb.append("data-qa-id=\"headerTitle\"><h1 class=\"");
		sb.append("lfr-portal-tooltip h4 mb-0\" title=\"");

		String headerTitle = _getHeaderTitle(httpServletRequest);

		sb.append(HtmlUtil.escapeAttribute(headerTitle));

		sb.append("\">");
		sb.append(HtmlUtil.escape(headerTitle));

		if (_hasDraftLayout(httpServletRequest) &&
			_hasEditPermission(httpServletRequest)) {

			sb.append("<span class=\"sr-only\">");
			sb.append(_language.get(httpServletRequest, "draft"));
			sb.append("</span>");
		}

		sb.append("</h1>");

		if (_hasDraftLayout(httpServletRequest) &&
			_hasEditPermission(httpServletRequest)) {

			sb.append("<sup aria-hidden=\"true\" ");
			sb.append("class=\"flex-shrink-0 small\">*</sup>");
		}

		sb.append("</span>");

		if (_isDraftLayout(httpServletRequest)) {
			sb.append("<span class=\"bg-transparent flex-shrink-0 label ");
			sb.append("label-inverse-secondary ml-2 mr-0\">");
			sb.append("<span class=\"label-item label-item-expand\">");
			sb.append(_language.get(httpServletRequest, "draft"));
			sb.append("</span></span>");
		}

		sb.append("</div>");

		writer.write(sb.toString());

		return true;
	}

	@Override
	public boolean isRelevant(HttpServletRequest httpServletRequest) {
		String layoutMode = ParamUtil.getString(
			httpServletRequest, "p_l_mode", Constants.VIEW);

		if (layoutMode.equals(Constants.EDIT)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return false;
		}

		return super.isShow(httpServletRequest);
	}

	private String _getCssClass(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (!Objects.equals(
				layout.getType(), LayoutConstants.TYPE_COLLECTION)) {

			return "control-menu-nav-item control-menu-nav-item-content";
		}

		return "control-menu-nav-item";
	}

	private String _getHeaderTitle(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String portletId = ParamUtil.getString(httpServletRequest, "p_p_id");

		Layout layout = themeDisplay.getLayout();

		if (Validator.isNotNull(portletId) && layout.isSystem() &&
			!layout.isTypeControlPanel() &&
			Objects.equals(
				layout.getFriendlyURL(),
				PropsValues.CONTROL_PANEL_LAYOUT_FRIENDLY_URL)) {

			return _portal.getPortletTitle(portletId, themeDisplay.getLocale());
		}

		if (layout.isTypeAssetDisplay()) {
			LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
				(LayoutDisplayPageObjectProvider<?>)
					httpServletRequest.getAttribute(
						LayoutDisplayPageWebKeys.
							LAYOUT_DISPLAY_PAGE_OBJECT_PROVIDER);

			if (layoutDisplayPageObjectProvider != null) {
				InfoItemFieldValuesProvider infoItemFieldValuesProvider =
					_infoItemServiceRegistry.getFirstInfoItemService(
						InfoItemFieldValuesProvider.class,
						layoutDisplayPageObjectProvider.getClassName());

				InfoItemFieldValues infoItemFieldValues =
					infoItemFieldValuesProvider.getInfoItemFieldValues(
						layoutDisplayPageObjectProvider.getDisplayObject());

				InfoFieldValue<Object> titleInfoFieldValue =
					infoItemFieldValues.getInfoFieldValue("title");

				if (titleInfoFieldValue != null) {
					return String.valueOf(
						titleInfoFieldValue.getValue(themeDisplay.getLocale()));
				}

				InfoFieldValue<Object> nameInfoFieldValue =
					infoItemFieldValues.getInfoFieldValue("name");

				if (nameInfoFieldValue != null) {
					return String.valueOf(
						nameInfoFieldValue.getValue(themeDisplay.getLocale()));
				}
			}

			AssetEntry assetEntry = (AssetEntry)httpServletRequest.getAttribute(
				WebKeys.LAYOUT_ASSET_ENTRY);

			if (assetEntry != null) {
				return assetEntry.getTitle(themeDisplay.getLanguageId());
			}
		}

		return layout.getName(themeDisplay.getLocale());
	}

	private boolean _hasDraftLayout(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (!layout.isTypeContent()) {
			return false;
		}

		Layout draftLayout = null;

		if (layout.isDraftLayout()) {
			draftLayout = layout;

			layout = _layoutLocalService.fetchLayout(draftLayout.getClassPK());
		}
		else {
			draftLayout = layout.fetchDraftLayout();
		}

		if (((draftLayout != null) && draftLayout.isDraft()) ||
			!layout.isPublished()) {

			return true;
		}

		return false;
	}

	private boolean _hasEditPermission(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		try {
			if (_layoutContentModelResourcePermission.contains(
					themeDisplay.getPermissionChecker(), layout.getPlid(),
					ActionKeys.UPDATE) ||
				_layoutPermission.containsLayoutUpdatePermission(
					themeDisplay.getPermissionChecker(), layout)) {

				return true;
			}
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}

		return false;
	}

	private boolean _isDraftLayout(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (!layout.isTypeContent()) {
			return false;
		}

		String mode = ParamUtil.getString(httpServletRequest, "p_l_mode");

		if (Objects.equals(mode, Constants.EDIT) || !layout.isDraftLayout()) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutHeaderProductNavigationControlMenuEntry.class);

	@Reference
	private InfoItemServiceRegistry _infoItemServiceRegistry;

	@Reference
	private Language _language;

	@Reference
	private LayoutContentModelResourcePermission
		_layoutContentModelResourcePermission;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPermission _layoutPermission;

	@Reference
	private Portal _portal;

}