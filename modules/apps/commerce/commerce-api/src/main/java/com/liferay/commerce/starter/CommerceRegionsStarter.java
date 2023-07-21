/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.starter;

import java.util.Locale;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public interface CommerceRegionsStarter {

	public String getKey();

	public String getLabel(Locale locale);

	public void start(long userId) throws Exception;

}