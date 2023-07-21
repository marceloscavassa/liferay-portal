/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.layout.prototype.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.BaseLocalizedColumnUpgradeProcess;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.util.PortalInstances;

/**
 * @author Leon Chi
 */
public class UpgradeLocalizedColumn extends BaseLocalizedColumnUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		upgradeLocalizedColumn(
			LanguageResources.PORTAL_RESOURCE_BUNDLE_LOADER, "LayoutPrototype",
			"name", _NAME, "layout-prototype-wiki-title", "Name", companyIds);

		upgradeLocalizedColumn(
			LanguageResources.PORTAL_RESOURCE_BUNDLE_LOADER, "LayoutPrototype",
			"description", _DESCRIPTION, "layout-prototype-wiki-description",
			"Description", companyIds);
	}

	private static final String _DESCRIPTION =
		"Collaborate with members through the wiki on this page. Discover " +
			"related content through tags, and navigate quickly and easily " +
				"with categories.";

	private static final String _NAME =
		"<?xml version='1.0' encoding='UTF-8'?><root available-locales=" +
			"\"en_US\" default-locale=\"en_US\"><Name language-id=\"en_US\">" +
				"Wiki</Name></root>";

}