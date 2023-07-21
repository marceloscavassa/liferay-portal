/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.service;

/**
 * Provides the remote service utility for BackgroundTask. This utility wraps
 * <code>com.liferay.portal.background.task.service.impl.BackgroundTaskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskService
 * @generated
 */
public class BackgroundTaskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.background.task.service.impl.BackgroundTaskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static int getBackgroundTasksCount(
		long groupId, java.lang.String taskExecutorClassName,
		boolean completed) {

		return getService().getBackgroundTasksCount(
			groupId, taskExecutorClassName, completed);
	}

	public static int getBackgroundTasksCount(
		long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName) {

		return getService().getBackgroundTasksCount(
			groupId, name, taskExecutorClassName);
	}

	public static java.lang.String getBackgroundTaskStatusJSON(
		long backgroundTaskId) {

		return getService().getBackgroundTaskStatusJSON(backgroundTaskId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static BackgroundTaskService getService() {
		return _service;
	}

	public static void setService(BackgroundTaskService service) {
		_service = service;
	}

	private static volatile BackgroundTaskService _service;

}