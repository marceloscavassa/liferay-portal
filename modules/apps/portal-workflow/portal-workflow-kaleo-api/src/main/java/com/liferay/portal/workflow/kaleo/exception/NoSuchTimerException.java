/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchTimerException extends NoSuchModelException {

	public NoSuchTimerException() {
	}

	public NoSuchTimerException(String msg) {
		super(msg);
	}

	public NoSuchTimerException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchTimerException(Throwable throwable) {
		super(throwable);
	}

}