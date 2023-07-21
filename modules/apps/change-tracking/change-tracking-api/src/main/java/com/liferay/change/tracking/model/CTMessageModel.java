/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CTMessage service. Represents a row in the &quot;CTMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.change.tracking.model.impl.CTMessageModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.change.tracking.model.impl.CTMessageImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTMessage
 * @generated
 */
@ProviderType
public interface CTMessageModel
	extends BaseModel<CTMessage>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ct message model instance should use the {@link CTMessage} interface instead.
	 */

	/**
	 * Returns the primary key of this ct message.
	 *
	 * @return the primary key of this ct message
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ct message.
	 *
	 * @param primaryKey the primary key of this ct message
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ct message.
	 *
	 * @return the mvcc version of this ct message
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ct message.
	 *
	 * @param mvccVersion the mvcc version of this ct message
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct message ID of this ct message.
	 *
	 * @return the ct message ID of this ct message
	 */
	public long getCtMessageId();

	/**
	 * Sets the ct message ID of this ct message.
	 *
	 * @param ctMessageId the ct message ID of this ct message
	 */
	public void setCtMessageId(long ctMessageId);

	/**
	 * Returns the company ID of this ct message.
	 *
	 * @return the company ID of this ct message
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ct message.
	 *
	 * @param companyId the company ID of this ct message
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the ct collection ID of this ct message.
	 *
	 * @return the ct collection ID of this ct message
	 */
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ct message.
	 *
	 * @param ctCollectionId the ct collection ID of this ct message
	 */
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the message content of this ct message.
	 *
	 * @return the message content of this ct message
	 */
	@AutoEscape
	public String getMessageContent();

	/**
	 * Sets the message content of this ct message.
	 *
	 * @param messageContent the message content of this ct message
	 */
	public void setMessageContent(String messageContent);

	@Override
	public CTMessage cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}