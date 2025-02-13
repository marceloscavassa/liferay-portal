/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.wish.list.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Andrea Di Giorgi
 */
public class NoSuchWishListItemException extends NoSuchModelException {

	public NoSuchWishListItemException() {
	}

	public NoSuchWishListItemException(String msg) {
		super(msg);
	}

	public NoSuchWishListItemException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchWishListItemException(Throwable throwable) {
		super(throwable);
	}

}