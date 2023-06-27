/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.uad.constants.KaleoUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kaleo instance token UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link KaleoInstanceTokenUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseKaleoInstanceTokenUADAnonymizer
	extends DynamicQueryUADAnonymizer<KaleoInstanceToken> {

	@Override
	public void autoAnonymize(
			KaleoInstanceToken kaleoInstanceToken, long userId,
			User anonymousUser)
		throws PortalException {

		if (kaleoInstanceToken.getUserId() == userId) {
			kaleoInstanceToken.setUserId(anonymousUser.getUserId());
			kaleoInstanceToken.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(kaleoInstanceToken, anonymousUser);
		}

		kaleoInstanceTokenLocalService.updateKaleoInstanceToken(
			kaleoInstanceToken);
	}

	@Override
	public void delete(KaleoInstanceToken kaleoInstanceToken)
		throws PortalException {

		kaleoInstanceTokenLocalService.deleteKaleoInstanceToken(
			kaleoInstanceToken);
	}

	@Override
	public Class<KaleoInstanceToken> getTypeClass() {
		return KaleoInstanceToken.class;
	}

	protected void autoAnonymizeAssetEntry(
		KaleoInstanceToken kaleoInstanceToken, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(kaleoInstanceToken);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return kaleoInstanceTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return KaleoUADConstants.USER_ID_FIELD_NAMES_KALEO_INSTANCE_TOKEN;
	}

	protected AssetEntry fetchAssetEntry(
		KaleoInstanceToken kaleoInstanceToken) {

		return assetEntryLocalService.fetchEntry(
			KaleoInstanceToken.class.getName(),
			kaleoInstanceToken.getKaleoInstanceTokenId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;

}