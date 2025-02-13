<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrderTypeQualifiersDisplayContext commerceOrderTypeQualifiersDisplayContext = (CommerceOrderTypeQualifiersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrderType commerceOrderType = commerceOrderTypeQualifiersDisplayContext.getCommerceOrderType();
long commerceOrderTypeId = commerceOrderTypeQualifiersDisplayContext.getCommerceOrderTypeId();

String channelQualifiers = ParamUtil.getString(request, "channelQualifiers", commerceOrderTypeQualifiersDisplayContext.getActiveChannelEligibility());

boolean hasPermission = commerceOrderTypeQualifiersDisplayContext.hasPermission(ActionKeys.UPDATE);
%>

<portlet:actionURL name="/commerce_order_type/edit_commerce_order_type_qualifiers" var="editCommerceOrderTypeQualifiersActionURL" />

<aui:form action="<%= editCommerceOrderTypeQualifiersActionURL %>" cssClass="pt-4" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceOrderType == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="externalReferenceCode" type="hidden" value="<%= commerceOrderType.getExternalReferenceCode() %>" />
	<aui:input name="commerceOrderTypeId" type="hidden" value="<%= commerceOrderTypeId %>" />
	<aui:input name="channelQualifiers" type="hidden" value="<%= channelQualifiers %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />

	<aui:model-context bean="<%= commerceOrderType %>" model="<%= CommerceOrderType.class %>" />

	<commerce-ui:panel
		bodyClasses="flex-fill"
		collapsed="<%= false %>"
		collapsible="<%= false %>"
		title='<%= LanguageUtil.get(request, "channel-eligibility") %>'
	>
		<aui:fieldset markupView="lexicon">
			<aui:input checked='<%= Objects.equals(channelQualifiers, "all") %>' label="all-channels" name="chooseChannelQualifiers" type="radio" value="all" />
			<aui:input checked='<%= Objects.equals(channelQualifiers, "channels") %>' label="specific-channels" name="chooseChannelQualifiers" type="radio" value="channels" />
		</aui:fieldset>
	</commerce-ui:panel>

	<c:if test='<%= Objects.equals(channelQualifiers, "channels") %>'>
		<%@ include file="/commerce_order_type/qualifier/channels.jspf" %>
	</c:if>
</aui:form>

<liferay-frontend:component
	context='<%=
		HashMapBuilder.<String, Object>put(
			"currentURL", currentURL
		).build()
	%>'
	module="js/qualifiers"
/>