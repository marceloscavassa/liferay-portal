/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.portal.tools.service.builder.test.model.DSLQueryEntry;
import com.liferay.portal.tools.service.builder.test.service.DSLQueryEntryLocalServiceUtil;

/**
 * The extended model base implementation for the DSLQueryEntry service. Represents a row in the &quot;DSLQueryEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DSLQueryEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DSLQueryEntryImpl
 * @see DSLQueryEntry
 * @generated
 */
public abstract class DSLQueryEntryBaseImpl
	extends DSLQueryEntryModelImpl implements DSLQueryEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dsl query entry model instance should use the <code>DSLQueryEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DSLQueryEntryLocalServiceUtil.addDSLQueryEntry(this);
		}
		else {
			DSLQueryEntryLocalServiceUtil.updateDSLQueryEntry(this);
		}
	}

}