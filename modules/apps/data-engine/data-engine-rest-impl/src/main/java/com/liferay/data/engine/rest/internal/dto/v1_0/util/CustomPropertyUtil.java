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

package com.liferay.data.engine.rest.internal.dto.v1_0.util;

import com.liferay.data.engine.rest.dto.v1_0.CustomProperty;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.petra.string.StringPool;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcela Cunha
 */
public class CustomPropertyUtil {

	public static CustomProperty[] addCustomProperty(
		CustomProperty[] customProperties, String property,
		Object valueObject) {

		CustomProperty customProperty = new CustomProperty() {
			{
				key = property;
				value = valueObject;
			}
		};

		return ArrayUtil.append(customProperties, customProperty);
	}

	public static Boolean getBooleanCustomProperty(
		CustomProperty[] customProperties, String property,
		boolean defaultValue) {

		for (CustomProperty customProperty : customProperties) {
			if (Objects.equals(property, customProperty.getKey())) {
				return GetterUtil.getBoolean(customProperty.getValue());
			}
		}

		return defaultValue;
	}

	public static String getStringCustomProperty(
		CustomProperty[] customProperties, String property) {

		for (CustomProperty customProperty : customProperties) {
			if (Objects.equals(property, customProperty.getKey())) {
				return GetterUtil.getString(customProperty.getValue());
			}
		}

		return StringPool.BLANK;
	}

}