/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.web.internal.frontend.data.set.provider;

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryReplenishmentItemService;
import com.liferay.commerce.inventory.web.internal.constants.CommerceInventoryFDSNames;
import com.liferay.commerce.inventory.web.internal.model.Replenishment;
import com.liferay.frontend.data.set.provider.FDSDataProvider;
import com.liferay.frontend.data.set.provider.search.FDSKeywords;
import com.liferay.frontend.data.set.provider.search.FDSPagination;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "fds.data.provider.key=" + CommerceInventoryFDSNames.INVENTORY_REPLENISHMENT,
	service = FDSDataProvider.class
)
public class CommerceInventoryReplenishmentFDSDataProvider
	implements FDSDataProvider<Replenishment> {

	@Override
	public List<Replenishment> getItems(
			FDSKeywords fdsKeywords, FDSPagination fdsPagination,
			HttpServletRequest httpServletRequest, Sort sort)
		throws PortalException {

		List<Replenishment> replenishments = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDate(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		List<CommerceInventoryReplenishmentItem>
			commerceInventoryReplenishmentItems =
				_commerceInventoryReplenishmentItemService.
					getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
						_portal.getCompanyId(httpServletRequest), sku,
						fdsPagination.getStartPosition(),
						fdsPagination.getEndPosition());

		for (CommerceInventoryReplenishmentItem
				commerceInventoryReplenishmentItem :
					commerceInventoryReplenishmentItems) {

			CommerceInventoryWarehouse commerceInventoryWarehouse =
				commerceInventoryReplenishmentItem.
					getCommerceInventoryWarehouse();

			replenishments.add(
				new Replenishment(
					commerceInventoryReplenishmentItem.
						getCommerceInventoryReplenishmentItemId(),
					commerceInventoryWarehouse.getName(
						_portal.getLocale(httpServletRequest)),
					dateTimeFormat.format(
						commerceInventoryReplenishmentItem.
							getAvailabilityDate()),
					commerceInventoryReplenishmentItem.getQuantity()));
		}

		return replenishments;
	}

	@Override
	public int getItemsCount(
			FDSKeywords fdsKeywords, HttpServletRequest httpServletRequest)
		throws PortalException {

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
				_portal.getCompanyId(httpServletRequest), sku);
	}

	@Reference
	private CommerceInventoryReplenishmentItemService
		_commerceInventoryReplenishmentItemService;

	@Reference
	private Portal _portal;

}