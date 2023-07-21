/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.exportimport.model.impl;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;

/**
 * The extended model base implementation for the ExportImportConfiguration service. Represents a row in the &quot;ExportImportConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExportImportConfigurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportConfigurationImpl
 * @see ExportImportConfiguration
 * @generated
 */
public abstract class ExportImportConfigurationBaseImpl
	extends ExportImportConfigurationModelImpl
	implements ExportImportConfiguration {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a export import configuration model instance should use the <code>ExportImportConfiguration</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ExportImportConfigurationLocalServiceUtil.
				addExportImportConfiguration(this);
		}
		else {
			ExportImportConfigurationLocalServiceUtil.
				updateExportImportConfiguration(this);
		}
	}

}