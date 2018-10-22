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

package com.liferay.site.navigation.menu.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.menu.web.internal.constants.SiteNavigationMenuPortletKeys;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SiteNavigationMenuPortletKeys.SITE_NAVIGATION_MENU,
	service = ExportImportPortletPreferencesProcessor.class
)
public class SiteNavigationMenuExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return ListUtil.toList(new Capability[] {_exportCapability});
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return ListUtil.toList(new Capability[] {_importCapability});
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			portletDataContext.addPortletPermissions(
				SiteNavigationConstants.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(
				"Unable to export portlet permissions", pe);
		}

		String portletId = portletDataContext.getPortletId();

		String siteNavigationMenuId = portletPreferences.getValue(
			"siteNavigationMenuId", null);
		long scopeGroupId = portletDataContext.getScopeGroupId();

		if (Validator.isNotNull(siteNavigationMenuId)) {
			SiteNavigationMenu siteNavigationMenu =
				_siteNavigationMenuLocalService.fetchSiteNavigationMenu(
					GetterUtil.getLong(siteNavigationMenuId));

			if (siteNavigationMenu != null) {
				String siteNavigationMenuUuid = siteNavigationMenu.getUuid();

				SiteNavigationMenu siteNavigationMenuToExport =
					_siteNavigationMenuLocalService.
						fetchSiteNavigationMenuByUuidAndGroupId(
							siteNavigationMenuUuid, scopeGroupId);

				if (siteNavigationMenuToExport != null) {
					StagedModelDataHandlerUtil.exportReferenceStagedModel(
						portletDataContext, portletId,
						siteNavigationMenuToExport);
				}
			}
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			portletDataContext.importPortletPermissions(
				SiteNavigationConstants.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(
				"Unable to import portlet permissions", pe);
		}

		String siteNavigationMenuId = portletPreferences.getValue(
			"siteNavigationMenuId", null);

		try {
			if (Validator.isNotNull(siteNavigationMenuId)) {
				StagedModelDataHandlerUtil.importReferenceStagedModel(
					portletDataContext, SiteNavigationMenu.class,
					siteNavigationMenuId);

				Map<Long, Long> siteNavigationMenuIds =
					(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
						SiteNavigationMenu.class);

				Long newSiteNavigationMenuId = siteNavigationMenuIds.get(
					GetterUtil.getLong(siteNavigationMenuId));

				if (newSiteNavigationMenuId != null) {
					portletPreferences.setValue(
						"siteNavigationMenuId",
						String.valueOf(newSiteNavigationMenuId));
				}
			}
		}
		catch (ReadOnlyException roe) {
			PortletDataException pde = new PortletDataException(roe);

			throw pde;
		}

		return portletPreferences;
	}

	@Reference(target = "(name=PortletDisplayTemplateExporter)")
	private Capability _exportCapability;

	@Reference(target = "(name=PortletDisplayTemplateImporter)")
	private Capability _importCapability;

	@Reference(unbind = "-")
	private SiteNavigationMenuLocalService _siteNavigationMenuLocalService;

}