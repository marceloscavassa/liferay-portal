<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String portletResource = ParamUtil.getString(request, "portletResource");

List<AssetRendererFactory<?>> classTypesAssetRendererFactories = new ArrayList<>();
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" varImpl="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
	onSubmit="event.preventDefault();"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL.toString() %>" />
	<aui:input name="groupId" type="hidden" />
	<aui:input name="typeSelection" type="hidden" />
	<aui:input name="assetEntryIds" type="hidden" />
	<aui:input name="assetEntryOrder" type="hidden" value="-1" />
	<aui:input name="assetEntryType" type="hidden" />

	<%
	request.setAttribute("configuration.jsp-classTypesAssetRendererFactories", classTypesAssetRendererFactories);
	request.setAttribute("configuration.jsp-configurationRenderURL", configurationRenderURL);
	request.setAttribute("configuration.jsp-redirect", redirect);
	%>

	<liferay-ui:success key='<%= portletResource + "requestProcessed" %>' message="the-collection-was-created-successfully" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:form-navigator
			id="<%= AssetPublisherConstants.FORM_NAVIGATOR_ID_CONFIGURATION %>"
			showButtons="<%= false %>"
		/>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			submitOnClick='<%= liferayPortletResponse.getNamespace() + "saveSelectBoxes();" %>'
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<script>
	function <portlet:namespace />saveSelectBoxes() {
		var form = document.<portlet:namespace />fm;

		<%
		for (AssetRendererFactory<?> curAssetRendererFactory : classTypesAssetRendererFactories) {
			String className = assetPublisherWebHelper.getClassName(curAssetRendererFactory);
		%>

			Liferay.Util.setFormValues(form, {
				classTypeIds<%= className %>: Liferay.Util.getSelectedOptionValues(
					Liferay.Util.getFormElement(
						form,
						'<%= className %>currentClassTypeIds'
					)
				),
			});

		<%
		}
		%>

		var currentClassNameIdsSelect = Liferay.Util.getFormElement(
			form,
			'currentClassNameIds'
		);
		var currentMetadataFieldsInput = Liferay.Util.getFormElement(
			form,
			'currentMetadataFields'
		);

		if (currentClassNameIdsSelect && currentMetadataFieldsInput) {
			Liferay.Util.postForm(form, {
				data: {
					classNameIds: Liferay.Util.getSelectedOptionValues(
						currentClassNameIdsSelect
					),
					metadataFields: Liferay.Util.getSelectedOptionValues(
						currentMetadataFieldsInput
					),
				},
			});
		}
		else if (currentMetadataFieldsInput) {
			Liferay.Util.postForm(form, {
				data: {
					metadataFields: Liferay.Util.getSelectedOptionValues(
						currentMetadataFieldsInput
					),
				},
			});
		}
		else {
			submitForm(form);
		}
	}
</script>