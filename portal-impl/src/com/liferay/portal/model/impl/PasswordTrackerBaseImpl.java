/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.PasswordTracker;
import com.liferay.portal.kernel.service.PasswordTrackerLocalServiceUtil;

/**
 * The extended model base implementation for the PasswordTracker service. Represents a row in the &quot;PasswordTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordTrackerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordTrackerImpl
 * @see PasswordTracker
 * @generated
 */
public abstract class PasswordTrackerBaseImpl
	extends PasswordTrackerModelImpl implements PasswordTracker {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password tracker model instance should use the <code>PasswordTracker</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			PasswordTrackerLocalServiceUtil.addPasswordTracker(this);
		}
		else {
			PasswordTrackerLocalServiceUtil.updatePasswordTracker(this);
		}
	}

}