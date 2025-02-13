/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.query;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.MatchQuery;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.ZeroTermsQueryOption;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = MatchQueryTranslator.class)
public class MatchQueryTranslatorImpl
	extends BaseMatchQueryTranslatorImpl implements MatchQueryTranslator {

	@Override
	public QueryBuilder translate(MatchQuery matchQuery) {
		String field = matchQuery.getField();

		MatchQuery.Type type = matchQuery.getType();
		Object value = matchQuery.getValue();

		if (value instanceof String) {
			String stringValue = (String)value;

			if (stringValue.startsWith(StringPool.QUOTE) &&
				stringValue.endsWith(StringPool.QUOTE)) {

				type = MatchQuery.Type.PHRASE;

				stringValue = StringUtil.unquote(stringValue);

				if (stringValue.endsWith(StringPool.STAR)) {
					type = MatchQuery.Type.PHRASE_PREFIX;
				}
			}

			if (type == MatchQuery.Type.PHRASE) {
				return _translateMatchPhraseQuery(
					field, stringValue, matchQuery);
			}
			else if (type == MatchQuery.Type.PHRASE_PREFIX) {
				return _translateMatchPhrasePrefixQuery(
					field, stringValue, matchQuery);
			}
		}

		if ((type == null) || (type == MatchQuery.Type.BOOLEAN)) {
			return _translateMatchQuery(field, value, matchQuery);
		}

		throw new IllegalArgumentException("Invalid match query type: " + type);
	}

	private QueryBuilder _translateMatchPhrasePrefixQuery(
		String field, String value, MatchQuery matchQuery) {

		MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder =
			QueryBuilders.matchPhrasePrefixQuery(field, value);

		if (Validator.isNotNull(matchQuery.getAnalyzer())) {
			matchPhrasePrefixQueryBuilder.analyzer(matchQuery.getAnalyzer());
		}

		if (matchQuery.getMaxExpansions() != null) {
			matchPhrasePrefixQueryBuilder.maxExpansions(
				matchQuery.getMaxExpansions());
		}

		if (matchQuery.getSlop() != null) {
			matchPhrasePrefixQueryBuilder.slop(matchQuery.getSlop());
		}

		return matchPhrasePrefixQueryBuilder;
	}

	private QueryBuilder _translateMatchPhraseQuery(
		String field, String value, MatchQuery matchQuery) {

		MatchPhraseQueryBuilder matchPhraseQueryBuilder =
			QueryBuilders.matchPhraseQuery(field, value);

		if (Validator.isNotNull(matchQuery.getAnalyzer())) {
			matchPhraseQueryBuilder.analyzer(matchQuery.getAnalyzer());
		}

		if (matchQuery.getSlop() != null) {
			matchPhraseQueryBuilder.slop(matchQuery.getSlop());
		}

		return matchPhraseQueryBuilder;
	}

	private QueryBuilder _translateMatchQuery(
		String field, Object value, MatchQuery matchQuery) {

		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(
			field, value);

		if (Validator.isNotNull(matchQuery.getAnalyzer())) {
			matchQueryBuilder.analyzer(matchQuery.getAnalyzer());
		}

		if (matchQuery.getCutOffFrequency() != null) {
			matchQueryBuilder.cutoffFrequency(matchQuery.getCutOffFrequency());
		}

		if (matchQuery.getFuzziness() != null) {
			matchQueryBuilder.fuzziness(
				Fuzziness.build(matchQuery.getFuzziness()));
		}

		if (matchQuery.getFuzzyRewriteMethod() != null) {
			String matchQueryFuzzyRewrite = translate(
				matchQuery.getFuzzyRewriteMethod());

			matchQueryBuilder.fuzzyRewrite(matchQueryFuzzyRewrite);
		}

		if (matchQuery.getMaxExpansions() != null) {
			matchQueryBuilder.maxExpansions(matchQuery.getMaxExpansions());
		}

		if (Validator.isNotNull(matchQuery.getMinShouldMatch())) {
			matchQueryBuilder.minimumShouldMatch(
				matchQuery.getMinShouldMatch());
		}

		if (matchQuery.getOperator() != null) {
			Operator operator = translate(matchQuery.getOperator());

			matchQueryBuilder.operator(operator);
		}

		if (matchQuery.getPrefixLength() != null) {
			matchQueryBuilder.prefixLength(matchQuery.getPrefixLength());
		}

		if (matchQuery.getZeroTermsQuery() != null) {
			ZeroTermsQueryOption zeroTermsQueryOption = translate(
				matchQuery.getZeroTermsQuery());

			matchQueryBuilder.zeroTermsQuery(zeroTermsQueryOption);
		}

		if (matchQuery.isFuzzyTranspositions() != null) {
			matchQueryBuilder.fuzzyTranspositions(
				matchQuery.isFuzzyTranspositions());
		}

		if (matchQuery.isLenient() != null) {
			matchQueryBuilder.lenient(matchQuery.isLenient());
		}

		return matchQueryBuilder;
	}

}