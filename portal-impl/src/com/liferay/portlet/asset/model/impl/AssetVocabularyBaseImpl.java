/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model.impl;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;

/**
 * The extended model base implementation for the AssetVocabulary service. Represents a row in the &quot;AssetVocabulary&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetVocabularyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetVocabularyImpl
 * @see AssetVocabulary
 * @generated
 */
public abstract class AssetVocabularyBaseImpl
	extends AssetVocabularyModelImpl implements AssetVocabulary {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset vocabulary model instance should use the <code>AssetVocabulary</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AssetVocabularyLocalServiceUtil.addAssetVocabulary(this);
		}
		else {
			AssetVocabularyLocalServiceUtil.updateAssetVocabulary(this);
		}
	}

}