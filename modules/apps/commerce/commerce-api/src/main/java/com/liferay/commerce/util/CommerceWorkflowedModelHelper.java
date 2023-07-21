/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.util.List;

/**
 * @author Alec Sloan
 */
public interface CommerceWorkflowedModelHelper {

	public List<ObjectValuePair<Long, String>> getWorkflowTransitions(
			long userId, long companyId, String className, long classPK)
		throws PortalException;

}