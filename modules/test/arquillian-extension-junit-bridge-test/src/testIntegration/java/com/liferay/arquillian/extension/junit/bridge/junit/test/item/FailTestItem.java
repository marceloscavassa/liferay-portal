/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.arquillian.extension.junit.bridge.junit.test.item;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Matthew Tambara
 */
@RunWith(Arquillian.class)
public class FailTestItem {

	@Test
	public void testFail() {
		Assert.assertTrue(false);
	}

}