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

package com.liferay.commerce.internal.starter;

import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.language.Language;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "commerce.region.starter.key=" + MexicoCommerceRegionsStarter.MEXICO_NUMERIC_ISO_CODE,
	service = CommerceRegionsStarter.class
)
public class MexicoCommerceRegionsStarter extends BaseCommerceRegionsStarter {

	public static final int MEXICO_NUMERIC_ISO_CODE = 484;

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "country.mexico");
	}

	@Override
	protected int getCountryIsoCode() {
		return MEXICO_NUMERIC_ISO_CODE;
	}

	@Override
	protected String getFilePath() {
		return _FILEPATH;
	}

	private static final String _FILEPATH =
		"com/liferay/commerce/internal/mexico.json";

	@Reference
	private Language _language;

}