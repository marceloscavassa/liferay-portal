/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchFormInstanceReportException extends NoSuchModelException {

	public NoSuchFormInstanceReportException() {
	}

	public NoSuchFormInstanceReportException(String msg) {
		super(msg);
	}

	public NoSuchFormInstanceReportException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFormInstanceReportException(Throwable throwable) {
		super(throwable);
	}

}