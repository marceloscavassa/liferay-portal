/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductOptionValueUtil {

	public static CPDefinitionOptionValueRel
			addOrUpdateCPDefinitionOptionValueRel(
				CPDefinitionOptionValueRelService
					cpDefinitionOptionValueRelService,
				ProductOptionValue productOptionValue,
				long cpDefinitionOptionRelId, ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			cpDefinitionOptionValueRelService.fetchCPDefinitionOptionValueRel(
				cpDefinitionOptionRelId, productOptionValue.getKey());

		if (cpDefinitionOptionValueRel == null) {
			cpDefinitionOptionValueRel =
				cpDefinitionOptionValueRelService.addCPDefinitionOptionValueRel(
					cpDefinitionOptionRelId,
					LanguageUtils.getLocalizedMap(productOptionValue.getName()),
					GetterUtil.get(productOptionValue.getPriority(), 0D),
					productOptionValue.getKey(), serviceContext);
		}
		else {
			cpDefinitionOptionValueRel =
				cpDefinitionOptionValueRelService.
					updateCPDefinitionOptionValueRel(
						cpDefinitionOptionValueRel.
							getCPDefinitionOptionValueRelId(),
						LanguageUtils.getLocalizedMap(
							productOptionValue.getName()),
						GetterUtil.get(
							productOptionValue.getPriority(),
							cpDefinitionOptionValueRel.getPriority()),
						productOptionValue.getKey(), 0, 0, false, null,
						serviceContext);
		}

		return cpDefinitionOptionValueRel;
	}

}