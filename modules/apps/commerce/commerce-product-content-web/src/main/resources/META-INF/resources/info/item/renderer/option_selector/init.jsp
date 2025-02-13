<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.util.CPContentHelper" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.taglib.servlet.PipingServletResponseFactory" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
long accountId = (long)request.getAttribute("liferay-commerce:option-selector:accountId");
long channelId = (long)request.getAttribute("liferay-commerce:option-selector:channelId");
long cpDefinitionId = (long)request.getAttribute("liferay-commerce:option-selector:cpDefinitionId");
String namespace = (String)request.getAttribute("liferay-commerce:option-selector:namespace");
long productId = (long)request.getAttribute("liferay-commerce:option-selector:productId");

CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);
%>