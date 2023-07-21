<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/designer/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

KaleoDefinitionVersion currentKaleoDefinitionVersion = (KaleoDefinitionVersion)request.getAttribute(KaleoDesignerWebKeys.KALEO_DRAFT_DEFINITION);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KaleoDefinitionVersion kaleoDefinitionVersion = (KaleoDefinitionVersion)row.getObject();
%>

<portlet:renderURL var="viewURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/designer/edit_workflow_definition.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="name" value="<%= kaleoDefinitionVersion.getName() %>" />
	<portlet:param name="draftVersion" value="<%= kaleoDefinitionVersion.getVersion() %>" />
	<portlet:param name="<%= WorkflowWebKeys.WORKFLOW_JSP_STATE %>" value="<%= WorkflowWebKeys.WORKFLOW_PREVIEW_BEFORE_RESTORE_STATE %>" />
</portlet:renderURL>

<liferay-portlet:actionURL name="/kaleo_designer/revert_kaleo_definition_version" var="revertURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="name" value="<%= kaleoDefinitionVersion.getName() %>" />
	<portlet:param name="draftVersion" value="<%= kaleoDefinitionVersion.getVersion() %>" />
</liferay-portlet:actionURL>

<c:if test="<%= !Objects.equals(kaleoDefinitionVersion.getVersion(), currentKaleoDefinitionVersion.getVersion()) %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		id='<%= "iconMenu_" + kaleoDefinitionVersion.getVersion() %>'
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
	>
		<liferay-ui:icon
			id='<%= "previewBeforeRevert" + kaleoDefinitionVersion.getVersion() %>'
			message="preview"
			url="javascript:void(0);"
		/>

		<liferay-ui:icon
			message="restore"
			url="<%= revertURL %>"
		/>
	</liferay-ui:icon-menu>
</c:if>

<aui:script use="liferay-kaleo-designer-utils">
	var title = '<liferay-ui:message key="preview" />';

	var previewBeforeRevert = A.rbind(
		'previewBeforeRevert',
		Liferay.KaleoDesignerUtils,
		'<%= viewURL %>',
		'<%= revertURL %>',
		title
	);

	Liferay.delegateClick(
		'<portlet:namespace />previewBeforeRevert<%= kaleoDefinitionVersion.getVersion() %>',
		previewBeforeRevert
	);
</aui:script>