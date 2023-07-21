/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.model.impl;

import com.liferay.layout.model.LayoutLocalization;
import com.liferay.layout.service.LayoutLocalizationLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutLocalization service. Represents a row in the &quot;LayoutLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutLocalizationImpl
 * @see LayoutLocalization
 * @generated
 */
public abstract class LayoutLocalizationBaseImpl
	extends LayoutLocalizationModelImpl implements LayoutLocalization {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout localization model instance should use the <code>LayoutLocalization</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			LayoutLocalizationLocalServiceUtil.addLayoutLocalization(this);
		}
		else {
			LayoutLocalizationLocalServiceUtil.updateLayoutLocalization(this);
		}
	}

}