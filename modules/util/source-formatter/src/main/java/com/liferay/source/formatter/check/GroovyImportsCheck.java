/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.portal.tools.ImportsFormatter;
import com.liferay.source.formatter.GroovyImportsFormatter;

import java.io.IOException;

/**
 * @author Alan Huang
 */
public class GroovyImportsCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		ImportsFormatter importsFormatter = new GroovyImportsFormatter();

		return importsFormatter.format(content, null, null);
	}

}