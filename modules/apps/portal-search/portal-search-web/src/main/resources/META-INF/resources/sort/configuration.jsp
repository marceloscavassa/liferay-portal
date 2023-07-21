<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/learn" prefix="liferay-learn" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/template" prefix="liferay-template" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.sort.configuration.SortPortletInstanceConfiguration" %><%@
page import="com.liferay.portal.search.web.internal.sort.display.context.SortDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferences" %><%@
page import="com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferencesImpl" %><%@
page import="com.liferay.portal.search.web.internal.util.PortletPreferencesJspUtil" %>

<portlet:defineObjects />

<%
SortDisplayContext sortDisplayContext = (SortDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));

SortPortletInstanceConfiguration sortPortletInstanceConfiguration = sortDisplayContext.getSortPortletInstanceConfiguration();

SortPortletPreferences sortPortletPreferences = new SortPortletPreferencesImpl(portletPreferences);

JSONArray fieldsJSONArray = sortPortletPreferences.getFieldsJSONArray();
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset
			collapsible="<%= true %>"
			label="display-settings"
		>
			<div class="display-template">
				<liferay-template:template-selector
					className="<%= SortDisplayContext.class.getName() %>"
					displayStyle="<%= sortPortletInstanceConfiguration.displayStyle() %>"
					displayStyleGroupId="<%= sortDisplayContext.getDisplayStyleGroupId() %>"
					refreshURL="<%= configurationRenderURL %>"
					showEmptyOption="<%= true %>"
				/>
			</div>
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset
			collapsible="<%= true %>"
			label="advanced-configuration"
		>
			<p class="sheet-text">
				<liferay-ui:message key="sort-advanced-configuration-description" />

				<liferay-learn:message
					key="sorting-search-results"
					resource="portal-search-web"
				/>
			</p>

			<div>
				<span aria-hidden="true" class="loading-animation loading-animation-sm mt-4"></span>

				<react:component
					module="js/components/SortConfigurationOptions"
					props='<%=
						HashMapBuilder.<String, Object>put(
							"fieldsInputName", PortletPreferencesJspUtil.getInputName(SortPortletPreferences.PREFERENCE_KEY_FIELDS)
						).put(
							"fieldsJSONArray", fieldsJSONArray
						).put(
							"namespace", liferayPortletResponse.getNamespace()
						).build()
					%>'
				/>
			</div>
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>