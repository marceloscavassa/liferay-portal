/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v9_1_0;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Danny Situ
 */
public class CommercePermissionUpgradeProcess extends UpgradeProcess {

	public CommercePermissionUpgradeProcess(
		ResourceActionLocalService resourceActionLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_resourceActionLocalService = resourceActionLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(
				"select ResourcePermissionId from ResourcePermission where " +
					"name = 'com.liferay.commerce.account' and scope = 1");

			while (resultSet.next()) {
				ResourcePermission resourcePermission =
					_resourcePermissionLocalService.getResourcePermission(
						resultSet.getLong(1));

				ResourceAction resourceAction =
					_resourceActionLocalService.fetchResourceAction(
						"com.liferay.commerce.account", "MANAGE_ALL_ACCOUNTS");

				if ((resourceAction == null) ||
					!_resourcePermissionLocalService.hasActionId(
						resourcePermission, resourceAction)) {

					continue;
				}

				ResourceAction commerceShipmentResourceAction =
					_resourceActionLocalService.fetchResourceAction(
						"com.liferay.commerce.shipment", "MANAGE_ALL_ACCOUNTS");

				if ((commerceShipmentResourceAction == null) ||
					!_resourcePermissionLocalService.hasActionId(
						resourcePermission, resourceAction)) {

					continue;
				}

				Role role = _roleLocalService.getRole(
					resourcePermission.getRoleId());

				_resourcePermissionLocalService.addResourcePermissions(
					"com.liferay.commerce.shipment", role.getName(),
					resourcePermission.getScope(),
					commerceShipmentResourceAction.getBitwiseValue());

				_resourcePermissionLocalService.removeResourcePermissions(
					resourcePermission.getCompanyId(),
					resourcePermission.getName(), resourcePermission.getScope(),
					resourcePermission.getRoleId(), "MANAGE_ALL_ACCOUNTS");
			}
		}
	}

	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}