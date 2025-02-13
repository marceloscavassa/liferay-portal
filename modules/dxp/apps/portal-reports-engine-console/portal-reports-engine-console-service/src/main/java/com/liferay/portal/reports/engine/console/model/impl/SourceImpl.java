/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.reports.engine.console.service.SourceLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SourceImpl extends SourceBaseImpl {

	@Override
	public String getAttachmentsDir() {
		return "reports_templates/".concat(String.valueOf(getSourceId()));
	}

	@Override
	public String[] getAttachmentsFileNames() throws PortalException {
		return SourceLocalServiceUtil.getAttachmentsFileNames(this);
	}

}