/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.common;

import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alberto Chaparro
 * @author Miguel Pastor
 */
public class OracleSQLTransformerTest extends BaseSQLTransformerTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testReplaceNotEqualsBlankStringComparison() {
		Assert.assertEquals(
			"SELECT * FROM User_ WHERE emailAddress IS NOT NULL",
			transformSQL("SELECT * FROM User_ WHERE emailAddress != ''"));
	}

	@Override
	protected DBType getDBType() {
		return DBType.ORACLE;
	}

}