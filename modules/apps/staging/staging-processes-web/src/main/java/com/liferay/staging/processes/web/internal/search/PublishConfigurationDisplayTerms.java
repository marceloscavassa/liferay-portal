/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging.processes.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Máté Thurzó
 */
public class PublishConfigurationDisplayTerms extends DisplayTerms {

	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public PublishConfigurationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		name = ParamUtil.getString(portletRequest, NAME);
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	protected String description;
	protected String name;

}