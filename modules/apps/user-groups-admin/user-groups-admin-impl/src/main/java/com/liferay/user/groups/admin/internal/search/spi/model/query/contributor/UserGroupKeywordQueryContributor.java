/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.groups.admin.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.ExpandoQueryContributor;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.UserGroup",
	service = KeywordQueryContributor.class
)
public class UserGroupKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	@SuppressWarnings("unchecked")
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		try {
			queryHelper.addSearchTerm(
				booleanQuery, searchContext, "description", false);
			queryHelper.addSearchTerm(
				booleanQuery, searchContext, "name", false);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			String expandoAttributes = (String)params.get("expandoAttributes");

			if (Validator.isNotNull(expandoAttributes)) {
				_addSearchExpando(
					booleanQuery, searchContext, expandoAttributes);
			}
		}
	}

	@Reference
	protected QueryHelper queryHelper;

	private Map<String, Query> _addSearchExpando(
		BooleanQuery searchQuery, SearchContext searchContext,
		String keywords) {

		_expandoQueryContributor.contribute(
			keywords, searchQuery, new String[] {UserGroup.class.getName()},
			searchContext);

		return new HashMap<>();
	}

	@Reference
	private ExpandoQueryContributor _expandoQueryContributor;

}