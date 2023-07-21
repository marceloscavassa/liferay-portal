<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:success key="categoryAdded" message='<%= GetterUtil.getString(MultiSessionMessages.get(renderRequest, "categoryAdded")) %>' />

<react:component
	module="js/SelectCategory"
	props="<%= assetCategoriesSelectorDisplayContext.getData() %>"
/>