/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.scripting.executor;

import java.util.Map;
import java.util.Set;

/**
 * @author Feliphe Marinho
 */
public interface ObjectScriptingExecutor {

	public Map<String, Object> execute(
		Map<String, Object> inputObjects, Set<String> outputNames,
		String script);

}