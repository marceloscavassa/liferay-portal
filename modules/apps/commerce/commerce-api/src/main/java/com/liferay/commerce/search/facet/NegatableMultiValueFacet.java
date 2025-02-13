/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.search.facet;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;
import com.liferay.portal.kernel.search.filter.Filter;

/**
 * @author Andrea Di Giorgi
 */
public class NegatableMultiValueFacet extends MultiValueFacet {

	public NegatableMultiValueFacet(SearchContext searchContext) {
		super(searchContext);
	}

	public boolean isNegated() {
		return _negated;
	}

	public void setNegated(boolean negated) {
		_negated = negated;
	}

	@Override
	protected BooleanClause<Filter> doGetFacetFilterBooleanClause() {
		BooleanClause<Filter> booleanClause =
			super.doGetFacetFilterBooleanClause();

		if (isNegated()) {
			booleanClause = BooleanClauseFactoryUtil.createFilter(
				booleanClause.getClause(), BooleanClauseOccur.MUST_NOT);
		}

		return booleanClause;
	}

	private boolean _negated;

}