<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<clay:container-fluid
	id='<%= liferayPortletResponse.getNamespace() + "newPublicationContainer" %>'
>
	<liferay-util:include page="/new_publication/publish_layouts.jsp" servletContext="<%= application %>" />
</clay:container-fluid>