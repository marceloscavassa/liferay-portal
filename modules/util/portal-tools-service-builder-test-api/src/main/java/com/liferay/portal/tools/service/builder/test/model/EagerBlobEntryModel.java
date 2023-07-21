/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.sql.Blob;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EagerBlobEntry service. Represents a row in the &quot;EagerBlobEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.EagerBlobEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.EagerBlobEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EagerBlobEntry
 * @generated
 */
@ProviderType
public interface EagerBlobEntryModel extends BaseModel<EagerBlobEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a eager blob entry model instance should use the {@link EagerBlobEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this eager blob entry.
	 *
	 * @return the primary key of this eager blob entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this eager blob entry.
	 *
	 * @param primaryKey the primary key of this eager blob entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this eager blob entry.
	 *
	 * @return the uuid of this eager blob entry
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this eager blob entry.
	 *
	 * @param uuid the uuid of this eager blob entry
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the eager blob entry ID of this eager blob entry.
	 *
	 * @return the eager blob entry ID of this eager blob entry
	 */
	public long getEagerBlobEntryId();

	/**
	 * Sets the eager blob entry ID of this eager blob entry.
	 *
	 * @param eagerBlobEntryId the eager blob entry ID of this eager blob entry
	 */
	public void setEagerBlobEntryId(long eagerBlobEntryId);

	/**
	 * Returns the group ID of this eager blob entry.
	 *
	 * @return the group ID of this eager blob entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this eager blob entry.
	 *
	 * @param groupId the group ID of this eager blob entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the blob of this eager blob entry.
	 *
	 * @return the blob of this eager blob entry
	 */
	public Blob getBlob();

	/**
	 * Sets the blob of this eager blob entry.
	 *
	 * @param blob the blob of this eager blob entry
	 */
	public void setBlob(Blob blob);

	@Override
	public EagerBlobEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}