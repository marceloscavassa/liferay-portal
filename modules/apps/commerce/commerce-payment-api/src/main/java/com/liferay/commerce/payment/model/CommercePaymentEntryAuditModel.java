/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.math.BigDecimal;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePaymentEntryAudit service. Represents a row in the &quot;CommercePaymentEntryAudit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.payment.model.impl.CommercePaymentEntryAuditModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.payment.model.impl.CommercePaymentEntryAuditImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentEntryAudit
 * @generated
 */
@ProviderType
public interface CommercePaymentEntryAuditModel
	extends AuditedModel, BaseModel<CommercePaymentEntryAudit>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce payment entry audit model instance should use the {@link CommercePaymentEntryAudit} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce payment entry audit.
	 *
	 * @return the primary key of this commerce payment entry audit
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce payment entry audit.
	 *
	 * @param primaryKey the primary key of this commerce payment entry audit
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce payment entry audit.
	 *
	 * @return the mvcc version of this commerce payment entry audit
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce payment entry audit.
	 *
	 * @param mvccVersion the mvcc version of this commerce payment entry audit
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce payment entry audit ID of this commerce payment entry audit.
	 *
	 * @return the commerce payment entry audit ID of this commerce payment entry audit
	 */
	public long getCommercePaymentEntryAuditId();

	/**
	 * Sets the commerce payment entry audit ID of this commerce payment entry audit.
	 *
	 * @param commercePaymentEntryAuditId the commerce payment entry audit ID of this commerce payment entry audit
	 */
	public void setCommercePaymentEntryAuditId(
		long commercePaymentEntryAuditId);

	/**
	 * Returns the company ID of this commerce payment entry audit.
	 *
	 * @return the company ID of this commerce payment entry audit
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce payment entry audit.
	 *
	 * @param companyId the company ID of this commerce payment entry audit
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce payment entry audit.
	 *
	 * @return the user ID of this commerce payment entry audit
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce payment entry audit.
	 *
	 * @param userId the user ID of this commerce payment entry audit
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce payment entry audit.
	 *
	 * @return the user uuid of this commerce payment entry audit
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce payment entry audit.
	 *
	 * @param userUuid the user uuid of this commerce payment entry audit
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce payment entry audit.
	 *
	 * @return the user name of this commerce payment entry audit
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce payment entry audit.
	 *
	 * @param userName the user name of this commerce payment entry audit
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce payment entry audit.
	 *
	 * @return the create date of this commerce payment entry audit
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce payment entry audit.
	 *
	 * @param createDate the create date of this commerce payment entry audit
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce payment entry audit.
	 *
	 * @return the modified date of this commerce payment entry audit
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce payment entry audit.
	 *
	 * @param modifiedDate the modified date of this commerce payment entry audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce payment entry ID of this commerce payment entry audit.
	 *
	 * @return the commerce payment entry ID of this commerce payment entry audit
	 */
	public long getCommercePaymentEntryId();

	/**
	 * Sets the commerce payment entry ID of this commerce payment entry audit.
	 *
	 * @param commercePaymentEntryId the commerce payment entry ID of this commerce payment entry audit
	 */
	public void setCommercePaymentEntryId(long commercePaymentEntryId);

	/**
	 * Returns the amount of this commerce payment entry audit.
	 *
	 * @return the amount of this commerce payment entry audit
	 */
	public BigDecimal getAmount();

	/**
	 * Sets the amount of this commerce payment entry audit.
	 *
	 * @param amount the amount of this commerce payment entry audit
	 */
	public void setAmount(BigDecimal amount);

	/**
	 * Returns the currency code of this commerce payment entry audit.
	 *
	 * @return the currency code of this commerce payment entry audit
	 */
	@AutoEscape
	public String getCurrencyCode();

	/**
	 * Sets the currency code of this commerce payment entry audit.
	 *
	 * @param currencyCode the currency code of this commerce payment entry audit
	 */
	public void setCurrencyCode(String currencyCode);

	/**
	 * Returns the log type of this commerce payment entry audit.
	 *
	 * @return the log type of this commerce payment entry audit
	 */
	@AutoEscape
	public String getLogType();

	/**
	 * Sets the log type of this commerce payment entry audit.
	 *
	 * @param logType the log type of this commerce payment entry audit
	 */
	public void setLogType(String logType);

	/**
	 * Returns the log type settings of this commerce payment entry audit.
	 *
	 * @return the log type settings of this commerce payment entry audit
	 */
	@AutoEscape
	public String getLogTypeSettings();

	/**
	 * Sets the log type settings of this commerce payment entry audit.
	 *
	 * @param logTypeSettings the log type settings of this commerce payment entry audit
	 */
	public void setLogTypeSettings(String logTypeSettings);

	@Override
	public CommercePaymentEntryAudit cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}