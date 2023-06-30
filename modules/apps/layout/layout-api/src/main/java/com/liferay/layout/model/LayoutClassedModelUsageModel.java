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

package com.liferay.layout.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LayoutClassedModelUsage service. Represents a row in the &quot;LayoutClassedModelUsage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.layout.model.impl.LayoutClassedModelUsageModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.layout.model.impl.LayoutClassedModelUsageImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutClassedModelUsage
 * @generated
 */
@ProviderType
public interface LayoutClassedModelUsageModel
	extends AttachedModel, BaseModel<LayoutClassedModelUsage>,
			CTModel<LayoutClassedModelUsage>, MVCCModel, ShardedModel,
			StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout classed model usage model instance should use the {@link LayoutClassedModelUsage} interface instead.
	 */

	/**
	 * Returns the primary key of this layout classed model usage.
	 *
	 * @return the primary key of this layout classed model usage
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout classed model usage.
	 *
	 * @param primaryKey the primary key of this layout classed model usage
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout classed model usage.
	 *
	 * @return the mvcc version of this layout classed model usage
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout classed model usage.
	 *
	 * @param mvccVersion the mvcc version of this layout classed model usage
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this layout classed model usage.
	 *
	 * @return the ct collection ID of this layout classed model usage
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this layout classed model usage.
	 *
	 * @param ctCollectionId the ct collection ID of this layout classed model usage
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this layout classed model usage.
	 *
	 * @return the uuid of this layout classed model usage
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this layout classed model usage.
	 *
	 * @param uuid the uuid of this layout classed model usage
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the layout classed model usage ID of this layout classed model usage.
	 *
	 * @return the layout classed model usage ID of this layout classed model usage
	 */
	public long getLayoutClassedModelUsageId();

	/**
	 * Sets the layout classed model usage ID of this layout classed model usage.
	 *
	 * @param layoutClassedModelUsageId the layout classed model usage ID of this layout classed model usage
	 */
	public void setLayoutClassedModelUsageId(long layoutClassedModelUsageId);

	/**
	 * Returns the group ID of this layout classed model usage.
	 *
	 * @return the group ID of this layout classed model usage
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this layout classed model usage.
	 *
	 * @param groupId the group ID of this layout classed model usage
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout classed model usage.
	 *
	 * @return the company ID of this layout classed model usage
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout classed model usage.
	 *
	 * @param companyId the company ID of this layout classed model usage
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this layout classed model usage.
	 *
	 * @return the create date of this layout classed model usage
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout classed model usage.
	 *
	 * @param createDate the create date of this layout classed model usage
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout classed model usage.
	 *
	 * @return the modified date of this layout classed model usage
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout classed model usage.
	 *
	 * @param modifiedDate the modified date of this layout classed model usage
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this layout classed model usage.
	 *
	 * @return the fully qualified class name of this layout classed model usage
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this layout classed model usage.
	 *
	 * @return the class name ID of this layout classed model usage
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this layout classed model usage.
	 *
	 * @param classNameId the class name ID of this layout classed model usage
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this layout classed model usage.
	 *
	 * @return the class pk of this layout classed model usage
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this layout classed model usage.
	 *
	 * @param classPK the class pk of this layout classed model usage
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the classed model external reference code of this layout classed model usage.
	 *
	 * @return the classed model external reference code of this layout classed model usage
	 */
	@AutoEscape
	public String getClassedModelExternalReferenceCode();

	/**
	 * Sets the classed model external reference code of this layout classed model usage.
	 *
	 * @param classedModelExternalReferenceCode the classed model external reference code of this layout classed model usage
	 */
	public void setClassedModelExternalReferenceCode(
		String classedModelExternalReferenceCode);

	/**
	 * Returns the container key of this layout classed model usage.
	 *
	 * @return the container key of this layout classed model usage
	 */
	@AutoEscape
	public String getContainerKey();

	/**
	 * Sets the container key of this layout classed model usage.
	 *
	 * @param containerKey the container key of this layout classed model usage
	 */
	public void setContainerKey(String containerKey);

	/**
	 * Returns the container type of this layout classed model usage.
	 *
	 * @return the container type of this layout classed model usage
	 */
	public long getContainerType();

	/**
	 * Sets the container type of this layout classed model usage.
	 *
	 * @param containerType the container type of this layout classed model usage
	 */
	public void setContainerType(long containerType);

	/**
	 * Returns the plid of this layout classed model usage.
	 *
	 * @return the plid of this layout classed model usage
	 */
	public long getPlid();

	/**
	 * Sets the plid of this layout classed model usage.
	 *
	 * @param plid the plid of this layout classed model usage
	 */
	public void setPlid(long plid);

	/**
	 * Returns the type of this layout classed model usage.
	 *
	 * @return the type of this layout classed model usage
	 */
	public int getType();

	/**
	 * Sets the type of this layout classed model usage.
	 *
	 * @param type the type of this layout classed model usage
	 */
	public void setType(int type);

	/**
	 * Returns the last publish date of this layout classed model usage.
	 *
	 * @return the last publish date of this layout classed model usage
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this layout classed model usage.
	 *
	 * @param lastPublishDate the last publish date of this layout classed model usage
	 */
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public LayoutClassedModelUsage cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}