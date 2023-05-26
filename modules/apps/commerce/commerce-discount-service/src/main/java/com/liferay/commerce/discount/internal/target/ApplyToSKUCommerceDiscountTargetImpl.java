/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.discount.internal.target;

import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.target.CommerceDiscountTarget;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"commerce.discount.target.key=" + CommerceDiscountConstants.TARGET_SKUS,
		"commerce.discount.target.order:Integer=20"
	},
	service = CommerceDiscountTarget.class
)
public class ApplyToSKUCommerceDiscountTargetImpl
	implements CommerceDiscountTarget {

	@Override
	public String getKey() {
		return CommerceDiscountConstants.TARGET_SKUS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return _language.get(
			resourceBundle, CommerceDiscountConstants.TARGET_SKUS);
	}

	@Override
	public Type getType() {
		return Type.APPLY_TO_SKU;
	}

	@Reference
	private Language _language;

}