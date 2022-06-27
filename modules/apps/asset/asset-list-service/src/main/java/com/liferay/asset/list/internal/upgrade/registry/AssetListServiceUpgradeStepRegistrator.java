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

package com.liferay.asset.list.internal.upgrade.registry;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.list.internal.upgrade.v1_4_0.AssetListEntryUsageUpgradeProcess;
import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class AssetListServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"AssetListEntry", "AssetListEntryAssetEntryRel",
						"AssetListEntrySegmentsEntryRel", "AssetListEntryUsage"
					};
				}

			});

		registry.register(
			"1.1.0", "1.2.0",
			new CTModelUpgradeProcess(
				"AssetListEntry", "AssetListEntryAssetEntryRel",
				"AssetListEntrySegmentsEntryRel", "AssetListEntryUsage"));

		registry.register(
			"1.2.0", "1.3.0",
			UpgradeProcessFactory.addColumns(
				"AssetListEntry", "assetEntrySubtype VARCHAR(255) null",
				"assetEntryType VARCHAR(255) null"),
			UpgradeProcessFactory.runSQL(
				"update AssetListEntry set assetEntryType = '" +
					AssetEntry.class.getName() + "'"));

		registry.register(
			"1.3.0", "1.4.0", new AssetListEntryUsageUpgradeProcess());

		registry.register(
			"1.4.0", "1.5.0",
			UpgradeProcessFactory.addColumns(
				"AssetListEntrySegmentsEntryRel",
				"priority INTEGER default 0 not null"),
			UpgradeProcessFactory.runSQL(
				"update AssetListEntrySegmentsEntryRel set priority = 1 " +
					"where segmentsEntryId = 0"));
	}

}