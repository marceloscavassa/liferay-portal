/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryReplenishmentItemService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemService
 * @generated
 */
public class CommerceInventoryReplenishmentItemServiceWrapper
	implements CommerceInventoryReplenishmentItemService,
			   ServiceWrapper<CommerceInventoryReplenishmentItemService> {

	public CommerceInventoryReplenishmentItemServiceWrapper() {
		this(null);
	}

	public CommerceInventoryReplenishmentItemServiceWrapper(
		CommerceInventoryReplenishmentItemService
			commerceInventoryReplenishmentItemService) {

		_commerceInventoryReplenishmentItemService =
			commerceInventoryReplenishmentItemService;
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				addCommerceInventoryReplenishmentItem(
					String externalReferenceCode,
					long commerceInventoryWarehouseId,
					java.util.Date availabilityDate, int quantity, String sku,
					String unitOfMeasureKey)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			addCommerceInventoryReplenishmentItem(
				externalReferenceCode, commerceInventoryWarehouseId,
				availabilityDate, quantity, sku, unitOfMeasureKey);
	}

	@Override
	public void deleteCommerceInventoryReplenishmentItem(
			long commerceInventoryReplenishmentItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceInventoryReplenishmentItemService.
			deleteCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	@Override
	public void deleteCommerceInventoryReplenishmentItems(
			long companyId, String sku)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceInventoryReplenishmentItemService.
			deleteCommerceInventoryReplenishmentItems(companyId, sku);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				fetchCommerceInventoryReplenishmentItemByExternalReferenceCode(
					String externalReferenceCode, long companyId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			fetchCommerceInventoryReplenishmentItemByExternalReferenceCode(
				externalReferenceCode, companyId);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				getCommerceInventoryReplenishmentItem(
					long commerceInventoryReplenishmentItemId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItem(
				commerceInventoryReplenishmentItemId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryReplenishmentItem>
					getCommerceInventoryReplenishmentItemsByCommerceInventoryWarehouseId(
						long commerceInventoryWarehouseId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryReplenishmentItem>
					getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
						long companyId, String sku, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsByCompanyIdAndSku(
				companyId, sku, start, end);
	}

	@Override
	public long getCommerceInventoryReplenishmentItemsCount(
			long commerceInventoryWarehouseId, String sku)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsCount(
				commerceInventoryWarehouseId, sku);
	}

	@Override
	public int
			getCommerceInventoryReplenishmentItemsCountByCommerceInventoryWarehouseId(
				long commerceInventoryWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsCountByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId);
	}

	@Override
	public int getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
			long companyId, String sku)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			getCommerceInventoryReplenishmentItemsCountByCompanyIdAndSku(
				companyId, sku);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryReplenishmentItemService.
			getOSGiServiceIdentifier();
	}

	@Override
	public
		com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem
				updateCommerceInventoryReplenishmentItem(
					String externalReferenceCode,
					long commerceInventoryReplenishmentItemId,
					java.util.Date availabilityDate, int quantity,
					long mvccVersion)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryReplenishmentItemService.
			updateCommerceInventoryReplenishmentItem(
				externalReferenceCode, commerceInventoryReplenishmentItemId,
				availabilityDate, quantity, mvccVersion);
	}

	@Override
	public CommerceInventoryReplenishmentItemService getWrappedService() {
		return _commerceInventoryReplenishmentItemService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryReplenishmentItemService
			commerceInventoryReplenishmentItemService) {

		_commerceInventoryReplenishmentItemService =
			commerceInventoryReplenishmentItemService;
	}

	private CommerceInventoryReplenishmentItemService
		_commerceInventoryReplenishmentItemService;

}