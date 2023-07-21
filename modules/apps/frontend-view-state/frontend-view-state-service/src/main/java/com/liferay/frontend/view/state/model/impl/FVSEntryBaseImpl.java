/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.view.state.model.impl;

import com.liferay.frontend.view.state.model.FVSEntry;
import com.liferay.frontend.view.state.service.FVSEntryLocalServiceUtil;

/**
 * The extended model base implementation for the FVSEntry service. Represents a row in the &quot;FVSEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FVSEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FVSEntryImpl
 * @see FVSEntry
 * @generated
 */
public abstract class FVSEntryBaseImpl
	extends FVSEntryModelImpl implements FVSEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a fvs entry model instance should use the <code>FVSEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			FVSEntryLocalServiceUtil.addFVSEntry(this);
		}
		else {
			FVSEntryLocalServiceUtil.updateFVSEntry(this);
		}
	}

}