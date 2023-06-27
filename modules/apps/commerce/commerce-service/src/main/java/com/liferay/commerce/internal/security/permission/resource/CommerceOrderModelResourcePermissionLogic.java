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

package com.liferay.commerce.internal.security.permission.resource;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.constants.AccountRoleConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderModelResourcePermissionLogic
	implements ModelResourcePermissionLogic<CommerceOrder> {

	public CommerceOrderModelResourcePermissionLogic(
		AccountEntryLocalService accountEntryLocalService,
		CommerceChannelLocalService commerceChannelLocalService,
		ConfigurationProvider configurationProvider,
		GroupLocalService groupLocalService,
		PortletResourcePermission portletResourcePermission,
		UserGroupRoleLocalService userGroupRoleLocalService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_accountEntryLocalService = accountEntryLocalService;
		_commerceChannelLocalService = commerceChannelLocalService;
		_configurationProvider = configurationProvider;
		_groupLocalService = groupLocalService;
		_portletResourcePermission = portletResourcePermission;
		_userGroupRoleLocalService = userGroupRoleLocalService;
		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	@Override
	public Boolean contains(
			PermissionChecker permissionChecker, String name,
			CommerceOrder commerceOrder, String actionId)
		throws PortalException {

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		if ((accountEntry.getAccountEntryId() ==
				AccountConstants.ACCOUNT_ENTRY_ID_GUEST) &&
			permissionChecker.isSignedIn()) {

			return _hasPermission(
				permissionChecker, 0,
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);
		}

		if (permissionChecker.isCompanyAdmin(commerceOrder.getCompanyId()) ||
			permissionChecker.isGroupAdmin(commerceOrder.getGroupId()) ||
			((accountEntry.getAccountEntryId() !=
				AccountConstants.ACCOUNT_ENTRY_ID_GUEST) &&
			 _hasAncestorPermission(
				 permissionChecker, accountEntry.getAccountEntryGroupId(),
				 CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS))) {

			return true;
		}

		if ((actionId.equals(ActionKeys.UPDATE) ||
			 actionId.equals(ActionKeys.VIEW)) &&
			_hasRoleAccountSupplier(permissionChecker, commerceOrder)) {

			return true;
		}

		if ((accountEntry.getAccountEntryId() !=
				AccountConstants.ACCOUNT_ENTRY_ID_GUEST) &&
			actionId.equals(CommerceOrderActionKeys.APPROVE_COMMERCE_ORDER)) {

			return _hasAncestorPermission(
				permissionChecker, accountEntry.getAccountEntryGroupId(),
				CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS);
		}

		if (actionId.equals(CommerceOrderActionKeys.CHECKOUT_COMMERCE_ORDER)) {
			return _containsCheckoutPermission(
				permissionChecker, commerceOrder);
		}

		if (actionId.equals(ActionKeys.DELETE)) {
			return _containsDeletePermission(permissionChecker, commerceOrder);
		}

		if (actionId.equals(
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_DELIVERY_TERMS)) {

			return _containsManageDeliveryTerms(
				permissionChecker, commerceOrder);
		}

		if (actionId.equals(
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_NOTES)) {

			return _containsManageNotes(
				permissionChecker, commerceOrder, false);
		}

		if (actionId.equals(
				CommerceOrderActionKeys.
					MANAGE_COMMERCE_ORDER_PAYMENT_METHODS)) {

			return _containsManagePaymentMethods(
				permissionChecker, commerceOrder);
		}

		if (actionId.equals(
				CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_PAYMENT_TERMS)) {

			return _containsManagePaymentTerms(
				permissionChecker, commerceOrder);
		}

		if ((accountEntry.getAccountEntryId() !=
				AccountConstants.ACCOUNT_ENTRY_ID_GUEST) &&
			actionId.equals(
				CommerceOrderActionKeys.
					MANAGE_COMMERCE_ORDER_RESTRICTED_NOTES)) {

			return _containsManageNotes(permissionChecker, commerceOrder, true);
		}

		if (actionId.equals(
				CommerceOrderActionKeys.
					MANAGE_COMMERCE_ORDER_SHIPPING_OPTIONS)) {

			return _containsManageShippingOptions(
				permissionChecker, commerceOrder);
		}

		if (actionId.equals(ActionKeys.UPDATE)) {
			return _containsUpdatePermission(permissionChecker, commerceOrder);
		}

		if (actionId.equals(ActionKeys.VIEW)) {
			return _containsViewPermission(permissionChecker, commerceOrder);
		}

		if (actionId.equals(CommerceOrderActionKeys.VIEW_BILLING_ADDRESS)) {
			return _containsViewBillingAddress(
				permissionChecker, commerceOrder);
		}

		return false;
	}

	private boolean _containsCheckoutPermission(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (!commerceOrder.isOpen() &&
			(commerceOrder.getOrderStatus() !=
				CommerceOrderConstants.ORDER_STATUS_IN_PROGRESS)) {

			return false;
		}

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		if (commerceOrder.isPending() &&
			!_hasPermission(
				permissionChecker, accountEntry.getAccountEntryGroupId(),
				CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS)) {

			return false;
		}

		if (commerceOrder.isApproved() &&
			_hasOwnerPermission(permissionChecker, commerceOrder)) {

			return true;
		}

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.CHECKOUT_OPEN_COMMERCE_ORDERS);
	}

	private boolean _containsDeletePermission(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (commerceOrder.isOpen()) {
			if (commerceOrder.isDraft()) {
				return _hasOwnerPermission(permissionChecker, commerceOrder);
			}

			if (_hasOwnerPermission(permissionChecker, commerceOrder)) {
				return true;
			}
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.DELETE_COMMERCE_ORDERS);
	}

	private boolean _containsManageDeliveryTerms(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return false;
		}

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_DELIVERY_TERMS);
	}

	private boolean _containsManageNotes(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder,
			boolean restricted)
		throws PortalException {

		if (!restricted &&
			_hasOwnerPermission(permissionChecker, commerceOrder)) {

			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _hasAncestorPermission(
			permissionChecker, accountEntry.getAccountEntryGroupId(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);
	}

	private boolean _containsManagePaymentMethods(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return false;
		}

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_PAYMENT_METHODS);
	}

	private boolean _containsManagePaymentTerms(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return false;
		}

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_PAYMENT_TERMS);
	}

	private boolean _containsManageShippingOptions(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return false;
		}

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_SHIPPING_OPTIONS);
	}

	private boolean _containsUpdatePermission(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		if (commerceOrder.isOpen()) {
			if (_hasOwnerPermission(permissionChecker, commerceOrder)) {
				return true;
			}

			if (commerceOrder.isDraft()) {
				return false;
			}

			if (_hasAncestorPermission(
					permissionChecker, accountEntry.getAccountEntryGroupId(),
					CommerceOrderActionKeys.ADD_COMMERCE_ORDER)) {

				return true;
			}

			if (_workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(
					commerceOrder.getCompanyId(), commerceOrder.getGroupId(),
					CommerceOrder.class.getName(), 0,
					CommerceOrderConstants.TYPE_PK_APPROVAL)) {

				return _hasPermission(
					permissionChecker, accountEntry.getAccountEntryGroupId(),
					CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS);
			}
		}

		return _hasAncestorPermission(
			permissionChecker, accountEntry.getAccountEntryGroupId(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS);
	}

	private boolean _containsViewBillingAddress(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		User user = permissionChecker.getUser();

		if (user.isGuestUser() && commerceOrder.isGuestOrder()) {
			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		return _portletResourcePermission.contains(
			permissionChecker, accountEntry.getAccountEntryGroup(),
			CommerceOrderActionKeys.VIEW_BILLING_ADDRESS);
	}

	private boolean _containsViewPermission(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		if (_hasOwnerPermission(permissionChecker, commerceOrder) ||
			commerceOrder.isGuestOrder()) {

			return true;
		}

		AccountEntry accountEntry = commerceOrder.getAccountEntry();

		if (commerceOrder.isOpen()) {
			if (commerceOrder.isDraft()) {
				return false;
			}

			return _hasPermission(
				permissionChecker, accountEntry.getAccountEntryGroupId(),
				CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS,
				CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS);
		}

		return _hasAncestorPermission(
			permissionChecker, accountEntry.getAccountEntryGroupId(),
			CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS,
			CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS);
	}

	private boolean _hasAncestorPermission(
			PermissionChecker permissionChecker, long groupId,
			String... actionIds)
		throws PortalException {

		Group group = _groupLocalService.getGroup(groupId);

		List<Group> groups = ListUtil.copy(group.getAncestors());

		groups.add(group);

		for (Group curGroup : groups) {
			if (_hasPermission(
					permissionChecker, curGroup.getGroupId(), actionIds)) {

				return true;
			}
		}

		return false;
	}

	private boolean _hasOwnerPermission(
		PermissionChecker permissionChecker, CommerceOrder commerceOrder) {

		long userId = permissionChecker.getUserId();

		if (userId == commerceOrder.getUserId()) {
			return true;
		}

		return false;
	}

	private boolean _hasPermission(
		PermissionChecker permissionChecker, long groupId,
		String... actionIds) {

		for (String actionId : actionIds) {
			if (_portletResourcePermission.contains(
					permissionChecker, groupId, actionId)) {

				return true;
			}
		}

		return false;
	}

	private boolean _hasRoleAccountSupplier(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder)
		throws PortalException {

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelByGroupClassPK(
				commerceOrder.getGroupId());

		if ((commerceChannel != null) &&
			(commerceChannel.getAccountEntryId() == 0)) {

			return false;
		}

		List<AccountEntry> accountEntries =
			_accountEntryLocalService.getUserAccountEntries(
				permissionChecker.getUserId(), 0L, StringPool.BLANK,
				new String[] {AccountConstants.ACCOUNT_ENTRY_TYPE_SUPPLIER},
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			if ((accountEntry.getAccountEntryId() ==
					commerceChannel.getAccountEntryId()) &&
				_userGroupRoleLocalService.hasUserGroupRole(
					permissionChecker.getUserId(),
					accountEntry.getAccountEntryGroupId(),
					AccountRoleConstants.ROLE_NAME_ACCOUNT_SUPPLIER)) {

				return true;
			}
		}

		return false;
	}

	private final AccountEntryLocalService _accountEntryLocalService;
	private final CommerceChannelLocalService _commerceChannelLocalService;
	private final ConfigurationProvider _configurationProvider;
	private final GroupLocalService _groupLocalService;
	private final PortletResourcePermission _portletResourcePermission;
	private final UserGroupRoleLocalService _userGroupRoleLocalService;
	private final WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}