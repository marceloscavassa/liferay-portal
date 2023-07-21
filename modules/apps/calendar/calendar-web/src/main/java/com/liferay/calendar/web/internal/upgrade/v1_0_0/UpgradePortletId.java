/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.web.internal.upgrade.v1_0_0;

import com.liferay.calendar.constants.CalendarPortletKeys;
import com.liferay.portal.kernel.upgrade.BasePortletIdUpgradeProcess;

/**
 * @author Marcellus Tavares
 */
public class UpgradePortletId extends BasePortletIdUpgradeProcess {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"1_WAR_calendarportlet", CalendarPortletKeys.CALENDAR}
		};
	}

}