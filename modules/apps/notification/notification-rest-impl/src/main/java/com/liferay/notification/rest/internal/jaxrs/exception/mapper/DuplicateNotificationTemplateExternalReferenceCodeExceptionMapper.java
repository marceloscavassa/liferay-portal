/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.rest.internal.jaxrs.exception.mapper;

import com.liferay.notification.exception.DuplicateNotificationTemplateExternalReferenceCodeException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Artur Souza
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Notification.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Notification.REST.DuplicateNotificationTemplateExternalReferenceCodeExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class DuplicateNotificationTemplateExternalReferenceCodeExceptionMapper
	extends BaseExceptionMapper
		<DuplicateNotificationTemplateExternalReferenceCodeException> {

	@Override
	protected Problem getProblem(
		DuplicateNotificationTemplateExternalReferenceCodeException
			duplicateNotificationTemplateExternalReferenceCodeException) {

		return new Problem(
			duplicateNotificationTemplateExternalReferenceCodeException);
	}

}