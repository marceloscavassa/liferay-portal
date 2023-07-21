/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil;

/**
 * The extended model base implementation for the PasswordPolicy service. Represents a row in the &quot;PasswordPolicy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordPolicyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyImpl
 * @see PasswordPolicy
 * @generated
 */
public abstract class PasswordPolicyBaseImpl
	extends PasswordPolicyModelImpl implements PasswordPolicy {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password policy model instance should use the <code>PasswordPolicy</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			PasswordPolicyLocalServiceUtil.addPasswordPolicy(this);
		}
		else {
			PasswordPolicyLocalServiceUtil.updatePasswordPolicy(this);
		}
	}

}