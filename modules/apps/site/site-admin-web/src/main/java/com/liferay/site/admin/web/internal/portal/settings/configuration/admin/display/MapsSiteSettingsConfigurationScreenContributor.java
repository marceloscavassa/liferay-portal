/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.admin.web.internal.portal.settings.configuration.admin.display;

import com.liferay.map.constants.MapProviderWebKeys;
import com.liferay.map.util.MapProviderHelperUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.settings.configuration.admin.display.SiteSettingsConfigurationScreenContributor;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = SiteSettingsConfigurationScreenContributor.class)
public class MapsSiteSettingsConfigurationScreenContributor
	implements SiteSettingsConfigurationScreenContributor {

	@Override
	public String getCategoryKey() {
		return "maps";
	}

	@Override
	public String getJspPath() {
		return "/site_settings/maps.jsp";
	}

	@Override
	public String getKey() {
		return "site-settings-maps";
	}

	@Override
	public String getName(Locale locale) {
		return _language.get(locale, "maps");
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public void setAttributes(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		SiteSettingsConfigurationScreenContributor.super.setAttributes(
			httpServletRequest, httpServletResponse);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group siteGroup = themeDisplay.getSiteGroup();

		Group liveGroup = null;

		if (siteGroup.isStagingGroup()) {
			liveGroup = siteGroup.getLiveGroup();
		}
		else {
			liveGroup = siteGroup;
		}

		httpServletRequest.setAttribute(
			MapProviderWebKeys.MAP_PROVIDER_KEY,
			MapProviderHelperUtil.getMapProviderKey(
				_groupLocalService, themeDisplay.getCompanyId(),
				liveGroup.getGroupId()));
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Language _language;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.site.admin.web)")
	private ServletContext _servletContext;

}