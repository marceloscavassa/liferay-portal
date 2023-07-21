/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AccountEntryOrganizationRel service. Represents a row in the &quot;AccountEntryOrganizationRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.account.model.impl.AccountEntryOrganizationRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.account.model.impl.AccountEntryOrganizationRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryOrganizationRel
 * @generated
 */
@ProviderType
public interface AccountEntryOrganizationRelModel
	extends BaseModel<AccountEntryOrganizationRel>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a account entry organization rel model instance should use the {@link AccountEntryOrganizationRel} interface instead.
	 */

	/**
	 * Returns the primary key of this account entry organization rel.
	 *
	 * @return the primary key of this account entry organization rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this account entry organization rel.
	 *
	 * @param primaryKey the primary key of this account entry organization rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this account entry organization rel.
	 *
	 * @return the mvcc version of this account entry organization rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this account entry organization rel.
	 *
	 * @param mvccVersion the mvcc version of this account entry organization rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the account entry organization rel ID of this account entry organization rel.
	 *
	 * @return the account entry organization rel ID of this account entry organization rel
	 */
	public long getAccountEntryOrganizationRelId();

	/**
	 * Sets the account entry organization rel ID of this account entry organization rel.
	 *
	 * @param accountEntryOrganizationRelId the account entry organization rel ID of this account entry organization rel
	 */
	public void setAccountEntryOrganizationRelId(
		long accountEntryOrganizationRelId);

	/**
	 * Returns the company ID of this account entry organization rel.
	 *
	 * @return the company ID of this account entry organization rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this account entry organization rel.
	 *
	 * @param companyId the company ID of this account entry organization rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the account entry ID of this account entry organization rel.
	 *
	 * @return the account entry ID of this account entry organization rel
	 */
	public long getAccountEntryId();

	/**
	 * Sets the account entry ID of this account entry organization rel.
	 *
	 * @param accountEntryId the account entry ID of this account entry organization rel
	 */
	public void setAccountEntryId(long accountEntryId);

	/**
	 * Returns the organization ID of this account entry organization rel.
	 *
	 * @return the organization ID of this account entry organization rel
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this account entry organization rel.
	 *
	 * @param organizationId the organization ID of this account entry organization rel
	 */
	public void setOrganizationId(long organizationId);

	@Override
	public AccountEntryOrganizationRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}