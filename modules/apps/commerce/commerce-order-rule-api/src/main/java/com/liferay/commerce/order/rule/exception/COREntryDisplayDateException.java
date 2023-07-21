/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Luca Pellizzon
 */
public class COREntryDisplayDateException extends PortalException {

	public COREntryDisplayDateException() {
	}

	public COREntryDisplayDateException(String msg) {
		super(msg);
	}

	public COREntryDisplayDateException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public COREntryDisplayDateException(Throwable throwable) {
		super(throwable);
	}

}