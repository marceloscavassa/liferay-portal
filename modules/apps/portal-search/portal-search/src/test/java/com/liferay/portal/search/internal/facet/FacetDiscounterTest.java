/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;
import com.liferay.portal.kernel.search.facet.RangeFacet;
import com.liferay.portal.kernel.search.facet.SimpleFacet;
import com.liferay.portal.kernel.search.facet.collector.DefaultTermCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class FacetDiscounterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testMultiValueFacet() {
		Facet facet = new MultiValueFacet(null);

		_populate(facet, _toTerm("a", 10), _toTerm("b", 5), _toTerm("c", 2));

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		_discount(facetDiscounter, _createDocument(new String[] {"a", "c"}));

		_assertFrequencies(facet, "[a=9, b=5, c=1]");
	}

	@Test
	public void testOrderStillDescendingAfterSkewedDecrements() {
		Facet facet = new SimpleFacet(null);

		_populate(facet, _toTerm("a", 9), _toTerm("b", 8), _toTerm("c", 6));

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		_discount(facetDiscounter, "a", "a", "b", "b", "b");

		_assertFrequencies(facet, "[a=7, c=6, b=5]");
	}

	@Test
	public void testRangeFacet() {
		Facet facet = new RangeFacet(null);

		_populate(facet, _toTerm("[0 TO 5]", 3), _toTerm("[0 TO 9]", 3));

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		_discount(facetDiscounter, "2", "7");

		_assertFrequencies(facet, "[[0 TO 5]=2, [0 TO 9]=1]");
	}

	@Test
	public void testSimpleFacet() {
		Facet facet = new SimpleFacet(null);

		_populate(facet, _toTerm("a", 10), _toTerm("b", 5), _toTerm("c", 2));

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		_discount(facetDiscounter, "a", "b", "c");

		_assertFrequencies(facet, "[a=9, b=4, c=1]");
	}

	@Test
	public void testZeroedTermIsRemoved() {
		SimpleFacet facet = new SimpleFacet(null);

		_populate(facet, _toTerm("public", 1000), _toTerm("secret", 1));

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		_discount(facetDiscounter, "secret");

		_assertFrequencies(facet, "[public=1000]");
	}

	private void _assertFrequencies(Facet facet, String expected) {
		FacetsAssert.assertFrequencies(_FIELD_NAME, facet, expected);
	}

	private Document _createDocument(String term) {
		Document document = Mockito.mock(Document.class);

		Mockito.doReturn(
			new Field(_FIELD_NAME, term)
		).when(
			document
		).getField(
			_FIELD_NAME
		);

		return document;
	}

	private Document _createDocument(String[] terms) {
		Document document = Mockito.mock(Document.class);

		Mockito.doReturn(
			new Field(_FIELD_NAME, terms)
		).when(
			document
		).getField(
			_FIELD_NAME
		);

		return document;
	}

	private SimpleFacetCollector _createFacetCollector(
		TermCollector... termCollectors) {

		return new SimpleFacetCollector(
			_FIELD_NAME, Arrays.asList(termCollectors));
	}

	private void _discount(
		FacetDiscounter facetDiscounter, Document... documents) {

		_discount(facetDiscounter, Arrays.asList(documents));
	}

	private void _discount(
		FacetDiscounter facetDiscounter, List<Document> documents) {

		facetDiscounter.discount(documents);
	}

	private void _discount(FacetDiscounter facetDiscounter, String... terms) {
		Document[] documents = new Document[terms.length];

		for (int i = 0; i < documents.length; i++) {
			documents[i] = _createDocument(terms[i]);
		}

		_discount(facetDiscounter, documents);
	}

	private void _populate(Facet facet, TermCollector... termCollectors) {
		facet.setFieldName(_FIELD_NAME);

		facet.setFacetCollector(_createFacetCollector(termCollectors));
	}

	private TermCollector _toTerm(String term, int frequency) {
		return new DefaultTermCollector(term, frequency);
	}

	private static final String _FIELD_NAME = RandomTestUtil.randomString();

}