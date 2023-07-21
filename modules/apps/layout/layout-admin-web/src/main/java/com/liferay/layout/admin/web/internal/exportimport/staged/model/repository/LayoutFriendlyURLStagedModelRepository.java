/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.exportimport.staged.model.repository;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(
	property = "model.class.name=com.liferay.portal.kernel.model.LayoutFriendlyURL",
	service = StagedModelRepository.class
)
public class LayoutFriendlyURLStagedModelRepository
	implements StagedModelRepository<LayoutFriendlyURL> {

	@Override
	public LayoutFriendlyURL addStagedModel(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteStagedModel(LayoutFriendlyURL layoutFriendlyURL)
		throws PortalException {

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
	public LayoutFriendlyURL fetchMissingReference(String uuid, long groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LayoutFriendlyURL fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<LayoutFriendlyURL> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public LayoutFriendlyURL getStagedModel(long layoutFriendlyURLId)
		throws PortalException {

		return _layoutFriendlyURLLocalService.getLayoutFriendlyURL(
			layoutFriendlyURLId);
	}

	@Override
	public void restoreStagedModel(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL)
		throws PortletDataException {

		throw new UnsupportedOperationException();
	}

	@Override
	public LayoutFriendlyURL saveStagedModel(
			LayoutFriendlyURL layoutFriendlyURL)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public LayoutFriendlyURL updateStagedModel(
			PortletDataContext portletDataContext,
			LayoutFriendlyURL layoutFriendlyURL)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference
	private LayoutFriendlyURLLocalService _layoutFriendlyURLLocalService;

}