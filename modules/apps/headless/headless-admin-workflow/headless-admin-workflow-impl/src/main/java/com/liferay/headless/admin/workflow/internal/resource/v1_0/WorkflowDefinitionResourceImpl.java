/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.workflow.internal.resource.v1_0;

import com.liferay.headless.admin.workflow.dto.v1_0.Node;
import com.liferay.headless.admin.workflow.dto.v1_0.Transition;
import com.liferay.headless.admin.workflow.dto.v1_0.WorkflowDefinition;
import com.liferay.headless.admin.workflow.internal.dto.v1_0.util.NodeUtil;
import com.liferay.headless.admin.workflow.internal.dto.v1_0.util.TransitionUtil;
import com.liferay.headless.admin.workflow.internal.odata.entity.v1_0.WorkflowDefinitionEntityModel;
import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowDefinitionResource;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.workflow.comparator.WorkflowComparatorFactory;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/workflow-definition.properties",
	scope = ServiceScope.PROTOTYPE, service = WorkflowDefinitionResource.class
)
@CTAware
public class WorkflowDefinitionResourceImpl
	extends BaseWorkflowDefinitionResourceImpl implements EntityModelResource {

	@Override
	public void create(
			Collection<WorkflowDefinition> workflowDefinitions,
			Map<String, Serializable> parameters)
		throws Exception {

		String createStrategy = (String)parameters.getOrDefault(
			"createStrategy", "INSERT");

		if (StringUtil.equalsIgnoreCase(createStrategy, "UPSERT")) {
			if (contextBatchUnsafeConsumer != null) {
				contextBatchUnsafeConsumer.accept(
					workflowDefinitions,
					workflowDefinition -> postWorkflowDefinitionDeploy(
						workflowDefinition));
			}
			else {
				for (WorkflowDefinition workflowDefinition :
						workflowDefinitions) {

					postWorkflowDefinitionDeploy(workflowDefinition);
				}
			}
		}
		else {
			super.create(workflowDefinitions, parameters);
		}
	}

	@Override
	public void deleteWorkflowDefinition(Long workflowDefinitionId)
		throws Exception {

		WorkflowDefinition workflowDefinition = getWorkflowDefinition(
			workflowDefinitionId);

		postWorkflowDefinitionUpdateActive(
			false, workflowDefinition.getName(),
			workflowDefinition.getVersion());

		deleteWorkflowDefinitionUndeploy(
			workflowDefinition.getName(), workflowDefinition.getVersion());
	}

	@Override
	public void deleteWorkflowDefinitionUndeploy(String name, String version)
		throws Exception {

		_workflowDefinitionManager.undeployWorkflowDefinition(
			contextCompany.getCompanyId(), contextUser.getUserId(), name,
			GetterUtil.getInteger(version));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public WorkflowDefinition getWorkflowDefinition(Long workflowDefinitionId)
		throws Exception {

		return _toWorkflowDefinition(
			() -> _workflowDefinitionManager.getWorkflowDefinition(
				workflowDefinitionId));
	}

	@Override
	public WorkflowDefinition getWorkflowDefinitionByName(
			String name, Integer version)
		throws Exception {

		return _toWorkflowDefinition(
			() -> {
				if (version == null) {
					return _workflowDefinitionManager.
						getLatestWorkflowDefinition(
							contextCompany.getCompanyId(), name);
				}

				return _workflowDefinitionManager.getWorkflowDefinition(
					contextCompany.getCompanyId(), name, version);
			});
	}

	@Override
	public Page<WorkflowDefinition> getWorkflowDefinitionsPage(
			Boolean active, Pagination pagination, Sort[] sorts)
		throws Exception {

		return Page.of(
			HashMapBuilder.put(
				"create",
				addAction(
					ActionKeys.ADD_DEFINITION, "postWorkflowDefinition",
					WorkflowConstants.RESOURCE_NAME, null)
			).put(
				"createBatch",
				addAction(
					ActionKeys.ADD_DEFINITION, "postWorkflowDefinitionBatch",
					WorkflowConstants.RESOURCE_NAME, null)
			).put(
				"deleteBatch",
				addAction(
					ActionKeys.DELETE, "deleteWorkflowDefinitionBatch",
					WorkflowConstants.RESOURCE_NAME, null)
			).put(
				"get",
				addAction(
					ActionKeys.VIEW, "getWorkflowDefinitionsPage",
					WorkflowConstants.RESOURCE_NAME, null)
			).put(
				"updateActive",
				addAction(
					ActionKeys.UPDATE, "postWorkflowDefinitionUpdateActive",
					WorkflowConstants.RESOURCE_NAME, null)
			).put(
				"updateBatch",
				addAction(
					ActionKeys.UPDATE, "putWorkflowDefinitionBatch",
					WorkflowConstants.RESOURCE_NAME, null)
			).build(),
			transform(
				_workflowDefinitionManager.getLatestWorkflowDefinitions(
					active, contextCompany.getCompanyId(),
					pagination.getStartPosition(), pagination.getEndPosition(),
					_toOrderByComparator((Sort)ArrayUtil.getValue(sorts, 0))),
				this::_toWorkflowDefinition),
			pagination,
			_workflowDefinitionManager.getLatestWorkflowDefinitionsCount(
				active, contextCompany.getCompanyId()));
	}

	@Override
	public WorkflowDefinition postWorkflowDefinition(
			WorkflowDefinition workflowDefinition)
		throws Exception {

		return postWorkflowDefinitionDeploy(workflowDefinition);
	}

	@Override
	public WorkflowDefinition postWorkflowDefinitionDeploy(
			WorkflowDefinition workflowDefinition)
		throws Exception {

		String content = workflowDefinition.getContent();

		return _toWorkflowDefinition(
			_workflowDefinitionManager.deployWorkflowDefinition(
				contextCompany.getCompanyId(), contextUser.getUserId(),
				_getTitle(workflowDefinition), workflowDefinition.getName(),
				content.getBytes()));
	}

	@Override
	public WorkflowDefinition postWorkflowDefinitionSave(
			WorkflowDefinition workflowDefinition)
		throws Exception {

		String content = workflowDefinition.getContent();

		return _toWorkflowDefinition(
			_workflowDefinitionManager.saveWorkflowDefinition(
				contextCompany.getCompanyId(), contextUser.getUserId(),
				_getTitle(workflowDefinition), workflowDefinition.getName(),
				content.getBytes()));
	}

	@Override
	public WorkflowDefinition postWorkflowDefinitionUpdateActive(
			Boolean active, String name, String version)
		throws Exception {

		return _toWorkflowDefinition(
			_workflowDefinitionManager.updateActive(
				contextCompany.getCompanyId(), contextUser.getUserId(), name,
				GetterUtil.getInteger(version), active));
	}

	@Override
	public WorkflowDefinition putWorkflowDefinition(
			Long workflowDefinitionId, WorkflowDefinition workflowDefinition)
		throws Exception {

		_workflowDefinitionManager.getLatestWorkflowDefinition(
			contextCompany.getCompanyId(), workflowDefinition.getName());

		return postWorkflowDefinitionDeploy(workflowDefinition);
	}

	private String _getTitle(WorkflowDefinition workflowDefinition)
		throws Exception {

		if (MapUtil.isEmpty(workflowDefinition.getTitle_i18n())) {
			return workflowDefinition.getTitle();
		}

		return _localization.getXml(
			workflowDefinition.getTitle_i18n(),
			_language.getLanguageId(contextCompany.getLocale()), "title");
	}

	private OrderByComparator
		<com.liferay.portal.kernel.workflow.WorkflowDefinition>
			_toOrderByComparator(Sort sort) {

		if (sort == null) {
			return _workflowComparatorFactory.
				getDefinitionModifiedDateComparator(false);
		}

		if (StringUtil.equals(sort.getFieldName(), "name")) {
			return _workflowComparatorFactory.getDefinitionNameComparator(
				!sort.isReverse());
		}

		return _workflowComparatorFactory.getDefinitionModifiedDateComparator(
			!sort.isReverse());
	}

	private WorkflowDefinition _toWorkflowDefinition(
			UnsafeSupplier
				<com.liferay.portal.kernel.workflow.WorkflowDefinition,
				 Exception> unsafeSupplier)
		throws Exception {

		try {
			return _toWorkflowDefinition(unsafeSupplier.get());
		}
		catch (Exception exception) {
			Throwable throwable = exception.getCause();

			if (throwable instanceof NoSuchModelException) {
				throw (NoSuchModelException)throwable;
			}

			throw exception;
		}
	}

	private WorkflowDefinition _toWorkflowDefinition(
		com.liferay.portal.kernel.workflow.WorkflowDefinition
			workflowDefinition) {

		return new WorkflowDefinition() {
			{
				active = workflowDefinition.isActive();
				content = workflowDefinition.getContent();
				dateCreated = workflowDefinition.getCreateDate();
				dateModified = workflowDefinition.getModifiedDate();
				description = workflowDefinition.getDescription();
				id = workflowDefinition.getWorkflowDefinitionId();
				name = workflowDefinition.getName();
				nodes = transformToArray(
					workflowDefinition.getWorkflowNodes(),
					workflowNode -> NodeUtil.toNode(
						contextAcceptLanguage.getPreferredLocale(),
						workflowNode),
					Node.class);
				title = workflowDefinition.getTitle(
					_language.getLanguageId(
						contextAcceptLanguage.getPreferredLocale()));
				transitions = transformToArray(
					workflowDefinition.getWorkflowTransitions(),
					workflowTransition -> TransitionUtil.toTransition(
						contextAcceptLanguage.getPreferredLocale(),
						workflowTransition),
					Transition.class);
				version = String.valueOf(workflowDefinition.getVersion());

				setActions(
					() -> HashMapBuilder.put(
						"delete",
						addAction(
							ActionKeys.DELETE,
							workflowDefinition.getWorkflowDefinitionId(),
							"deleteWorkflowDefinition",
							_workflowDefinitionModelResourcePermission)
					).put(
						"update",
						addAction(
							ActionKeys.UPDATE,
							workflowDefinition.getWorkflowDefinitionId(),
							"putWorkflowDefinition",
							_workflowDefinitionModelResourcePermission)
					).build());

				setTitle_i18n(
					() -> {
						Map<String, String> title_i18n = new HashMap<>();

						Map<Locale, String> map =
							_localization.getLocalizationMap(
								workflowDefinition.getTitle());

						for (Map.Entry<Locale, String> entry : map.entrySet()) {
							title_i18n.put(
								_language.getLanguageId(entry.getKey()),
								entry.getValue());
						}

						return title_i18n;
					});
			}
		};
	}

	private static final EntityModel _entityModel =
		new WorkflowDefinitionEntityModel();

	@Reference
	private Language _language;

	@Reference
	private Localization _localization;

	@Reference
	private WorkflowComparatorFactory _workflowComparatorFactory;

	@Reference
	private WorkflowDefinitionManager _workflowDefinitionManager;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.workflow.WorkflowDefinition)"
	)
	private ModelResourcePermission<?>
		_workflowDefinitionModelResourcePermission;

}