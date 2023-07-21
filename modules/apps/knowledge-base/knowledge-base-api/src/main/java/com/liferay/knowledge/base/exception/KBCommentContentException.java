/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class KBCommentContentException extends PortalException {

	public KBCommentContentException() {
	}

	public KBCommentContentException(String msg) {
		super(msg);
	}

	public KBCommentContentException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public KBCommentContentException(Throwable throwable) {
		super(throwable);
	}

}