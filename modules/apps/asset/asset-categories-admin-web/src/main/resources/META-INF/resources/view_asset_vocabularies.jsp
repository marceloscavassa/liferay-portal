<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new AssetVocabulariesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, assetCategoriesDisplayContext) %>"
/>

<aui:form cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:search-container
		id="assetVocabularies"
		searchContainer="<%= assetCategoriesDisplayContext.getVocabulariesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetVocabulary"
			keyProperty="vocabularyId"
			modelVar="vocabulary"
		>
			<c:choose>
				<c:when test='<%= Objects.equals(assetCategoriesDisplayContext.getDisplayStyle(), "descriptive") %>'>
					<liferay-ui:search-container-column-icon
						icon="vocabulary"
						toggleRowChecker="<%= true %>"
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<span class="text-default">
							<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - vocabulary.getCreateDate().getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
						</span>

						<h2 class="h5">
							<%= HtmlUtil.escape(vocabulary.getTitle(locale)) %>
						</h2>

						<span class="text-default">
							<%= HtmlUtil.escape(vocabulary.getDescription(locale)) %>
						</span>
						<span class="text-default">
							<strong><liferay-ui:message key="number-of-categories" /></strong>:

							<%= vocabulary.getCategoriesCount() %>
						</span>
						<span class="text-default">
							<strong><liferay-ui:message key="asset-type" /></strong>: <%= assetCategoriesDisplayContext.getAssetType(vocabulary) %>
						</span>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= Objects.equals(assetCategoriesDisplayContext.getDisplayStyle(), "list") %>'>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200 table-title"
						name="name"
						value="<%= HtmlUtil.escape(vocabulary.getTitle(locale)) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand table-cell-minw-200"
						name="description"
						value="<%= HtmlUtil.escape(vocabulary.getDescription(locale)) %>"
					/>

					<liferay-ui:search-container-column-date
						cssClass="table-cell-ws-nowrap"
						name="create-date"
						property="createDate"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-column-text-center"
						name="number-of-categories"
						value="<%= String.valueOf(vocabulary.getCategoriesCount()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-expand-smallest table-cell-minw-150"
						name="asset-type"
						value="<%= assetCategoriesDisplayContext.getAssetType(vocabulary) %>"
					/>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= assetCategoriesDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>