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

package com.liferay.headless.admin.list.type.internal.jaxrs.exception.mapper;

import com.liferay.list.type.exception.DuplicateListTypeEntryExternalReferenceCodeException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Diogo Melo
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Admin.List.Type)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Admin.List.Type.DuplicateListTypeEntryExternalReferenceCodeExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class DuplicateListTypeEntryExternalReferenceCodeExceptionMapper
	extends BaseExceptionMapper
		<DuplicateListTypeEntryExternalReferenceCodeException> {

	@Override
	protected Problem getProblem(
		DuplicateListTypeEntryExternalReferenceCodeException
			duplicateListTypeEntryExternalReferenceCodeException) {

		return new Problem(
			Response.Status.BAD_REQUEST,
			duplicateListTypeEntryExternalReferenceCodeException.getMessage());
	}

}