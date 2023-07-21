<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AddedAllCommerceDiscountRuleDisplayContext addedAllCommerceDiscountRuleDisplayContext = (AddedAllCommerceDiscountRuleDisplayContext)request.getAttribute("view.jsp-addedAllCommerceDiscountRuleDisplayContext");

CommerceDiscountRule commerceDiscountRule = addedAllCommerceDiscountRuleDisplayContext.getCommerceDiscountRule();
%>

<%@ include file="/commerce_discounts/rule/type/products.jspf" %>