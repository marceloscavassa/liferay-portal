<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
MailTemplatesHelper mailTemplatesHelper = new MailTemplatesHelper(wikiRequestHelper);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL">
	<portlet:param name="serviceName" value="<%= WikiConstants.SERVICE_NAME %>" />
	<portlet:param name="settingsScope" value="group" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<%
	String tabs2Names = "email-from,page-added-email,page-updated-email";

	if (PortalUtil.isRSSFeedsEnabled()) {
		tabs2Names += ",rss";
	}
	%>

	<liferay-frontend:edit-form-body>
		<liferay-ui:tabs
			names="<%= tabs2Names %>"
			refresh="<%= false %>"
		>
			<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
			<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />
			<liferay-ui:error key="emailPageAddedBody" message="please-enter-a-valid-body" />
			<liferay-ui:error key="emailPageAddedSubject" message="please-enter-a-valid-subject" />
			<liferay-ui:error key="emailPageUpdatedBody" message="please-enter-a-valid-body" />
			<liferay-ui:error key="emailPageUpdatedSubject" message="please-enter-a-valid-subject" />

			<liferay-ui:section>
				<liferay-frontend:fieldset>
					<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" value="<%= wikiGroupServiceOverriddenConfiguration.emailFromName() %>" />

					<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" value="<%= wikiGroupServiceOverriddenConfiguration.emailFromAddress() %>" />
				</liferay-frontend:fieldset>

				<liferay-frontend:fieldset
					collapsed="<%= true %>"
					collapsible="<%= true %>"
					label="definition-of-terms"
				>
					<dl>

						<%
						Map<String, String> definitionTerms = mailTemplatesHelper.getEmailFromDefinitionTerms();

						for (Map.Entry<String, String> definitionTerm : definitionTerms.entrySet()) {
						%>

							<dt>
								<%= definitionTerm.getKey() %>
							</dt>
							<dd>
								<%= definitionTerm.getValue() %>
							</dd>

						<%
						}
						%>

					</dl>
				</liferay-frontend:fieldset>
			</liferay-ui:section>

			<%
			Map<String, String> definitionTerms = mailTemplatesHelper.getEmailNotificationDefinitionTerms();
			%>

			<liferay-ui:section>
				<liferay-frontend:email-notification-settings
					emailBodyLocalizedValuesMap="<%= wikiGroupServiceOverriddenConfiguration.emailPageAddedBody() %>"
					emailDefinitionTerms="<%= definitionTerms %>"
					emailEnabled="<%= wikiGroupServiceOverriddenConfiguration.emailPageAddedEnabled() %>"
					emailParam="emailPageAdded"
					emailSubjectLocalizedValuesMap="<%= wikiGroupServiceOverriddenConfiguration.emailPageAddedSubject() %>"
				/>
			</liferay-ui:section>

			<liferay-ui:section>
				<liferay-frontend:email-notification-settings
					emailBodyLocalizedValuesMap="<%= wikiGroupServiceOverriddenConfiguration.emailPageUpdatedBody() %>"
					emailDefinitionTerms="<%= definitionTerms %>"
					emailEnabled="<%= wikiGroupServiceOverriddenConfiguration.emailPageUpdatedEnabled() %>"
					emailParam="emailPageUpdated"
					emailSubjectLocalizedValuesMap="<%= wikiGroupServiceOverriddenConfiguration.emailPageUpdatedSubject() %>"
				/>
			</liferay-ui:section>

			<c:if test="<%= PortalUtil.isRSSFeedsEnabled() %>">
				<liferay-ui:section>
					<liferay-rss:rss-settings
						delta="<%= GetterUtil.getInteger(wikiGroupServiceOverriddenConfiguration.rssDelta()) %>"
						displayStyle="<%= wikiGroupServiceOverriddenConfiguration.rssDisplayStyle() %>"
						enabled="<%= wikiGroupServiceOverriddenConfiguration.enableRss() %>"
						feedType="<%= wikiGroupServiceOverriddenConfiguration.rssFeedType() %>"
					/>
				</liferay-ui:section>
			</c:if>
		</liferay-ui:tabs>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>