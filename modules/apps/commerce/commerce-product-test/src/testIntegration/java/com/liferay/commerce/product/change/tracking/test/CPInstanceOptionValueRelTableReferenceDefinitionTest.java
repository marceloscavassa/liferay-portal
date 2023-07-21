/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.change.tracking.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.test.util.BaseTableReferenceDefinitionTestCase;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPInstanceOptionValueRelLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.simple.constants.SimpleCPTypeConstants;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class CPInstanceOptionValueRelTableReferenceDefinitionTest
	extends BaseTableReferenceDefinitionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				null, RandomTestUtil.randomString(),
				RandomTestUtil.randomString(),
				LocaleUtil.US.getDisplayLanguage(),
				ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		CPDefinition cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			commerceCatalog.getGroupId(), SimpleCPTypeConstants.NAME, false,
			false);

		CPOption cpOption = CPTestUtil.addCPOption(group.getGroupId(), true);

		_cpDefinitionOptionRel = CPTestUtil.addCPDefinitionOptionRel(
			group.getGroupId(), cpDefinition.getCPDefinitionId(),
			cpOption.getCPOptionId());

		_cpDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelLocalService.
				addCPDefinitionOptionValueRel(
					_cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
					RandomTestUtil.randomLocaleStringMap(),
					RandomTestUtil.nextDouble(),
					CPInstanceOptionValueRelTableReferenceDefinitionTest.class.
						getSimpleName(),
					ServiceContextTestUtil.getServiceContext(
						group.getGroupId()));

		_cpInstance = CPTestUtil.addCPInstanceWithRandomSku(group.getGroupId());
	}

	@Override
	protected CTModel<?> addCTModel() throws Exception {
		return _cpInstanceOptionValueRelLocalService.
			addCPInstanceOptionValueRel(
				group.getGroupId(), group.getCompanyId(),
				TestPropsValues.getUserId(),
				_cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
				_cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId(),
				_cpInstance.getCPInstanceId());
	}

	@Inject
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	private CPDefinitionOptionRel _cpDefinitionOptionRel;
	private CPDefinitionOptionValueRel _cpDefinitionOptionValueRel;

	@Inject
	private CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	private CPInstance _cpInstance;

	@Inject
	private CPInstanceOptionValueRelLocalService
		_cpInstanceOptionValueRelLocalService;

}