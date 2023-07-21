/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.facet;

import com.liferay.portal.search.elasticsearch7.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.facet.BaseModifiedFacetTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class ModifiedFacetTest extends BaseModifiedFacetTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testSearchEngineDateMath() throws Exception {
		addDocument("17760704000000");
		addDocument("27760704000000");

		String dateMathExpressionWithAlphabeticalOrderSwitched =
			"[now-500y TO now]";

		doTestSearchEngineDateMath(
			dateMathExpressionWithAlphabeticalOrderSwitched, 1);
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return LiferayElasticsearchIndexingFixtureFactory.builder(
		).facetProcessor(
			new RangeFacetProcessor()
		).build();
	}

}