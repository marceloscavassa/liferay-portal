<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="form-group form-group-sm">
	<label class="control-label <%= fragmentCollectionFilterCategoryDisplayContext.isShowLabel() ? "" : "sr-only" %>">
		<%= fragmentCollectionFilterCategoryDisplayContext.getLabel() %>
	</label>

	<div>
		<button
		class="dropdown-toggle form-control form-control-select form-control-sm text-left w-100" disabled="disabled"><liferay-ui:message key="select" /></button
	>
		<react:component
			module="js/SelectCategory.es"
			props="<%= fragmentCollectionFilterCategoryDisplayContext.getProps() %>"
		/>
	</div>
</div>