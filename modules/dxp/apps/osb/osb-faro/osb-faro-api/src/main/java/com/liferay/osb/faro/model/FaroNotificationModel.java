/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FaroNotification service. Represents a row in the &quot;OSBFaro_FaroNotification&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.faro.model.impl.FaroNotificationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.faro.model.impl.FaroNotificationImpl</code>.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroNotification
 * @generated
 */
@ProviderType
public interface FaroNotificationModel
	extends BaseModel<FaroNotification>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a faro notification model instance should use the {@link FaroNotification} interface instead.
	 */

	/**
	 * Returns the primary key of this faro notification.
	 *
	 * @return the primary key of this faro notification
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this faro notification.
	 *
	 * @param primaryKey the primary key of this faro notification
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this faro notification.
	 *
	 * @return the mvcc version of this faro notification
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this faro notification.
	 *
	 * @param mvccVersion the mvcc version of this faro notification
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the faro notification ID of this faro notification.
	 *
	 * @return the faro notification ID of this faro notification
	 */
	public long getFaroNotificationId();

	/**
	 * Sets the faro notification ID of this faro notification.
	 *
	 * @param faroNotificationId the faro notification ID of this faro notification
	 */
	public void setFaroNotificationId(long faroNotificationId);

	/**
	 * Returns the group ID of this faro notification.
	 *
	 * @return the group ID of this faro notification
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this faro notification.
	 *
	 * @param groupId the group ID of this faro notification
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this faro notification.
	 *
	 * @return the company ID of this faro notification
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this faro notification.
	 *
	 * @param companyId the company ID of this faro notification
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this faro notification.
	 *
	 * @return the user ID of this faro notification
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this faro notification.
	 *
	 * @param userId the user ID of this faro notification
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this faro notification.
	 *
	 * @return the user uuid of this faro notification
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this faro notification.
	 *
	 * @param userUuid the user uuid of this faro notification
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create time of this faro notification.
	 *
	 * @return the create time of this faro notification
	 */
	public long getCreateTime();

	/**
	 * Sets the create time of this faro notification.
	 *
	 * @param createTime the create time of this faro notification
	 */
	public void setCreateTime(long createTime);

	/**
	 * Returns the modified time of this faro notification.
	 *
	 * @return the modified time of this faro notification
	 */
	public long getModifiedTime();

	/**
	 * Sets the modified time of this faro notification.
	 *
	 * @param modifiedTime the modified time of this faro notification
	 */
	public void setModifiedTime(long modifiedTime);

	/**
	 * Returns the owner ID of this faro notification.
	 *
	 * @return the owner ID of this faro notification
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this faro notification.
	 *
	 * @param ownerId the owner ID of this faro notification
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Returns the scope of this faro notification.
	 *
	 * @return the scope of this faro notification
	 */
	@AutoEscape
	public String getScope();

	/**
	 * Sets the scope of this faro notification.
	 *
	 * @param scope the scope of this faro notification
	 */
	public void setScope(String scope);

	/**
	 * Returns the read of this faro notification.
	 *
	 * @return the read of this faro notification
	 */
	public boolean getRead();

	/**
	 * Returns <code>true</code> if this faro notification is read.
	 *
	 * @return <code>true</code> if this faro notification is read; <code>false</code> otherwise
	 */
	public boolean isRead();

	/**
	 * Sets whether this faro notification is read.
	 *
	 * @param read the read of this faro notification
	 */
	public void setRead(boolean read);

	/**
	 * Returns the type of this faro notification.
	 *
	 * @return the type of this faro notification
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this faro notification.
	 *
	 * @param type the type of this faro notification
	 */
	public void setType(String type);

	/**
	 * Returns the subtype of this faro notification.
	 *
	 * @return the subtype of this faro notification
	 */
	@AutoEscape
	public String getSubtype();

	/**
	 * Sets the subtype of this faro notification.
	 *
	 * @param subtype the subtype of this faro notification
	 */
	public void setSubtype(String subtype);

	@Override
	public FaroNotification cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}