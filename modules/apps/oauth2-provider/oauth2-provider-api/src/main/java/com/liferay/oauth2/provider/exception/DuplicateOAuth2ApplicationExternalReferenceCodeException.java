/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.exception;

import com.liferay.portal.kernel.exception.DuplicateExternalReferenceCodeException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateOAuth2ApplicationExternalReferenceCodeException
	extends DuplicateExternalReferenceCodeException {

	public DuplicateOAuth2ApplicationExternalReferenceCodeException() {
	}

	public DuplicateOAuth2ApplicationExternalReferenceCodeException(
		String msg) {

		super(msg);
	}

	public DuplicateOAuth2ApplicationExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateOAuth2ApplicationExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}