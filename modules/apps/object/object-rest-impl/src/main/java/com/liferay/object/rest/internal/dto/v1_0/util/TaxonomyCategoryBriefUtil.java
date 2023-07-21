/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.dto.v1_0.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.object.rest.dto.v1_0.TaxonomyCategoryBrief;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.util.Collections;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * @author Javier Gamarra
 */
public class TaxonomyCategoryBriefUtil {

	public static TaxonomyCategoryBrief toTaxonomyCategoryBrief(
			AssetCategory assetCategory,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		return new TaxonomyCategoryBrief() {
			{
				embeddedTaxonomyCategory = _toTaxonomyCategory(
					assetCategory.getCategoryId(), dtoConverterContext);
				taxonomyCategoryId = assetCategory.getCategoryId();
				taxonomyCategoryName = assetCategory.getTitle(
					dtoConverterContext.getLocale());
				taxonomyCategoryName_i18n = LocalizedMapUtil.getI18nMap(
					dtoConverterContext.isAcceptAllLanguages(),
					assetCategory.getTitleMap());
			}
		};
	}

	private static Object _toTaxonomyCategory(
			long categoryId, DTOConverterContext dtoConverterContext)
		throws Exception {

		UriInfo uriInfo = dtoConverterContext.getUriInfo();

		if (uriInfo == null) {
			return null;
		}

		MultivaluedMap<String, String> queryParameters =
			uriInfo.getQueryParameters();

		String nestedFields = queryParameters.getFirst("nestedFields");

		if (Validator.isNotNull(nestedFields) &&
			nestedFields.contains("embeddedTaxonomyCategory")) {

			DTOConverterRegistry dtoConverterRegistry =
				dtoConverterContext.getDTOConverterRegistry();

			DTOConverter<?, ?> dtoConverter =
				dtoConverterRegistry.getDTOConverter(
					"Liferay.Headless.Admin.Taxonomy",
					AssetCategory.class.getName(), "v1.0");

			if (dtoConverter == null) {
				return null;
			}

			return dtoConverter.toDTO(
				new DefaultDTOConverterContext(
					dtoConverterContext.isAcceptAllLanguages(),
					Collections.emptyMap(), dtoConverterRegistry,
					dtoConverterContext.getHttpServletRequest(), categoryId,
					dtoConverterContext.getLocale(), uriInfo,
					dtoConverterContext.getUser()));
		}

		return null;
	}

}