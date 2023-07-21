/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.processor.factory;

import com.liferay.portal.kernel.model.User;
import com.liferay.saml.opensaml.integration.field.expression.handler.registry.UserFieldExpressionHandlerRegistry;
import com.liferay.saml.opensaml.integration.processor.UserProcessor;

/**
 * @author Stian Sigvartsen
 */
public interface UserProcessorFactory {

	public UserProcessor create(
		User user,
		UserFieldExpressionHandlerRegistry userFieldExpressionHandlerRegistry);

}