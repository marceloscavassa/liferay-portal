/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.validation.rule;

import com.liferay.object.constants.ObjectValidationRuleConstants;
import com.liferay.object.scripting.executor.ObjectScriptingExecutor;
import com.liferay.object.validation.rule.ObjectValidationRuleEngine;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ObjectValidationRuleEngine.class)
public class GroovyObjectValidationRuleEngineImpl
	implements ObjectValidationRuleEngine {

	@Override
	public Map<String, Object> execute(
		Map<String, Object> inputObjects, String script) {

		return _objectScriptingExecutor.execute(
			inputObjects, SetUtil.fromArray("invalidFields"), script);
	}

	@Override
	public String getName() {
		return ObjectValidationRuleConstants.ENGINE_TYPE_GROOVY;
	}

	@Reference(target = "(scripting.language=groovy)")
	private ObjectScriptingExecutor _objectScriptingExecutor;

}