/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;

/**
 * The extended model base implementation for the LayoutSet service. Represents a row in the &quot;LayoutSet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetImpl
 * @see LayoutSet
 * @generated
 */
public abstract class LayoutSetBaseImpl
	extends LayoutSetModelImpl implements LayoutSet {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout set model instance should use the <code>LayoutSet</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			LayoutSetLocalServiceUtil.addLayoutSet(this);
		}
		else {
			LayoutSetLocalServiceUtil.updateLayoutSet(this);
		}
	}

}