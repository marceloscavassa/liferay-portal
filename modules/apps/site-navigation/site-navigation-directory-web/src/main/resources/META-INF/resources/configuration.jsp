<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	fluid="<%= true %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<clay:row>
			<clay:col
				md="6"
			>
				<liferay-frontend:fieldset
					cssClass="ml-3"
				>
					<clay:row>
						<aui:select name="preferences--sites--" value="<%= sitesDirectoryDisplayContext.getSites() %>">
							<aui:option label="top-level" />
							<aui:option label="parent-level" />
							<aui:option label="siblings" />
							<aui:option label="children" />
						</aui:select>
					</clay:row>

					<clay:row>
						<aui:select name="preferences--displayStyle--" value="<%= sitesDirectoryDisplayContext.getDisplayStyle() %>">
							<aui:option label="icon" />
							<aui:option label="descriptive" />
							<aui:option label="list" />
							<aui:option label="list-hierarchy" />
						</aui:select>
					</clay:row>
				</liferay-frontend:fieldset>
			</clay:col>

			<clay:col
				md="6"
			>
				<liferay-portlet:preview
					portletName="<%= portletResource %>"
					queryString="struts_action=/sites_directory/view"
					showBorders="<%= true %>"
				/>
			</clay:col>
		</clay:row>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script sandbox="<%= true %>">
	function refreshPreview(displayStyle, sites) {
		var data = Liferay.Util.ns('_<%= HtmlUtil.escapeJS(portletResource) %>_', {
			displayStyle: displayStyle,
			sites: sites,
		});

		Liferay.Portlet.refresh(
			'#p_p_id_<%= HtmlUtil.escapeJS(portletResource) %>_',
			data
		);
	}

	var form = document.<portlet:namespace />fm;

	var displayStyleSelect = Liferay.Util.getFormElement(form, 'displayStyle');
	var sitesSelect = Liferay.Util.getFormElement(form, 'sites');

	if (displayStyleSelect && sitesSelect) {
		form.addEventListener('change', () => {
			refreshPreview(displayStyleSelect.value, sitesSelect.value);
		});

		form.addEventListener('select', () => {
			refreshPreview(displayStyleSelect.value, sitesSelect.value);
		});
	}
</aui:script>