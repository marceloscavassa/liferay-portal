<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/input_asset_links/init.jsp" %>

<liferay-util:buffer
	var="removeLinkIcon"
>
	<clay:icon
		symbol="times-circle"
	/>
</liferay-util:buffer>

<clay:dropdown-menu
	additionalProps='<%=
		HashMapBuilder.<String, Object>put(
			"removeIcon", removeLinkIcon
		).build()
	%>'
	aria-label='<%= LanguageUtil.get(request, "select-items") %>'
	cssClass="btn btn-secondary"
	dropdownItems="<%= inputAssetLinksDisplayContext.getActionDropdownItems() %>"
	label='<%= LanguageUtil.get(request, "select") %>'
	propsTransformer="js/InputAssetLinkDropdownDefaultPropsTransformer"
/>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	emptyResultsMessage="none"
	headerNames="title,null"
	total="<%= inputAssetLinksDisplayContext.getAssetLinksCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= inputAssetLinksDisplayContext.getAssetLinks() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.asset.kernel.model.AssetLink"
		keyProperty="entryId2"
		modelVar="assetLink"
	>

		<%
		AssetEntry assetLinkEntry = inputAssetLinksDisplayContext.getAssetLinkEntry(assetLink);
		%>

		<liferay-ui:search-container-column-text
			name="title"
		>
			<h4 class="list-group-title">
				<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>
			</h4>

			<p class="list-group-subtitle">
				<%= inputAssetLinksDisplayContext.getAssetType(assetLinkEntry) %>
			</p>

			<p class="list-group-subtitle">
				<liferay-ui:message key="scope" />: <%= HtmlUtil.escape(inputAssetLinksDisplayContext.getGroupDescriptiveName(assetLinkEntry)) %>
			</p>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text>
			<clay:button
				aria-label='<%= LanguageUtil.get(request, "remove") %>'
				borderless="<%= true %>"
				cssClass="float-right lfr-portal-tooltip modify-link"
				data-rowId="<%= assetLinkEntry.getEntryId() %>"
				displayType="secondary"
				icon="times-circle"
				title='<%= LanguageUtil.get(request, "remove") %>'
				type="button"
			/>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
		searchResultCssClass="table table-autofit table-heading-nowrap"
	/>
</liferay-ui:search-container>

<c:if test="<%= stagingGroupHelper.isLiveGroup(themeDisplay.getScopeGroupId()) %>">
	<span>
		<liferay-ui:message key="related-assets-for-staged-asset-types-can-be-managed-on-the-staging-site" />
	</span>
</c:if>

<aui:input name="assetLinkEntryIds" type="hidden" />

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assetLinksSearchContainer'
	);

	searchContainer.get('contentBox').delegate(
		'click',
		(event) => {
			var link = event.currentTarget;

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>