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

package com.liferay.headless.builder.internal.helper;

import com.liferay.headless.builder.application.APIApplication;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedFieldsContext;
import com.liferay.portal.vulcan.fields.NestedFieldsContextThreadLocal;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luis Miguel Barcos
 * @authot Carlos Correa
 * @author Alejandro Tardín
 */
@Component(service = ObjectEntryHelper.class)
public class ObjectEntryHelper {

	public List<ObjectEntry> getObjectEntries(
			long companyId, String filterString, List<String> nestedFields,
			String objectDefinitionExternalReferenceCode)
		throws Exception {

		NestedFieldsContext nestedFieldsContext = new NestedFieldsContext(
			1, nestedFields);

		NestedFieldsContext oldNestedFieldsContext =
			NestedFieldsContextThreadLocal.getAndSetNestedFieldsContext(
				nestedFieldsContext);

		try {
			return getObjectEntries(
				companyId, filterString, objectDefinitionExternalReferenceCode);
		}
		finally {
			NestedFieldsContextThreadLocal.setNestedFieldsContext(
				oldNestedFieldsContext);
		}
	}

	public List<ObjectEntry> getObjectEntries(
			long companyId, String filterString,
			String objectDefinitionExternalReferenceCode)
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				fetchObjectDefinitionByExternalReferenceCode(
					objectDefinitionExternalReferenceCode, companyId);

		if (objectDefinition == null) {
			return null;
		}

		PermissionThreadLocal.setPermissionChecker(
			_permissionCheckerFactory.create(
				_userLocalService.getUser(objectDefinition.getUserId())));

		Page<ObjectEntry> page = _objectEntryManager.getObjectEntries(
			companyId, objectDefinition, null, null,
			new DefaultDTOConverterContext(
				false, null, null, null, null, LocaleUtil.getSiteDefault(),
				null, _userLocalService.getUser(objectDefinition.getUserId())),
			filterString, Pagination.of(QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			null, null);

		return new ArrayList<>(page.getItems());
	}

	public ObjectEntry getObjectEntry(
			long companyId, String filterString,
			String objectDefinitionExternalReferenceCode)
		throws Exception {

		List<ObjectEntry> objectEntries = getObjectEntries(
			companyId, filterString, objectDefinitionExternalReferenceCode);

		if (ListUtil.isEmpty(objectEntries)) {
			return null;
		}

		return objectEntries.get(0);
	}

	public List<Map<String, Object>> getSchemaEntities(
			long companyId, APIApplication.Endpoint endpoint)
		throws Exception {

		APIApplication.Schema responseSchema = endpoint.getResponseSchema();

		ObjectDefinition schemaMainObjectDefinition =
			_getSchemaMainObjectDefinition(companyId, responseSchema);

		Map<APIApplication.Property, ObjectField> propertyObjectFieldHashMap =
			_getAPIApplicationPropertyObjectFieldMap(
				companyId, responseSchema, schemaMainObjectDefinition);

		List<ObjectEntry> objectEntries = getObjectEntries(
			companyId, null,
			schemaMainObjectDefinition.getExternalReferenceCode());

		List<Map<String, Object>> entities = new ArrayList<>();

		for (ObjectEntry objectEntry : objectEntries) {
			Map<String, Object> objectEntryProperties =
				_getAllObjectEntryProperties(objectEntry);

			Map<String, Object> entity = new HashMap<>();

			for (Map.Entry<APIApplication.Property, ObjectField>
					propertyObjectFieldEntry :
						propertyObjectFieldHashMap.entrySet()) {

				APIApplication.Property property =
					propertyObjectFieldEntry.getKey();
				ObjectField objectField = propertyObjectFieldEntry.getValue();

				entity.put(
					property.getName(),
					objectEntryProperties.get(objectField.getName()));
			}

			entities.add(entity);
		}

		return entities;
	}

	private Map<String, Object> _getAllObjectEntryProperties(
		ObjectEntry objectEntry) {

		return HashMapBuilder.<String, Object>putAll(
			objectEntry.getProperties()
		).put(
			"createDate", objectEntry.getDateCreated()
		).put(
			"externalReferenceCode", objectEntry.getExternalReferenceCode()
		).put(
			"modifiedDate", objectEntry.getDateModified()
		).build();
	}

	private Map<APIApplication.Property, ObjectField>
			_getAPIApplicationPropertyObjectFieldMap(
				long companyId, APIApplication.Schema schema,
				ObjectDefinition objectDefinition)
		throws Exception {

		Map<APIApplication.Property, ObjectField> propertyObjectFieldHashMap =
			new HashMap<>();

		ObjectDefinition propertyObjectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_API_PROPERTY", companyId);

		for (APIApplication.Property property : schema.getProperties()) {
			ObjectEntry objectEntry1 = _objectEntryManager.getObjectEntry(
				companyId, _getDefaultDTOConverterContext(objectDefinition),
				property.getExternalReferenceCode(), propertyObjectDefinition,
				null);

			Map<String, Object> stringObjectMap = _getAllObjectEntryProperties(
				objectEntry1);

			propertyObjectFieldHashMap.put(
				property,
				_objectFieldLocalService.getObjectField(
					(String)stringObjectMap.get("objectFieldERC"),
					objectDefinition.getObjectDefinitionId()));
		}

		return propertyObjectFieldHashMap;
	}

	private DTOConverterContext _getDefaultDTOConverterContext(
			ObjectDefinition objectDefinition)
		throws Exception {

		return new DefaultDTOConverterContext(
			false, null, null, null, null, LocaleUtil.getSiteDefault(), null,
			_userLocalService.getUser(objectDefinition.getUserId()));
	}

	private ObjectDefinition _getSchemaMainObjectDefinition(
			long companyId, APIApplication.Schema schema)
		throws Exception {

		String objectEntryExternalReferenceCode =
			schema.getExternalReferenceCode();

		ObjectDefinition apiSchemaObjectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_API_SCHEMA", companyId);

		Map<String, Object> objectEntryProperties =
			_getAllObjectEntryProperties(
				_objectEntryManager.getObjectEntry(
					companyId,
					new DefaultDTOConverterContext(
						false, null, null, null, null,
						LocaleUtil.getSiteDefault(), null,
						_userLocalService.getUser(
							apiSchemaObjectDefinition.getUserId())),
					objectEntryExternalReferenceCode, apiSchemaObjectDefinition,
					null));

		return _objectDefinitionLocalService.
			getObjectDefinitionByExternalReferenceCode(
				(String)objectEntryProperties.get("mainObjectDefinitionERC"),
				companyId);
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference(target = "(object.entry.manager.storage.type=default)")
	private ObjectEntryManager _objectEntryManager;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

}