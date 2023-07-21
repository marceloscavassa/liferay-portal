/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Riccardo Alberti
 */
public class DuplicateCommercePricingClassCPDefinitionRelException
	extends PortalException {

	public DuplicateCommercePricingClassCPDefinitionRelException() {
	}

	public DuplicateCommercePricingClassCPDefinitionRelException(String msg) {
		super(msg);
	}

	public DuplicateCommercePricingClassCPDefinitionRelException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateCommercePricingClassCPDefinitionRelException(
		Throwable throwable) {

		super(throwable);
	}

}