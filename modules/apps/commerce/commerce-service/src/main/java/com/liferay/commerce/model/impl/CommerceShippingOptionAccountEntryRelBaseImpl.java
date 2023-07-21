/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShippingOptionAccountEntryRel;
import com.liferay.commerce.service.CommerceShippingOptionAccountEntryRelLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceShippingOptionAccountEntryRel service. Represents a row in the &quot;CSOptionAccountEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShippingOptionAccountEntryRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingOptionAccountEntryRelImpl
 * @see CommerceShippingOptionAccountEntryRel
 * @generated
 */
public abstract class CommerceShippingOptionAccountEntryRelBaseImpl
	extends CommerceShippingOptionAccountEntryRelModelImpl
	implements CommerceShippingOptionAccountEntryRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipping option account entry rel model instance should use the <code>CommerceShippingOptionAccountEntryRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceShippingOptionAccountEntryRelLocalServiceUtil.
				addCommerceShippingOptionAccountEntryRel(this);
		}
		else {
			CommerceShippingOptionAccountEntryRelLocalServiceUtil.
				updateCommerceShippingOptionAccountEntryRel(this);
		}
	}

}