/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.reports.layout.display.page.internal.request.attributes.contributor.test;

import com.liferay.analytics.reports.constants.AnalyticsReportsWebKeys;
import com.liferay.analytics.reports.info.item.ClassNameClassPKInfoItemIdentifier;
import com.liferay.analytics.reports.layout.display.page.internal.test.MockObject;
import com.liferay.analytics.reports.layout.display.page.internal.test.layout.display.page.MockObjectLayoutDisplayPageObjectProvider;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.info.display.request.attributes.contributor.InfoDisplayRequestAttributesContributor;
import com.liferay.info.item.InfoItemReference;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.constants.LayoutDisplayPageWebKeys;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class
	AnalyticsReportsLayoutDisplayPageInfoDisplayRequestAttributesContributorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddAttributes() {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		_infoDisplayRequestAttributesContributor.addAttributes(
			mockHttpServletRequest);

		Assert.assertNull(
			mockHttpServletRequest.getAttribute(
				AnalyticsReportsWebKeys.ANALYTICS_INFO_ITEM_REFERENCE));
	}

	@Test
	public void testAddAttributesWithLayoutDisplayPageObjectProvider() {
		ClassName className = _classNameLocalService.addClassName(
			MockObject.class.getName());

		try {
			MockHttpServletRequest mockHttpServletRequest =
				new MockHttpServletRequest();

			mockHttpServletRequest.setAttribute(
				LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_OBJECT_PROVIDER,
				new MockObjectLayoutDisplayPageObjectProvider(
					className.getClassNameId()));

			_infoDisplayRequestAttributesContributor.addAttributes(
				mockHttpServletRequest);

			InfoItemReference infoItemReference =
				(InfoItemReference)mockHttpServletRequest.getAttribute(
					AnalyticsReportsWebKeys.ANALYTICS_INFO_ITEM_REFERENCE);

			Assert.assertEquals(
				LayoutDisplayPageObjectProvider.class.getName(),
				infoItemReference.getClassName());

			ClassNameClassPKInfoItemIdentifier
				classNameClassPKInfoItemIdentifier =
					(ClassNameClassPKInfoItemIdentifier)
						infoItemReference.getInfoItemIdentifier();

			Assert.assertEquals(
				MockObject.class.getName(),
				classNameClassPKInfoItemIdentifier.getClassName());
			Assert.assertEquals(
				0L, classNameClassPKInfoItemIdentifier.getClassPK());
		}
		finally {
			_classNameLocalService.deleteClassName(className);
		}
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject(
		filter = "component.name=*.AnalyticsReportsLayoutDisplayPageInfoDisplayRequestAttributesContributor"
	)
	private InfoDisplayRequestAttributesContributor
		_infoDisplayRequestAttributesContributor;

}