/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.RegionLocalServiceUtil;

/**
 * The extended model base implementation for the Region service. Represents a row in the &quot;Region&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RegionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionImpl
 * @see Region
 * @generated
 */
public abstract class RegionBaseImpl extends RegionModelImpl implements Region {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a region model instance should use the <code>Region</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			RegionLocalServiceUtil.addRegion(this);
		}
		else {
			RegionLocalServiceUtil.updateRegion(this);
		}
	}

}