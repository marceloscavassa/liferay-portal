/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.frontend.data.set.provider;

import com.liferay.commerce.product.definitions.web.internal.constants.CommerceProductFDSNames;
import com.liferay.commerce.product.definitions.web.internal.model.ProductOption;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesRegistry;
import com.liferay.frontend.data.set.provider.FDSDataProvider;
import com.liferay.frontend.data.set.provider.search.FDSKeywords;
import com.liferay.frontend.data.set.provider.search.FDSPagination;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "fds.data.provider.key=" + CommerceProductFDSNames.PRODUCT_OPTIONS,
	service = FDSDataProvider.class
)
public class CommerceProductOptionFDSDataProvider
	implements FDSDataProvider<ProductOption> {

	@Override
	public List<ProductOption> getItems(
			FDSKeywords fdsKeywords, FDSPagination fdsPagination,
			HttpServletRequest httpServletRequest, Sort sort)
		throws PortalException {

		List<ProductOption> productOptions = new ArrayList<>();

		long cpDefinitionId = ParamUtil.getLong(
			httpServletRequest, "cpDefinitionId");

		Locale locale = _portal.getLocale(httpServletRequest);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_getCPDefinitionOptionRels(
				cpDefinitionId, fdsKeywords.getKeywords(),
				fdsPagination.getStartPosition(),
				fdsPagination.getEndPosition(), sort);

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			productOptions.add(
				new ProductOption(
					cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
					_getDDMFormFieldTypeLabel(
						cpDefinitionOptionRel.getDDMFormFieldTypeName(),
						locale),
					cpDefinitionOptionRel.getName(
						_language.getLanguageId(locale)),
					cpDefinitionOptionRel.getPriority(),
					_language.get(
						locale,
						cpDefinitionOptionRel.isRequired() ? "yes" : "no"),
					_language.get(
						locale,
						cpDefinitionOptionRel.isSkuContributor() ? "yes" :
							"no"),
					cpDefinitionOptionRel.
						getCPDefinitionOptionValueRelsCount()));
		}

		return productOptions;
	}

	@Override
	public int getItemsCount(
			FDSKeywords fdsKeywords, HttpServletRequest httpServletRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			httpServletRequest, "cpDefinitionId");

		CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
			cpDefinitionId);

		return _cpDefinitionOptionRelService.searchCPDefinitionOptionRelsCount(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
			cpDefinition.getCPDefinitionId(), fdsKeywords.getKeywords());
	}

	private BaseModelSearchResult<CPDefinitionOptionRel>
			_getBaseModelSearchResult(
				long cpDefinitionId, String keywords, int start, int end,
				Sort sort)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
			cpDefinitionId);

		return _cpDefinitionOptionRelService.searchCPDefinitionOptionRels(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
			cpDefinitionId, keywords, start, end, new Sort[] {sort});
	}

	private List<CPDefinitionOptionRel> _getCPDefinitionOptionRels(
			long cpDefinitionId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		BaseModelSearchResult<CPDefinitionOptionRel> baseModelSearchResult =
			_getBaseModelSearchResult(
				cpDefinitionId, keywords, start, end, sort);

		return baseModelSearchResult.getBaseModels();
	}

	private String _getDDMFormFieldTypeLabel(
		String ddmFormFieldTypeName, Locale locale) {

		DDMFormFieldType ddmFormFieldType =
			_ddmFormFieldTypeServicesRegistry.getDDMFormFieldType(
				ddmFormFieldTypeName);

		String label = MapUtil.getString(
			_ddmFormFieldTypeServicesRegistry.getDDMFormFieldTypeProperties(
				ddmFormFieldType.getName()),
			"ddm.form.field.type.label");

		try {
			if (Validator.isNotNull(label)) {
				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					"content.Language", locale, ddmFormFieldType.getClass());

				return _language.get(resourceBundle, label);
			}
		}
		catch (MissingResourceException missingResourceException) {
			if (_log.isWarnEnabled()) {
				_log.warn(missingResourceException);
			}
		}

		return ddmFormFieldType.getName();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductOptionFDSDataProvider.class);

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DDMFormFieldTypeServicesRegistry _ddmFormFieldTypeServicesRegistry;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}