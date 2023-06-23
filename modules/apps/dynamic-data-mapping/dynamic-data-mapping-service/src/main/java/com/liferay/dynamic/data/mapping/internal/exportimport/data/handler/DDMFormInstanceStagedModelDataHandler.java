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

package com.liferay.dynamic.data.mapping.internal.exportimport.data.handler;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceSettings;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.util.DDMFormFactory;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = StagedModelDataHandler.class)
public class DDMFormInstanceStagedModelDataHandler
	extends BaseStagedModelDataHandler<DDMFormInstance> {

	public static final String[] CLASS_NAMES = {
		DDMFormInstance.class.getName()
	};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(DDMFormInstance formInstance) {
		return formInstance.getNameCurrentValue();
	}

	protected DDMFormValues deserialize(String content, DDMForm ddmForm) {
		DDMFormValuesDeserializerDeserializeRequest.Builder builder =
			DDMFormValuesDeserializerDeserializeRequest.Builder.newBuilder(
				content, ddmForm);

		DDMFormValuesDeserializerDeserializeResponse
			ddmFormValuesDeserializerDeserializeResponse =
				_jsonDDMFormValuesDeserializer.deserialize(builder.build());

		return ddmFormValuesDeserializerDeserializeResponse.getDDMFormValues();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, DDMFormInstance formInstance)
		throws Exception {

		DDMStructure ddmStructure = formInstance.getStructure();

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, formInstance, ddmStructure,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		List<DDMTemplate> ddmTemplates = ddmStructure.getTemplates();

		Element formInstanceElement = portletDataContext.getExportDataElement(
			formInstance);

		for (DDMTemplate ddmTemplate : ddmTemplates) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, formInstance, ddmTemplate,
				PortletDataContext.REFERENCE_TYPE_STRONG);
		}

		_exportFormInstanceSettings(
			formInstance, formInstanceElement, portletDataContext,
			ddmStructure.getStorageType());

		portletDataContext.addClassedModel(
			formInstanceElement,
			ExportImportPathUtil.getModelPath(formInstance), formInstance);
	}

	@Override
	protected void doImportMissingReference(
			PortletDataContext portletDataContext, String uuid, long groupId,
			long formInstanceId)
		throws Exception {

		DDMFormInstance existingFormInstance = fetchMissingReference(
			uuid, groupId);

		if (existingFormInstance == null) {
			return;
		}

		Map<Long, Long> formInstanceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDMFormInstance.class);

		formInstanceIds.put(
			formInstanceId, existingFormInstance.getFormInstanceId());
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, DDMFormInstance formInstance)
		throws Exception {

		Map<Long, Long> ddmStructureIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDMStructure.class);

		long ddmStructureId = MapUtil.getLong(
			ddmStructureIds, formInstance.getStructureId(),
			formInstance.getStructureId());

		DDMFormInstance importedFormInstance =
			(DDMFormInstance)formInstance.clone();

		importedFormInstance.setGroupId(portletDataContext.getScopeGroupId());
		importedFormInstance.setStructureId(ddmStructureId);

		DDMFormInstance existingFormInstance =
			_stagedModelRepository.fetchStagedModelByUuidAndGroupId(
				formInstance.getUuid(), portletDataContext.getScopeGroupId());

		if ((existingFormInstance == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			importedFormInstance = _stagedModelRepository.addStagedModel(
				portletDataContext, importedFormInstance);
		}
		else {
			importedFormInstance.setMvccVersion(
				existingFormInstance.getMvccVersion());
			importedFormInstance.setFormInstanceId(
				existingFormInstance.getFormInstanceId());

			importedFormInstance = _stagedModelRepository.updateStagedModel(
				portletDataContext, importedFormInstance);
		}

		Element formInstanceElement = portletDataContext.getImportDataElement(
			formInstance);

		DDMStructure ddmStructure = _ddmStructureLocalService.getDDMStructure(
			importedFormInstance.getStructureId());

		_ddmFormInstanceLocalService.updateFormInstance(
			importedFormInstance.getFormInstanceId(),
			importedFormInstance.getStructureId(),
			importedFormInstance.getNameMap(),
			importedFormInstance.getDescriptionMap(),
			_getImportFormInstanceSettings(
				formInstanceElement, portletDataContext,
				ddmStructure.getStorageType()),
			portletDataContext.createServiceContext(importedFormInstance));

		portletDataContext.importClassedModel(
			formInstance, importedFormInstance);
	}

	@Override
	protected StagedModelRepository<DDMFormInstance>
		getStagedModelRepository() {

		return _stagedModelRepository;
	}

	private void _exportFormInstanceSettings(
			DDMFormInstance formInstance, Element formInstanceElement,
			PortletDataContext portletDataContext, String storageType)
		throws Exception {

		String settingsDDMFormValuesPath = ExportImportPathUtil.getModelPath(
			formInstance, "settings-ddm-form-values.json");

		formInstanceElement.addAttribute(
			"settings-ddm-form-values-path", settingsDDMFormValuesPath);

		String formInstanceString = formInstance.getSettings();

		if (StringUtil.equals(storageType, "object")) {
			formInstanceString = _includeObjectDefinitionExternalReferenceCode(
				_jsonFactory.createJSONObject(formInstanceString));
		}

		portletDataContext.addZipEntry(
			settingsDDMFormValuesPath, formInstanceString);
	}

	private DDMFormValues _getImportFormInstanceSettings(
			Element formInstanceElement, PortletDataContext portletDataContext,
			String storageType)
		throws Exception {

		String serializedSettingsDDMFormValues =
			portletDataContext.getZipEntryAsString(
				formInstanceElement.attributeValue(
					"settings-ddm-form-values-path"));

		if (StringUtil.equals(storageType, "object")) {
			serializedSettingsDDMFormValues = _updateObjectDefinitionId(
				_jsonFactory.createJSONObject(serializedSettingsDDMFormValues));
		}

		return deserialize(
			serializedSettingsDDMFormValues,
			DDMFormFactory.create(DDMFormInstanceSettings.class));
	}

	private String _includeObjectDefinitionExternalReferenceCode(
		JSONObject formInstanceSettingsJSONObject) {

		JSONArray fieldValuesJSONArray =
			formInstanceSettingsJSONObject.getJSONArray("fieldValues");

		ObjectDefinition objectDefinition = null;

		for (int i = 0; i < fieldValuesJSONArray.length(); i++) {
			JSONObject fieldValueJSONObject =
				fieldValuesJSONArray.getJSONObject(i);

			if (StringUtil.equals(
					fieldValueJSONObject.getString("name"),
					"objectDefinitionId")) {

				objectDefinition =
					_objectDefinitionLocalService.fetchObjectDefinition(
						GetterUtil.getLong(
							StringUtil.removeSubstrings(
								fieldValueJSONObject.getString("value"), "[\"",
								"\"]")));

				break;
			}
		}

		if (objectDefinition == null) {
			return formInstanceSettingsJSONObject.toString();
		}

		formInstanceSettingsJSONObject.put(
			"fieldValues",
			fieldValuesJSONArray.put(
				JSONUtil.put(
					"name", "objectDefinitionExternalReferenceCode"
				).put(
					"value", objectDefinition.getExternalReferenceCode()
				)));

		return formInstanceSettingsJSONObject.toString();
	}

	private String _updateObjectDefinitionId(
		JSONObject formInstanceSettingsJSONObject) {

		JSONArray fieldValuesJSONArray =
			formInstanceSettingsJSONObject.getJSONArray("fieldValues");
		JSONArray newFieldValuesJSONArray = _jsonFactory.createJSONArray();

		String objectDefinitionExternalReferenceCode = null;
		JSONObject objectDefinitionIdJSONObject = null;

		for (int i = 0; i < fieldValuesJSONArray.length(); i++) {
			JSONObject fieldValueJSONObject =
				fieldValuesJSONArray.getJSONObject(i);

			if (StringUtil.equals(
					fieldValueJSONObject.getString("name"),
					"objectDefinitionExternalReferenceCode")) {

				objectDefinitionExternalReferenceCode =
					fieldValueJSONObject.getString("value");

				continue;
			}
			else if (StringUtil.equals(
						fieldValueJSONObject.getString("name"),
						"objectDefinitionId")) {

				objectDefinitionIdJSONObject = fieldValueJSONObject;
			}

			newFieldValuesJSONArray.put(fieldValueJSONObject);
		}

		formInstanceSettingsJSONObject.put(
			"fieldValues", newFieldValuesJSONArray);

		if (Validator.isNull(objectDefinitionExternalReferenceCode)) {
			return formInstanceSettingsJSONObject.toString();
		}

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				fetchObjectDefinitionByExternalReferenceCode(
					objectDefinitionExternalReferenceCode,
					CompanyThreadLocal.getCompanyId());

		if ((objectDefinition != null) &&
			(objectDefinitionIdJSONObject != null)) {

			objectDefinitionIdJSONObject.put(
				"value",
				"[\"" + objectDefinition.getObjectDefinitionId() + "\"]");
		}

		return formInstanceSettingsJSONObject.toString();
	}

	@Reference
	private DDMFormInstanceLocalService _ddmFormInstanceLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference(target = "(ddm.form.values.deserializer.type=json)")
	private DDMFormValuesDeserializer _jsonDDMFormValuesDeserializer;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstance)"
	)
	private StagedModelRepository<DDMFormInstance> _stagedModelRepository;

}