/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuth2ApplicationPrivacyPolicyURLException
	extends PortalException {

	public OAuth2ApplicationPrivacyPolicyURLException() {
	}

	public OAuth2ApplicationPrivacyPolicyURLException(String msg) {
		super(msg);
	}

	public OAuth2ApplicationPrivacyPolicyURLException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public OAuth2ApplicationPrivacyPolicyURLException(Throwable throwable) {
		super(throwable);
	}

}