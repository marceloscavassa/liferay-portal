/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.CompanyInfo;
import com.liferay.portal.kernel.service.CompanyInfoLocalServiceUtil;

/**
 * The extended model base implementation for the CompanyInfo service. Represents a row in the &quot;CompanyInfo&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CompanyInfoImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompanyInfoImpl
 * @see CompanyInfo
 * @generated
 */
public abstract class CompanyInfoBaseImpl
	extends CompanyInfoModelImpl implements CompanyInfo {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a company info model instance should use the <code>CompanyInfo</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CompanyInfoLocalServiceUtil.addCompanyInfo(this);
		}
		else {
			CompanyInfoLocalServiceUtil.updateCompanyInfo(this);
		}
	}

}