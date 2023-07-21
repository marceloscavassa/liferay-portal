/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.service.CountryLocalServiceUtil;

/**
 * The extended model base implementation for the Country service. Represents a row in the &quot;Country&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CountryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryImpl
 * @see Country
 * @generated
 */
public abstract class CountryBaseImpl
	extends CountryModelImpl implements Country {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a country model instance should use the <code>Country</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CountryLocalServiceUtil.addCountry(this);
		}
		else {
			CountryLocalServiceUtil.updateCountry(this);
		}
	}

}