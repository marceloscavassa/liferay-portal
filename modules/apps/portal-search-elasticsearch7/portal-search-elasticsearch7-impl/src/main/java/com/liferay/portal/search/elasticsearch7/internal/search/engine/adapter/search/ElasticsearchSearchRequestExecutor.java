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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.portal.search.engine.adapter.search.ClosePointInTimeRequest;
import com.liferay.portal.search.engine.adapter.search.ClosePointInTimeResponse;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchResponse;
import com.liferay.portal.search.engine.adapter.search.OpenPointInTimeRequest;
import com.liferay.portal.search.engine.adapter.search.OpenPointInTimeResponse;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "search.engine.impl=Elasticsearch",
	service = SearchRequestExecutor.class
)
public class ElasticsearchSearchRequestExecutor
	implements SearchRequestExecutor {

	@Override
	public ClosePointInTimeResponse executeSearchRequest(
		ClosePointInTimeRequest closePointInTimeRequest) {

		return _closePointInTimeRequestExecutor.execute(
			closePointInTimeRequest);
	}

	@Override
	public CountSearchResponse executeSearchRequest(
		CountSearchRequest countSearchRequest) {

		return _countSearchRequestExecutor.execute(countSearchRequest);
	}

	@Override
	public MultisearchSearchResponse executeSearchRequest(
		MultisearchSearchRequest multisearchSearchRequest) {

		return _multisearchSearchRequestExecutor.execute(
			multisearchSearchRequest);
	}

	@Override
	public OpenPointInTimeResponse executeSearchRequest(
		OpenPointInTimeRequest openPointInTimeRequest) {

		return _openPointInTimeRequestExecutor.execute(openPointInTimeRequest);
	}

	@Override
	public SearchSearchResponse executeSearchRequest(
		SearchSearchRequest searchSearchRequest) {

		return _searchSearchRequestExecutor.execute(searchSearchRequest);
	}

	@Override
	public SuggestSearchResponse executeSearchRequest(
		SuggestSearchRequest suggestSearchRequest) {

		return _suggestSearchRequestExecutor.execute(suggestSearchRequest);
	}

	@Reference
	private ClosePointInTimeRequestExecutor _closePointInTimeRequestExecutor;

	@Reference
	private CountSearchRequestExecutor _countSearchRequestExecutor;

	@Reference
	private MultisearchSearchRequestExecutor _multisearchSearchRequestExecutor;

	@Reference
	private OpenPointInTimeRequestExecutor _openPointInTimeRequestExecutor;

	@Reference
	private SearchSearchRequestExecutor _searchSearchRequestExecutor;

	@Reference
	private SuggestSearchRequestExecutor _suggestSearchRequestExecutor;

}