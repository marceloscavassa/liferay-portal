/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchPageTemplateStructureRelException
	extends NoSuchModelException {

	public NoSuchPageTemplateStructureRelException() {
	}

	public NoSuchPageTemplateStructureRelException(String msg) {
		super(msg);
	}

	public NoSuchPageTemplateStructureRelException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public NoSuchPageTemplateStructureRelException(Throwable throwable) {
		super(throwable);
	}

}