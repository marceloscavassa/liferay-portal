/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.model.impl;

import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionVersionLocalServiceUtil;

/**
 * The extended model base implementation for the WorkflowMetricsSLADefinitionVersion service. Represents a row in the &quot;WMSLADefinitionVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WorkflowMetricsSLADefinitionVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowMetricsSLADefinitionVersionImpl
 * @see WorkflowMetricsSLADefinitionVersion
 * @generated
 */
public abstract class WorkflowMetricsSLADefinitionVersionBaseImpl
	extends WorkflowMetricsSLADefinitionVersionModelImpl
	implements WorkflowMetricsSLADefinitionVersion {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a workflow metrics sla definition version model instance should use the <code>WorkflowMetricsSLADefinitionVersion</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
				addWorkflowMetricsSLADefinitionVersion(this);
		}
		else {
			WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
				updateWorkflowMetricsSLADefinitionVersion(this);
		}
	}

}