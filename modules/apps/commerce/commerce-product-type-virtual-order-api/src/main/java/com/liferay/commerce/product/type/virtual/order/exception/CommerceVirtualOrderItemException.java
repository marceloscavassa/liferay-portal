/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceVirtualOrderItemException extends PortalException {

	public CommerceVirtualOrderItemException() {
	}

	public CommerceVirtualOrderItemException(String msg) {
		super(msg);
	}

	public CommerceVirtualOrderItemException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public CommerceVirtualOrderItemException(Throwable throwable) {
		super(throwable);
	}

}