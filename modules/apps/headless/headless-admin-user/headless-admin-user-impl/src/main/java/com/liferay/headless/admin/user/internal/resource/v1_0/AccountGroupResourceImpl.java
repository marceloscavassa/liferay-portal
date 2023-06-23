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

package com.liferay.headless.admin.user.internal.resource.v1_0;

import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountGroupRel;
import com.liferay.account.service.AccountGroupRelService;
import com.liferay.account.service.AccountGroupService;
import com.liferay.headless.admin.user.dto.v1_0.AccountGroup;
import com.liferay.headless.admin.user.internal.dto.v1_0.converter.AccountResourceDTOConverter;
import com.liferay.headless.admin.user.internal.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.admin.user.internal.odata.entity.v1_0.AccountGroupEntityModel;
import com.liferay.headless.admin.user.resource.v1_0.AccountGroupResource;
import com.liferay.headless.common.spi.service.context.ServiceContextRequestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account-group.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountGroupResource.class
)
public class AccountGroupResourceImpl extends BaseAccountGroupResourceImpl {

	@Override
	public void deleteAccountGroup(Long accountGroupId) throws Exception {
		_accountGroupService.deleteAccountGroup(accountGroupId);
	}

	@Override
	public void deleteAccountGroupByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		deleteAccountGroup(getAccountGroupId(externalReferenceCode));
	}

	@Override
	public void
			deleteAccountGroupByExternalReferenceCodeAccountByExternalReferenceCode(
				String accountExternalReferenceCode,
				String externalReferenceCode)
		throws Exception {

		AccountGroupRel accountGroupRel =
			_accountGroupRelService.fetchAccountGroupRel(
				getAccountGroupId(externalReferenceCode),
				AccountEntry.class.getName(),
				_accountResourceDTOConverter.getAccountEntryId(
					accountExternalReferenceCode));

		_accountGroupRelService.deleteAccountGroupRel(
			accountGroupRel.getAccountGroupRelId());
	}

	@Override
	public Page<AccountGroup> getAccountAccountGroupsPage(
			Long accountId, Pagination pagination)
		throws Exception {

		return _getAccountGroups(accountId, pagination);
	}

	@Override
	public Page<AccountGroup>
			getAccountByExternalReferenceCodeAccountExternalReferenceCodeAccountGroupsPage(
				String accountExternalReferenceCode, Pagination pagination)
		throws Exception {

		return getAccountAccountGroupsPage(
			_accountResourceDTOConverter.getAccountEntryId(
				accountExternalReferenceCode),
			pagination);
	}

	@Override
	public AccountGroup getAccountGroup(Long accountGroupId) throws Exception {
		return _toAccountGroup(
			_accountGroupService.getAccountGroup(accountGroupId));
	}

	@Override
	public AccountGroup getAccountGroupByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		return getAccountGroup(getAccountGroupId(externalReferenceCode));
	}

	public long getAccountGroupId(String externalReferenceCode)
		throws Exception {

		com.liferay.account.model.AccountGroup accountGroup =
			_accountGroupResourceDTOConverter.getObject(externalReferenceCode);

		return accountGroup.getAccountGroupId();
	}

	@Override
	public Page<AccountGroup> getAccountGroupsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			HashMapBuilder.<String, Map<String, String>>put(
				"create",
				addAction(
					AccountActionKeys.ADD_ACCOUNT_GROUP, "postAccountGroup",
					PortletKeys.PORTAL, 0L)
			).put(
				"create-by-external-reference-code",
				addAction(
					AccountActionKeys.ADD_ACCOUNT_GROUP,
					"putAccountGroupByExternalReferenceCode",
					PortletKeys.PORTAL, 0L)
			).put(
				"get",
				addAction(
					ActionKeys.VIEW, 0L, "getAccountGroupsPage",
					_accountGroupModelResourcePermission)
			).build(),
			booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			com.liferay.account.model.AccountGroup.class.getName(),
			StringPool.BLANK, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.setCompanyId(contextCompany.getCompanyId());

				if (Validator.isNotNull(search)) {
					searchContext.setKeywords(search);
				}
			},
			sorts,
			document -> _toAccountGroup(
				_accountGroupService.getAccountGroup(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	public AccountGroup postAccountGroup(AccountGroup accountGroup)
		throws Exception {

		com.liferay.account.model.AccountGroup newAccountGroup =
			_accountGroupService.addAccountGroup(
				contextUser.getUserId(), accountGroup.getDescription(),
				accountGroup.getName(), _getServiceContext(accountGroup));

		newAccountGroup = _accountGroupService.updateExternalReferenceCode(
			newAccountGroup.getAccountGroupId(),
			accountGroup.getExternalReferenceCode());

		return _toAccountGroup(newAccountGroup);
	}

	@Override
	public void
			postAccountGroupByExternalReferenceCodeAccountByExternalReferenceCode(
				String accountExternalReferenceCode,
				String externalReferenceCode)
		throws Exception {

		_accountGroupRelService.addAccountGroupRel(
			getAccountGroupId(externalReferenceCode),
			AccountEntry.class.getName(),
			_accountResourceDTOConverter.getAccountEntryId(
				accountExternalReferenceCode));
	}

	@Override
	public AccountGroup putAccountGroup(
			Long accountGroupId, AccountGroup accountGroup)
		throws Exception {

		_accountGroupService.updateExternalReferenceCode(
			accountGroupId, accountGroup.getExternalReferenceCode());

		return _toAccountGroup(
			_accountGroupService.updateAccountGroup(
				accountGroupId, accountGroup.getDescription(),
				accountGroup.getName(), _getServiceContext(accountGroup)));
	}

	@Override
	public AccountGroup putAccountGroupByExternalReferenceCode(
			String externalReferenceCode, AccountGroup accountGroup)
		throws Exception {

		return putAccountGroup(
			getAccountGroupId(externalReferenceCode), accountGroup);
	}

	private Page<AccountGroup> _getAccountGroups(
			long accountId, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_accountGroupService.getAccountGroupsByAccountEntryId(
					accountId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				accountGroup -> _toAccountGroup(accountGroup)),
			pagination,
			_accountGroupService.getAccountGroupsCountByAccountEntryId(
				accountId));
	}

	private DTOConverterContext _getDTOConverterContext(long accountGroupId) {
		return new DefaultDTOConverterContext(
			contextAcceptLanguage.isAcceptAllLanguages(),
			HashMapBuilder.<String, Map<String, String>>put(
				"create-account-group-by-external-reference-code-account-by-" +
					"external-reference-code",
				addAction(
					AccountActionKeys.ASSIGN_ACCOUNTS, accountGroupId,
					"postAccountGroupByExternalReferenceCodeAccount" +
						"ByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).put(
				"delete",
				addAction(
					ActionKeys.DELETE, accountGroupId, "deleteAccountGroup",
					_accountGroupModelResourcePermission)
			).put(
				"delete-account-group-by-external-reference-code-account-by-" +
					"external-reference-code",
				addAction(
					AccountActionKeys.ASSIGN_ACCOUNTS, accountGroupId,
					"deleteAccountGroupByExternalReferenceCode" +
						"AccountByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).put(
				"delete-by-external-reference-code",
				addAction(
					ActionKeys.DELETE, accountGroupId,
					"deleteAccountGroupByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).put(
				"get",
				addAction(
					ActionKeys.VIEW, accountGroupId, "getAccountGroup",
					_accountGroupModelResourcePermission)
			).put(
				"get-by-external-reference-code",
				addAction(
					ActionKeys.VIEW, accountGroupId,
					"getAccountGroupByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).put(
				"replace",
				addAction(
					ActionKeys.UPDATE, accountGroupId, "putAccountGroup",
					_accountGroupModelResourcePermission)
			).put(
				"replace-by-external-reference-code",
				addAction(
					ActionKeys.UPDATE, accountGroupId,
					"putAccountGroupByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).put(
				"update",
				addAction(
					ActionKeys.UPDATE, accountGroupId, "patchAccountGroup",
					_accountGroupModelResourcePermission)
			).put(
				"update-by-external-reference-code",
				addAction(
					ActionKeys.UPDATE, accountGroupId,
					"patchAccountGroupByExternalReferenceCode",
					_accountGroupModelResourcePermission)
			).build(),
			null, contextHttpServletRequest, accountGroupId,
			contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
			contextUser);
	}

	private ServiceContext _getServiceContext(AccountGroup accountGroup)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextRequestUtil.createServiceContext(
				CustomFieldsUtil.toMap(
					com.liferay.account.model.AccountGroup.class.getName(),
					contextCompany.getCompanyId(),
					accountGroup.getCustomFields(),
					contextAcceptLanguage.getPreferredLocale()),
				contextCompany.getGroupId(), contextHttpServletRequest, null);

		serviceContext.setCompanyId(contextCompany.getCompanyId());
		serviceContext.setUserId(contextUser.getUserId());

		return serviceContext;
	}

	private AccountGroup _toAccountGroup(
			com.liferay.account.model.AccountGroup accountGroup)
		throws Exception {

		return _accountGroupResourceDTOConverter.toDTO(
			_getDTOConverterContext(accountGroup.getAccountGroupId()));
	}

	@Reference(
		target = "(model.class.name=com.liferay.account.model.AccountGroup)"
	)
	private ModelResourcePermission<com.liferay.account.model.AccountGroup>
		_accountGroupModelResourcePermission;

	@Reference
	private AccountGroupRelService _accountGroupRelService;

	@Reference(
		target = "(component.name=com.liferay.headless.admin.user.internal.dto.v1_0.converter.AccountGroupResourceDTOConverter)"
	)
	private DTOConverter<com.liferay.account.model.AccountGroup, AccountGroup>
		_accountGroupResourceDTOConverter;

	@Reference
	private AccountGroupService _accountGroupService;

	@Reference
	private AccountResourceDTOConverter _accountResourceDTOConverter;

	private final EntityModel _entityModel = new AccountGroupEntityModel();

}