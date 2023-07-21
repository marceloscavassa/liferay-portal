/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal.security.permission.resource;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.BaseModelResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	property = "model.class.name=com.liferay.portal.background.task.model.BackgroundTask",
	service = ModelResourcePermission.class
)
public class BackgroundTaskModelResourcePermissionWrapper
	extends BaseModelResourcePermissionWrapper<BackgroundTask> {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<ModelResourcePermissionLogic<BackgroundTask>>)
				(Class<?>)ModelResourcePermissionLogic.class,
			"background.task.executor.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Override
	protected ModelResourcePermission<BackgroundTask>
		doGetModelResourcePermission() {

		return ModelResourcePermissionFactory.create(
			BackgroundTask.class, BackgroundTask::getBackgroundTaskId,
			_backgroundTaskLocalService::getBackgroundTask, null,
			(modelResourcePermission, consumer) -> consumer.accept(
				new BackgroundTaskModelResourcePermissionLogic()));
	}

	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	private ServiceTrackerMap
		<String, ModelResourcePermissionLogic<BackgroundTask>>
			_serviceTrackerMap;

	private class BackgroundTaskModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<BackgroundTask> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				BackgroundTask backgroundTask, String actionId)
			throws PortalException {

			ModelResourcePermissionLogic<BackgroundTask>
				backgroundTaskModelResourcePermissionLogic =
					_serviceTrackerMap.getService(
						backgroundTask.getTaskExecutorClassName());

			if (backgroundTaskModelResourcePermissionLogic == null) {
				return null;
			}

			return backgroundTaskModelResourcePermissionLogic.contains(
				permissionChecker, name, backgroundTask, actionId);
		}

	}

}