/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.layout.uad.constants.LayoutUADConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutSetBranchLocalService;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout set branch UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link LayoutSetBranchUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseLayoutSetBranchUADAnonymizer
	extends DynamicQueryUADAnonymizer<LayoutSetBranch> {

	@Override
	public void autoAnonymize(
			LayoutSetBranch layoutSetBranch, long userId, User anonymousUser)
		throws PortalException {

		if (layoutSetBranch.getUserId() == userId) {
			layoutSetBranch.setUserId(anonymousUser.getUserId());
			layoutSetBranch.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(layoutSetBranch, anonymousUser);
		}

		layoutSetBranchLocalService.updateLayoutSetBranch(layoutSetBranch);
	}

	@Override
	public void delete(LayoutSetBranch layoutSetBranch) throws PortalException {
		layoutSetBranchLocalService.deleteLayoutSetBranch(layoutSetBranch);
	}

	@Override
	public Class<LayoutSetBranch> getTypeClass() {
		return LayoutSetBranch.class;
	}

	protected void autoAnonymizeAssetEntry(
		LayoutSetBranch layoutSetBranch, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(layoutSetBranch);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return layoutSetBranchLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return LayoutUADConstants.USER_ID_FIELD_NAMES_LAYOUT_SET_BRANCH;
	}

	protected AssetEntry fetchAssetEntry(LayoutSetBranch layoutSetBranch) {
		return assetEntryLocalService.fetchEntry(
			LayoutSetBranch.class.getName(),
			layoutSetBranch.getLayoutSetBranchId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected LayoutSetBranchLocalService layoutSetBranchLocalService;

}