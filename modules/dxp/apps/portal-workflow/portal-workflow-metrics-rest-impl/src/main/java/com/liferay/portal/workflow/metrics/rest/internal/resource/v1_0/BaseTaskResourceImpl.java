/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.rest.internal.resource.v1_0;

import com.liferay.petra.function.UnsafeBiConsumer;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParser;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.VulcanBatchEngineTaskItemDelegate;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.Task;
import com.liferay.portal.workflow.metrics.rest.dto.v1_0.TaskBulkSelection;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.TaskResource;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
@javax.ws.rs.Path("/v1.0")
public abstract class BaseTaskResourceImpl
	implements EntityModelResource, TaskResource,
			   VulcanBatchEngineTaskItemDelegate<Task> {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks'  -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.GET
	@javax.ws.rs.Path("/processes/{processId}/tasks")
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public Page<Task> getProcessTasksPage(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "assignee": ___, "className": ___, "classPK": ___, "completed": ___, "completionUserId": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "instanceId": ___, "name": ___, "nodeId": ___, "processId": ___, "processVersion": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.Consumes({"application/json", "application/xml"})
	@javax.ws.rs.Path("/processes/{processId}/tasks")
	@javax.ws.rs.POST
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public Task postProcessTask(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			Task task)
		throws Exception {

		return new Task();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks/batch'  -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY,
				name = "callbackURL"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.Consumes("application/json")
	@javax.ws.rs.Path("/processes/{processId}/tasks/batch")
	@javax.ws.rs.POST
	@javax.ws.rs.Produces("application/json")
	@Override
	public Response postProcessTaskBatch(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.ws.rs.QueryParam("callbackURL")
			String callbackURL,
			Object object)
		throws Exception {

		vulcanBatchEngineImportTaskResource.setContextAcceptLanguage(
			contextAcceptLanguage);
		vulcanBatchEngineImportTaskResource.setContextCompany(contextCompany);
		vulcanBatchEngineImportTaskResource.setContextHttpServletRequest(
			contextHttpServletRequest);
		vulcanBatchEngineImportTaskResource.setContextUriInfo(contextUriInfo);
		vulcanBatchEngineImportTaskResource.setContextUser(contextUser);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.entity(
			vulcanBatchEngineImportTaskResource.postImportTask(
				Task.class.getName(), callbackURL, null, object)
		).build();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks/{taskId}'  -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "taskId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.DELETE
	@javax.ws.rs.Path("/processes/{processId}/tasks/{taskId}")
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public void deleteProcessTask(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("taskId")
			Long taskId)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks/{taskId}'  -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "taskId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.GET
	@javax.ws.rs.Path("/processes/{processId}/tasks/{taskId}")
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public Task getProcessTask(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("taskId")
			Long taskId)
		throws Exception {

		return new Task();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks/{taskId}' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "assignee": ___, "className": ___, "classPK": ___, "completed": ___, "completionUserId": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "instanceId": ___, "name": ___, "nodeId": ___, "processId": ___, "processVersion": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "taskId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.Consumes({"application/json", "application/xml"})
	@javax.ws.rs.PATCH
	@javax.ws.rs.Path("/processes/{processId}/tasks/{taskId}")
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public void patchProcessTask(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("taskId")
			Long taskId,
			Task task)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PATCH' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/processes/{processId}/tasks/{taskId}/complete' -d $'{"assetTitle_i18n": ___, "assetType_i18n": ___, "assignee": ___, "className": ___, "classPK": ___, "completed": ___, "completionUserId": ___, "dateCompletion": ___, "dateCreated": ___, "dateModified": ___, "duration": ___, "id": ___, "instanceId": ___, "name": ___, "nodeId": ___, "processId": ___, "processVersion": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "processId"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
				name = "taskId"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.Consumes({"application/json", "application/xml"})
	@javax.ws.rs.PATCH
	@javax.ws.rs.Path("/processes/{processId}/tasks/{taskId}/complete")
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public void patchProcessTaskComplete(
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("processId")
			Long processId,
			@io.swagger.v3.oas.annotations.Parameter(hidden = true)
			@javax.validation.constraints.NotNull
			@javax.ws.rs.PathParam("taskId")
			Long taskId,
			Task task)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/portal-workflow-metrics/v1.0/tasks' -d $'{"assigneeIds": ___, "instanceIds": ___, "processId": ___, "slaStatuses": ___, "taskNames": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@io.swagger.v3.oas.annotations.Parameters(
		value = {
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY,
				name = "page"
			),
			@io.swagger.v3.oas.annotations.Parameter(
				in = io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY,
				name = "pageSize"
			)
		}
	)
	@io.swagger.v3.oas.annotations.tags.Tags(
		value = {@io.swagger.v3.oas.annotations.tags.Tag(name = "Task")}
	)
	@javax.ws.rs.Consumes({"application/json", "application/xml"})
	@javax.ws.rs.Path("/tasks")
	@javax.ws.rs.POST
	@javax.ws.rs.Produces({"application/json", "application/xml"})
	@Override
	public Page<Task> postTasksPage(
			@javax.ws.rs.core.Context Pagination pagination,
			TaskBulkSelection taskBulkSelection)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@SuppressWarnings("PMD.UnusedLocalVariable")
	public void create(
			java.util.Collection<Task> tasks,
			Map<String, Serializable> parameters)
		throws Exception {

		UnsafeConsumer<Task, Exception> taskUnsafeConsumer = null;

		String createStrategy = (String)parameters.getOrDefault(
			"createStrategy", "INSERT");

		if ("INSERT".equalsIgnoreCase(createStrategy)) {
			if (parameters.containsKey("processId")) {
				taskUnsafeConsumer = task -> postProcessTask(
					Long.parseLong((String)parameters.get("processId")), task);
			}
			else {
				throw new NotSupportedException(
					"One of the following parameters must be specified: [processId]");
			}
		}

		if (taskUnsafeConsumer == null) {
			throw new NotSupportedException(
				"Create strategy \"" + createStrategy +
					"\" is not supported for Task");
		}

		if (contextBatchUnsafeConsumer != null) {
			contextBatchUnsafeConsumer.accept(tasks, taskUnsafeConsumer);
		}
		else {
			for (Task task : tasks) {
				taskUnsafeConsumer.accept(task);
			}
		}
	}

	@Override
	public void delete(
			java.util.Collection<Task> tasks,
			Map<String, Serializable> parameters)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	public Set<String> getAvailableCreateStrategies() {
		return SetUtil.fromArray("INSERT");
	}

	public Set<String> getAvailableUpdateStrategies() {
		return SetUtil.fromArray();
	}

	@Override
	public EntityModel getEntityModel(Map<String, List<String>> multivaluedMap)
		throws Exception {

		return getEntityModel(
			new MultivaluedHashMap<String, Object>(multivaluedMap));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return null;
	}

	public String getVersion() {
		return "v1.0";
	}

	@Override
	public Page<Task> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		if (parameters.containsKey("processId")) {
			return getProcessTasksPage(
				Long.parseLong((String)parameters.get("processId")));
		}
		else {
			throw new NotSupportedException(
				"One of the following parameters must be specified: [processId]");
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		this.contextAcceptLanguage = new AcceptLanguage() {

			@Override
			public List<Locale> getLocales() {
				return null;
			}

			@Override
			public String getPreferredLanguageId() {
				return languageId;
			}

			@Override
			public Locale getPreferredLocale() {
				return LocaleUtil.fromLanguageId(languageId);
			}

		};
	}

	@Override
	public void update(
			java.util.Collection<Task> tasks,
			Map<String, Serializable> parameters)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextBatchUnsafeConsumer(
		UnsafeBiConsumer
			<java.util.Collection<Task>, UnsafeConsumer<Task, Exception>,
			 Exception> contextBatchUnsafeConsumer) {

		this.contextBatchUnsafeConsumer = contextBatchUnsafeConsumer;
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany) {

		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser) {

		this.contextUser = contextUser;
	}

	public void setExpressionConvert(
		ExpressionConvert<Filter> expressionConvert) {

		this.expressionConvert = expressionConvert;
	}

	public void setFilterParserProvider(
		FilterParserProvider filterParserProvider) {

		this.filterParserProvider = filterParserProvider;
	}

	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	public void setResourceActionLocalService(
		ResourceActionLocalService resourceActionLocalService) {

		this.resourceActionLocalService = resourceActionLocalService;
	}

	public void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}

	public void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	public void setVulcanBatchEngineImportTaskResource(
		VulcanBatchEngineImportTaskResource
			vulcanBatchEngineImportTaskResource) {

		this.vulcanBatchEngineImportTaskResource =
			vulcanBatchEngineImportTaskResource;
	}

	@Override
	public Filter toFilter(
		String filterString, Map<String, List<String>> multivaluedMap) {

		try {
			EntityModel entityModel = getEntityModel(multivaluedMap);

			FilterParser filterParser = filterParserProvider.provide(
				entityModel);

			com.liferay.portal.odata.filter.Filter oDataFilter =
				new com.liferay.portal.odata.filter.Filter(
					filterParser.parse(filterString));

			return expressionConvert.convert(
				oDataFilter.getExpression(),
				contextAcceptLanguage.getPreferredLocale(), entityModel);
		}
		catch (Exception exception) {
			_log.error("Invalid filter " + filterString, exception);
		}

		return null;
	}

	protected Map<String, String> addAction(
		String actionName, GroupedModel groupedModel, String methodName) {

		return ActionUtil.addAction(
			actionName, getClass(), groupedModel, methodName,
			contextScopeChecker, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName, Long ownerId,
		String permissionName, Long siteId) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			ownerId, permissionName, siteId, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName,
		ModelResourcePermission modelResourcePermission) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			modelResourcePermission, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, String methodName, String permissionName,
		Long siteId) {

		return addAction(
			actionName, siteId, methodName, null, permissionName, siteId);
	}

	protected <T, R, E extends Throwable> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, E> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R, E extends Throwable> R[] transform(
		T[] array, UnsafeFunction<T, R, E> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R, E extends Throwable> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, E> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R, E extends Throwable> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, E> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected <T, R, E extends Throwable> List<R> unsafeTransform(
			java.util.Collection<T> collection,
			UnsafeFunction<T, R, E> unsafeFunction)
		throws E {

		return TransformUtil.unsafeTransform(collection, unsafeFunction);
	}

	protected <T, R, E extends Throwable> R[] unsafeTransform(
			T[] array, UnsafeFunction<T, R, E> unsafeFunction, Class<?> clazz)
		throws E {

		return TransformUtil.unsafeTransform(array, unsafeFunction, clazz);
	}

	protected <T, R, E extends Throwable> R[] unsafeTransformToArray(
			java.util.Collection<T> collection,
			UnsafeFunction<T, R, E> unsafeFunction, Class<?> clazz)
		throws E {

		return TransformUtil.unsafeTransformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R, E extends Throwable> List<R> unsafeTransformToList(
			T[] array, UnsafeFunction<T, R, E> unsafeFunction)
		throws E {

		return TransformUtil.unsafeTransformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected UnsafeBiConsumer
		<java.util.Collection<Task>, UnsafeConsumer<Task, Exception>, Exception>
			contextBatchUnsafeConsumer;
	protected com.liferay.portal.kernel.model.Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected Object contextScopeChecker;
	protected UriInfo contextUriInfo;
	protected com.liferay.portal.kernel.model.User contextUser;
	protected ExpressionConvert<Filter> expressionConvert;
	protected FilterParserProvider filterParserProvider;
	protected GroupLocalService groupLocalService;
	protected ResourceActionLocalService resourceActionLocalService;
	protected ResourcePermissionLocalService resourcePermissionLocalService;
	protected RoleLocalService roleLocalService;
	protected VulcanBatchEngineImportTaskResource
		vulcanBatchEngineImportTaskResource;

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseTaskResourceImpl.class);

}