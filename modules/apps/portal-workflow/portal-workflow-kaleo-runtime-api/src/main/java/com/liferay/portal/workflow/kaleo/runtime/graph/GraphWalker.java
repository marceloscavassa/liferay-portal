/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.graph;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.List;

/**
 * @author Michael C. Han
 */
public interface GraphWalker {

	public void follow(
			KaleoNode sourceKaleoNode, KaleoNode targetKaleoNode,
			List<PathElement> remainingPathElements,
			ExecutionContext executionContext)
		throws PortalException;

}