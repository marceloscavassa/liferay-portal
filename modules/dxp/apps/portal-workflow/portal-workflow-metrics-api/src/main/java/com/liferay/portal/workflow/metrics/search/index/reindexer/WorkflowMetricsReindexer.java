/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.search.index.reindexer;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Rafael Praxedes
 */
public interface WorkflowMetricsReindexer {

	public void reindex(long companyId) throws PortalException;

}