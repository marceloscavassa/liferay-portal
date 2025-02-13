/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.ddm.internal;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Locale;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Julius Lee
 */
public class DDMHelperImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testCreateDDMFormRuleInputMapping() {
		DDMForm ddmForm = new DDMForm();

		ddmForm.addDDMFormField(new DDMFormField("DDMTest1", ""));
		ddmForm.addDDMFormField(new DDMFormField("DDMTest2", ""));
		ddmForm.addDDMFormField(new DDMFormField("DDMTest3", ""));

		long userId = 1L;
		long commerceAccountId = 2L;
		long groupId = 3L;
		long cpDefinitionId = 4L;
		long companyId = 5L;

		Assert.assertEquals(
			StringUtil.merge(
				new String[] {
					"'locale=en_US'", "'userId=1'", "'commerceAccountId=2'",
					"'groupId=3'", "'cpDefinitionId=4'", "'companyId=5'",
					"'DDMTest1=', getValue('DDMTest1')",
					"'DDMTest2=', getValue('DDMTest2')",
					"'DDMTest3=', getValue('DDMTest3')"
				},
				", ';',"),
			ReflectionTestUtil.invoke(
				new DDMHelperImpl(), "_createDDMFormRuleInputMapping",
				new Class<?>[] {
					DDMForm.class, long.class, long.class, long.class,
					long.class, long.class, Locale.class
				},
				ddmForm, groupId, commerceAccountId, cpDefinitionId, companyId,
				userId, LocaleUtil.US));
	}

}