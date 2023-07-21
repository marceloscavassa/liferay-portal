<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

long accountRoleId = ParamUtil.getLong(request, "accountRoleId");

AccountRole accountRole = AccountRoleLocalServiceUtil.fetchAccountRole(accountRoleId);

Role role = null;

if (accountRole != null) {
	role = accountRole.getRole();
}
%>

<portlet:actionURL name="/account_admin/edit_account_role" var="editAccountRoleURL" />

<liferay-frontend:edit-form
	action="<%= editAccountRoleURL %>"
>
	<portlet:renderURL var="redirect">
		<portlet:param name="mvcPath" value="/account_entries_admin/edit_account_role.jsp" />
		<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		<portlet:param name="accountRoleId" value="<%= String.valueOf(accountRoleId) %>" />
	</portlet:renderURL>

	<liferay-frontend:edit-form-body>
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (role == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="accountEntryId" type="hidden" value="<%= String.valueOf(accountEntryId) %>" />
		<aui:input name="accountRoleId" type="hidden" value="<%= accountRoleId %>" />

		<aui:model-context bean="<%= role %>" model="<%= Role.class %>" />

		<aui:input helpMessage="title-field-help" name="title" />
		<aui:input name="description" />

		<liferay-ui:error exception="<%= DuplicateRoleException.class %>" message="please-enter-a-unique-name" />

		<%
		String nameLabel = LanguageUtil.get(request, "role-key");
		%>

		<liferay-ui:error exception="<%= RoleNameException.class %>">
			<p>
				<liferay-ui:message arguments="<%= new String[] {nameLabel, RoleConstants.getNameGeneralRestrictions(locale, PropsValues.ROLES_NAME_ALLOW_NUMERIC), RoleConstants.NAME_RESERVED_WORDS} %>" key="the-x-cannot-be-x-or-a-reserved-word-such-as-x" />
			</p>

			<p>
				<liferay-ui:message arguments="<%= new String[] {nameLabel, RoleConstants.NAME_INVALID_CHARACTERS} %>" key="the-x-cannot-contain-the-following-invalid-characters-x" />
			</p>
		</liferay-ui:error>

		<c:choose>
			<c:when test="<%= (role != null) && AccountRoleConstants.isRequiredRole(role) %>">
				<aui:input disabled="<%= true %>" helpMessage="key-field-help" label="key" name="viewNameField" type="text" value="<%= role.getName() %>" />
				<aui:input name="name" type="hidden" />
			</c:when>
			<c:otherwise>
				<aui:input helpMessage="key-field-help" label="key" name="name" />
			</c:otherwise>
		</c:choose>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>

		<%
		String backURL = ParamUtil.getString(request, "backURL");

		if (Validator.isNull(backURL)) {
			backURL = PortletURLBuilder.createRenderURL(
				renderResponse
			).setMVCRenderCommandName(
				"/account_admin/edit_account_entry"
			).setParameter(
				"accountEntryId", accountEntryId
			).setParameter(
				"screenNavigationCategoryKey", AccountScreenNavigationEntryConstants.CATEGORY_KEY_ROLES
			).buildString();
		}
		%>

		<liferay-frontend:edit-form-buttons
			redirect="<%= backURL %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<c:if test="<%= role == null %>">
	<aui:script require="frontend-js-web/index as frontendJsWeb">
		var {debounce} = frontendJsWeb;

		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var nameInput = form.querySelector('#<portlet:namespace />name');
			var titleInput = form.querySelector('#<portlet:namespace />title');

			if (nameInput && titleInput) {
				var handleOnTitleInput = function (event) {
					var value = event.target.value;

					if (nameInput.hasAttribute('maxLength')) {
						value = value.substring(0, nameInput.getAttribute('maxLength'));
					}

					nameInput.value = value;
				};

				titleInput.addEventListener('input', debounce(handleOnTitleInput, 200));
			}
		}
	</aui:script>
</c:if>