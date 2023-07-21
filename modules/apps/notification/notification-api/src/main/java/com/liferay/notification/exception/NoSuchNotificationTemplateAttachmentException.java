/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Gabriel Albuquerque
 */
public class NoSuchNotificationTemplateAttachmentException
	extends NoSuchModelException {

	public NoSuchNotificationTemplateAttachmentException() {
	}

	public NoSuchNotificationTemplateAttachmentException(String msg) {
		super(msg);
	}

	public NoSuchNotificationTemplateAttachmentException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public NoSuchNotificationTemplateAttachmentException(Throwable throwable) {
		super(throwable);
	}

}