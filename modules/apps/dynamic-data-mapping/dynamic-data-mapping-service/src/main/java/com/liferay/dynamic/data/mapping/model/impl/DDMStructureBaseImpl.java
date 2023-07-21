/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;

/**
 * The extended model base implementation for the DDMStructure service. Represents a row in the &quot;DDMStructure&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStructureImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureImpl
 * @see DDMStructure
 * @generated
 */
public abstract class DDMStructureBaseImpl
	extends DDMStructureModelImpl implements DDMStructure {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm structure model instance should use the <code>DDMStructure</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DDMStructureLocalServiceUtil.addDDMStructure(this);
		}
		else {
			DDMStructureLocalServiceUtil.updateDDMStructure(this);
		}
	}

}