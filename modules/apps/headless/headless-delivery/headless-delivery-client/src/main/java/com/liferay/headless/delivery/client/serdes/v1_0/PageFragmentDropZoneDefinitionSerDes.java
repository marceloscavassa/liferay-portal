/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.PageFragmentDropZoneDefinition;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class PageFragmentDropZoneDefinitionSerDes {

	public static PageFragmentDropZoneDefinition toDTO(String json) {
		PageFragmentDropZoneDefinitionJSONParser
			pageFragmentDropZoneDefinitionJSONParser =
				new PageFragmentDropZoneDefinitionJSONParser();

		return pageFragmentDropZoneDefinitionJSONParser.parseToDTO(json);
	}

	public static PageFragmentDropZoneDefinition[] toDTOs(String json) {
		PageFragmentDropZoneDefinitionJSONParser
			pageFragmentDropZoneDefinitionJSONParser =
				new PageFragmentDropZoneDefinitionJSONParser();

		return pageFragmentDropZoneDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		PageFragmentDropZoneDefinition pageFragmentDropZoneDefinition) {

		if (pageFragmentDropZoneDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (pageFragmentDropZoneDefinition.getFragmentDropZoneId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fragmentDropZoneId\": ");

			sb.append("\"");

			sb.append(
				_escape(
					pageFragmentDropZoneDefinition.getFragmentDropZoneId()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PageFragmentDropZoneDefinitionJSONParser
			pageFragmentDropZoneDefinitionJSONParser =
				new PageFragmentDropZoneDefinitionJSONParser();

		return pageFragmentDropZoneDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		PageFragmentDropZoneDefinition pageFragmentDropZoneDefinition) {

		if (pageFragmentDropZoneDefinition == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (pageFragmentDropZoneDefinition.getFragmentDropZoneId() == null) {
			map.put("fragmentDropZoneId", null);
		}
		else {
			map.put(
				"fragmentDropZoneId",
				String.valueOf(
					pageFragmentDropZoneDefinition.getFragmentDropZoneId()));
		}

		return map;
	}

	public static class PageFragmentDropZoneDefinitionJSONParser
		extends BaseJSONParser<PageFragmentDropZoneDefinition> {

		@Override
		protected PageFragmentDropZoneDefinition createDTO() {
			return new PageFragmentDropZoneDefinition();
		}

		@Override
		protected PageFragmentDropZoneDefinition[] createDTOArray(int size) {
			return new PageFragmentDropZoneDefinition[size];
		}

		@Override
		protected void setField(
			PageFragmentDropZoneDefinition pageFragmentDropZoneDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "fragmentDropZoneId")) {
				if (jsonParserFieldValue != null) {
					pageFragmentDropZoneDefinition.setFragmentDropZoneId(
						(String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}