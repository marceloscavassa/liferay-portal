/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentRequest;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.index.SyncReindexManager;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.DateRangeTermQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;

import java.text.Format;

import java.util.Date;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(service = SyncReindexManager.class)
public class SyncReindexManagerImpl implements SyncReindexManager {

	@Override
	public void deleteStaleDocuments(
		long companyId, Date date, Set<String> classNames) {

		deleteStaleDocuments(
			_indexNameBuilder.getIndexName(companyId), date, classNames);
	}

	@Override
	public void deleteStaleDocuments(
		String indexName, Date date, Set<String> classNames) {

		BooleanQuery booleanQuery = _queries.booleanQuery();

		if (SetUtil.isNotEmpty(classNames)) {
			TermsQuery termsQuery = _queries.terms(Field.ENTRY_CLASS_NAME);

			termsQuery.addValues(classNames.toArray());

			booleanQuery.addFilterQueryClauses(termsQuery);
		}

		Format format = _fastDateFormatFactory.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		DateRangeTermQuery dateRangeTermQuery = _queries.dateRangeTerm(
			"timestamp", false, false, null, format.format(date));

		booleanQuery.addFilterQueryClauses(dateRangeTermQuery);

		DeleteByQueryDocumentRequest deleteByQueryDocumentRequest =
			new DeleteByQueryDocumentRequest(booleanQuery, indexName);

		_searchEngineAdapter.execute(deleteByQueryDocumentRequest);
	}

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}