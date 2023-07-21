/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.security.permission.resource.definition;

import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
import com.liferay.sharing.security.permission.resource.SharingModelResourcePermissionConfigurator;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = ModelResourcePermissionDefinition.class)
public class BlogsEntryModelResourcePermissionDefinition
	implements ModelResourcePermissionDefinition<BlogsEntry> {

	@Override
	public BlogsEntry getModel(long entryId) throws PortalException {
		return _blogsEntryLocalService.getBlogsEntry(entryId);
	}

	@Override
	public Class<BlogsEntry> getModelClass() {
		return BlogsEntry.class;
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Override
	public long getPrimaryKey(BlogsEntry blogsEntry) {
		return blogsEntry.getEntryId();
	}

	@Override
	public void registerModelResourcePermissionLogics(
		ModelResourcePermission<BlogsEntry> modelResourcePermission,
		Consumer<ModelResourcePermissionLogic<BlogsEntry>>
			modelResourcePermissionLogicConsumer) {

		modelResourcePermissionLogicConsumer.accept(
			new StagedModelPermissionLogic<>(
				_stagingPermission, BlogsPortletKeys.BLOGS,
				BlogsEntry::getEntryId));
		modelResourcePermissionLogicConsumer.accept(
			new WorkflowedModelPermissionLogic<>(
				_workflowPermission, modelResourcePermission,
				_groupLocalService, BlogsEntry::getEntryId));

		if (_sharingModelResourcePermissionConfigurator != null) {
			_sharingModelResourcePermissionConfigurator.configure(
				modelResourcePermission, modelResourcePermissionLogicConsumer);
		}
	}

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private SharingModelResourcePermissionConfigurator
		_sharingModelResourcePermissionConfigurator;

	@Reference
	private StagingPermission _stagingPermission;

	@Reference
	private WorkflowPermission _workflowPermission;

}