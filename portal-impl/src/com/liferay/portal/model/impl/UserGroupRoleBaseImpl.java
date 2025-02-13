/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;

/**
 * The extended model base implementation for the UserGroupRole service. Represents a row in the &quot;UserGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRoleImpl
 * @see UserGroupRole
 * @generated
 */
public abstract class UserGroupRoleBaseImpl
	extends UserGroupRoleModelImpl implements UserGroupRole {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group role model instance should use the <code>UserGroupRole</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			UserGroupRoleLocalServiceUtil.addUserGroupRole(this);
		}
		else {
			UserGroupRoleLocalServiceUtil.updateUserGroupRole(this);
		}
	}

}