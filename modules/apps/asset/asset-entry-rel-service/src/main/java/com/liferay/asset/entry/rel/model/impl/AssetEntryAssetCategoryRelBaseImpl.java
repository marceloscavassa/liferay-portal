/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.entry.rel.model.impl;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;

/**
 * The extended model base implementation for the AssetEntryAssetCategoryRel service. Represents a row in the &quot;AssetEntryAssetCategoryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetEntryAssetCategoryRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetCategoryRelImpl
 * @see AssetEntryAssetCategoryRel
 * @generated
 */
public abstract class AssetEntryAssetCategoryRelBaseImpl
	extends AssetEntryAssetCategoryRelModelImpl
	implements AssetEntryAssetCategoryRel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset entry asset category rel model instance should use the <code>AssetEntryAssetCategoryRel</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AssetEntryAssetCategoryRelLocalServiceUtil.
				addAssetEntryAssetCategoryRel(this);
		}
		else {
			AssetEntryAssetCategoryRelLocalServiceUtil.
				updateAssetEntryAssetCategoryRel(this);
		}
	}

}