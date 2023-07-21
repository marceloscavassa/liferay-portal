/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.values.query;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Adolfo Pérez
 */
public class DDMFormValuesQuerySyntaxException extends PortalException {

	public DDMFormValuesQuerySyntaxException() {
	}

	public DDMFormValuesQuerySyntaxException(String msg) {
		super(msg);
	}

	public DDMFormValuesQuerySyntaxException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public DDMFormValuesQuerySyntaxException(Throwable throwable) {
		super(throwable);
	}

}