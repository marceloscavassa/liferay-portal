/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.process;

/**
 * @author Shuyang Zhou
 */
public class ProcessException extends Exception {

	public ProcessException(String message) {
		super(message);
	}

	public ProcessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ProcessException(Throwable throwable) {
		super(throwable);
	}

	private static final long serialVersionUID = 1L;

}