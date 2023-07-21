/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchVersionedEntryException extends NoSuchModelException {

	public NoSuchVersionedEntryException() {
	}

	public NoSuchVersionedEntryException(String msg) {
		super(msg);
	}

	public NoSuchVersionedEntryException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchVersionedEntryException(Throwable throwable) {
		super(throwable);
	}

}