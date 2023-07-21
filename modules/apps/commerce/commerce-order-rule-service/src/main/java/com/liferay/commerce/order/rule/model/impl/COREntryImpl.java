/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.model.impl;

import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class COREntryImpl extends COREntryBaseImpl {

	@Override
	public void setTypeSettingsUnicodeProperties(
		UnicodeProperties typeSettingsUnicodeProperties) {

		super.setTypeSettings(typeSettingsUnicodeProperties.toString());
	}

}