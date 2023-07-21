/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.roles.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.roles.uad.constants.RolesUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the role UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link RoleUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRoleUADAnonymizer
	extends DynamicQueryUADAnonymizer<Role> {

	@Override
	public void autoAnonymize(Role role, long userId, User anonymousUser)
		throws PortalException {

		if (role.getUserId() == userId) {
			role.setUserId(anonymousUser.getUserId());
			role.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(role, anonymousUser);
		}

		roleLocalService.updateRole(role);
	}

	@Override
	public void delete(Role role) throws PortalException {
		roleLocalService.deleteRole(role);
	}

	@Override
	public Class<Role> getTypeClass() {
		return Role.class;
	}

	protected void autoAnonymizeAssetEntry(Role role, User anonymousUser) {
		AssetEntry assetEntry = fetchAssetEntry(role);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return roleLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return RolesUADConstants.USER_ID_FIELD_NAMES_ROLE;
	}

	protected AssetEntry fetchAssetEntry(Role role) {
		return assetEntryLocalService.fetchEntry(
			Role.class.getName(), role.getRoleId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

}