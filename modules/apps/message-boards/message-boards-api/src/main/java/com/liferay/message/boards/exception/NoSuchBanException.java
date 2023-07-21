/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchBanException extends NoSuchModelException {

	public NoSuchBanException() {
	}

	public NoSuchBanException(String msg) {
		super(msg);
	}

	public NoSuchBanException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchBanException(Throwable throwable) {
		super(throwable);
	}

}