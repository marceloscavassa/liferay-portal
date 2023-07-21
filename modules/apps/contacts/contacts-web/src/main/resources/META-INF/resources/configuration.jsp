<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="contacts-portlet">
	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

	<liferay-frontend:edit-form
		action="<%= configurationActionURL %>"
		method="post"
		name="fm"
	>
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-frontend:edit-form-body>
			<liferay-frontend:fieldset>
				<aui:select label="display-style" name="preferences--displayStyle--" value="<%= displayStyle %>">
					<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_FULL_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_FULL %>" />
					<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_BASIC_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_BASIC %>" />
					<aui:option label="<%= ContactsConstants.DISPLAY_STYLE_DETAIL_LABEL %>" value="<%= ContactsConstants.DISPLAY_STYLE_DETAIL %>" />
				</aui:select>

				<clay:row>
					<clay:col
						md="6"
					>
						<aui:input name="preferences--showAdditionalEmailAddresses--" type="checkbox" value="<%= showAdditionalEmailAddresses %>" />

						<aui:input name="preferences--showAddresses--" type="checkbox" value="<%= showAddresses %>" />

						<aui:input name="preferences--showComments--" type="checkbox" value="<%= showComments %>" />

						<aui:input name="preferences--showCompleteYourProfile--" type="checkbox" value="<%= showCompleteYourProfile %>" />

						<aui:input name="preferences--showEmailAddress--" type="checkbox" value="<%= showEmailAddress %>" />

						<aui:input name="preferences--showInstantMessenger--" type="checkbox" value="<%= showInstantMessenger %>" />

						<aui:input name="preferences--showPhones--" type="checkbox" value="<%= showPhones %>" />

						<aui:input label="show-sms" name="preferences--showSMS--" type="checkbox" value="<%= showSMS %>" />
					</clay:col>

					<clay:col
						md="6"
					>
						<aui:input name="preferences--showSocialNetwork--" type="checkbox" value="<%= showSocialNetwork %>" />

						<aui:input label="show-icon" name="preferences--showIcon--" type="checkbox" value="<%= showIcon %>" />

						<aui:input name="preferences--showRecentActivity--" type="checkbox" value="<%= showRecentActivity %>" />

						<aui:input label="show-sites" name="preferences--showSites--" type="checkbox" value="<%= showSites %>" />

						<aui:input label="show-tags" name="preferences--showTags--" type="checkbox" value="<%= showTags %>" />

						<aui:input name="preferences--showUsersInformation--" type="checkbox" value="<%= showUsersInformation %>" />

						<aui:input name="preferences--showWebsites--" type="checkbox" value="<%= showWebsites %>" />
					</clay:col>
				</clay:row>
			</liferay-frontend:fieldset>
		</liferay-frontend:edit-form-body>

		<liferay-frontend:edit-form-footer>
			<liferay-frontend:edit-form-buttons />
		</liferay-frontend:edit-form-footer>
	</liferay-frontend:edit-form>
</div>