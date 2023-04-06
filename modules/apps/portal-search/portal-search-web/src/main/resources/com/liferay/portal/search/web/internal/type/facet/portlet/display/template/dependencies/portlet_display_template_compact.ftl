<@liferay_ui["panel-container"]
	extended=true
	id="${namespace + 'facetAssetEntriesPanelContainer'}"
	markupView="lexicon"
	persistState=true
>
	<@liferay_ui.panel
		collapsible=true
		cssClass="search-facet"
		id="${namespace + 'facetAssetEntriesPanel'}"
		markupView="lexicon"
		persistState=true
		title="type"
	>
		<#if !assetEntriesSearchFacetDisplayContext.isNothingSelected()>
			<@clay.button
				cssClass="btn-unstyled c-mb-4 facet-clear-btn"
				displayType="link"
				id="${namespace + 'facetAssetEntriesClear'}"
				onClick="Liferay.Search.FacetUtil.clearSelections(event);"
			>
				<strong>${languageUtil.get(locale, "clear")}</strong>
			</@clay.button>
		</#if>

		<ul class="list-unstyled">
			<#if entries?has_content>
				<#list entries as entry>
					<li class="facet-value">
						<@clay.button
							cssClass="btn-unstyled facet-term ${(entry.isSelected())?then('facet-term-selected', 'facet-term-unselected')} term-name"
							data\-term\-id="${entry.getFilterValue()}"
							disabled="true"
							displayType="link"
							onClick="Liferay.Search.FacetUtil.changeSelection(event);"
						>
							<#if entry.isSelected()>
								<strong>${htmlUtil.escape(entry.getBucketText())}</strong>
							<#else>
								${htmlUtil.escape(entry.getBucketText())}
							</#if>

							<#if entry.isFrequencyVisible()>
								<small class="term-count">
									(${entry.getFrequency()})
								</small>
							</#if>
						</@clay.button>
					</li>
				</#list>
			</#if>
		</ul>
	</@>
</@>