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

package com.liferay.dynamic.data.mapping.form.taglib.internal.servlet.taglib.util;

import com.liferay.dynamic.data.mapping.form.builder.context.DDMFormBuilderContextFactory;
import com.liferay.dynamic.data.mapping.form.builder.context.DDMFormBuilderContextRequest;
import com.liferay.dynamic.data.mapping.form.builder.context.DDMFormBuilderContextResponse;
import com.liferay.dynamic.data.mapping.form.builder.settings.DDMFormBuilderSettingsRequest;
import com.liferay.dynamic.data.mapping.form.builder.settings.DDMFormBuilderSettingsResponse;
import com.liferay.dynamic.data.mapping.form.builder.settings.DDMFormBuilderSettingsRetriever;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(service = {})
public class DDMFormTaglibUtil {

	public static DDMForm getDDMForm(
		long ddmStructureId, long ddmStructureVersionId) {

		if (ddmStructureVersionId > 0) {
			DDMStructureVersion ddmStructureVersion =
				_ddmStructureVersionLocalService.fetchDDMStructureVersion(
					ddmStructureVersionId);

			if (ddmStructureVersion != null) {
				return ddmStructureVersion.getDDMForm();
			}
		}

		if (ddmStructureId > 0) {
			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchDDMStructure(ddmStructureId);

			if (ddmStructure != null) {
				return ddmStructure.getDDMForm();
			}
		}

		return new DDMForm();
	}

	public static DDMFormBuilderSettingsResponse getDDMFormBuilderSettings(
		DDMFormBuilderSettingsRequest ddmFormBuilderSettingsRequest) {

		if (_ddmFormBuilderSettingsRetriever == null) {
			throw new IllegalStateException();
		}

		DDMFormBuilderSettingsRetriever ddmFormBuilderSettingsRetriever =
			_ddmFormBuilderSettingsRetriever;

		return ddmFormBuilderSettingsRetriever.getSettings(
			ddmFormBuilderSettingsRequest);
	}

	public static String getFormBuilderContext(
		long ddmStructureId, long ddmStructureVersionId,
		HttpServletRequest httpServletRequest) {

		String serializedFormBuilderContext = ParamUtil.getString(
			httpServletRequest, "serializedFormBuilderContext");

		if (Validator.isNotNull(serializedFormBuilderContext)) {
			return serializedFormBuilderContext;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		JSONSerializer jsonSerializer = _jsonFactory.createJSONSerializer();

		DDMStructure ddmStructure = _ddmStructureLocalService.fetchDDMStructure(
			ddmStructureId);

		DDMStructureVersion ddmStructureVersion =
			_ddmStructureVersionLocalService.fetchDDMStructureVersion(
				ddmStructureVersionId);

		Locale locale = themeDisplay.getSiteDefaultLocale();

		if ((ddmStructure != null) || (ddmStructureVersion != null)) {
			DDMForm ddmForm = getDDMForm(ddmStructureId, ddmStructureVersionId);

			locale = ddmForm.getDefaultLocale();
		}

		DDMFormBuilderContextRequest ddmFormBuilderContextRequest =
			DDMFormBuilderContextRequest.with(
				ddmStructure, themeDisplay.getRequest(),
				themeDisplay.getResponse(), locale, true);

		ddmFormBuilderContextRequest.addProperty(
			"ddmStructureVersion", ddmStructureVersion);

		DDMFormBuilderContextResponse ddmFormBuilderContextResponse =
			_ddmFormBuilderContextFactory.create(ddmFormBuilderContextRequest);

		return jsonSerializer.serializeDeep(
			ddmFormBuilderContextResponse.getContext());
	}

	public static String getNPMResolvedPackageName() {
		return _npmResolver.resolveModuleName(
			"dynamic-data-mapping-form-builder");
	}

	@Reference(unbind = "-")
	protected void setDDMFormBuilderContextFactory(
		DDMFormBuilderContextFactory ddmFormBuilderContextFactory) {

		_ddmFormBuilderContextFactory = ddmFormBuilderContextFactory;
	}

	@Reference(unbind = "-")
	protected void setDDMFormBuilderSettingsRetriever(
		DDMFormBuilderSettingsRetriever ddmFormBuilderSettingsRetriever) {

		_ddmFormBuilderSettingsRetriever = ddmFormBuilderSettingsRetriever;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureVersionLocalService(
		DDMStructureVersionLocalService ddmStructureVersionLocalService) {

		_ddmStructureVersionLocalService = ddmStructureVersionLocalService;
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Reference(unbind = "-")
	protected void setNPMResolver(NPMResolver npmResolver) {
		_npmResolver = npmResolver;
	}

	private static DDMFormBuilderContextFactory _ddmFormBuilderContextFactory;
	private static DDMFormBuilderSettingsRetriever
		_ddmFormBuilderSettingsRetriever;
	private static DDMStructureLocalService _ddmStructureLocalService;
	private static DDMStructureVersionLocalService
		_ddmStructureVersionLocalService;
	private static JSONFactory _jsonFactory;
	private static NPMResolver _npmResolver;

}