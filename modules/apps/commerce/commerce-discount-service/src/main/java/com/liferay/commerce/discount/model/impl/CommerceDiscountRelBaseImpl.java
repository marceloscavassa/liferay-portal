/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceDiscountRel service. Represents a row in the &quot;CommerceDiscountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDiscountRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountRelImpl
 * @see CommerceDiscountRel
 * @generated
 */
public abstract class CommerceDiscountRelBaseImpl
	extends CommerceDiscountRelModelImpl implements CommerceDiscountRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce discount rel model instance should use the <code>CommerceDiscountRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceDiscountRelLocalServiceUtil.addCommerceDiscountRel(this);
		}
		else {
			CommerceDiscountRelLocalServiceUtil.updateCommerceDiscountRel(this);
		}
	}

}