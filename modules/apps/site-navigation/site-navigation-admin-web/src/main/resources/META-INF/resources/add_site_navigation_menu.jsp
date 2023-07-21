<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "add-new-menu"));
%>

<portlet:actionURL name="/site_navigation_admin/add_site_navigation_menu" var="editSitaNavigationMenuURL">
	<portlet:param name="mvcPath" value="/add_site_navigation_menu.jsp" />
	<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
</portlet:actionURL>

<aui:form action="<%= editSitaNavigationMenuURL %>" cssClass="container-fluid container-fluid-max-xl" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:model-context model="<%= SiteNavigationMenu.class %>" />

	<div class="sheet">
		<div class="panel-group panel-group-flush">
			<aui:fieldset>
				<aui:input label="name" name="name" placeholder="name" />
			</aui:fieldset>
		</div>
	</div>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>