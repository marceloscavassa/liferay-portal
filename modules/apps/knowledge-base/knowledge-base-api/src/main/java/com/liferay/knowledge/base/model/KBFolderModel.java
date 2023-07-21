/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KBFolder service. Represents a row in the &quot;KBFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.knowledge.base.model.impl.KBFolderModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.knowledge.base.model.impl.KBFolderImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolder
 * @generated
 */
@ProviderType
public interface KBFolderModel
	extends BaseModel<KBFolder>, CTModel<KBFolder>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kb folder model instance should use the {@link KBFolder} interface instead.
	 */

	/**
	 * Returns the primary key of this kb folder.
	 *
	 * @return the primary key of this kb folder
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kb folder.
	 *
	 * @param primaryKey the primary key of this kb folder
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kb folder.
	 *
	 * @return the mvcc version of this kb folder
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kb folder.
	 *
	 * @param mvccVersion the mvcc version of this kb folder
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this kb folder.
	 *
	 * @return the ct collection ID of this kb folder
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this kb folder.
	 *
	 * @param ctCollectionId the ct collection ID of this kb folder
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this kb folder.
	 *
	 * @return the uuid of this kb folder
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this kb folder.
	 *
	 * @param uuid the uuid of this kb folder
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this kb folder.
	 *
	 * @return the external reference code of this kb folder
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this kb folder.
	 *
	 * @param externalReferenceCode the external reference code of this kb folder
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the kb folder ID of this kb folder.
	 *
	 * @return the kb folder ID of this kb folder
	 */
	public long getKbFolderId();

	/**
	 * Sets the kb folder ID of this kb folder.
	 *
	 * @param kbFolderId the kb folder ID of this kb folder
	 */
	public void setKbFolderId(long kbFolderId);

	/**
	 * Returns the group ID of this kb folder.
	 *
	 * @return the group ID of this kb folder
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kb folder.
	 *
	 * @param groupId the group ID of this kb folder
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kb folder.
	 *
	 * @return the company ID of this kb folder
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kb folder.
	 *
	 * @param companyId the company ID of this kb folder
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kb folder.
	 *
	 * @return the user ID of this kb folder
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kb folder.
	 *
	 * @param userId the user ID of this kb folder
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kb folder.
	 *
	 * @return the user uuid of this kb folder
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kb folder.
	 *
	 * @param userUuid the user uuid of this kb folder
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kb folder.
	 *
	 * @return the user name of this kb folder
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kb folder.
	 *
	 * @param userName the user name of this kb folder
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kb folder.
	 *
	 * @return the create date of this kb folder
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kb folder.
	 *
	 * @param createDate the create date of this kb folder
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kb folder.
	 *
	 * @return the modified date of this kb folder
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kb folder.
	 *
	 * @param modifiedDate the modified date of this kb folder
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent kb folder ID of this kb folder.
	 *
	 * @return the parent kb folder ID of this kb folder
	 */
	public long getParentKBFolderId();

	/**
	 * Sets the parent kb folder ID of this kb folder.
	 *
	 * @param parentKBFolderId the parent kb folder ID of this kb folder
	 */
	public void setParentKBFolderId(long parentKBFolderId);

	/**
	 * Returns the name of this kb folder.
	 *
	 * @return the name of this kb folder
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kb folder.
	 *
	 * @param name the name of this kb folder
	 */
	public void setName(String name);

	/**
	 * Returns the url title of this kb folder.
	 *
	 * @return the url title of this kb folder
	 */
	@AutoEscape
	public String getUrlTitle();

	/**
	 * Sets the url title of this kb folder.
	 *
	 * @param urlTitle the url title of this kb folder
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Returns the description of this kb folder.
	 *
	 * @return the description of this kb folder
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kb folder.
	 *
	 * @param description the description of this kb folder
	 */
	public void setDescription(String description);

	/**
	 * Returns the last publish date of this kb folder.
	 *
	 * @return the last publish date of this kb folder
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this kb folder.
	 *
	 * @param lastPublishDate the last publish date of this kb folder
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public KBFolder cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}