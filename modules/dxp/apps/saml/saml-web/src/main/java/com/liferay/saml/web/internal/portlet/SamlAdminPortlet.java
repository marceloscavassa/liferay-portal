/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.saml.constants.SamlPortletKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mika Koivisto
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=saml-portlet-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/admin/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"javax.portlet.display-name=SAML Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.mvc-command-names-default-views=/admin/view",
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"javax.portlet.portlet.info.keywords=SAML Admin",
		"javax.portlet.portlet.info.short-title=SAML Admin",
		"javax.portlet.portlet.info.title=SAML Admin",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class SamlAdminPortlet extends MVCPortlet {

	@Override
	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable throwable) {
		if (_log.isDebugEnabled()) {
			_log.debug(throwable.getMessage(), throwable);
		}
		else if (_log.isInfoEnabled()) {
			_log.info(throwable.getMessage());
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SamlAdminPortlet.class);

}