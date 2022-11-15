<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalDisplayContext.getArticle();
%>

<liferay-portlet:renderURL plid="<%= JournalUtil.getPreviewPlid(article, themeDisplay) %>" varImpl="previewArticleContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcPath" value="/preview_article_content.jsp" />
	<portlet:param name="groupId" value="<%= String.valueOf(article.getGroupId()) %>" />
	<portlet:param name="articleId" value="<%= article.getArticleId() %>" />
	<portlet:param name="version" value="<%= String.valueOf(article.getVersion()) %>" />
</liferay-portlet:renderURL>

<clay:container-fluid>
	<clay:row>
		<clay:col>
			<liferay-journal:journal-article-display
				articleDisplay="<%= journalDisplayContext.getArticleDisplay() %>"
				paginationURL="<%= previewArticleContentURL %>"
				showTitle='<%= GetterUtil.getBoolean(renderRequest.getParameter("showTitle")) %>'
			/>
		</clay:col>
	</clay:row>
</clay:container-fluid>

<liferay-util:include page="/html/common/themes/bottom.jsp" />