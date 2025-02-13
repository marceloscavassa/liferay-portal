/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.target;

import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.target.CommerceDiscountTarget;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"commerce.discount.target.key=" + CommerceDiscountConstants.TARGET_PRODUCT_GROUPS,
		"commerce.discount.target.order:Integer=10"
	},
	service = CommerceDiscountTarget.class
)
public class ApplyToPricingClassCommerceDiscountTarget
	extends BaseCommerceDiscountTarget {

	@Override
	public String getKey() {
		return CommerceDiscountConstants.TARGET_PRODUCT_GROUPS;
	}

}