/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.internal.upgrade.v4_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.upgrade.UpgradeStep;

/**
 * @author Matthew Kong
 */
public class UpgradeFaroProjectUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSBFaro_FaroProject set lastAccessTime = " +
				System.currentTimeMillis());
	}

	@Override
	protected UpgradeStep[] getPreUpgradeSteps() {
		return new UpgradeStep[] {
			UpgradeProcessFactory.addColumns(
				"OSBFaro_FaroProject", "lastAccessTime LONG")
		};
	}

}