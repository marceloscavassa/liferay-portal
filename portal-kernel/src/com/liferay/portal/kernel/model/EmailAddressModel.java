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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EmailAddress service. Represents a row in the &quot;EmailAddress&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.EmailAddressModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.EmailAddressImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddress
 * @generated
 */
@ProviderType
public interface EmailAddressModel
	extends AttachedModel, BaseModel<EmailAddress>, CTModel<EmailAddress>,
			MVCCModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a email address model instance should use the {@link EmailAddress} interface instead.
	 */

	/**
	 * Returns the primary key of this email address.
	 *
	 * @return the primary key of this email address
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this email address.
	 *
	 * @param primaryKey the primary key of this email address
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this email address.
	 *
	 * @return the mvcc version of this email address
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this email address.
	 *
	 * @param mvccVersion the mvcc version of this email address
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this email address.
	 *
	 * @return the ct collection ID of this email address
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this email address.
	 *
	 * @param ctCollectionId the ct collection ID of this email address
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this email address.
	 *
	 * @return the uuid of this email address
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this email address.
	 *
	 * @param uuid the uuid of this email address
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the email address ID of this email address.
	 *
	 * @return the email address ID of this email address
	 */
	public long getEmailAddressId();

	/**
	 * Sets the email address ID of this email address.
	 *
	 * @param emailAddressId the email address ID of this email address
	 */
	public void setEmailAddressId(long emailAddressId);

	/**
	 * Returns the company ID of this email address.
	 *
	 * @return the company ID of this email address
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this email address.
	 *
	 * @param companyId the company ID of this email address
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this email address.
	 *
	 * @return the user ID of this email address
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this email address.
	 *
	 * @param userId the user ID of this email address
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this email address.
	 *
	 * @return the user uuid of this email address
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this email address.
	 *
	 * @param userUuid the user uuid of this email address
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this email address.
	 *
	 * @return the user name of this email address
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this email address.
	 *
	 * @param userName the user name of this email address
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this email address.
	 *
	 * @return the create date of this email address
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this email address.
	 *
	 * @param createDate the create date of this email address
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this email address.
	 *
	 * @return the modified date of this email address
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this email address.
	 *
	 * @param modifiedDate the modified date of this email address
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this email address.
	 *
	 * @return the fully qualified class name of this email address
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this email address.
	 *
	 * @return the class name ID of this email address
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this email address.
	 *
	 * @param classNameId the class name ID of this email address
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this email address.
	 *
	 * @return the class pk of this email address
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this email address.
	 *
	 * @param classPK the class pk of this email address
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the address of this email address.
	 *
	 * @return the address of this email address
	 */
	@AutoEscape
	public String getAddress();

	/**
	 * Sets the address of this email address.
	 *
	 * @param address the address of this email address
	 */
	public void setAddress(String address);

	/**
	 * Returns the list type ID of this email address.
	 *
	 * @return the list type ID of this email address
	 */
	public long getListTypeId();

	/**
	 * Sets the list type ID of this email address.
	 *
	 * @param listTypeId the list type ID of this email address
	 */
	public void setListTypeId(long listTypeId);

	/**
	 * Returns the primary of this email address.
	 *
	 * @return the primary of this email address
	 */
	public boolean getPrimary();

	/**
	 * Returns <code>true</code> if this email address is primary.
	 *
	 * @return <code>true</code> if this email address is primary; <code>false</code> otherwise
	 */
	public boolean isPrimary();

	/**
	 * Sets whether this email address is primary.
	 *
	 * @param primary the primary of this email address
	 */
	public void setPrimary(boolean primary);

	@Override
	public EmailAddress cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}