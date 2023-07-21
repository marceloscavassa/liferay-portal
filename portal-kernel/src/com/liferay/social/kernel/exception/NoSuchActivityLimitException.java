/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchActivityLimitException extends NoSuchModelException {

	public NoSuchActivityLimitException() {
	}

	public NoSuchActivityLimitException(String msg) {
		super(msg);
	}

	public NoSuchActivityLimitException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchActivityLimitException(Throwable throwable) {
		super(throwable);
	}

}