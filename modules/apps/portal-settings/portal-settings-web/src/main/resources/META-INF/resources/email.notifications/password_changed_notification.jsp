<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String adminEmailFromName = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
String adminEmailFromAddress = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

PortletPreferences companyPortletPreferences = PrefsPropsUtil.getPreferences(company.getCompanyId());

String sectionName = "password-changed-notification";
%>

<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:error key="emailPasswordChangedSubject" message="please-enter-a-valid-subject" />
<liferay-ui:error key="emailPasswordChangedBody" message="please-enter-a-valid-body" />

<aui:field-wrapper label="email-without-password">
	<liferay-frontend:email-notification-settings
		emailBody='<%= LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordChangedBody", "settings", StringUtil.read(PortalClassLoaderUtil.getClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_CHANGED_BODY)) %>'
		emailParam="adminEmailPasswordChanged"
		emailSubject='<%= LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordChangedSubject", "settings", StringUtil.read(PortalClassLoaderUtil.getClassLoader(), PropsValues.ADMIN_EMAIL_PASSWORD_CHANGED_SUBJECT)) %>'
		fieldPrefix="settings"
		showEmailEnabled="<%= false %>"
	/>
</aui:field-wrapper>

<aui:fieldset cssClass="definition-of-terms email-verification terms" label="definition-of-terms">
	<%@ include file="/email.notifications/definition_of_terms.jspf" %>
</aui:fieldset>

<%
String adminEmailPasswordSentBody = LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordSentBody", "preferences", StringPool.BLANK);
String adminEmailPasswordSentSubject = LocalizationUtil.getLocalizationXmlFromPreferences(companyPortletPreferences, renderRequest, "adminEmailPasswordSentSubject", "preferences", StringPool.BLANK);
%>

<c:if test="<%= Validator.isNotNull(adminEmailPasswordSentSubject) || Validator.isNotNull(adminEmailPasswordSentBody) %>">
	<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="legacy-template-no-longer-used" markupView="lexicon">
		<aui:input checked="<%= false %>" label="discard" name="discardLegacyKey" type="checkbox" value="adminEmailPasswordSentSubject,adminEmailPasswordSentBody" />

		<div class="alert alert-info">
			<liferay-ui:message key="sending-of-passwords-by-email-is-no-longer-supported-the-template-below-is-not-used-and-can-be-discarded" />
		</div>

		<c:if test="<%= Validator.isNotNull(adminEmailPasswordSentSubject) %>">
			<aui:field-wrapper label="subject">
				<liferay-ui:input-localized
					fieldPrefix="settings"
					fieldPrefixSeparator="--"
					name="adminEmailPasswordSentSubject"
					readonly="<%= true %>"
					xml="<%= adminEmailPasswordSentSubject %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<c:if test="<%= Validator.isNotNull(adminEmailPasswordSentBody) %>">
			<aui:field-wrapper label="body">
				<liferay-ui:input-localized
					fieldPrefix="settings"
					fieldPrefixSeparator="--"
					name="adminEmailPasswordSentBody"
					readonly="<%= true %>"
					type="textarea"
					xml="<%= adminEmailPasswordSentBody %>"
				/>
			</aui:field-wrapper>
		</c:if>
	</aui:fieldset>
</c:if>