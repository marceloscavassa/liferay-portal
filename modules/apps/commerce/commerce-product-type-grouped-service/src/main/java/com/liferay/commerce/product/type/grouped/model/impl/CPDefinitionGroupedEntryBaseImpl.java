/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.grouped.model.impl;

import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.commerce.product.type.grouped.service.CPDefinitionGroupedEntryLocalServiceUtil;

/**
 * The extended model base implementation for the CPDefinitionGroupedEntry service. Represents a row in the &quot;CPDefinitionGroupedEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDefinitionGroupedEntryImpl}.
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see CPDefinitionGroupedEntryImpl
 * @see CPDefinitionGroupedEntry
 * @generated
 */
public abstract class CPDefinitionGroupedEntryBaseImpl
	extends CPDefinitionGroupedEntryModelImpl
	implements CPDefinitionGroupedEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition grouped entry model instance should use the <code>CPDefinitionGroupedEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CPDefinitionGroupedEntryLocalServiceUtil.
				addCPDefinitionGroupedEntry(this);
		}
		else {
			CPDefinitionGroupedEntryLocalServiceUtil.
				updateCPDefinitionGroupedEntry(this);
		}
	}

}