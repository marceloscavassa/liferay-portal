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

package com.liferay.poshi.core.elements;

import com.liferay.poshi.core.PoshiContext;
import com.liferay.poshi.core.script.PoshiScriptParserException;
import com.liferay.poshi.core.script.PoshiScriptParserUtil;
import com.liferay.poshi.core.util.CharPool;
import com.liferay.poshi.core.util.ListUtil;
import com.liferay.poshi.core.util.NaturalOrderStringComparator;
import com.liferay.poshi.core.util.RegexUtil;
import com.liferay.poshi.core.util.StringUtil;
import com.liferay.poshi.core.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * @author Kenji Heigel
 */
public class ExecutePoshiElement extends PoshiElement {

	@Override
	public PoshiElement clone(Element element) {
		if (isElementType(_ELEMENT_NAME, element)) {
			return new ExecutePoshiElement(element);
		}

		return null;
	}

	@Override
	public PoshiElement clone(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		if (_isElementType(parentPoshiElement, poshiScript)) {
			return new ExecutePoshiElement(parentPoshiElement, poshiScript);
		}

		return null;
	}

	@Override
	public void parsePoshiScript(String poshiScript)
		throws PoshiScriptParserException {

		String poshiScriptParentheticalContent = getParentheticalContent(
			poshiScript);
		String fileExtension = getFileExtension();

		validatePoshiScriptParameterSyntax(poshiScriptParentheticalContent);

		if (fileExtension.equals("function") &&
			poshiScript.startsWith("selenium.")) {

			addAttribute("selenium", getCommandName(poshiScript));

			List<String> methodParameterValues =
				PoshiScriptParserUtil.getMethodParameterValues(
					poshiScriptParentheticalContent);

			for (int i = 0; i < methodParameterValues.size(); i++) {
				String methodParameterValue = methodParameterValues.get(i);

				addAttribute(
					"argument" + (i + 1),
					getDoubleQuotedContent(methodParameterValue));
			}

			return;
		}

		String executeCommandName = RegexUtil.getGroup(
			poshiScript, "([^\\s]*?)\\(", 1);

		boolean namespacedCommandName = false;

		for (String namespace : PoshiContext.getNamespaces()) {
			if (executeCommandName.startsWith(namespace + ".")) {
				namespacedCommandName = true;

				break;
			}
		}

		if (namespacedCommandName) {
			int index = executeCommandName.indexOf(".");

			String namespace = executeCommandName.substring(0, index);

			executeCommandName = StringUtil.replace(
				executeCommandName, namespace + ".", "");

			executeCommandName = StringUtil.replace(
				executeCommandName, '.', '#');

			executeCommandName = namespace + "." + executeCommandName;
		}
		else {
			executeCommandName = StringUtil.replace(
				executeCommandName, '.', '#');
		}

		if (isValidFunctionFileName(poshiScript)) {
			addAttribute("function", executeCommandName);
		}
		else if (isValidMacroFileName(poshiScript)) {
			addAttribute("macro", executeCommandName);

			if (poshiScript.startsWith("var ")) {
				PoshiNode<?, ?> returnPoshiNode = PoshiNodeFactory.newPoshiNode(
					this, poshiScript);

				if (returnPoshiNode instanceof ReturnPoshiElement) {
					add(returnPoshiNode);
				}
			}
		}
		else {
			addAttribute("class", getClassName(poshiScript));
			addAttribute("method", getCommandName(poshiScript));

			for (String methodParameterValue :
					PoshiScriptParserUtil.getMethodParameterValues(
						poshiScriptParentheticalContent)) {

				add(PoshiNodeFactory.newPoshiNode(this, methodParameterValue));
			}

			return;
		}

		for (String methodParameterValue :
				PoshiScriptParserUtil.getMethodParameterValues(
					poshiScriptParentheticalContent,
					_executeParameterPattern)) {

			methodParameterValue = methodParameterValue.trim();

			boolean functionAttributeAdded = false;

			for (String functionAttributeName : _functionAttributeNames) {
				String name = getNameFromAssignment(methodParameterValue);

				if (name.equals(functionAttributeName)) {
					String value = getValueFromAssignment(methodParameterValue);

					Matcher matcher = quotedPattern.matcher(value);

					if (!matcher.matches()) {
						StringBuilder sb = new StringBuilder();

						sb.append("Invalid function parameter syntax, must ");
						sb.append("match: (locator|value)(1|2) = \".*\"");

						throw new PoshiScriptParserException(
							sb.toString(), methodParameterValue,
							(PoshiElement)getParent());
					}

					value = getDoubleQuotedContent(value);

					value = value.replace("\\\"", "\"");

					value = StringEscapeUtils.unescapeXml(value);

					add(
						new PoshiElementAttribute(
							name, value, methodParameterValue));

					functionAttributeAdded = true;

					break;
				}
			}

			if (functionAttributeAdded) {
				continue;
			}

			add(PoshiNodeFactory.newPoshiNode(this, methodParameterValue));
		}
	}

	@Override
	public String toPoshiScript() {
		List<String> assignments = new ArrayList<>();

		for (PoshiElementAttribute poshiElementAttribute :
				toPoshiElementAttributes(attributes())) {

			String poshiElementAttributeName = poshiElementAttribute.getName();

			if (poshiElementAttributeName.equals("class") ||
				poshiElementAttributeName.equals("function") ||
				poshiElementAttributeName.equals("macro") ||
				poshiElementAttributeName.equals("method") ||
				poshiElementAttributeName.equals("selenium")) {

				continue;
			}

			String fileExtension = getFileExtension();

			if (fileExtension.equals("function") &&
				Validator.isNotNull(attributeValue("selenium"))) {

				String poshiElementAttributeValue =
					poshiElementAttribute.getValue();

				assignments.add(doubleQuoteContent(poshiElementAttributeValue));

				continue;
			}

			String poshiScript = poshiElementAttribute.toPoshiScript();

			assignments.add(poshiScript.trim());
		}

		ReturnPoshiElement returnPoshiElement = null;

		for (PoshiElement poshiElement : toPoshiElements(elements())) {
			if (poshiElement instanceof ReturnPoshiElement) {
				returnPoshiElement = (ReturnPoshiElement)poshiElement;

				continue;
			}

			String poshiScript = poshiElement.toPoshiScript();

			assignments.add(poshiScript.trim());
		}

		String poshiScriptSnippet = createPoshiScriptSnippet(assignments);

		if (returnPoshiElement == null) {
			return poshiScriptSnippet;
		}

		return returnPoshiElement.createPoshiScriptSnippet(poshiScriptSnippet);
	}

	public void validatePoshiScriptParameterSyntax(String parentheticalContent)
		throws PoshiScriptParserException {

		Stack<Character> stack = new Stack<>();

		boolean expectedComma = false;

		for (int i = 0; i < parentheticalContent.length(); i++) {
			char c = parentheticalContent.charAt(i);

			if (!stack.isEmpty() && (c == '\"') &&
				(parentheticalContent.charAt(i - 1) != '\\')) {

				stack.pop();
				expectedComma = true;

				continue;
			}

			if (c == '\"') {
				stack.push(c);

				continue;
			}

			if (expectedComma) {
				if (c == ',') {
					expectedComma = false;

					continue;
				}

				if (c == ' ') {
					continue;
				}

				PoshiScriptParserException poshiScriptParserException =
					new PoshiScriptParserException(
						"Missing comma in execute method", this);

				int errorLineNumber =
					poshiScriptParserException.getErrorLineNumber();

				poshiScriptParserException.setErrorLineNumber(
					errorLineNumber +
						StringUtil.count(parentheticalContent, "\n", i));

				throw poshiScriptParserException;
			}
		}
	}

	protected ExecutePoshiElement() {
		this(_ELEMENT_NAME);
	}

	protected ExecutePoshiElement(Element element) {
		super("execute", element);
	}

	protected ExecutePoshiElement(
		List<Attribute> attributes, List<Node> nodes) {

		this(_ELEMENT_NAME, attributes, nodes);
	}

	protected ExecutePoshiElement(
			PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		super("execute", parentPoshiElement, poshiScript);
	}

	protected ExecutePoshiElement(String name) {
		super(name);
	}

	protected ExecutePoshiElement(String name, Element element) {
		super(name, element);
	}

	protected ExecutePoshiElement(
		String elementName, List<Attribute> attributes, List<Node> nodes) {

		super(elementName, attributes, nodes);
	}

	protected ExecutePoshiElement(
			String name, PoshiElement parentPoshiElement, String poshiScript)
		throws PoshiScriptParserException {

		super(name, parentPoshiElement, poshiScript);
	}

	protected String createPoshiScriptSnippet(List<String> assignments) {
		StringBuilder sb = new StringBuilder();

		String pad = getPad();

		sb.append("\n\n");
		sb.append(pad);
		sb.append(StringUtil.replace(getBlockName(), '#', '.'));
		sb.append("(");

		boolean multilineSnippet = false;

		String assignmentsString = ListUtil.toString(assignments);

		if ((assignments.size() > 1) &&
			assignmentsString.matches("(?s)\\w+\\s*=.+") &&
			!isConditionValidInParent((PoshiElement)getParent())) {

			multilineSnippet = true;
		}

		Collections.sort(
			assignments,
			new Comparator<String>() {

				@Override
				public int compare(String assignment1, String assignment2) {
					if (!assignment1.matches("\\w+ = .+") &&
						!assignment2.matches("\\w+ = .+")) {

						return 0;
					}

					String name1 = assignment1.substring(
						0, assignment1.indexOf(CharPool.EQUAL));
					String name2 = assignment2.substring(
						0, assignment2.indexOf(CharPool.EQUAL));

					NaturalOrderStringComparator naturalOrderStringComparator =
						new NaturalOrderStringComparator();

					return naturalOrderStringComparator.compare(name1, name2);
				}

			});

		for (String assignment : assignments) {
			String s = sb.toString();

			s = s.substring(s.lastIndexOf("\n"));

			if (multilineSnippet) {
				if (s.endsWith(" ")) {
					sb.setLength(sb.length() - 1);
				}

				sb.append("\n\t");
				sb.append(pad);
			}

			sb.append(assignment);
			sb.append(", ");
		}

		if (!assignments.isEmpty()) {
			sb.setLength(sb.length() - 2);
		}

		sb.append(");");

		return sb.toString();
	}

	@Override
	protected String getBlockName() {
		if (attributeValue("class") != null) {
			return attributeValue("class") + "." + attributeValue("method");
		}

		if (attributeValue("function") != null) {
			return attributeValue("function");
		}

		if (attributeValue("macro") != null) {
			return attributeValue("macro");
		}

		return "selenium." + attributeValue("selenium");
	}

	private boolean _isElementType(
		PoshiElement parentPoshiElement, String poshiScript) {

		if (parentPoshiElement instanceof ExecutePoshiElement) {
			return false;
		}

		if ((isVarAssignedToMacroInvocation(poshiScript) ||
			 isValidPoshiScriptStatement(_statementPattern, poshiScript)) &&
			!isValidPoshiScriptStatement(
				_utilityInvocationStatementPattern, poshiScript)) {

			return PoshiScriptParserUtil.isBalancedPoshiScript(
				getParentheticalContent(poshiScript));
		}

		return false;
	}

	private static final String _ELEMENT_NAME = "execute";

	private static final String _UTILITY_INVOCATION_REGEX =
		"(echo|fail|takeScreenshot)\\(.*?\\)";

	private static final Pattern _executeParameterPattern = Pattern.compile(
		"^[\\s]*(\\w*\\s*=\\s*\"[ \\t\\S]*?\"|\\w*\\s*=\\s*'''.*?'''|" +
			"\\w*\\s=\\s*[\\w\\.]*\\(.*?\\))[\\s]*$",
		Pattern.DOTALL);
	private static final List<String> _functionAttributeNames = Arrays.asList(
		"locator1", "locator2", "value1", "value2", "value3");
	private static final Pattern _statementPattern = Pattern.compile(
		"^" + INVOCATION_REGEX + STATEMENT_END_REGEX, Pattern.DOTALL);
	private static final Pattern _utilityInvocationStatementPattern =
		Pattern.compile("^" + _UTILITY_INVOCATION_REGEX + STATEMENT_END_REGEX);

}