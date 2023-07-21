/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.sql.Blob;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AnalyticsMessage service. Represents a row in the &quot;AnalyticsMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.analytics.message.storage.model.impl.AnalyticsMessageModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.analytics.message.storage.model.impl.AnalyticsMessageImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsMessage
 * @generated
 */
@ProviderType
public interface AnalyticsMessageModel
	extends BaseModel<AnalyticsMessage>, CTModel<AnalyticsMessage>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a analytics message model instance should use the {@link AnalyticsMessage} interface instead.
	 */

	/**
	 * Returns the primary key of this analytics message.
	 *
	 * @return the primary key of this analytics message
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this analytics message.
	 *
	 * @param primaryKey the primary key of this analytics message
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this analytics message.
	 *
	 * @return the mvcc version of this analytics message
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this analytics message.
	 *
	 * @param mvccVersion the mvcc version of this analytics message
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this analytics message.
	 *
	 * @return the ct collection ID of this analytics message
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this analytics message.
	 *
	 * @param ctCollectionId the ct collection ID of this analytics message
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the analytics message ID of this analytics message.
	 *
	 * @return the analytics message ID of this analytics message
	 */
	public long getAnalyticsMessageId();

	/**
	 * Sets the analytics message ID of this analytics message.
	 *
	 * @param analyticsMessageId the analytics message ID of this analytics message
	 */
	public void setAnalyticsMessageId(long analyticsMessageId);

	/**
	 * Returns the company ID of this analytics message.
	 *
	 * @return the company ID of this analytics message
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this analytics message.
	 *
	 * @param companyId the company ID of this analytics message
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this analytics message.
	 *
	 * @return the user ID of this analytics message
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this analytics message.
	 *
	 * @param userId the user ID of this analytics message
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this analytics message.
	 *
	 * @return the user uuid of this analytics message
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this analytics message.
	 *
	 * @param userUuid the user uuid of this analytics message
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this analytics message.
	 *
	 * @return the user name of this analytics message
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this analytics message.
	 *
	 * @param userName the user name of this analytics message
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this analytics message.
	 *
	 * @return the create date of this analytics message
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this analytics message.
	 *
	 * @param createDate the create date of this analytics message
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the body of this analytics message.
	 *
	 * @return the body of this analytics message
	 */
	public Blob getBody();

	/**
	 * Sets the body of this analytics message.
	 *
	 * @param body the body of this analytics message
	 */
	public void setBody(Blob body);

	@Override
	public AnalyticsMessage cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}