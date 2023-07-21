/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.jaxrs.exception.mapper;

import com.liferay.data.engine.rest.resource.exception.DataDefinitionValidationException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Leonardo Barros
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Data.Engine.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Data.Engine.REST.DataDefinitionMustSetValidValidationExpressionValidationExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class
	DataDefinitionMustSetValidValidationExpressionValidationExceptionMapper
		extends BaseExceptionMapper
			<DataDefinitionValidationException.
				MustSetValidValidationExpression> {

	@Override
	protected Problem getProblem(
		DataDefinitionValidationException.MustSetValidValidationExpression
			mustSetValidValidationExpression) {

		return new Problem(
			JSONUtil.put(
				"expression",
				mustSetValidValidationExpression.getValidationExpression()
			).put(
				"fieldName", mustSetValidValidationExpression.getFieldName()
			).toString(),
			Response.Status.BAD_REQUEST,
			mustSetValidValidationExpression.getMessage(),
			DataDefinitionValidationException.MustSetValidValidationExpression.
				class.getName());
	}

}