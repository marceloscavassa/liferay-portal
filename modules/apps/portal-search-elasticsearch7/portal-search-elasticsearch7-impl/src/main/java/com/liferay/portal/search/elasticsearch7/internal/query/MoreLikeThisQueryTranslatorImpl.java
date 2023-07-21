/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.query;

import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.internal.util.DocumentTypes;
import com.liferay.portal.search.query.MoreLikeThisQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = MoreLikeThisQueryTranslator.class)
public class MoreLikeThisQueryTranslatorImpl
	implements MoreLikeThisQueryTranslator {

	@Override
	public QueryBuilder translate(MoreLikeThisQuery moreLikeThisQuery) {
		List<MoreLikeThisQueryBuilder.Item> likeItems = new ArrayList<>();

		if (SetUtil.isNotEmpty(moreLikeThisQuery.getDocumentIdentifiers())) {
			Set<MoreLikeThisQuery.DocumentIdentifier> documentIdentifiers =
				moreLikeThisQuery.getDocumentIdentifiers();

			documentIdentifiers.forEach(
				documentIdentifier -> {
					String type = documentIdentifier.getType();

					if (Validator.isNull(type)) {
						type = moreLikeThisQuery.getType();
					}

					if (Validator.isNull(type)) {
						type = DocumentTypes.LIFERAY;
					}

					MoreLikeThisQueryBuilder.Item moreLikeThisQueryBuilderItem =
						new MoreLikeThisQueryBuilder.Item(
							documentIdentifier.getIndex(), type,
							documentIdentifier.getId());

					likeItems.add(moreLikeThisQueryBuilderItem);
				});
		}

		List<String> fields = moreLikeThisQuery.getFields();

		String[] fieldsArray = null;

		if (!fields.isEmpty()) {
			fieldsArray = fields.toArray(new String[0]);
		}

		List<String> likeTexts = moreLikeThisQuery.getLikeTexts();

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

}