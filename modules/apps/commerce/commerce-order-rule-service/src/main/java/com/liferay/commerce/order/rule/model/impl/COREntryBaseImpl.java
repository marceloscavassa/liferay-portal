/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.rule.model.impl;

import com.liferay.commerce.order.rule.model.COREntry;
import com.liferay.commerce.order.rule.service.COREntryLocalServiceUtil;

/**
 * The extended model base implementation for the COREntry service. Represents a row in the &quot;COREntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link COREntryImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see COREntryImpl
 * @see COREntry
 * @generated
 */
public abstract class COREntryBaseImpl
	extends COREntryModelImpl implements COREntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cor entry model instance should use the <code>COREntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			COREntryLocalServiceUtil.addCOREntry(this);
		}
		else {
			COREntryLocalServiceUtil.updateCOREntry(this);
		}
	}

}