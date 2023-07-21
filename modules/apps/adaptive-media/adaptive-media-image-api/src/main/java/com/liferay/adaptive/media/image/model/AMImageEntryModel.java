/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AMImageEntry service. Represents a row in the &quot;AMImageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.adaptive.media.image.model.impl.AMImageEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.adaptive.media.image.model.impl.AMImageEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AMImageEntry
 * @generated
 */
@ProviderType
public interface AMImageEntryModel
	extends BaseModel<AMImageEntry>, CTModel<AMImageEntry>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a am image entry model instance should use the {@link AMImageEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this am image entry.
	 *
	 * @return the primary key of this am image entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this am image entry.
	 *
	 * @param primaryKey the primary key of this am image entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this am image entry.
	 *
	 * @return the mvcc version of this am image entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this am image entry.
	 *
	 * @param mvccVersion the mvcc version of this am image entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this am image entry.
	 *
	 * @return the ct collection ID of this am image entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this am image entry.
	 *
	 * @param ctCollectionId the ct collection ID of this am image entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this am image entry.
	 *
	 * @return the uuid of this am image entry
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this am image entry.
	 *
	 * @param uuid the uuid of this am image entry
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the am image entry ID of this am image entry.
	 *
	 * @return the am image entry ID of this am image entry
	 */
	public long getAmImageEntryId();

	/**
	 * Sets the am image entry ID of this am image entry.
	 *
	 * @param amImageEntryId the am image entry ID of this am image entry
	 */
	public void setAmImageEntryId(long amImageEntryId);

	/**
	 * Returns the group ID of this am image entry.
	 *
	 * @return the group ID of this am image entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this am image entry.
	 *
	 * @param groupId the group ID of this am image entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this am image entry.
	 *
	 * @return the company ID of this am image entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this am image entry.
	 *
	 * @param companyId the company ID of this am image entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this am image entry.
	 *
	 * @return the create date of this am image entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this am image entry.
	 *
	 * @param createDate the create date of this am image entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the configuration uuid of this am image entry.
	 *
	 * @return the configuration uuid of this am image entry
	 */
	@AutoEscape
	public String getConfigurationUuid();

	/**
	 * Sets the configuration uuid of this am image entry.
	 *
	 * @param configurationUuid the configuration uuid of this am image entry
	 */
	public void setConfigurationUuid(String configurationUuid);

	/**
	 * Returns the file version ID of this am image entry.
	 *
	 * @return the file version ID of this am image entry
	 */
	public long getFileVersionId();

	/**
	 * Sets the file version ID of this am image entry.
	 *
	 * @param fileVersionId the file version ID of this am image entry
	 */
	public void setFileVersionId(long fileVersionId);

	/**
	 * Returns the mime type of this am image entry.
	 *
	 * @return the mime type of this am image entry
	 */
	@AutoEscape
	public String getMimeType();

	/**
	 * Sets the mime type of this am image entry.
	 *
	 * @param mimeType the mime type of this am image entry
	 */
	public void setMimeType(String mimeType);

	/**
	 * Returns the height of this am image entry.
	 *
	 * @return the height of this am image entry
	 */
	public int getHeight();

	/**
	 * Sets the height of this am image entry.
	 *
	 * @param height the height of this am image entry
	 */
	public void setHeight(int height);

	/**
	 * Returns the width of this am image entry.
	 *
	 * @return the width of this am image entry
	 */
	public int getWidth();

	/**
	 * Sets the width of this am image entry.
	 *
	 * @param width the width of this am image entry
	 */
	public void setWidth(int width);

	/**
	 * Returns the size of this am image entry.
	 *
	 * @return the size of this am image entry
	 */
	public long getSize();

	/**
	 * Sets the size of this am image entry.
	 *
	 * @param size the size of this am image entry
	 */
	public void setSize(long size);

	@Override
	public AMImageEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}