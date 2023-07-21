<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.account.model.AccountGroup" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupAccountItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupAccountItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />