/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.source.formatter.check;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tamyris Bernardo
 */
public class UpgradeGetPortletGroupIdMethodCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (!fileName.endsWith(".java") && !fileName.endsWith(".jsp") &&
			!fileName.endsWith(".jspf") && !fileName.endsWith(".ftl")) {

			return content;
		}

		String newContent = content;

		Matcher getPortletGroupIdMatcher = _getPortletGroupIdPattern.matcher(
			content);

		while (getPortletGroupIdMatcher.find()) {
			String variableName = getPortletGroupIdMatcher.group(1);

			if (!fileName.endsWith(".java") &&
				!variableName.equals("themeDisplay")) {

				continue;
			}

			String methodCall = getPortletGroupIdMatcher.group(0);

			if (fileName.endsWith(".java") &&
				!hasClassOrVariableName(
					"ThemeDisplay", newContent, methodCall)) {

				continue;
			}

			newContent = StringUtil.replace(
				newContent, methodCall,
				StringUtil.replace(
					methodCall, ".getPortletGroupId()", ".getScopeGroupId()"));
		}

		return newContent;
	}

	private static final Pattern _getPortletGroupIdPattern = Pattern.compile(
		"(\\w+)\\.getPortletGroupId\\(\\s*\\)");

}