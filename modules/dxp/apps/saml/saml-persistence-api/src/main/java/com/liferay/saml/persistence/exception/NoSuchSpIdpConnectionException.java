/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Mika Koivisto
 */
public class NoSuchSpIdpConnectionException extends NoSuchModelException {

	public NoSuchSpIdpConnectionException() {
	}

	public NoSuchSpIdpConnectionException(String msg) {
		super(msg);
	}

	public NoSuchSpIdpConnectionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchSpIdpConnectionException(Throwable throwable) {
		super(throwable);
	}

}