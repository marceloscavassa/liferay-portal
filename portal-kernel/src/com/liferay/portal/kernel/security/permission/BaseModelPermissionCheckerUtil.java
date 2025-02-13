/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionRegistryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

/**
 * @author Roberto Díaz
 */
public class BaseModelPermissionCheckerUtil {

	public static Boolean containsBaseModelPermission(
		PermissionChecker permissionChecker, long groupId, String className,
		long classPK, String actionId) {

		ModelResourcePermission<?> modelResourcePermission =
			ModelResourcePermissionRegistryUtil.getModelResourcePermission(
				className);

		if (modelResourcePermission != null) {
			try {
				PortletResourcePermission portletResourcePermission =
					modelResourcePermission.getPortletResourcePermission();

				if (portletResourcePermission == null) {
					return modelResourcePermission.contains(
						permissionChecker, classPK, actionId);
				}

				return ModelResourcePermissionUtil.contains(
					modelResourcePermission, permissionChecker, groupId,
					classPK, actionId);
			}
			catch (PortalException portalException) {
				if (_log.isWarnEnabled()) {
					_log.warn(portalException);
				}

				return false;
			}
		}

		BaseModelPermissionChecker baseModelPermissionChecker =
			_baseModelPermissionCheckers.getService(className);

		if (baseModelPermissionChecker == null) {
			return null;
		}

		try {
			baseModelPermissionChecker.checkBaseModel(
				permissionChecker, groupId, classPK, actionId);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseModelPermissionCheckerUtil.class);

	private static final ServiceTrackerMap<String, BaseModelPermissionChecker>
		_baseModelPermissionCheckers =
			ServiceTrackerMapFactory.openSingleValueMap(
				SystemBundleUtil.getBundleContext(),
				BaseModelPermissionChecker.class, "model.class.name");

}