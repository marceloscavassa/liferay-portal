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
 * The base model interface for the FaroChannel service. Represents a row in the &quot;OSBFaro_FaroChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.faro.model.impl.FaroChannelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.faro.model.impl.FaroChannelImpl</code>.
 * </p>
 *
 * @author Matthew Kong
 * @see FaroChannel
 * @generated
 */
@ProviderType
public interface FaroChannelModel
	extends BaseModel<FaroChannel>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a faro channel model instance should use the {@link FaroChannel} interface instead.
	 */

	/**
	 * Returns the primary key of this faro channel.
	 *
	 * @return the primary key of this faro channel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this faro channel.
	 *
	 * @param primaryKey the primary key of this faro channel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this faro channel.
	 *
	 * @return the mvcc version of this faro channel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this faro channel.
	 *
	 * @param mvccVersion the mvcc version of this faro channel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the faro channel ID of this faro channel.
	 *
	 * @return the faro channel ID of this faro channel
	 */
	public long getFaroChannelId();

	/**
	 * Sets the faro channel ID of this faro channel.
	 *
	 * @param faroChannelId the faro channel ID of this faro channel
	 */
	public void setFaroChannelId(long faroChannelId);

	/**
	 * Returns the group ID of this faro channel.
	 *
	 * @return the group ID of this faro channel
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this faro channel.
	 *
	 * @param groupId the group ID of this faro channel
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this faro channel.
	 *
	 * @return the company ID of this faro channel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this faro channel.
	 *
	 * @param companyId the company ID of this faro channel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this faro channel.
	 *
	 * @return the user ID of this faro channel
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this faro channel.
	 *
	 * @param userId the user ID of this faro channel
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this faro channel.
	 *
	 * @return the user uuid of this faro channel
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this faro channel.
	 *
	 * @param userUuid the user uuid of this faro channel
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this faro channel.
	 *
	 * @return the user name of this faro channel
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this faro channel.
	 *
	 * @param userName the user name of this faro channel
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create time of this faro channel.
	 *
	 * @return the create time of this faro channel
	 */
	public long getCreateTime();

	/**
	 * Sets the create time of this faro channel.
	 *
	 * @param createTime the create time of this faro channel
	 */
	public void setCreateTime(long createTime);

	/**
	 * Returns the modified time of this faro channel.
	 *
	 * @return the modified time of this faro channel
	 */
	public long getModifiedTime();

	/**
	 * Sets the modified time of this faro channel.
	 *
	 * @param modifiedTime the modified time of this faro channel
	 */
	public void setModifiedTime(long modifiedTime);

	/**
	 * Returns the channel ID of this faro channel.
	 *
	 * @return the channel ID of this faro channel
	 */
	@AutoEscape
	public String getChannelId();

	/**
	 * Sets the channel ID of this faro channel.
	 *
	 * @param channelId the channel ID of this faro channel
	 */
	public void setChannelId(String channelId);

	/**
	 * Returns the name of this faro channel.
	 *
	 * @return the name of this faro channel
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this faro channel.
	 *
	 * @param name the name of this faro channel
	 */
	public void setName(String name);

	/**
	 * Returns the permission type of this faro channel.
	 *
	 * @return the permission type of this faro channel
	 */
	public int getPermissionType();

	/**
	 * Sets the permission type of this faro channel.
	 *
	 * @param permissionType the permission type of this faro channel
	 */
	public void setPermissionType(int permissionType);

	/**
	 * Returns the workspace group ID of this faro channel.
	 *
	 * @return the workspace group ID of this faro channel
	 */
	public long getWorkspaceGroupId();

	/**
	 * Sets the workspace group ID of this faro channel.
	 *
	 * @param workspaceGroupId the workspace group ID of this faro channel
	 */
	public void setWorkspaceGroupId(long workspaceGroupId);

	@Override
	public FaroChannel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}