/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.workflow;

import com.liferay.journal.constants.JournalArticleConstants;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 * @author Juan Fernández
 * @author Julio Camarero
 */
@Component(
	property = "model.class.name=com.liferay.journal.model.JournalArticle",
	service = WorkflowHandler.class
)
public class JournalArticleWorkflowHandler
	extends BaseWorkflowHandler<JournalArticle> {

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException {

		JournalArticle article = _journalArticleLocalService.getArticle(
			classPK);

		long folderId = _journalFolderLocalService.getInheritedWorkflowFolderId(
			article.getFolderId());

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
				companyId, groupId, JournalFolder.class.getName(), folderId,
				article.getDDMStructureId(), true);

		if (workflowDefinitionLink == null) {
			workflowDefinitionLink =
				_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
					companyId, groupId, JournalFolder.class.getName(), folderId,
					JournalArticleConstants.DDM_STRUCTURE_ID_ALL, true);
		}

		if (workflowDefinitionLink != null) {
			return workflowDefinitionLink;
		}

		if (folderId == 0) {
			return super.getWorkflowDefinitionLink(companyId, groupId, classPK);
		}

		JournalFolder folder = _journalFolderLocalService.fetchFolder(folderId);

		if ((folder != null) &&
			(folder.getRestrictionType() ==
				JournalFolderConstants.RESTRICTION_TYPE_INHERIT)) {

			return super.getWorkflowDefinitionLink(companyId, groupId, classPK);
		}

		return null;
	}

	@Override
	public boolean isVisible() {
		return _VISIBLE;
	}

	@Override
	public JournalArticle updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		JournalArticle article = _journalArticleLocalService.getArticle(
			classPK);

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			"serviceContext");

		String articleURL = _portal.getControlPanelFullURL(
			serviceContext.getScopeGroupId(),
			PortletProviderUtil.getPortletId(
				JournalArticle.class.getName(), PortletProvider.Action.EDIT),
			null);

		return _journalArticleLocalService.updateStatus(
			userId, article, status, articleURL, serviceContext,
			workflowContext);
	}

	private static final boolean _VISIBLE = true;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalFolderLocalService _journalFolderLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}