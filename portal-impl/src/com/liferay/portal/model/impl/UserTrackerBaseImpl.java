/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.service.UserTrackerLocalServiceUtil;

/**
 * The extended model base implementation for the UserTracker service. Represents a row in the &quot;UserTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserTrackerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerImpl
 * @see UserTracker
 * @generated
 */
public abstract class UserTrackerBaseImpl
	extends UserTrackerModelImpl implements UserTracker {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user tracker model instance should use the <code>UserTracker</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			UserTrackerLocalServiceUtil.addUserTracker(this);
		}
		else {
			UserTrackerLocalServiceUtil.updateUserTracker(this);
		}
	}

}