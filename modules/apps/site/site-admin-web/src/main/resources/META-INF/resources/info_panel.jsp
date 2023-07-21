<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<Group> groups = (List<Group>)request.getAttribute(SiteAdminWebKeys.GROUP_ENTRIES);

SiteAdminDisplayContext siteAdminDisplayContext = new SiteAdminDisplayContext(request, liferayPortletRequest, liferayPortletResponse);

if (ListUtil.isEmpty(groups)) {
	groups = new ArrayList<>();

	Group group = siteAdminDisplayContext.getGroup();

	if (group != null) {
		groups.add(group);
	}
	else if (siteAdminDisplayContext.getGroupId() != GroupConstants.DEFAULT_PARENT_GROUP_ID) {
		groups.add(GroupLocalServiceUtil.fetchGroup(siteAdminDisplayContext.getGroupId()));
	}
	else {
		groups.add(group);
	}
}

request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(groups) && (groups.size() == 1) %>">

		<%
		Group group = groups.get(0);
		%>

		<c:choose>
			<c:when test="<%= group == null %>">
				<div class="sidebar-header">
					<clay:content-row
						cssClass="sidebar-section"
					>
						<clay:content-col
							expand="<%= true %>"
						>
							<h2 class="component-title"><liferay-ui:message key="sites" /></h2>
						</clay:content-col>
					</clay:content-row>
				</div>

				<div class="sidebar-body">
					<div class="sheet-row">
						<clay:tabs
							tabsItems="<%= siteAdminDisplayContext.getTabsItem() %>"
						>
							<clay:tabs-panel>
								<dl class="sidebar-dl">
									<dt class="sidebar-dt">
										<liferay-ui:message key="num-of-sites" />
									</dt>
									<dd class="sidebar-dd">
										<%= GroupLocalServiceUtil.getGroupsCount(company.getCompanyId(), siteAdminDisplayContext.getGroupId(), true) %>
									</dd>
								</dl>
							</clay:tabs-panel>
						</clay:tabs>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="sidebar-header">
					<clay:content-row
						cssClass="sidebar-section"
					>
						<clay:content-col
							expand="<%= true %>"
						>
							<h2 class="component-title"><%= HtmlUtil.escape(group.getDescriptiveName()) %></h2>
						</clay:content-col>

						<clay:content-col>
							<ul class="autofit-padded-no-gutters autofit-row">
								<li class="autofit-col">
									<clay:dropdown-actions
										aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
										dropdownItems="<%= siteAdminDisplayContext.getActionDropdownItems(group) %>"
										propsTransformer="js/SiteDropdownDefaultPropsTransformer"
									/>
								</li>
							</ul>
						</clay:content-col>
					</clay:content-row>
				</div>

				<div class="sidebar-body">
					<div class="sheet-row">
						<clay:tabs
							tabsItems="<%= siteAdminDisplayContext.getTabsItem() %>"
						>
							<clay:tabs-panel>

								<%
								String logoURL = group.getLogoURL(themeDisplay, false);
								%>

								<c:if test="<%= Validator.isNotNull(logoURL) %>">
									<p class="aspect-ratio aspect-ratio-16-to-9 sidebar-panel">
										<img alt="<%= HtmlUtil.escapeAttribute(group.getDescriptiveName()) %>" class="aspect-ratio-item aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= logoURL %>" />
									</p>
								</c:if>

								<c:if test="<%= group.isOrganization() %>">

									<%
									Organization groupOrganization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
									%>

									<p>
										<liferay-ui:message arguments="<%= new String[] {groupOrganization.getName(), LanguageUtil.get(request, groupOrganization.getType())} %>" key="this-site-belongs-to-x-which-is-an-organization-of-type-x" translateArguments="<%= false %>" />
									</p>
								</c:if>

								<ul class="list-unstyled sidebar-dl sidebar-section">
									<li class="sidebar-dt">
										<liferay-ui:message key="members" />
									</li>
									<li class="sidebar-dd">
										<c:if test="<%= (siteAdminDisplayContext.getUsersCount(group) == 0) && (siteAdminDisplayContext.getOrganizationsCount(group) == 0) && (siteAdminDisplayContext.getUserGroupsCount(group) == 0) %>">
											<liferay-ui:message key="none" />
										</c:if>

										<%
										String portletId = PortletProviderUtil.getPortletId(MembershipRequest.class.getName(), PortletProvider.Action.VIEW);

										PortletURL assignMembersURL = PortletURLBuilder.create(
											PortalUtil.getControlPanelPortletURL(request, group, portletId, 0, 0, PortletRequest.RENDER_PHASE)
										).setRedirect(
											currentURL
										).setParameter(
											"groupId", group.getGroupId()
										).buildPortletURL();
										%>

										<c:if test="<%= siteAdminDisplayContext.getUsersCount(group) > 0 %>">
											<div>
												<clay:link
													href='<%= HttpComponentsUtil.addParameter(assignMembersURL.toString(), "tabs1", "users") %>'
													label='<%= LanguageUtil.format(request, (siteAdminDisplayContext.getUsersCount(group) == 1) ? "x-user" : "x-users", siteAdminDisplayContext.getUsersCount(group), false) %>'
												/>
											<div>
										</c:if>

										<c:if test="<%= siteAdminDisplayContext.getOrganizationsCount(group) > 0 %>">
											<div>
												<clay:link
													href='<%= HttpComponentsUtil.addParameter(assignMembersURL.toString(), "tabs1", "organizations") %>'
													label='<%= LanguageUtil.format(request, (siteAdminDisplayContext.getOrganizationsCount(group) == 1) ? "x-organization" : "x-organizations", siteAdminDisplayContext.getOrganizationsCount(group), false) %>'
												/>
											</div>
										</c:if>

										<c:if test="<%= siteAdminDisplayContext.getUserGroupsCount(group) > 0 %>">
											<div>
												<clay:link
													href='<%= HttpComponentsUtil.addParameter(assignMembersURL.toString(), "tabs1", "user-groups") %>'
													label='<%= LanguageUtil.format(request, (siteAdminDisplayContext.getUserGroupsCount(group) == 1) ? "x-user-groups" : "x-user-groups", siteAdminDisplayContext.getUserGroupsCount(group), false) %>'
												/>
											</div>
										</c:if>
									</li>

									<c:if test="<%= siteAdminDisplayContext.getPendingRequestsCount(group) > 0 %>">
										<li class="sidebar-dt"><liferay-ui:message key="request-pending" /></li>

										<liferay-portlet:renderURL portletName="<%= portletId %>" var="viewMembershipRequestsURL">
											<portlet:param name="mvcPath" value="/view_membership_requests.jsp" />
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
										</liferay-portlet:renderURL>

										<li class="sidebar-dd">
											<clay:link
												href="<%= viewMembershipRequestsURL %>"
												label='<%= LanguageUtil.format(request, (siteAdminDisplayContext.getPendingRequestsCount(group) == 1) ? "x-request-pending" : "x-requests-pending", siteAdminDisplayContext.getPendingRequestsCount(group), false) %>'
											/>
										</li>
									</c:if>

									<li class="sidebar-dt"><liferay-ui:message key="membership-type" /></li>

									<li class="sidebar-dd">
										<liferay-ui:message key="<%= GroupConstants.getTypeLabel(group.getType()) %>" />
									</li>

									<c:if test="<%= Validator.isNotNull(group.getDescription()) %>">
										<li class="sidebar-dt"><liferay-ui:message key="description" /></li>

										<li class="sidebar-dd">
											<%= HtmlUtil.escape(group.getDescription(locale)) %>
										</li>
									</c:if>

									<li class="sidebar-dt">
										<liferay-asset:asset-categories-summary
											className="<%= Group.class.getName() %>"
											classPK="<%= group.getGroupId() %>"
										/>
									</li>
									<li class="sidebar-dt">
										<liferay-asset:asset-tags-summary
											className="<%= Group.class.getName() %>"
											classPK="<%= group.getGroupId() %>"
										/>
									</li>
								</ul>
							</clay:tabs-panel>
						</clay:tabs>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<clay:content-row
				cssClass="sidebar-section"
			>
				<clay:content-col
					expand="<%= true %>"
				>
					<h2 class="component-title">
						<liferay-ui:message arguments="<%= groups.size() %>" key="x-items-are-selected" />
					</h2>
				</clay:content-col>
			</clay:content-row>
		</div>

		<div class="sidebar-body">
			<div class="sheet-row">
				<clay:tabs
					tabsItems="<%= siteAdminDisplayContext.getTabsItem() %>"
				>
					<clay:tabs-panel>
						<dl class="sidebar-dl sidebar-section">
							<dt class="sidebar-dt">
								<liferay-ui:message arguments="<%= groups.size() %>" key="x-items-are-selected" />
							</dt>
						</dl>
					</clay:tabs-panel>
				</clay:tabs>
			</div>
		</div>
	</c:otherwise>
</c:choose>