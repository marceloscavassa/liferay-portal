/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.validation.rule;

import com.liferay.dynamic.data.mapping.expression.CreateExpressionRequest;
import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionException;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFactory;
import com.liferay.object.constants.ObjectValidationRuleConstants;
import com.liferay.object.dynamic.data.mapping.expression.ObjectEntryDDMExpressionFieldAccessor;
import com.liferay.object.internal.dynamic.data.mapping.expression.ObjectEntryDDMExpressionParameterAccessor;
import com.liferay.object.validation.rule.ObjectValidationRuleEngine;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ObjectValidationRuleEngine.class)
public class DDMObjectValidationRuleEngineImpl
	implements ObjectValidationRuleEngine {

	@Override
	public Map<String, Object> execute(
		Map<String, Object> inputObjects, String script) {

		Map<String, Object> results = HashMapBuilder.<String, Object>put(
			"invalidFields", false
		).put(
			"invalidScript", false
		).build();

		try {
			DDMExpression<Boolean> ddmExpression =
				_ddmExpressionFactory.createExpression(
					CreateExpressionRequest.Builder.newBuilder(
						script
					).withDDMExpressionFieldAccessor(
						new ObjectEntryDDMExpressionFieldAccessor(
							(Map<String, Object>)inputObjects.get("baseModel"))
					).withDDMExpressionParameterAccessor(
						new ObjectEntryDDMExpressionParameterAccessor(
							(Map<String, Object>)inputObjects.get(
								"originalBaseModel"))
					).build());

			ddmExpression.setVariables(
				(Map<String, Object>)inputObjects.get("baseModel"));

			results.put("invalidFields", !ddmExpression.evaluate());
		}
		catch (DDMExpressionException ddmExpressionException) {
			_log.error(ddmExpressionException);

			results.put("invalidScript", true);
		}
		catch (Exception exception) {
			_log.error(exception);

			results.put("invalidFields", true);
		}

		return results;
	}

	@Override
	public String getName() {
		return ObjectValidationRuleConstants.ENGINE_TYPE_DDM;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMObjectValidationRuleEngineImpl.class);

	@Reference
	private DDMExpressionFactory _ddmExpressionFactory;

}