/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.message.boards.model.MBBan;
import com.liferay.message.boards.service.MBBanLocalService;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(
	property = "model.class.name=com.liferay.message.boards.model.MBBan",
	service = StagedModelRepository.class
)
public class MBBanStagedModelRepository
	implements StagedModelRepository<MBBan> {

	@Override
	public MBBan addStagedModel(
			PortletDataContext portletDataContext, MBBan mbBan)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(MBBan mbBan) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MBBan fetchMissingReference(String uuid, long groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MBBan fetchStagedModelByUuidAndGroupId(String uuid, long groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<MBBan> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public MBBan getStagedModel(long banId) throws PortalException {
		return _mbBanLocalService.getMBBan(banId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext, MBBan mbBan)
		throws PortletDataException {

		throw new UnsupportedOperationException();
	}

	@Override
	public MBBan saveStagedModel(MBBan mbBan) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public MBBan updateStagedModel(
			PortletDataContext portletDataContext, MBBan mbBan)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	private MBBanLocalService _mbBanLocalService;

}