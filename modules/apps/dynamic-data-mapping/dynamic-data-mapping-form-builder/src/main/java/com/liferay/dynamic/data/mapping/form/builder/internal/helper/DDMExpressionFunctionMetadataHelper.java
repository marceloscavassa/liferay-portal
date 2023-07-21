/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.helper;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunctionRegistry;
import com.liferay.dynamic.data.mapping.form.builder.internal.util.DDMExpressionFunctionMetadata;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(service = DDMExpressionFunctionMetadataHelper.class)
public class DDMExpressionFunctionMetadataHelper {

	public Map<String, List<DDMExpressionFunctionMetadata>>
		getDDMExpressionFunctionsMetadata(Locale locale) {

		Map<String, List<DDMExpressionFunctionMetadata>>
			ddmExpressionFunctionMetadatasMap = new HashMap<>();

		populateCustomDDMExpressionFunctionsMetadata(
			ddmExpressionFunctionMetadatasMap, locale);
		populateDDMExpressionFunctionsMetadata(
			ddmExpressionFunctionMetadatasMap, getResourceBundle(locale));

		return ddmExpressionFunctionMetadatasMap;
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle portalResourceBundle = _portal.getResourceBundle(locale);

		ResourceBundle portletResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return new AggregateResourceBundle(
			portletResourceBundle, portalResourceBundle);
	}

	protected void populateCustomDDMExpressionFunctionsMetadata(
		Map<String, List<DDMExpressionFunctionMetadata>>
			ddmExpressionFunctionMetadatasMap,
		Locale locale) {

		Map<String, DDMExpressionFunction> customDDMExpressionFunctions =
			_ddmExpressionFunctionRegistry.getCustomDDMExpressionFunctions();

		for (Map.Entry<String, DDMExpressionFunction> entry :
				customDDMExpressionFunctions.entrySet()) {

			Method method = null;

			DDMExpressionFunction ddmExpressionFunction = entry.getValue();

			Class<?> clazz = ddmExpressionFunction.getClass();

			for (Method curMethod : clazz.getMethods()) {
				if (Objects.equals(curMethod.getName(), "apply") &&
					Objects.equals(curMethod.getReturnType(), Boolean.class)) {

					method = curMethod;

					break;
				}
			}

			if (method == null) {
				continue;
			}

			int parameterCount = method.getParameterCount();

			if (parameterCount > 2) {
				continue;
			}

			String label = ddmExpressionFunction.getLabel(locale);

			if (Validator.isNull(label)) {
				label = entry.getKey();
			}

			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(), label, _TYPE_BOOLEAN,
					_getParameterClassNames(parameterCount, _TYPE_NUMBER)));
			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(), label, _TYPE_BOOLEAN,
					_getParameterClassNames(parameterCount, _TYPE_TEXT)));
		}
	}

	protected void populateDDMExpressionFunctionsMetadata(
		Map<String, List<DDMExpressionFunctionMetadata>>
			ddmExpressionFunctionMetadatasMap,
		ResourceBundle resourceBundle) {

		_addDDMExpressionFunctionMetadata(
			ddmExpressionFunctionMetadatasMap,
			new DDMExpressionFunctionMetadata(
				"belongs-to", _language.get(resourceBundle, "belongs-to"),
				_TYPE_BOOLEAN, new String[] {_TYPE_USER, _TYPE_LIST}));
		_addDDMExpressionFunctionMetadata(
			ddmExpressionFunctionMetadatasMap,
			new DDMExpressionFunctionMetadata(
				"equals-to", _language.get(resourceBundle, "is-equal-to"),
				_TYPE_BOOLEAN, new String[] {_TYPE_BOOLEAN, _TYPE_BOOLEAN}));

		for (Map.Entry<String, String> entry : _binaryFunctions.entrySet()) {
			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_NUMBER, _TYPE_NUMBER}));
			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_TEXT, _TYPE_TEXT}));
		}

		for (Map.Entry<String, String> entry :
				_numberBinaryFunctions.entrySet()) {

			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_NUMBER, _TYPE_NUMBER}));
		}

		for (Map.Entry<String, String> entry :
				_textBinaryFunctions.entrySet()) {

			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_TEXT, _TYPE_TEXT}));
		}

		for (Map.Entry<String, String> entry : _unaryFunctions.entrySet()) {
			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_NUMBER}));
			_addDDMExpressionFunctionMetadata(
				ddmExpressionFunctionMetadatasMap,
				new DDMExpressionFunctionMetadata(
					entry.getKey(),
					_language.get(resourceBundle, entry.getValue()),
					_TYPE_BOOLEAN, new String[] {_TYPE_TEXT}));
		}
	}

	private void _addDDMExpressionFunctionMetadata(
		Map<String, List<DDMExpressionFunctionMetadata>>
			ddmExpressionFunctionMetadatasMap,
		DDMExpressionFunctionMetadata ddmExpressionFunctionMetadata) {

		String firstParameterClassName =
			ddmExpressionFunctionMetadata.getParameterClassNames()[0];

		List<DDMExpressionFunctionMetadata> ddmExpressionFunctionMetadatas =
			ddmExpressionFunctionMetadatasMap.get(firstParameterClassName);

		if (ddmExpressionFunctionMetadatas == null) {
			ddmExpressionFunctionMetadatas = new ArrayList<>();

			ddmExpressionFunctionMetadatasMap.put(
				firstParameterClassName, ddmExpressionFunctionMetadatas);
		}

		ddmExpressionFunctionMetadatas.add(ddmExpressionFunctionMetadata);
	}

	private String[] _getParameterClassNames(
		int parameterCount, String parameterClassName) {

		String[] parameterClassNames = new String[parameterCount];

		Arrays.fill(parameterClassNames, parameterClassName);

		return parameterClassNames;
	}

	private static final String _TYPE_BOOLEAN = "boolean";

	private static final String _TYPE_LIST = "list";

	private static final String _TYPE_NUMBER = "number";

	private static final String _TYPE_TEXT = "text";

	private static final String _TYPE_USER = "user";

	private static final Map<String, String> _binaryFunctions =
		LinkedHashMapBuilder.put(
			"equals-to", "is-equal-to"
		).put(
			"not-equals-to", "is-not-equal-to"
		).build();
	private static final Map<String, String> _numberBinaryFunctions =
		LinkedHashMapBuilder.put(
			"greater-than", "is-greater-than"
		).put(
			"greater-than-equals", "is-greater-than-or-equal-to"
		).put(
			"less-than", "is-less-than"
		).put(
			"less-than-equals", "is-less-than-or-equal-to"
		).build();
	private static final Map<String, String> _textBinaryFunctions =
		LinkedHashMapBuilder.put(
			"contains", "contains"
		).put(
			"not-contains", "does-not-contain"
		).build();
	private static final Map<String, String> _unaryFunctions =
		LinkedHashMapBuilder.put(
			"is-empty", "is-empty"
		).put(
			"not-is-empty", "is-not-empty"
		).build();

	@Reference
	private DDMExpressionFunctionRegistry _ddmExpressionFunctionRegistry;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}