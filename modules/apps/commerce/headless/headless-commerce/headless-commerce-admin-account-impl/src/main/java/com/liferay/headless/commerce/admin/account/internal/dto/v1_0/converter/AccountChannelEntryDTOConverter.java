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

package com.liferay.headless.commerce.admin.account.internal.dto.v1_0.converter;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryService;
import com.liferay.commerce.account.exception.NoSuchAccountGroupCommerceAccountRelException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.product.constants.CommerceChannelAccountEntryRelConstants;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelAccountEntryRel;
import com.liferay.commerce.product.service.CommerceChannelAccountEntryRelService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.term.model.CommerceTermEntry;
import com.liferay.commerce.term.service.CommerceTermEntryService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountChannelEntry;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.AddressService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Sbarra
 */
@Component(
	property = "dto.class.name=com.liferay.commerce.model.AccountChannelEntry",
	service = {AccountChannelEntryDTOConverter.class, DTOConverter.class}
)
public class AccountChannelEntryDTOConverter
	implements DTOConverter
		<CommerceChannelAccountEntryRel, AccountChannelEntry> {

	@Override
	public String getContentType() {
		return AccountChannelEntry.class.getSimpleName();
	}

	@Override
	public AccountChannelEntry toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceChannelAccountEntryRel commerceChannelAccountEntryRel =
			_commerceChannelAccountEntryRelService.
				fetchCommerceChannelAccountEntryRel(
					(Long)dtoConverterContext.getId());

		if (commerceChannelAccountEntryRel == null) {
			throw new NoSuchAccountGroupCommerceAccountRelException();
		}

		return new AccountChannelEntry() {
			{
				accountId = commerceChannelAccountEntryRel.getAccountEntryId();
				channelId =
					commerceChannelAccountEntryRel.getCommerceChannelId();
				entryId = commerceChannelAccountEntryRel.getClassPK();
				id =
					commerceChannelAccountEntryRel.
						getCommerceChannelAccountEntryRelId();
				overrideEligibility =
					commerceChannelAccountEntryRel.isOverrideEligibility();
				priority = commerceChannelAccountEntryRel.getPriority();

				setAccountExternalReferenceCode(
					() -> {
						AccountEntry accountEntry =
							_accountEntryService.fetchAccountEntry(
								commerceChannelAccountEntryRel.
									getAccountEntryId());

						if ((accountEntry != null) &&
							!Validator.isBlank(
								accountEntry.getExternalReferenceCode())) {

							return accountEntry.getExternalReferenceCode();
						}

						return null;
					});

				setChannelExternalReferenceCode(
					() -> {
						CommerceChannel commerceChannel =
							_commerceChannelService.fetchCommerceChannel(
								commerceChannelAccountEntryRel.
									getCommerceChannelId());

						if ((commerceChannel != null) &&
							!Validator.isBlank(
								commerceChannel.getExternalReferenceCode())) {

							return commerceChannel.getExternalReferenceCode();
						}

						return null;
					});

				setEntryExternalReferenceCode(
					() -> {
						int type = commerceChannelAccountEntryRel.getType();

						if ((type ==
								CommerceChannelAccountEntryRelConstants.
									TYPE_BILLING_ADDRESS) ||
							(type ==
								CommerceChannelAccountEntryRelConstants.
									TYPE_SHIPPING_ADDRESS)) {

							Address address = _addressService.getAddress(
								GetterUtil.getLong(
									commerceChannelAccountEntryRel.
										getClassPK()));

							if (!Validator.isBlank(
									address.getExternalReferenceCode())) {

								return address.getExternalReferenceCode();
							}
						}
						else if (type ==
									CommerceChannelAccountEntryRelConstants.
										TYPE_DELIVERY_TERM) {

							CommerceTermEntry commerceTermEntry =
								_commerceTermEntryService.getCommerceTermEntry(
									GetterUtil.getLong(
										commerceChannelAccountEntryRel.
											getClassPK()));

							if (!Validator.isBlank(
									commerceTermEntry.
										getExternalReferenceCode())) {

								return commerceTermEntry.
									getExternalReferenceCode();
							}
						}
						else if (type ==
									CommerceChannelAccountEntryRelConstants.
										TYPE_DISCOUNT) {

							CommerceDiscount commerceDiscount =
								_commerceDiscountService.getCommerceDiscount(
									GetterUtil.getLong(
										commerceChannelAccountEntryRel.
											getClassPK()));

							if (!Validator.isBlank(
									commerceDiscount.
										getExternalReferenceCode())) {

								return commerceDiscount.
									getExternalReferenceCode();
							}
						}
						else if (type ==
									CommerceChannelAccountEntryRelConstants.
										TYPE_PAYMENT_TERM) {

							CommerceTermEntry commerceTermEntry =
								_commerceTermEntryService.getCommerceTermEntry(
									GetterUtil.getLong(
										commerceChannelAccountEntryRel.
											getClassPK()));

							if (!Validator.isBlank(
									commerceTermEntry.
										getExternalReferenceCode())) {

								return commerceTermEntry.
									getExternalReferenceCode();
							}
						}
						else if (type ==
									CommerceChannelAccountEntryRelConstants.
										TYPE_PRICE_LIST) {

							CommercePriceList commercePriceList =
								_commercePriceListService.getCommercePriceList(
									GetterUtil.getLong(
										commerceChannelAccountEntryRel.
											getClassPK()));

							if (!Validator.isBlank(
									commercePriceList.
										getExternalReferenceCode())) {

								return commercePriceList.
									getExternalReferenceCode();
							}
						}
						else if (type ==
									CommerceChannelAccountEntryRelConstants.
										TYPE_USER) {

							User user = _userService.getUserById(
								GetterUtil.getLong(
									commerceChannelAccountEntryRel.
										getClassPK()));

							if (!Validator.isBlank(
									user.getExternalReferenceCode())) {

								return user.getExternalReferenceCode();
							}
						}

						return null;
					});
			}
		};
	}

	@Reference
	private AccountEntryService _accountEntryService;

	@Reference
	private AddressService _addressService;

	@Reference
	private CommerceChannelAccountEntryRelService
		_commerceChannelAccountEntryRelService;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private CommerceTermEntryService _commerceTermEntryService;

	@Reference
	private UserService _userService;

}