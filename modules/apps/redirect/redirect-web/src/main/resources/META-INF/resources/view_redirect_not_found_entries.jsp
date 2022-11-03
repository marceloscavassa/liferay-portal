<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
RedirectNotFoundEntriesDisplayContext redirectNotFoundEntriesDisplayContext = (RedirectNotFoundEntriesDisplayContext)request.getAttribute(RedirectNotFoundEntriesDisplayContext.class.getName());
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= redirectNotFoundEntriesDisplayContext.getRedirectNotFoundEntriesManagementToolbarDisplayContext() %>"
	propsTransformer="js/RedirectNotFoundEntriesManagementToolbarPropsTransformer"
/>

<aui:form action="<%= redirectNotFoundEntriesDisplayContext.getActionURL() %>" cssClass="container-fluid container-fluid-max-xl" name="fm">
	<c:choose>
		<c:when test="<%= redirectNotFoundEntriesDisplayContext.hasResults() %>">
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="ignored" type="hidden" />

			<liferay-ui:search-container
				id="<%= redirectNotFoundEntriesDisplayContext.getSearchContainerId() %>"
				searchContainer="<%= redirectNotFoundEntriesDisplayContext.searchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.redirect.model.RedirectNotFoundEntry"
					keyProperty="redirectNotFoundEntryId"
					modelVar="redirectNotFoundEntry"
				>

					<%
					row.setData(
						HashMapBuilder.<String, Object>put(
							"actions", redirectNotFoundEntriesDisplayContext.getAvailableActions(redirectNotFoundEntry)
						).build());
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand"
						name="not-found-urls"
					>
						<%= HtmlUtil.escape(redirectNotFoundEntriesDisplayContext.getURL(redirectNotFoundEntry)) %>
					</liferay-ui:search-container-column-text>

					<c:if test='<%= StringUtil.equals("all", ParamUtil.getString(request, "filterType")) %>'>
						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-minw-200 table-column-text-center"
							name="ignored-urls"
						>
							<c:if test="<%= redirectNotFoundEntry.isIgnored() %>">
								<clay:icon
									symbol="hidden"
								/>
							</c:if>
						</liferay-ui:search-container-column-text>
					</c:if>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smallest table-column-text-end"
						name="requests"
					>
						<%= redirectNotFoundEntry.getRequestCount() %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text>
						<clay:dropdown-actions
							aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
							dropdownItems="<%= redirectNotFoundEntriesDisplayContext.getActionDropdownItems(redirectNotFoundEntry) %>"
						/>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
		<c:otherwise>
			<liferay-frontend:empty-result-message
				animationType="<%= EmptyResultMessageKeys.AnimationType.SEARCH %>"
				description="<%= LanguageUtil.get(request, redirectNotFoundEntriesDisplayContext.getEmptyResultsMessage()) %>"
				title='<%= LanguageUtil.get(request, "all-is-in-order") %>'
			/>
		</c:otherwise>
	</c:choose>
</aui:form>