/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.failure.message.generator;

import com.liferay.jenkins.results.parser.Build;
import com.liferay.jenkins.results.parser.Dom4JUtil;
import com.liferay.jenkins.results.parser.TopLevelBuild;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

/**
 * @author Peter Yoo
 */
public class PluginFailureMessageGenerator extends BaseFailureMessageGenerator {

	@Override
	public Element getMessageElement(Build build) {
		String buildURL = build.getBuildURL();

		if (!buildURL.contains("portal-acceptance")) {
			return null;
		}

		String jobVariant = build.getParameterValue("JOB_VARIANT");

		if (!buildURL.contains("plugins") && !jobVariant.contains("plugins")) {
			return null;
		}

		String consoleText = build.getConsoleText();

		Matcher matcher = _pattern.matcher(consoleText);

		Element messageElement = Dom4JUtil.getNewElement("div");

		Element paragraphElement = Dom4JUtil.getNewElement("p", messageElement);

		if (matcher.find()) {
			String group = matcher.group(0);

			paragraphElement.addText(group);

			Element pluginsListElement = Dom4JUtil.getNewElement(
				"ul", messageElement);

			int x = matcher.start() + group.length() + 1;

			int count = Integer.parseInt(matcher.group(1));

			for (int i = 0; i < count; i++) {
				Element pluginListItemElement = Dom4JUtil.getNewElement(
					"li", pluginsListElement);

				if (i == 10) {
					pluginListItemElement.addText("...");

					break;
				}

				int y = consoleText.indexOf("\n", x);

				String pluginName = consoleText.substring(x, y);

				pluginListItemElement.addText(
					pluginName.replace("[echo] ", ""));

				x = y + 1;
			}
		}
		else {
			TopLevelBuild topLevelBuild = build.getTopLevelBuild();

			int end = consoleText.indexOf("merge-test-results:");

			Dom4JUtil.addToElement(
				paragraphElement,
				"To include a plugin fix for this pull request, ",
				"please edit your ",
				getGitCommitPluginsAnchorElement(topLevelBuild), ". Click ",
				Dom4JUtil.getNewAnchorElement(_URL_BLOG, "here"),
				" for more details.",
				getConsoleTextSnippetElementByEnd(consoleText, true, end));
		}

		return messageElement;
	}

	private static final String _URL_BLOG =
		"https://in.liferay.com/web/global.engineering/blog/-/blogs" +
			"/new-tests-for-the-pull-request-tester-";

	private static final Pattern _pattern = Pattern.compile(
		"(\\d+) of \\d+ plugins? failed to compile:");

}