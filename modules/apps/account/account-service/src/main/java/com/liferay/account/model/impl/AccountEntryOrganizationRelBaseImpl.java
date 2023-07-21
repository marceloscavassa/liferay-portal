/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model.impl;

import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.service.AccountEntryOrganizationRelLocalServiceUtil;

/**
 * The extended model base implementation for the AccountEntryOrganizationRel service. Represents a row in the &quot;AccountEntryOrganizationRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountEntryOrganizationRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryOrganizationRelImpl
 * @see AccountEntryOrganizationRel
 * @generated
 */
public abstract class AccountEntryOrganizationRelBaseImpl
	extends AccountEntryOrganizationRelModelImpl
	implements AccountEntryOrganizationRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account entry organization rel model instance should use the <code>AccountEntryOrganizationRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AccountEntryOrganizationRelLocalServiceUtil.
				addAccountEntryOrganizationRel(this);
		}
		else {
			AccountEntryOrganizationRelLocalServiceUtil.
				updateAccountEntryOrganizationRel(this);
		}
	}

}