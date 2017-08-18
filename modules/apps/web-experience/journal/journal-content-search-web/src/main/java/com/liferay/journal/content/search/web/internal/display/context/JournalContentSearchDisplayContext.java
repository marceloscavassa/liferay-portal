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

package com.liferay.journal.content.search.web.internal.display.context;

import com.liferay.journal.content.search.web.configuration.JournalContentSearchPortletInstanceConfiguration;
import com.liferay.journal.content.search.web.internal.constants.JournalContentSearchWebKeys;
import com.liferay.journal.content.search.web.internal.util.ContentHits;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.summary.Summary;
import com.liferay.portal.search.summary.SummaryBuilder;
import com.liferay.portal.search.summary.SummaryBuilderFactory;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class JournalContentSearchDisplayContext {

	public JournalContentSearchDisplayContext(
		HttpServletRequest request,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		JournalContentSearchPortletInstanceConfiguration
			journalContentSearchPortletInstanceConfiguration) {

		_request = request;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_journalContentSearchPortletInstanceConfiguration =
			journalContentSearchPortletInstanceConfiguration;

		_summaryBuilderFactory = (SummaryBuilderFactory)request.getAttribute(
			JournalContentSearchWebKeys.SUMMARY_BUILDER_FACTORY);
	}

	public Hits getHits() throws Exception {
		if (_hits != null) {
			return _hits;
		}

		Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(
			JournalArticle.class);

		SearchContext searchContext = SearchContextFactory.getInstance(
			_request);

		searchContext.setGroupIds(null);
		searchContext.setKeywords(getKeywords());

		_hits = indexer.search(searchContext);

		return _hits;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		String defaultKeywords =
			LanguageUtil.get(_request, "search") + StringPool.TRIPLE_PERIOD;

		_keywords = StringUtil.unquote(
			ParamUtil.getString(_request, "keywords", defaultKeywords));

		return _keywords;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletURL renderURL = _liferayPortletResponse.createRenderURL();

		renderURL.setParameter("mvcPath", "/search.jsp");
		renderURL.setParameter("keywords", getKeywords());

		_searchContainer = new SearchContainer(
			_liferayPortletRequest, null, null,
			SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA,
			renderURL, null,
			LanguageUtil.format(
				_request, "no-pages-were-found-that-matched-the-keywords-x",
				"<strong>" + HtmlUtil.escape(getKeywords()) + "</strong>",
				false));

		Hits hits = getHits();

		ContentHits contentHits = new ContentHits();

		contentHits.setShowListed(
			_journalContentSearchPortletInstanceConfiguration.showListed());

		contentHits.recordHits(
			hits, layout.getGroupId(), layout.isPrivateLayout(),
			_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setTotal(hits.getLength());
		_searchContainer.setResults(ListUtil.toList(hits.getDocs()));

		return _searchContainer;
	}

	public Summary getSummary(Document document) throws Exception {
		if (_summary != null) {
			return _summary;
		}

		Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(
			JournalArticle.class);

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		SummaryBuilder summaryBuilder = _summaryBuilderFactory.newInstance();

		summaryBuilder.setHighlight(
			_journalContentSearchPortletInstanceConfiguration.
				enableHighlighting());

		com.liferay.portal.kernel.search.Summary oldSummary =
			indexer.getSummary(
				document, StringPool.BLANK, _liferayPortletRequest,
				_liferayPortletResponse);

		summaryBuilder.setLocale(themeDisplay.getLocale());
		summaryBuilder.setContent(oldSummary.getContent());
		summaryBuilder.setTitle(oldSummary.getTitle());
		summaryBuilder.setMaxContentLength(oldSummary.getMaxContentLength());

		_summary = summaryBuilder.build();

		return _summary;
	}

	private Hits _hits;
	private final JournalContentSearchPortletInstanceConfiguration
		_journalContentSearchPortletInstanceConfiguration;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final HttpServletRequest _request;
	private SearchContainer _searchContainer;
	private Summary _summary;
	private final SummaryBuilderFactory _summaryBuilderFactory;

}