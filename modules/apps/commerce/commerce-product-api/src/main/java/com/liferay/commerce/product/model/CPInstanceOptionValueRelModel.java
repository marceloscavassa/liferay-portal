/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CPInstanceOptionValueRel service. Represents a row in the &quot;CPInstanceOptionValueRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPInstanceOptionValueRel
 * @generated
 */
@ProviderType
public interface CPInstanceOptionValueRelModel
	extends BaseModel<CPInstanceOptionValueRel>,
			CTModel<CPInstanceOptionValueRel>, GroupedModel, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp instance option value rel model instance should use the {@link CPInstanceOptionValueRel} interface instead.
	 */

	/**
	 * Returns the primary key of this cp instance option value rel.
	 *
	 * @return the primary key of this cp instance option value rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp instance option value rel.
	 *
	 * @param primaryKey the primary key of this cp instance option value rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this cp instance option value rel.
	 *
	 * @return the mvcc version of this cp instance option value rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this cp instance option value rel.
	 *
	 * @param mvccVersion the mvcc version of this cp instance option value rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this cp instance option value rel.
	 *
	 * @return the ct collection ID of this cp instance option value rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this cp instance option value rel.
	 *
	 * @param ctCollectionId the ct collection ID of this cp instance option value rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this cp instance option value rel.
	 *
	 * @return the uuid of this cp instance option value rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp instance option value rel.
	 *
	 * @param uuid the uuid of this cp instance option value rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp instance option value rel ID of this cp instance option value rel.
	 *
	 * @return the cp instance option value rel ID of this cp instance option value rel
	 */
	public long getCPInstanceOptionValueRelId();

	/**
	 * Sets the cp instance option value rel ID of this cp instance option value rel.
	 *
	 * @param CPInstanceOptionValueRelId the cp instance option value rel ID of this cp instance option value rel
	 */
	public void setCPInstanceOptionValueRelId(long CPInstanceOptionValueRelId);

	/**
	 * Returns the group ID of this cp instance option value rel.
	 *
	 * @return the group ID of this cp instance option value rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp instance option value rel.
	 *
	 * @param groupId the group ID of this cp instance option value rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp instance option value rel.
	 *
	 * @return the company ID of this cp instance option value rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp instance option value rel.
	 *
	 * @param companyId the company ID of this cp instance option value rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp instance option value rel.
	 *
	 * @return the user ID of this cp instance option value rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp instance option value rel.
	 *
	 * @param userId the user ID of this cp instance option value rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp instance option value rel.
	 *
	 * @return the user uuid of this cp instance option value rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp instance option value rel.
	 *
	 * @param userUuid the user uuid of this cp instance option value rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp instance option value rel.
	 *
	 * @return the user name of this cp instance option value rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp instance option value rel.
	 *
	 * @param userName the user name of this cp instance option value rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp instance option value rel.
	 *
	 * @return the create date of this cp instance option value rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp instance option value rel.
	 *
	 * @param createDate the create date of this cp instance option value rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp instance option value rel.
	 *
	 * @return the modified date of this cp instance option value rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp instance option value rel.
	 *
	 * @param modifiedDate the modified date of this cp instance option value rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition option rel ID of this cp instance option value rel.
	 *
	 * @return the cp definition option rel ID of this cp instance option value rel
	 */
	public long getCPDefinitionOptionRelId();

	/**
	 * Sets the cp definition option rel ID of this cp instance option value rel.
	 *
	 * @param CPDefinitionOptionRelId the cp definition option rel ID of this cp instance option value rel
	 */
	public void setCPDefinitionOptionRelId(long CPDefinitionOptionRelId);

	/**
	 * Returns the cp definition option value rel ID of this cp instance option value rel.
	 *
	 * @return the cp definition option value rel ID of this cp instance option value rel
	 */
	public long getCPDefinitionOptionValueRelId();

	/**
	 * Sets the cp definition option value rel ID of this cp instance option value rel.
	 *
	 * @param CPDefinitionOptionValueRelId the cp definition option value rel ID of this cp instance option value rel
	 */
	public void setCPDefinitionOptionValueRelId(
		long CPDefinitionOptionValueRelId);

	/**
	 * Returns the cp instance ID of this cp instance option value rel.
	 *
	 * @return the cp instance ID of this cp instance option value rel
	 */
	public long getCPInstanceId();

	/**
	 * Sets the cp instance ID of this cp instance option value rel.
	 *
	 * @param CPInstanceId the cp instance ID of this cp instance option value rel
	 */
	public void setCPInstanceId(long CPInstanceId);

	@Override
	public CPInstanceOptionValueRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}