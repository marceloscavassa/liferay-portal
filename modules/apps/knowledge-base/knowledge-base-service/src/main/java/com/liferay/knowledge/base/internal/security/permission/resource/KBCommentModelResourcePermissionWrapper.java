/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.security.permission.resource;

import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.knowledge.base.constants.KBConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.knowledge.base.service.KBCommentLocalService;
import com.liferay.knowledge.base.service.KBTemplateLocalService;
import com.liferay.portal.kernel.security.permission.resource.BaseModelResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	property = "model.class.name=com.liferay.knowledge.base.model.KBComment",
	service = ModelResourcePermission.class
)
public class KBCommentModelResourcePermissionWrapper
	extends BaseModelResourcePermissionWrapper<KBComment> {

	@Override
	protected ModelResourcePermission<KBComment>
		doGetModelResourcePermission() {

		return ModelResourcePermissionFactory.create(
			KBComment.class, KBComment::getKbCommentId,
			_kbCommentLocalService::getKBComment, _portletResourcePermission,
			(modelResourcePermission, consumer) -> consumer.accept(
				(permissionChecker, name, kbComment, actionId) -> {
					if (permissionChecker.getUserId() ==
							kbComment.getUserId()) {

						return true;
					}

					if (actionId.equals(KBActionKeys.VIEW)) {
						return _portletResourcePermission.contains(
							permissionChecker, kbComment.getGroupId(),
							KBActionKeys.VIEW_SUGGESTIONS);
					}

					if (!actionId.equals(KBActionKeys.DELETE) &&
						!actionId.equals(KBActionKeys.UPDATE)) {

						return false;
					}

					String className = kbComment.getClassName();

					if (className.equals(KBArticle.class.getName())) {
						KBArticle kbArticle =
							_kbArticleLocalService.getLatestKBArticle(
								kbComment.getClassPK(),
								WorkflowConstants.STATUS_ANY);

						return permissionChecker.hasPermission(
							kbArticle.getGroupId(), KBArticle.class.getName(),
							kbArticle.getResourcePrimKey(),
							KBActionKeys.UPDATE);
					}

					if (className.equals(KBTemplate.class.getName())) {
						KBTemplate kbTemplate =
							_kbTemplateLocalService.getKBTemplate(
								kbComment.getClassPK());

						return permissionChecker.hasPermission(
							kbTemplate.getGroupId(), KBTemplate.class.getName(),
							kbTemplate.getPrimaryKey(), KBActionKeys.UPDATE);
					}

					return false;
				}));
	}

	@Reference
	private KBArticleLocalService _kbArticleLocalService;

	@Reference
	private KBCommentLocalService _kbCommentLocalService;

	@Reference
	private KBTemplateLocalService _kbTemplateLocalService;

	@Reference(
		target = "(resource.name=" + KBConstants.RESOURCE_NAME_ADMIN + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}