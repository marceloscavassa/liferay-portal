/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.service.CPDAvailabilityEstimateLocalServiceUtil;

/**
 * The extended model base implementation for the CPDAvailabilityEstimate service. Represents a row in the &quot;CPDAvailabilityEstimate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDAvailabilityEstimateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CPDAvailabilityEstimateImpl
 * @see CPDAvailabilityEstimate
 * @generated
 */
public abstract class CPDAvailabilityEstimateBaseImpl
	extends CPDAvailabilityEstimateModelImpl
	implements CPDAvailabilityEstimate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cpd availability estimate model instance should use the <code>CPDAvailabilityEstimate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CPDAvailabilityEstimateLocalServiceUtil.addCPDAvailabilityEstimate(
				this);
		}
		else {
			CPDAvailabilityEstimateLocalServiceUtil.
				updateCPDAvailabilityEstimate(this);
		}
	}

}