/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.model.Group;

/**
 * @author Shuyang Zhou
 */
public interface WorkflowHandlerVisibleFilter {

	public boolean isVisible(WorkflowHandler<?> workflowHandler, Group group);

}