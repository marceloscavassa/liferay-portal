/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.builder.internal.model.listener;

import com.liferay.object.exception.ObjectEntryValuesException;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.listener.RelevantObjectEntryModelListener;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio Jiménez del Coso
 */
@Component(service = RelevantObjectEntryModelListener.class)
public class APIApplicationRelevantObjectEntryModelListener
	extends BaseModelListener<ObjectEntry>
	implements RelevantObjectEntryModelListener {

	@Override
	public String getObjectDefinitionExternalReferenceCode() {
		return "L_API_APPLICATION";
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

		_validate(objectEntry);
	}

	private void _validate(ObjectEntry objectEntry) {

		// APIApplication is defined in headless-builder.json and has a required
		// object field called "baseURL".

		Map<String, Serializable> values = objectEntry.getValues();

		String baseURL = (String)values.get("baseURL");

		if (baseURL == null) {
			return;
		}

		// Just because you have an object field called "baseURL" does not mean
		// you are an APIApplication. My mom is a woman, but not every woman is
		// my mom.

		try {
			Matcher matcher = _baseURLPattern.matcher(baseURL);

			if (!matcher.matches()) {
				User user = _userLocalService.getUser(objectEntry.getUserId());

				ObjectField objectField =
					_objectFieldLocalService.getObjectField(
						objectEntry.getObjectDefinitionId(), "baseURL");

				throw new ObjectEntryValuesException.InvalidObjectField(
					String.format(
						"%s can have a maximum of 255 alphanumeric characters",
						objectField.getLabel(user.getLocale())),
					"x-can-have-a-maximum-of-255-alphanumeric-characters",
					Arrays.asList(objectField.getLabel(user.getLocale())));
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	private static final Pattern _baseURLPattern = Pattern.compile(
		"[a-zA-Z0-9-]{1,255}");

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private UserLocalService _userLocalService;

}