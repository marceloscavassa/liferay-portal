/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(
	property = "content.processor.type=FragmentEntryLinkEditableValues",
	service = ExportImportContentProcessor.class
)
public class
	NavigationMenuSelectorEditableValuesConfigurationExportImportContentProcessor
		extends BaseEditableValuesConfigurationExportImportContentProcessor {

	@Override
	protected String getConfigurationType() {
		return "navigationMenuSelector";
	}

	@Override
	protected FragmentEntryConfigurationParser
		getFragmentEntryConfigurationParser() {

		return _fragmentEntryConfigurationParser;
	}

	@Override
	protected void replaceExportContentReferences(
			PortletDataContext portletDataContext,
			StagedModel referrerStagedModel,
			JSONObject configurationValueJSONObject,
			boolean exportReferencedContent)
		throws Exception {

		long siteNavigationMenuId = configurationValueJSONObject.getLong(
			"siteNavigationMenuId");

		StagedModel stagedModel = null;

		if (siteNavigationMenuId > 0) {
			stagedModel =
				_siteNavigationMenuLocalService.fetchSiteNavigationMenu(
					siteNavigationMenuId);
		}
		else {
			stagedModel = _layoutLocalService.fetchLayout(
				configurationValueJSONObject.getLong(
					"parentSiteNavigationMenuItemId"));
		}

		if (stagedModel == null) {
			return;
		}

		if (exportReferencedContent) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, referrerStagedModel, stagedModel,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
		}
		else {
			Element entityElement = portletDataContext.getExportDataElement(
				referrerStagedModel);

			portletDataContext.addReferenceElement(
				referrerStagedModel, entityElement, stagedModel,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}
	}

	@Override
	protected void replaceImportContentReferences(
		PortletDataContext portletDataContext,
		JSONObject configurationValueJSONObject) {

		long siteNavigationMenuId = configurationValueJSONObject.getLong(
			"siteNavigationMenuId");

		long parentSiteNavigationMenuItemId =
			configurationValueJSONObject.getLong(
				"parentSiteNavigationMenuItemId");

		if (siteNavigationMenuId == 0) {
			Map<Long, Long> layoutNewPrimaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					Layout.class.getName());

			configurationValueJSONObject.put(
				"parentSiteNavigationMenuItemId",
				layoutNewPrimaryKeys.getOrDefault(
					parentSiteNavigationMenuItemId, 0L));
		}
		else {
			Map<Long, Long> siteNavigationMenuNewPrimaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					SiteNavigationMenu.class.getName());

			configurationValueJSONObject.put(
				"siteNavigationMenuId",
				siteNavigationMenuNewPrimaryKeys.getOrDefault(
					siteNavigationMenuId, 0L));

			Map<Long, Long> siteNavigationMenuItemNewPrimaryKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					SiteNavigationMenuItem.class.getName());

			configurationValueJSONObject.put(
				"parentSiteNavigationMenuItemId",
				siteNavigationMenuItemNewPrimaryKeys.getOrDefault(
					parentSiteNavigationMenuItemId, 0L));
		}
	}

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private SiteNavigationMenuLocalService _siteNavigationMenuLocalService;

}