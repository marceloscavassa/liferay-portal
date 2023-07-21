/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.contributor;

import java.util.Map;

/**
 * @author Rodolfo Roza Miranda
 */
public interface ClayTagContextContributor {

	public void populate(Map<String, Object> context);

}