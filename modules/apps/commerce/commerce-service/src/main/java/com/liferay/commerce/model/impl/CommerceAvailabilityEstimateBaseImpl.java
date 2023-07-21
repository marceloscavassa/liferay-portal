/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.service.CommerceAvailabilityEstimateLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceAvailabilityEstimate service. Represents a row in the &quot;CommerceAvailabilityEstimate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAvailabilityEstimateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAvailabilityEstimateImpl
 * @see CommerceAvailabilityEstimate
 * @generated
 */
public abstract class CommerceAvailabilityEstimateBaseImpl
	extends CommerceAvailabilityEstimateModelImpl
	implements CommerceAvailabilityEstimate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce availability estimate model instance should use the <code>CommerceAvailabilityEstimate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceAvailabilityEstimateLocalServiceUtil.
				addCommerceAvailabilityEstimate(this);
		}
		else {
			CommerceAvailabilityEstimateLocalServiceUtil.
				updateCommerceAvailabilityEstimate(this);
		}
	}

}