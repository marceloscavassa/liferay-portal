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

package com.liferay.object.entry.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectField;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Feliphe Marinho
 */
public class ObjectEntryValuesUtil {

	public static Map<String, Object> getObjectEntryValues(
		BaseModel<?> baseModel, DTOConverterRegistry dtoConverterRegistry,
		String objectDefinitionName,
		SystemObjectDefinitionManagerRegistry
			systemObjectDefinitionManagerRegistry,
		User user) {

		try {
			Object dtoModel = ObjectEntryDTOConverterUtil.toDTO(
				baseModel, dtoConverterRegistry,
				systemObjectDefinitionManagerRegistry.
					getSystemObjectDefinitionManager(objectDefinitionName),
				user);

			return ObjectMapperUtil.readValue(Map.class, dtoModel.toString());
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return new HashMap<>();
	}

	public static Map<String, Object> getObjectEntryValues(
			long companyId, DTOConverterRegistry dtoConverterRegistry,
			String objectDefinitionName, long primaryKey,
			SystemObjectDefinitionManagerRegistry
				systemObjectDefinitionManagerRegistry,
			User user)
		throws PortalException {

		SystemObjectDefinitionManager systemObjectDefinitionManager =
			systemObjectDefinitionManagerRegistry.
				getSystemObjectDefinitionManager(objectDefinitionName);

		return getObjectEntryValues(
			systemObjectDefinitionManager.getBaseModelByExternalReferenceCode(
				systemObjectDefinitionManager.getExternalReferenceCode(
					primaryKey),
				companyId),
			dtoConverterRegistry, objectDefinitionName,
			systemObjectDefinitionManagerRegistry, user);
	}

	public static Object getTitleFieldValue(
		String businessType, Object value, String userLanguageId) {

		if (value instanceof Map) {
			Map<String, Object> localizedValues = (Map<String, Object>)value;

			String siteDefaultLanguageId = String.valueOf(
				LocaleUtil.getSiteDefault());

			if (localizedValues.containsKey(siteDefaultLanguageId)) {
				value = localizedValues.get(siteDefaultLanguageId);
			}
			else if (localizedValues.containsKey(userLanguageId)) {
				value = localizedValues.get(userLanguageId);
			}
			else {
				value = localizedValues.get(
					String.valueOf(LocaleUtil.getDefault()));
			}
		}
		else if (StringUtil.equals(
					businessType, ObjectFieldConstants.BUSINESS_TYPE_BOOLEAN)) {

			value = Boolean.parseBoolean(String.valueOf(value));
		}

		return value;
	}

	public static String getValueString(
		ObjectField objectField, Map<String, Serializable> values) {

		Object value = values.get(objectField.getName());

		if (StringUtil.equals(
				objectField.getBusinessType(),
				ObjectFieldConstants.BUSINESS_TYPE_ATTACHMENT)) {

			return _getFileName(GetterUtil.getLong(value));
		}
		else if (StringUtil.equals(
					objectField.getBusinessType(),
					ObjectFieldConstants.BUSINESS_TYPE_RICH_TEXT)) {

			return HtmlParserUtil.extractText(GetterUtil.getString(value));
		}

		if (Validator.isNull(value)) {
			String objectFieldName = objectField.getName();

			if (objectFieldName.equals("creator")) {
				objectFieldName = "userName";
			}
			else if (objectFieldName.equals("id")) {
				objectFieldName = "objectEntryId";
			}

			value = values.get(objectFieldName);
		}

		return String.valueOf(value);
	}

	private static String _getFileName(long dlFileEntryId) {
		try {
			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.getDLFileEntry(dlFileEntryId);

			return dlFileEntry.getFileName();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return StringPool.BLANK;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryValuesUtil.class);

}