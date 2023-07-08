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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.check.util.JavaSourceUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaMethod;
import com.liferay.source.formatter.parser.JavaParameter;
import com.liferay.source.formatter.parser.JavaSignature;
import com.liferay.source.formatter.parser.JavaTerm;

import java.util.List;
import java.util.Objects;

/**
 * @author Tamyris Torres
 */
public class UpgradeJavaOnAfterUpdateParameterCheck extends BaseJavaTermCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, JavaTerm javaTerm,
			String fileContent)
		throws Exception {

		String content = javaTerm.getContent();

		if (!_extendsFromBaseModelListener(javaTerm.getParentJavaClass())) {
			return content;
		}

		JavaMethod javaMethod = (JavaMethod)javaTerm;

		JavaSignature javaSignature = javaMethod.getSignature();

		List<JavaParameter> javaParameters = javaSignature.getParameters();

		if (!Objects.equals(javaMethod.getName(), "onAfterUpdate") ||
			!Objects.equals(javaSignature.getReturnType(), "") ||
			(javaParameters.size() != 1)) {

			return content;
		}

		return StringUtil.replace(
			content, JavaSourceUtil.getParameters(javaMethod.getContent()),
			_cloneAndRenameFirstParameter(javaParameters.get(0)));
	}

	@Override
	protected String[] getCheckableJavaTermNames() {
		return new String[] {JAVA_METHOD};
	}

	private String _cloneAndRenameFirstParameter(JavaParameter parameter) {
		StringBundler sb = new StringBundler(7);

		sb.append(parameter.getParameterType());
		sb.append(" original");
		sb.append(
			StringUtil.upperCaseFirstLetter(parameter.getParameterName()));
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(parameter.getParameterType());
		sb.append(StringPool.SPACE);
		sb.append(parameter.getParameterName());

		return sb.toString();
	}

	private boolean _extendsFromBaseModelListener(JavaClass javaClass) {
		List<String> extendedClassNames = javaClass.getExtendedClassNames();

		if (extendedClassNames.contains("BaseModelListener")) {
			return true;
		}

		return false;
	}

}