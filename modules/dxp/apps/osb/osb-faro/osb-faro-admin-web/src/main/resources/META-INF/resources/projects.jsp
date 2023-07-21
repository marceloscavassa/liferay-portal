<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
FaroAdminDisplayContext faroAdminDisplayContext = new FaroAdminDisplayContext(request, renderRequest, renderResponse);
%>

<clay:management-toolbar
	displayContext="<%= new FaroAdminManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, renderResponse, faroAdminDisplayContext.getSearchContainer()) %>"
/>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/faro_admin/info_panel" var="sidebarPanelURL" />

	<liferay-frontend:sidebar-panel
		resourceURL="<%= sidebarPanelURL %>"
		searchContainerId="faro_admin"
	>
		<liferay-util:include page="" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<div class="container-fluid container-fluid-max-xl sidenav-content">
		<aui:form name="fm">
			<liferay-ui:search-container
				id="faro_admin"
				searchContainer="<%= faroAdminDisplayContext.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.osb.faro.admin.web.internal.model.FaroProjectAdminDisplay"
					escapedModel="<%= true %>"
					keyProperty="faroProjectId"
					modelVar="faroProjectAdminDisplay"
					rowIdProperty="faroProjectId"
				>
					<liferay-ui:search-container-column-text
						orderable="<%= true %>"
						property="name"
					/>

					<liferay-ui:search-container-column-text
						property="owner"
					/>

					<liferay-ui:search-container-column-date
						name="create-date"
						orderable="<%= true %>"
						property="createDate"
					/>

					<liferay-ui:search-container-column-text
						name="subscription"
						property="subscriptionName"
					/>

					<liferay-ui:search-container-column-text
						name="individuals-usage"
						property="individualsUsage"
					/>

					<liferay-ui:search-container-column-text
						name="page-views-usage"
						property="pageViewsUsage"
					/>

					<liferay-ui:search-container-column-text
						property="offline"
					/>

					<liferay-ui:search-container-column-text>
						<c:if test="<%= permissionChecker.isOmniadmin() %>">

							<%
							List<DropdownItem> dropdownItems = faroAdminDisplayContext.getActionDropdownItems(faroProjectAdminDisplay);
							%>

							<c:if test="<%= ListUtil.isNotEmpty(dropdownItems) %>">
								<clay:dropdown-actions
									aria-label=""
									aria-labelledby=""
									dropdownItems="<%= dropdownItems %>"
									title=""
								/>
							</c:if>
						</c:if>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					searchResultCssClass="show-quick-actions-on-hover table table-autofit"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>