/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.theme.favicon.servlet.internal;

import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Basto
 */
@Component(
	property = {
		"osgi.http.whiteboard.context.path=/favicon",
		"osgi.http.whiteboard.servlet.name=com.liferay.frontend.theme.favicon.servlet.internal.FaviconServlet",
		"osgi.http.whiteboard.servlet.pattern=/favicon/*"
	},
	service = {FaviconServlet.class, Servlet.class}
)
public class FaviconServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			httpServletResponse.sendRedirect(themeDisplay.getFaviconURL());

			return;
		}

		LayoutSet layoutSet = (LayoutSet)httpServletRequest.getAttribute(
			WebKeys.VIRTUAL_HOST_LAYOUT_SET);

		if (layoutSet != null) {
			Theme theme = layoutSet.getTheme();

			httpServletResponse.sendRedirect(
				theme.getContextPath() + theme.getImagesPath() +
					"/favicon.ico");
		}
	}

	private static final long serialVersionUID = 1L;

}