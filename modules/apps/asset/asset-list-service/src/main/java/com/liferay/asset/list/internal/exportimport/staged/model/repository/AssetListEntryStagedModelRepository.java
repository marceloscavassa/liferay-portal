/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.internal.exportimport.staged.model.repository;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "model.class.name=com.liferay.asset.list.model.AssetListEntry",
	service = StagedModelRepository.class
)
public class AssetListEntryStagedModelRepository
	implements StagedModelRepository<AssetListEntry> {

	@Override
	public AssetListEntry addStagedModel(
			PortletDataContext portletDataContext,
			AssetListEntry assetListEntry)
		throws PortalException {

		long userId = portletDataContext.getUserId(
			assetListEntry.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			assetListEntry);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(assetListEntry.getUuid());
		}

		return _assetListEntryLocalService.addAssetListEntry(
			userId, assetListEntry.getGroupId(), assetListEntry.getTitle(),
			assetListEntry.getType(), serviceContext);
	}

	@Override
	public void deleteStagedModel(AssetListEntry assetListEntry)
		throws PortalException {

		_assetListEntryLocalService.deleteAssetListEntry(assetListEntry);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		AssetListEntry assetListEntry = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (assetListEntry != null) {
			deleteStagedModel(assetListEntry);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public AssetListEntry fetchMissingReference(String uuid, long groupId) {
		return _stagedModelRepositoryHelper.fetchMissingReference(
			uuid, groupId, this);
	}

	@Override
	public AssetListEntry fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _assetListEntryLocalService.fetchAssetListEntryByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<AssetListEntry> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _assetListEntryLocalService.
			getAssetListEntriesByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return _assetListEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public AssetListEntry getStagedModel(long id) throws PortalException {
		return _assetListEntryLocalService.getAssetListEntry(id);
	}

	@Override
	public AssetListEntry saveStagedModel(AssetListEntry assetListEntry)
		throws PortalException {

		return _assetListEntryLocalService.updateAssetListEntry(assetListEntry);
	}

	@Override
	public AssetListEntry updateStagedModel(
			PortletDataContext portletDataContext,
			AssetListEntry assetListEntry)
		throws PortalException {

		return _assetListEntryLocalService.updateAssetListEntry(
			assetListEntry.getAssetListEntryId(), assetListEntry.getTitle());
	}

	@Reference
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

}