/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.web.internal.portlet;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rafael Praxedes
 */
@Component(
	property = {
		"model.class.name=com.liferay.dynamic.data.mapping.model.DDMStructure",
		"model.class.name=com.liferay.dynamic.data.mapping.model.DDMTemplate"
	},
	service = PortletProvider.class
)
public class DDMEditPortletProvider extends BasePortletProvider {

	@Override
	public String getPortletName() {
		return DDMPortletKeys.DYNAMIC_DATA_MAPPING;
	}

	@Override
	public Action[] getSupportedActions() {
		return _supportedActions;
	}

	private final Action[] _supportedActions = {Action.EDIT, Action.VIEW};

}