/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.converter;

import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pablo Molina
 */
public class AlignConverter {

	public static final Map<String, String> externalToInternalValuesMap =
		HashMapBuilder.put(
			"Baseline", "align-items-baseline"
		).put(
			"Center", "align-items-center"
		).put(
			"End", "align-items-end"
		).put(
			"Start", "align-items-start"
		).put(
			"Stretch", "align-items-stretch"
		).build();

	public static String convertToExternalValue(String value) {
		Set<String> externalValues = externalToInternalValuesMap.keySet();

		for (String externalValue : externalValues) {
			if (Objects.equals(
					value, externalToInternalValuesMap.get(externalValue))) {

				return externalValue;
			}
		}

		return null;
	}

	public static String convertToInternalValue(String label) {
		return externalToInternalValuesMap.get(label);
	}

}