/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.legacy.query;

import com.liferay.portal.kernel.search.generic.MoreLikeThisQuery;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.internal.util.DocumentTypes;
import com.liferay.portal.search.index.IndexNameBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = MoreLikeThisQueryTranslator.class)
public class MoreLikeThisQueryTranslatorImpl
	implements MoreLikeThisQueryTranslator {

	@Override
	public QueryBuilder translate(MoreLikeThisQuery moreLikeThisQuery) {
		List<MoreLikeThisQueryBuilder.Item> likeItems = new ArrayList<>();

		if (moreLikeThisQuery.getDocumentUIDs() != null) {
			String type = moreLikeThisQuery.getType();

			if (Validator.isNotNull(type)) {
				type = DocumentTypes.LIFERAY;
			}

			for (String documentUID : moreLikeThisQuery.getDocumentUIDs()) {
				MoreLikeThisQueryBuilder.Item moreLikeThisQueryBuilderItem =
					new MoreLikeThisQueryBuilder.Item(
						indexNameBuilder.getIndexName(
							moreLikeThisQuery.getCompanyId()),
						type, documentUID);

				likeItems.add(moreLikeThisQueryBuilderItem);
			}
		}

		List<String> fields = moreLikeThisQuery.getFields();

		String[] fieldsArray = null;

		if (!fields.isEmpty()) {
			fieldsArray = fields.toArray(new String[0]);
		}

		List<String> likeTexts = new ArrayList<>();

		if (Validator.isNotNull(moreLikeThisQuery.getLikeText())) {
			likeTexts.add(moreLikeThisQuery.getLikeText());
		}

		MoreLikeThisQueryBuilder moreLikeThisQueryBuilder =
			QueryBuilders.moreLikeThisQuery(
				fieldsArray, likeTexts.toArray(new String[0]),
				likeItems.toArray(new MoreLikeThisQueryBuilder.Item[0]));

		if (Validator.isNotNull(moreLikeThisQuery.getAnalyzer())) {
			moreLikeThisQueryBuilder.analyzer(moreLikeThisQuery.getAnalyzer());
		}

		if (moreLikeThisQuery.getMaxDocFrequency() != null) {
			moreLikeThisQueryBuilder.maxDocFreq(
				moreLikeThisQuery.getMaxDocFrequency());
		}

		if (moreLikeThisQuery.getMaxQueryTerms() != null) {
			moreLikeThisQueryBuilder.maxQueryTerms(
				moreLikeThisQuery.getMaxQueryTerms());
		}

		if (moreLikeThisQuery.getMaxWordLength() != null) {
			moreLikeThisQueryBuilder.maxWordLength(
				moreLikeThisQuery.getMaxWordLength());
		}

		if (moreLikeThisQuery.getMinDocFrequency() != null) {
			moreLikeThisQueryBuilder.minDocFreq(
				moreLikeThisQuery.getMinDocFrequency());
		}

		if (Validator.isNotNull(moreLikeThisQuery.getMinShouldMatch())) {
			moreLikeThisQueryBuilder.minimumShouldMatch(
				moreLikeThisQuery.getMinShouldMatch());
		}

		if (moreLikeThisQuery.getMinTermFrequency() != null) {
			moreLikeThisQueryBuilder.minTermFreq(
				moreLikeThisQuery.getMinTermFrequency());
		}

		if (moreLikeThisQuery.getMinWordLength() != null) {
			moreLikeThisQueryBuilder.minWordLength(
				moreLikeThisQuery.getMinWordLength());
		}

		if (!moreLikeThisQuery.isDefaultBoost()) {
			moreLikeThisQueryBuilder.boost(moreLikeThisQuery.getBoost());
		}

		Collection<String> stopWords = moreLikeThisQuery.getStopWords();

		if (!stopWords.isEmpty()) {
			moreLikeThisQueryBuilder.stopWords(
				stopWords.toArray(new String[0]));
		}

		if (moreLikeThisQuery.getTermBoost() != null) {
			moreLikeThisQueryBuilder.boostTerms(
				moreLikeThisQuery.getTermBoost());
		}

		if (moreLikeThisQuery.isIncludeInput() != null) {
			moreLikeThisQueryBuilder.include(
				moreLikeThisQuery.isIncludeInput());
		}

		return moreLikeThisQueryBuilder;
	}

	@Reference
	protected IndexNameBuilder indexNameBuilder;

}