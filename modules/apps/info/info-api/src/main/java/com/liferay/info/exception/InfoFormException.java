/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.exception;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

/**
 * @author Rubén Pulido
 */
public class InfoFormException extends PortalException {

	public String getLocalizedMessage(Locale locale) {
		return LanguageUtil.get(
			locale, "an-error-occurred-while-sending-the-form-information");
	}

}