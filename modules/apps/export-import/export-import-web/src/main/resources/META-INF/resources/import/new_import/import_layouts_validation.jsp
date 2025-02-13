<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/import/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
%>

<aui:form cssClass="lfr-export-dialog sheet" method="post" name="fm1">
	<div class="lfr-dynamic-uploader">
		<clay:container-fluid>
			<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
		</clay:container-fluid>
	</div>

	<%
	FileEntry fileEntry = ExportImportHelperUtil.getTempFileEntry(groupId, themeDisplay.getUserId(), ExportImportHelper.TEMP_FOLDER_NAME);
	%>

	<aui:button-row>
		<aui:button cssClass='<%= (fileEntry == null) ? " hide" : StringPool.BLANK %>' name="continueButton" type="submit" value="continue" />
	</aui:button-row>

	<%
	Date expirationDate = new Date(System.currentTimeMillis() + (PropsValues.SESSION_TIMEOUT * Time.MINUTE));

	Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());
	%>

	<aui:script use="liferay-upload">
		var liferayUpload = new Liferay.Upload({
			'boundingBox': '#<portlet:namespace />fileUpload',

			<%
			DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);
			%>

			'decimalSeparator': '<%= decimalFormatSymbols.getDecimalSeparator() %>',

			'deleteFile':
				'<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/export_import/import_layouts"><portlet:param name="mvcRenderCommandName" value="/export_import/import_layouts" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE_TEMP %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= Group.class.getName() %>" />',

			<%
			DLConfiguration dlConfiguration = ConfigurationProviderUtil.getSystemConfiguration(DLConfiguration.class);
			%>

			'fileDescription':
				'<%= StringUtil.merge(dlConfiguration.fileExtensions()) %>',

			'maxFileSize':
				'<%= UploadServletRequestConfigurationProviderUtil.getMaxSize() %> B',
			'metadataContainer': '#<portlet:namespace />commonFileMetadataContainer',
			'metadataExplanationContainer':
				'#<portlet:namespace />metadataExplanationContainer',
			'multipleFiles': false,
			'namespace': '<portlet:namespace />',
			'strings.dropFileText':
				'<liferay-ui:message key="drop-a-lar-file-here-to-import" />',
			'strings.fileCannotBeSavedText':
				'<liferay-ui:message key="the-file-x-cannot-be-imported" />',
			'strings.pendingFileText':
				'<liferay-ui:message key="this-file-was-previously-uploaded-but-not-actually-imported" />',
			'strings.uploadsCompleteText':
				'<liferay-ui:message key="the-file-is-ready-to-be-imported" />',
			'tempFileURL': {
				method: Liferay.Service.bind('/layout/get-temp-file-names'),
				params: {
					folderName: '<%= ExportImportHelper.TEMP_FOLDER_NAME %>',
					groupId: <%= groupId %>,
				},
			},
			'uploadFile':
				'<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="/export_import/import_layouts"><portlet:param name="mvcRenderCommandName" value="/export_import/import_layouts" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD_TEMP %>" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /><portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" /></liferay-portlet:actionURL>&ticketKey=<%= ticket.getKey() %><liferay-ui:input-permissions-params modelName="<%= Group.class.getName() %>" />',
		});

		var continueButton = A.one('#<portlet:namespace />continueButton');

		liferayUpload._uploader.on('alluploadscomplete', (event) => {
			toggleContinueButton();
		});

		Liferay.on('tempFileRemoved', (event) => {
			toggleContinueButton();
		});

		function toggleContinueButton() {
			var uploadedFiles = liferayUpload._fileListContent.all(
				'.upload-file.upload-complete'
			);

			if (uploadedFiles.size() == 1) {
				continueButton.show();
			}
			else {
				continueButton.hide();
			}
		}
	</aui:script>
</aui:form>

<aui:script use="aui-base,aui-io-plugin-deprecated,aui-loading-mask-deprecated,io">
	var form = A.one('#<portlet:namespace />fm1');

	form.on('submit', (event) => {
		event.halt();

		var exportImportOptions = A.one(
			'#<portlet:namespace />exportImportOptions'
		);

		exportImportOptions.plug(A.Plugin.IO, {
			form: {
				id: '<portlet:namespace />fm1',
			},

			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/export_import/import_layouts" var="importPagesURL">
				<portlet:param name="p_p_isolated" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
				<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
				<portlet:param name="validate" value="<%= String.valueOf(Boolean.FALSE) %>" />
			</liferay-portlet:resourceURL>

			uri: '<%= importPagesURL %>',
		});
	});
</aui:script>