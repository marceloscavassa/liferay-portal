/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.planner.model.impl;

import com.liferay.batch.planner.model.BatchPlannerPolicy;
import com.liferay.batch.planner.service.BatchPlannerPolicyLocalServiceUtil;

/**
 * The extended model base implementation for the BatchPlannerPolicy service. Represents a row in the &quot;BatchPlannerPolicy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BatchPlannerPolicyImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see BatchPlannerPolicyImpl
 * @see BatchPlannerPolicy
 * @generated
 */
public abstract class BatchPlannerPolicyBaseImpl
	extends BatchPlannerPolicyModelImpl implements BatchPlannerPolicy {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a batch planner policy model instance should use the <code>BatchPlannerPolicy</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			BatchPlannerPolicyLocalServiceUtil.addBatchPlannerPolicy(this);
		}
		else {
			BatchPlannerPolicyLocalServiceUtil.updateBatchPlannerPolicy(this);
		}
	}

}