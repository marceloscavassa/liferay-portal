/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.admin.taxonomy.internal.dto.v1_0.action.metadata;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.headless.admin.taxonomy.internal.resource.v1_0.TaxonomyCategoryResourceImpl;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.vulcan.dto.action.ActionInfo;

/**
 * @author Javier Gamarra
 */
public class TaxonomyCategoryDTOActionMetadataProvider
	extends BaseTaxonomyCategoryDTOActionMetadataProvider {

	public TaxonomyCategoryDTOActionMetadataProvider() {
		registerActionInfo(
			"add-category",
			new ActionInfo(
				ActionKeys.ADD_CATEGORY, TaxonomyCategoryResourceImpl.class,
				"postTaxonomyCategoryTaxonomyCategory"));
	}

	@Override
	public String getPermissionName() {
		return AssetCategory.class.getName();
	}

}