/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.web.internal.upgrade.v1_0_0;

import com.liferay.bookmarks.constants.BookmarksPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseAdminPortletsUpgradeProcess;

/**
 * @author Miguel Pastor
 */
public class UpgradeAdminPortlets extends BaseAdminPortletsUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccessInControlPanelPermission(
			BookmarksPortletKeys.BOOKMARKS,
			BookmarksPortletKeys.BOOKMARKS_ADMIN);
	}

}