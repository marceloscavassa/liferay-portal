/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaTerm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaTermDividersCheck extends BaseJavaTermCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, JavaTerm javaTerm,
		String fileContent) {

		JavaClass javaClass = (JavaClass)javaTerm;

		String classContent = javaClass.getContent();

		List<JavaTerm> childJavaTerms = javaClass.getChildJavaTerms();

		if (childJavaTerms.isEmpty()) {
			Matcher matcher = _missingEmptyLinePattern.matcher(classContent);

			if (matcher.find()) {
				return matcher.replaceAll("$1\n$2");
			}

			return classContent;
		}

		JavaTerm previousChildJavaTerm = null;

		for (JavaTerm childJavaTerm : childJavaTerms) {
			if (previousChildJavaTerm != null) {
				classContent = _fixJavaTermDivider(
					classContent, previousChildJavaTerm, childJavaTerm);
			}

			previousChildJavaTerm = childJavaTerm;
		}

		String lastJavaTermContent = previousChildJavaTerm.getContent();

		if (!classContent.contains(lastJavaTermContent + "\n")) {
			classContent = StringUtil.replace(
				classContent, lastJavaTermContent, lastJavaTermContent + "\n");
		}

		return classContent;
	}

	@Override
	protected String[] getCheckableJavaTermNames() {
		return new String[] {JAVA_CLASS};
	}

	private String _fixJavaTermDivider(
		String classContent, JavaTerm previousJavaTerm, JavaTerm javaTerm) {

		String javaTermContent = javaTerm.getContent();

		String previousJavaTermContent = previousJavaTerm.getContent();

		String afterPreviousJavaTerm = StringUtil.trim(
			classContent.substring(
				classContent.indexOf("\n" + previousJavaTermContent) +
					previousJavaTermContent.length() + 1));

		if (!afterPreviousJavaTerm.startsWith(
				StringUtil.trim(javaTermContent))) {

			return classContent;
		}

		if (!javaTerm.isJavaVariable() || !previousJavaTerm.isJavaVariable() ||
			(previousJavaTerm.isStatic() ^ javaTerm.isStatic())) {

			return _fixJavaTermDivider(classContent, javaTermContent, true);
		}

		String javaTermAccessModifier = javaTerm.getAccessModifier();
		String previousJavaTermAccessModifier =
			previousJavaTerm.getAccessModifier();

		if (!previousJavaTermAccessModifier.equals(javaTermAccessModifier)) {
			return _fixJavaTermDivider(classContent, javaTermContent, true);
		}

		String javaTermName = javaTerm.getName();
		String previousJavaTermName = previousJavaTerm.getName();

		if ((StringUtil.isUpperCase(javaTermName) &&
			 !StringUtil.isLowerCase(javaTermName)) ||
			(StringUtil.isUpperCase(previousJavaTermName) &&
			 !StringUtil.isLowerCase(previousJavaTermName))) {

			return _fixJavaTermDivider(classContent, javaTermContent, true);
		}

		if (javaTermContent.matches("\\s*[/@*][\\S\\s]*") ||
			previousJavaTermContent.matches("\\s*[/@*][\\S\\s]*") ||
			javaTermContent.contains("\n\n\t") ||
			previousJavaTermContent.contains("\n\n\t")) {

			return _fixJavaTermDivider(classContent, javaTermContent, true);
		}

		if (previousJavaTerm.isStatic() &&
			(previousJavaTermName.equals("_instance") ||
			 previousJavaTermName.equals("_log") ||
			 previousJavaTermName.equals("_logger"))) {

			return _fixJavaTermDivider(classContent, javaTermContent, true);
		}

		return _fixJavaTermDivider(classContent, javaTermContent, false);
	}

	private String _fixJavaTermDivider(
		String classContent, String javaTermContent,
		boolean requiresEmptyLine) {

		if (requiresEmptyLine) {
			if (classContent.contains("\n\n" + javaTermContent)) {
				return classContent;
			}

			return StringUtil.replace(
				classContent, "\n" + javaTermContent, "\n\n" + javaTermContent);
		}

		if (!classContent.contains("\n\n" + javaTermContent)) {
			return classContent;
		}

		return StringUtil.replace(
			classContent, "\n\n" + javaTermContent, "\n" + javaTermContent);
	}

	private static final Pattern _missingEmptyLinePattern = Pattern.compile(
		"([^{}\n]\n)(\t*\\}\n?)$");

}