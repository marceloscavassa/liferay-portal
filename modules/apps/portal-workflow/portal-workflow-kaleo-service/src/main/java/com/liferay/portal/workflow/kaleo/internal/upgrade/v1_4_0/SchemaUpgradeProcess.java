/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_4_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Inácio Nery
 */
public class SchemaUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_updateSQL();
	}

	private void _updateSQL() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			String template = StringUtil.read(
				SchemaUpgradeProcess.class.getResourceAsStream(
					"dependencies/update.sql"));

			runSQLTemplateString(template, false);
		}
	}

}