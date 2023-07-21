/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.model.impl;

import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelLocalServiceUtil;

/**
 * The extended model base implementation for the CommercePricingClassCPDefinitionRel service. Represents a row in the &quot;CPricingClassCPDefinitionRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePricingClassCPDefinitionRelImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommercePricingClassCPDefinitionRelImpl
 * @see CommercePricingClassCPDefinitionRel
 * @generated
 */
public abstract class CommercePricingClassCPDefinitionRelBaseImpl
	extends CommercePricingClassCPDefinitionRelModelImpl
	implements CommercePricingClassCPDefinitionRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce pricing class cp definition rel model instance should use the <code>CommercePricingClassCPDefinitionRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommercePricingClassCPDefinitionRelLocalServiceUtil.
				addCommercePricingClassCPDefinitionRel(this);
		}
		else {
			CommercePricingClassCPDefinitionRelLocalServiceUtil.
				updateCommercePricingClassCPDefinitionRel(this);
		}
	}

}