/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.internal.servlet;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.portal.servlet.friendly.url.PublicFriendlyURLServlet",
		"osgi.http.whiteboard.servlet.pattern=/web/*",
		"servlet.init.private=false", "servlet.type=friendly-url"
	},
	service = Servlet.class
)
public class PublicFriendlyURLServlet extends FriendlyURLServlet {
}