/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.service;

import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutPageTemplateCollectionService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateCollectionService
 * @generated
 */
public class LayoutPageTemplateCollectionServiceWrapper
	implements LayoutPageTemplateCollectionService,
			   ServiceWrapper<LayoutPageTemplateCollectionService> {

	public LayoutPageTemplateCollectionServiceWrapper() {
		this(null);
	}

	public LayoutPageTemplateCollectionServiceWrapper(
		LayoutPageTemplateCollectionService
			layoutPageTemplateCollectionService) {

		_layoutPageTemplateCollectionService =
			layoutPageTemplateCollectionService;
	}

	@Override
	public LayoutPageTemplateCollection addLayoutPageTemplateCollection(
			long groupId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutPageTemplateCollectionService.
			addLayoutPageTemplateCollection(
				groupId, name, description, serviceContext);
	}

	@Override
	public LayoutPageTemplateCollection deleteLayoutPageTemplateCollection(
			long layoutPageTemplateCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutPageTemplateCollectionService.
			deleteLayoutPageTemplateCollection(layoutPageTemplateCollectionId);
	}

	@Override
	public void deleteLayoutPageTemplateCollections(
			long[] layoutPageTemplateCollectionIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		_layoutPageTemplateCollectionService.
			deleteLayoutPageTemplateCollections(
				layoutPageTemplateCollectionIds);
	}

	@Override
	public LayoutPageTemplateCollection fetchLayoutPageTemplateCollection(
			long layoutPageTemplateCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutPageTemplateCollectionService.
			fetchLayoutPageTemplateCollection(layoutPageTemplateCollectionId);
	}

	@Override
	public java.util.List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(long groupId) {

		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollections(groupId);
	}

	@Override
	public java.util.List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(long groupId, int start, int end) {

		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollections(groupId, start, end);
	}

	@Override
	public java.util.List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<LayoutPageTemplateCollection> orderByComparator) {

		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollections(
				groupId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<LayoutPageTemplateCollection>
		getLayoutPageTemplateCollections(
			long groupId, String name, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<LayoutPageTemplateCollection> orderByComparator) {

		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollections(
				groupId, name, start, end, orderByComparator);
	}

	@Override
	public int getLayoutPageTemplateCollectionsCount(long groupId) {
		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollectionsCount(groupId);
	}

	@Override
	public int getLayoutPageTemplateCollectionsCount(
		long groupId, String name) {

		return _layoutPageTemplateCollectionService.
			getLayoutPageTemplateCollectionsCount(groupId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutPageTemplateCollectionService.getOSGiServiceIdentifier();
	}

	@Override
	public LayoutPageTemplateCollection updateLayoutPageTemplateCollection(
			long layoutPageTemplateCollectionId, String name,
			String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutPageTemplateCollectionService.
			updateLayoutPageTemplateCollection(
				layoutPageTemplateCollectionId, name, description);
	}

	@Override
	public LayoutPageTemplateCollectionService getWrappedService() {
		return _layoutPageTemplateCollectionService;
	}

	@Override
	public void setWrappedService(
		LayoutPageTemplateCollectionService
			layoutPageTemplateCollectionService) {

		_layoutPageTemplateCollectionService =
			layoutPageTemplateCollectionService;
	}

	private LayoutPageTemplateCollectionService
		_layoutPageTemplateCollectionService;

}