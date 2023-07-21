/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model.impl;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;

/**
 * The extended model base implementation for the ObjectEntry service. Represents a row in the &quot;ObjectEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ObjectEntryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectEntryImpl
 * @see ObjectEntry
 * @generated
 */
public abstract class ObjectEntryBaseImpl
	extends ObjectEntryModelImpl implements ObjectEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a object entry model instance should use the <code>ObjectEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ObjectEntryLocalServiceUtil.addObjectEntry(this);
		}
		else {
			ObjectEntryLocalServiceUtil.updateObjectEntry(this);
		}
	}

}