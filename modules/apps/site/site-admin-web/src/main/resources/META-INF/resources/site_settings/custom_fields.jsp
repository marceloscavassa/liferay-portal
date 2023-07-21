<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-expando:custom-attribute-list
	className="<%= Group.class.getName() %>"
	classPK='<%= (long)request.getAttribute("site.liveGroupId") %>'
	editable="<%= true %>"
	label="<%= true %>"
/>