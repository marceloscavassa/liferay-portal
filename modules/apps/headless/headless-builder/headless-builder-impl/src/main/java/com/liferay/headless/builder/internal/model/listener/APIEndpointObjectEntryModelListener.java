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

package com.liferay.headless.builder.internal.model.listener;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.listener.ObjectDefinitionObjectEntryModelListener;
import com.liferay.object.rest.petra.sql.dsl.expression.FilterPredicateFactory;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio Jiménez del Coso
 */
@Component(service = ObjectDefinitionObjectEntryModelListener.class)
public class APIEndpointObjectEntryModelListener
	extends BaseModelListener<ObjectEntry>
	implements ObjectDefinitionObjectEntryModelListener {

	@Override
	public String getObjectDefinitionExternalReferenceCode() {
		return "MSOD_API_ENDPOINT";
	}

	@Override
	public void onBeforeCreate(ObjectEntry objectEntry)
		throws ModelListenerException {

		_validate(objectEntry);
	}

	@Override
	public void onBeforeUpdate(
			ObjectEntry originalObjectEntry, ObjectEntry objectEntry)
		throws ModelListenerException {

		if (_isModified(originalObjectEntry, objectEntry)) {
			_validate(objectEntry);
		}
	}

	private void _validate(ObjectEntry objectEntry) {
		ObjectDefinition apiEndpointObjectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectEntry.getObjectDefinitionId());

		try {
			Map<String, Serializable> values = objectEntry.getValues();

			if (!_validatePath((String)values.get("path"))) {
				throw new IllegalArgumentException(
					"Path can have a maximum of 255 alphanumeric characters");
			}

			String filterString = StringBundler.concat(
				"id ne '", objectEntry.getObjectEntryId(),
				"' and httpMethod eq '", values.get("httpMethod"),
				"' and path eq '", values.get("path"),
				"' and r_apiApplicationToAPIEndpoints_c_apiApplicationId eq '",
				values.get("r_apiApplicationToAPIEndpoints_c_apiApplicationId"),
				"'");

			Predicate predicate = _filterPredicateFactory.create(
				filterString,
				apiEndpointObjectDefinition.getObjectDefinitionId());

			List<Map<String, Serializable>> valuesList =
				_objectEntryLocalService.getValuesList(
					objectEntry.getGroupId(), objectEntry.getCompanyId(),
					objectEntry.getUserId(),
					apiEndpointObjectDefinition.getObjectDefinitionId(),
					predicate, null, -1, -1, null);

			if (!valuesList.isEmpty()) {
				throw new IllegalArgumentException(
					"There is an endpoint with the same http method and path " +
						"combination");
			}

			if ((long)values.get(
					"r_apiApplicationToAPIEndpoints_c_apiApplicationId") == 0) {

				throw new IllegalArgumentException(
					"An endpoint must be related to an application");
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	private boolean _validatePath(String path) {
		Matcher matcher = _pathPattern.matcher(path);

		return matcher.matches();
	}

	private boolean _isModified(
		ObjectEntry originalObjectEntry, ObjectEntry objectEntry) {

		Map<String, Serializable> values = objectEntry.getValues();
		Map<String, Serializable> originalValues =
			originalObjectEntry.getValues();

		if (Objects.equals(
				values.get("httpMethod"), originalValues.get("httpMethod")) &&
			Objects.equals(values.get("path"), originalValues.get("path")) &&
			Objects.equals(
				values.get("r_apiApplicationToAPIEndpoints_c_apiApplicationId"),
				originalValues.get(
					"r_apiApplicationToAPIEndpoints_c_apiApplicationId"))) {

			return false;
		}

		return true;
	}

	private static final Pattern _pathPattern = Pattern.compile(
		"[a-zA-Z0-9-/]{1,255}");

	@Reference
	private FilterPredicateFactory _filterPredicateFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

}