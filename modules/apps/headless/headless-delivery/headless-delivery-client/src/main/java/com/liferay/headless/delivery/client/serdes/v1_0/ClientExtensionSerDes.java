/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.ClientExtension;
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
public class ClientExtensionSerDes {

	public static ClientExtension toDTO(String json) {
		ClientExtensionJSONParser clientExtensionJSONParser =
			new ClientExtensionJSONParser();

		return clientExtensionJSONParser.parseToDTO(json);
	}

	public static ClientExtension[] toDTOs(String json) {
		ClientExtensionJSONParser clientExtensionJSONParser =
			new ClientExtensionJSONParser();

		return clientExtensionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ClientExtension clientExtension) {
		if (clientExtension == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (clientExtension.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(clientExtension.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (clientExtension.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(clientExtension.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ClientExtensionJSONParser clientExtensionJSONParser =
			new ClientExtensionJSONParser();

		return clientExtensionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ClientExtension clientExtension) {
		if (clientExtension == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (clientExtension.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(clientExtension.getExternalReferenceCode()));
		}

		if (clientExtension.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(clientExtension.getName()));
		}

		return map;
	}

	public static class ClientExtensionJSONParser
		extends BaseJSONParser<ClientExtension> {

		@Override
		protected ClientExtension createDTO() {
			return new ClientExtension();
		}

		@Override
		protected ClientExtension[] createDTOArray(int size) {
			return new ClientExtension[size];
		}

		@Override
		protected void setField(
			ClientExtension clientExtension, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "externalReferenceCode")) {
				if (jsonParserFieldValue != null) {
					clientExtension.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					clientExtension.setName((String)jsonParserFieldValue);
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