/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.legacy.query;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.FilterTranslator;
import com.liferay.portal.kernel.search.query.QueryVisitor;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(service = BooleanQueryTranslator.class)
public class BooleanQueryTranslatorImpl implements BooleanQueryTranslator {

	@Override
	public QueryBuilder translate(
		BooleanQuery booleanQuery, QueryVisitor<QueryBuilder> queryVisitor) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		List<BooleanClause<Query>> clauses = booleanQuery.clauses();

		for (BooleanClause<Query> clause : clauses) {
			_addClause(clause, boolQueryBuilder, queryVisitor);
		}

		if (!booleanQuery.isDefaultBoost()) {
			boolQueryBuilder.boost(booleanQuery.getBoost());
		}

		BooleanFilter booleanFilter = booleanQuery.getPreBooleanFilter();

		if (booleanFilter == null) {
			return boolQueryBuilder;
		}

		// LPS-86537 The following conversion is present for backwards
		// compatibility with how Liferay's Indexer frameworks handles queries.
		// Ideally, we do not wrap the BooleanQuery with another BooleanQuery.

		BoolQueryBuilder wrapperBoolQueryBuilder = QueryBuilders.boolQuery();

		if (!clauses.isEmpty()) {
			wrapperBoolQueryBuilder.must(boolQueryBuilder);
		}

		QueryBuilder filterQueryBuilder = filterTranslator.translate(
			booleanFilter, null);

		wrapperBoolQueryBuilder.filter(filterQueryBuilder);

		return wrapperBoolQueryBuilder;
	}

	@Reference(target = "(search.engine.impl=Elasticsearch)")
	protected FilterTranslator<QueryBuilder> filterTranslator;

	private void _addClause(
		BooleanClause<Query> clause, BoolQueryBuilder boolQuery,
		QueryVisitor<QueryBuilder> queryVisitor) {

		BooleanClauseOccur booleanClauseOccur = clause.getBooleanClauseOccur();

		Query query = clause.getClause();

		QueryBuilder queryBuilder = query.accept(queryVisitor);

		if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
			boolQuery.must(queryBuilder);

			return;
		}

		if (booleanClauseOccur.equals(BooleanClauseOccur.MUST_NOT)) {
			boolQuery.mustNot(queryBuilder);

			return;
		}

		if (booleanClauseOccur.equals(BooleanClauseOccur.SHOULD)) {
			boolQuery.should(queryBuilder);

			return;
		}

		throw new IllegalArgumentException();
	}

}