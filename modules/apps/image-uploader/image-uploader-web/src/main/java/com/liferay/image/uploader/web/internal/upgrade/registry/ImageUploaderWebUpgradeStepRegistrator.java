/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.image.uploader.web.internal.upgrade.registry;

import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.image.uploader.web.internal.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.image.uploader.web.internal.upgrade.v1_0_1.DLFolderUpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(service = UpgradeStepRegistrator.class)
public class ImageUploaderWebUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.registerInitialization();

		registry.register("0.0.1", "1.0.0", new UpgradePortletId());

		registry.register(
			"1.0.0", "1.0.1",
			new DLFolderUpgradeProcess(_dlFolderLocalService));
	}

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

}