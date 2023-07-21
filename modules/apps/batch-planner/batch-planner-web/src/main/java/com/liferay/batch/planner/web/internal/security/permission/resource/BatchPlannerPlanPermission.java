/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.planner.web.internal.security.permission.resource;

import com.liferay.batch.planner.model.BatchPlannerPlan;
import com.liferay.osgi.util.service.Snapshot;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

/**
 * @author Matija Petanjek
 */
public class BatchPlannerPlanPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			BatchPlannerPlan batchPlannerPlan, String actionId)
		throws PortalException {

		ModelResourcePermission<BatchPlannerPlan> modelResourcePermission =
			_batchPlannerPlanModelResourcePermissionSnapshot.get();

		return modelResourcePermission.contains(
			permissionChecker, batchPlannerPlan, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long batchPlannerPlanId,
			String actionId)
		throws PortalException {

		ModelResourcePermission<BatchPlannerPlan> modelResourcePermission =
			_batchPlannerPlanModelResourcePermissionSnapshot.get();

		return modelResourcePermission.contains(
			permissionChecker, batchPlannerPlanId, actionId);
	}

	private static final Snapshot<ModelResourcePermission<BatchPlannerPlan>>
		_batchPlannerPlanModelResourcePermissionSnapshot = new Snapshot<>(
			BatchPlannerPlanPermission.class,
			Snapshot.cast(ModelResourcePermission.class),
			"(model.class.name=com.liferay.batch.planner.model." +
				"BatchPlannerPlan)");

}