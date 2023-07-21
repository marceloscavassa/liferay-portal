/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.utility.page.service.impl;

import com.liferay.layout.utility.page.constants.LayoutUtilityPageActionKeys;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.base.LayoutUtilityPageEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.GroupPermission;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=layoututilitypage",
		"json.web.service.context.path=LayoutUtilityPageEntry"
	},
	service = AopService.class
)
public class LayoutUtilityPageEntryServiceImpl
	extends LayoutUtilityPageEntryServiceBaseImpl {

	@Override
	public LayoutUtilityPageEntry addLayoutUtilityPageEntry(
			String externalReferenceCode, long groupId, long plid,
			long previewFileEntryId, boolean defaultLayoutUtilityPageEntry,
			String name, String type, long masterLayoutPlid,
			ServiceContext serviceContext)
		throws PortalException {

		_groupPermission.check(
			getPermissionChecker(), groupId,
			LayoutUtilityPageActionKeys.ADD_LAYOUT_UTILITY_PAGE_ENTRY);

		return layoutUtilityPageEntryLocalService.addLayoutUtilityPageEntry(
			externalReferenceCode, getUserId(), groupId, plid,
			previewFileEntryId, defaultLayoutUtilityPageEntry, name, type,
			masterLayoutPlid, serviceContext);
	}

	@Override
	public LayoutUtilityPageEntry copyLayoutUtilityPageEntry(
			long groupId, long sourceLayoutUtilityPageEntryId,
			ServiceContext serviceContext)
		throws Exception {

		_groupPermission.check(
			getPermissionChecker(), groupId,
			LayoutUtilityPageActionKeys.ADD_LAYOUT_UTILITY_PAGE_ENTRY);

		return layoutUtilityPageEntryLocalService.copyLayoutUtilityPageEntry(
			getUserId(), groupId, sourceLayoutUtilityPageEntryId,
			serviceContext);
	}

	@Override
	public LayoutUtilityPageEntry deleteLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException {

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntry(
				layoutUtilityPageEntryId);

		_layoutUtilityPageEntryModelResourcePermission.check(
			getPermissionChecker(), layoutUtilityPageEntryId,
			ActionKeys.DELETE);

		if (layoutUtilityPageEntry.isDefaultLayoutUtilityPageEntry()) {
			_groupPermission.check(
				getPermissionChecker(), layoutUtilityPageEntry.getGroupId(),
				LayoutUtilityPageActionKeys.
					ASSIGN_DEFAULT_LAYOUT_UTILITY_PAGE_ENTRY);
		}

		return layoutUtilityPageEntryLocalService.deleteLayoutUtilityPageEntry(
			layoutUtilityPageEntryId);
	}

	@Override
	public LayoutUtilityPageEntry fetchLayoutUtilityPageEntry(
		long layoutUtilityPageEntryId) {

		return layoutUtilityPageEntryLocalService.fetchLayoutUtilityPageEntry(
			layoutUtilityPageEntryId);
	}

	@Override
	public LayoutUtilityPageEntry getDefaultLayoutUtilityPageEntry(
			long groupId, String type)
		throws PortalException {

		return layoutUtilityPageEntryLocalService.
			getDefaultLayoutUtilityPageEntry(groupId, type);
	}

	@Override
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId) {

		return layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntries(
			groupId);
	}

	@Override
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator) {

		return layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntries(
			groupId, start, end, orderByComparator);
	}

	@Override
	public List<LayoutUtilityPageEntry> getLayoutUtilityPageEntries(
		long groupId, String type, int start, int end,
		OrderByComparator<LayoutUtilityPageEntry> orderByComparator) {

		return layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntries(
			groupId, type, start, end, orderByComparator);
	}

	@Override
	public int getLayoutUtilityPageEntriesCount(long groupId) {
		return layoutUtilityPageEntryLocalService.
			getLayoutUtilityPageEntriesCount(groupId);
	}

	@Override
	public LayoutUtilityPageEntry setDefaultLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException {

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntry(
				layoutUtilityPageEntryId);

		_groupPermission.check(
			getPermissionChecker(), layoutUtilityPageEntry.getGroupId(),
			LayoutUtilityPageActionKeys.
				ASSIGN_DEFAULT_LAYOUT_UTILITY_PAGE_ENTRY);

		_layoutUtilityPageEntryModelResourcePermission.check(
			getPermissionChecker(), layoutUtilityPageEntryId,
			ActionKeys.UPDATE);

		return layoutUtilityPageEntryLocalService.
			setDefaultLayoutUtilityPageEntry(layoutUtilityPageEntryId);
	}

	@Override
	public LayoutUtilityPageEntry unsetDefaultLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId)
		throws PortalException {

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			layoutUtilityPageEntryLocalService.getLayoutUtilityPageEntry(
				layoutUtilityPageEntryId);

		_groupPermission.check(
			getPermissionChecker(), layoutUtilityPageEntry.getGroupId(),
			LayoutUtilityPageActionKeys.
				ASSIGN_DEFAULT_LAYOUT_UTILITY_PAGE_ENTRY);

		_layoutUtilityPageEntryModelResourcePermission.check(
			getPermissionChecker(), layoutUtilityPageEntryId,
			ActionKeys.UPDATE);

		if (!layoutUtilityPageEntry.isDefaultLayoutUtilityPageEntry()) {
			return layoutUtilityPageEntry;
		}

		layoutUtilityPageEntry.setDefaultLayoutUtilityPageEntry(false);

		return layoutUtilityPageEntryLocalService.updateLayoutUtilityPageEntry(
			layoutUtilityPageEntry);
	}

	@Override
	public LayoutUtilityPageEntry updateLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId, long previewFileEntryId)
		throws PortalException {

		_layoutUtilityPageEntryModelResourcePermission.check(
			getPermissionChecker(), layoutUtilityPageEntryId,
			ActionKeys.UPDATE);

		return layoutUtilityPageEntryLocalService.updateLayoutUtilityPageEntry(
			layoutUtilityPageEntryId, previewFileEntryId);
	}

	@Override
	public LayoutUtilityPageEntry updateLayoutUtilityPageEntry(
			long layoutUtilityPageEntryId, String name)
		throws PortalException {

		return layoutUtilityPageEntryLocalService.updateLayoutUtilityPageEntry(
			layoutUtilityPageEntryId, name);
	}

	@Reference
	private GroupPermission _groupPermission;

	@Reference(
		target = "(model.class.name=com.liferay.layout.utility.page.model.LayoutUtilityPageEntry)"
	)
	private ModelResourcePermission<LayoutUtilityPageEntry>
		_layoutUtilityPageEntryModelResourcePermission;

}