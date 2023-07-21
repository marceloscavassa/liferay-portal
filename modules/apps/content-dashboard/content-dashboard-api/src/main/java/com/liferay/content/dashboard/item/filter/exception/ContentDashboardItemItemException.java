/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.content.dashboard.item.filter.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Cristina González
 */
public class ContentDashboardItemItemException extends PortalException {

	public ContentDashboardItemItemException(String msg) {
		super(msg);
	}

	public ContentDashboardItemItemException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ContentDashboardItemItemException(Throwable throwable) {
		super(throwable);
	}

}