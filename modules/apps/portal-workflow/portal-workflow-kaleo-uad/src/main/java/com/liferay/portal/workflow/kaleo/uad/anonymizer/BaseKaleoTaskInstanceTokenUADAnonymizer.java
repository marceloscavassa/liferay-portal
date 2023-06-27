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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.uad.constants.KaleoUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kaleo task instance token UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link KaleoTaskInstanceTokenUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseKaleoTaskInstanceTokenUADAnonymizer
	extends DynamicQueryUADAnonymizer<KaleoTaskInstanceToken> {

	@Override
	public void autoAnonymize(
			KaleoTaskInstanceToken kaleoTaskInstanceToken, long userId,
			User anonymousUser)
		throws PortalException {

		if (kaleoTaskInstanceToken.getUserId() == userId) {
			kaleoTaskInstanceToken.setUserId(anonymousUser.getUserId());
			kaleoTaskInstanceToken.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(kaleoTaskInstanceToken, anonymousUser);
		}

		if (kaleoTaskInstanceToken.getCompletionUserId() == userId) {
			kaleoTaskInstanceToken.setCompletionUserId(
				anonymousUser.getUserId());
		}

		kaleoTaskInstanceTokenLocalService.updateKaleoTaskInstanceToken(
			kaleoTaskInstanceToken);
	}

	@Override
	public void delete(KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws PortalException {

		kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(
			kaleoTaskInstanceToken);
	}

	@Override
	public Class<KaleoTaskInstanceToken> getTypeClass() {
		return KaleoTaskInstanceToken.class;
	}

	protected void autoAnonymizeAssetEntry(
		KaleoTaskInstanceToken kaleoTaskInstanceToken, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(kaleoTaskInstanceToken);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return kaleoTaskInstanceTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return KaleoUADConstants.USER_ID_FIELD_NAMES_KALEO_TASK_INSTANCE_TOKEN;
	}

	protected AssetEntry fetchAssetEntry(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		return assetEntryLocalService.fetchEntry(
			KaleoTaskInstanceToken.class.getName(),
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected KaleoTaskInstanceTokenLocalService
		kaleoTaskInstanceTokenLocalService;

}