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

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectValidationRuleSetting;

import java.util.List;
import java.util.Objects;

/**
 * @author Marco Leo
 */
public class ObjectValidationRuleImpl extends ObjectValidationRuleBaseImpl {

	@Override
	public boolean compareOutputType(String outputType) {
		if (Objects.equals(getOutputType(), outputType)) {
			return true;
		}

		return false;
	}

	@Override
	public List<ObjectValidationRuleSetting> getObjectValidationRuleSettings() {
		return _objectValidationRuleSettings;
	}

	@Override
	public void setObjectValidationRuleSettings(
		List<ObjectValidationRuleSetting> objectValidationRuleSettings) {

		_objectValidationRuleSettings = objectValidationRuleSettings;
	}

	private List<ObjectValidationRuleSetting> _objectValidationRuleSettings;

}