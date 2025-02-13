/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class RequiredObjectFieldException extends PortalException {

	public RequiredObjectFieldException() {
		super("At least one custom field must be added");

		_messageKey = "at-least-one-custom-field-must-be-added";
	}

	public String getMessageKey() {
		return _messageKey;
	}

	private final String _messageKey;

}