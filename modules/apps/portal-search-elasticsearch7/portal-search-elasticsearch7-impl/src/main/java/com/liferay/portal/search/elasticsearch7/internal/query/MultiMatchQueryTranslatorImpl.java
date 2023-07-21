/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.query;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.MultiMatchQuery;

import java.util.Map;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.ZeroTermsQueryOption;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = MultiMatchQueryTranslator.class)
public class MultiMatchQueryTranslatorImpl
	extends BaseMatchQueryTranslatorImpl implements MultiMatchQueryTranslator {

	@Override
	public QueryBuilder translate(MultiMatchQuery multiMatchQuery) {
		MultiMatchQueryBuilder multiMatchQueryBuilder =
			QueryBuilders.multiMatchQuery(multiMatchQuery.getValue());

		if (Validator.isNotNull(multiMatchQuery.getAnalyzer())) {
			multiMatchQueryBuilder.analyzer(multiMatchQuery.getAnalyzer());
		}

		if (multiMatchQuery.getCutOffFrequency() != null) {
			multiMatchQueryBuilder.cutoffFrequency(
				multiMatchQuery.getCutOffFrequency());
		}

		Map<String, Float> fieldsBoosts = multiMatchQuery.getFieldsBoosts();

		for (Map.Entry<String, Float> entry : fieldsBoosts.entrySet()) {
			Float boost = entry.getValue();
			String field = entry.getKey();

			if (boost == null) {
				multiMatchQueryBuilder.field(field);
			}
			else {
				multiMatchQueryBuilder.field(field, boost);
			}
		}

		if (multiMatchQuery.getFuzziness() != null) {
			multiMatchQueryBuilder.fuzziness(
				Fuzziness.build(multiMatchQuery.getFuzziness()));
		}

		if (multiMatchQuery.getFuzzyRewriteMethod() != null) {
			String multiMatchQueryFuzzyRewriteMethod = translate(
				multiMatchQuery.getFuzzyRewriteMethod());

			multiMatchQueryBuilder.fuzzyRewrite(
				multiMatchQueryFuzzyRewriteMethod);
		}

		if (multiMatchQuery.getMaxExpansions() != null) {
			multiMatchQueryBuilder.maxExpansions(
				multiMatchQuery.getMaxExpansions());
		}

		if (Validator.isNotNull(multiMatchQuery.getMinShouldMatch())) {
			multiMatchQueryBuilder.minimumShouldMatch(
				multiMatchQuery.getMinShouldMatch());
		}

		if (multiMatchQuery.getOperator() != null) {
			Operator matchQueryBuilderOperator = translate(
				multiMatchQuery.getOperator());

			multiMatchQueryBuilder.operator(matchQueryBuilderOperator);
		}

		if (multiMatchQuery.getPrefixLength() != null) {
			multiMatchQueryBuilder.prefixLength(
				multiMatchQuery.getPrefixLength());
		}

		if (multiMatchQuery.getSlop() != null) {
			multiMatchQueryBuilder.slop(multiMatchQuery.getSlop());
		}

		if (multiMatchQuery.getType() != null) {
			MultiMatchQueryBuilder.Type multiMatchQueryBuilderType = translate(
				multiMatchQuery.getType());

			multiMatchQueryBuilder.type(multiMatchQueryBuilderType);
		}

		if (multiMatchQuery.getZeroTermsQuery() != null) {
			ZeroTermsQueryOption zeroTermsQueryOption = translate(
				multiMatchQuery.getZeroTermsQuery());

			multiMatchQueryBuilder.zeroTermsQuery(zeroTermsQueryOption);
		}

		if (multiMatchQuery.isLenient() != null) {
			multiMatchQueryBuilder.lenient(multiMatchQuery.isLenient());
		}

		return multiMatchQueryBuilder;
	}

	protected MultiMatchQueryBuilder.Type translate(
		MultiMatchQuery.Type multiMatchQueryType) {

		if (multiMatchQueryType == MultiMatchQuery.Type.BEST_FIELDS) {
			return MultiMatchQueryBuilder.Type.BEST_FIELDS;
		}
		else if (multiMatchQueryType == MultiMatchQuery.Type.BOOL_PREFIX) {
			return MultiMatchQueryBuilder.Type.BOOL_PREFIX;
		}
		else if (multiMatchQueryType == MultiMatchQuery.Type.CROSS_FIELDS) {
			return MultiMatchQueryBuilder.Type.CROSS_FIELDS;
		}
		else if (multiMatchQueryType == MultiMatchQuery.Type.MOST_FIELDS) {
			return MultiMatchQueryBuilder.Type.MOST_FIELDS;
		}
		else if (multiMatchQueryType == MultiMatchQuery.Type.PHRASE) {
			return MultiMatchQueryBuilder.Type.PHRASE;
		}
		else if (multiMatchQueryType == MultiMatchQuery.Type.PHRASE_PREFIX) {
			return MultiMatchQueryBuilder.Type.PHRASE_PREFIX;
		}

		throw new IllegalArgumentException(
			"Invalid multi match query type: " + multiMatchQueryType);
	}

}