/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DLOpenerFileEntryReference service. Represents a row in the &quot;DLOpenerFileEntryReference&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.document.library.opener.model.impl.DLOpenerFileEntryReferenceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.document.library.opener.model.impl.DLOpenerFileEntryReferenceImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLOpenerFileEntryReference
 * @generated
 */
@ProviderType
public interface DLOpenerFileEntryReferenceModel
	extends BaseModel<DLOpenerFileEntryReference>, GroupedModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dl opener file entry reference model instance should use the {@link DLOpenerFileEntryReference} interface instead.
	 */

	/**
	 * Returns the primary key of this dl opener file entry reference.
	 *
	 * @return the primary key of this dl opener file entry reference
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dl opener file entry reference.
	 *
	 * @param primaryKey the primary key of this dl opener file entry reference
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the dl opener file entry reference ID of this dl opener file entry reference.
	 *
	 * @return the dl opener file entry reference ID of this dl opener file entry reference
	 */
	public long getDlOpenerFileEntryReferenceId();

	/**
	 * Sets the dl opener file entry reference ID of this dl opener file entry reference.
	 *
	 * @param dlOpenerFileEntryReferenceId the dl opener file entry reference ID of this dl opener file entry reference
	 */
	public void setDlOpenerFileEntryReferenceId(
		long dlOpenerFileEntryReferenceId);

	/**
	 * Returns the group ID of this dl opener file entry reference.
	 *
	 * @return the group ID of this dl opener file entry reference
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this dl opener file entry reference.
	 *
	 * @param groupId the group ID of this dl opener file entry reference
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this dl opener file entry reference.
	 *
	 * @return the company ID of this dl opener file entry reference
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this dl opener file entry reference.
	 *
	 * @param companyId the company ID of this dl opener file entry reference
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this dl opener file entry reference.
	 *
	 * @return the user ID of this dl opener file entry reference
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this dl opener file entry reference.
	 *
	 * @param userId the user ID of this dl opener file entry reference
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this dl opener file entry reference.
	 *
	 * @return the user uuid of this dl opener file entry reference
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this dl opener file entry reference.
	 *
	 * @param userUuid the user uuid of this dl opener file entry reference
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this dl opener file entry reference.
	 *
	 * @return the user name of this dl opener file entry reference
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this dl opener file entry reference.
	 *
	 * @param userName the user name of this dl opener file entry reference
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this dl opener file entry reference.
	 *
	 * @return the create date of this dl opener file entry reference
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this dl opener file entry reference.
	 *
	 * @param createDate the create date of this dl opener file entry reference
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this dl opener file entry reference.
	 *
	 * @return the modified date of this dl opener file entry reference
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this dl opener file entry reference.
	 *
	 * @param modifiedDate the modified date of this dl opener file entry reference
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the reference key of this dl opener file entry reference.
	 *
	 * @return the reference key of this dl opener file entry reference
	 */
	@AutoEscape
	public String getReferenceKey();

	/**
	 * Sets the reference key of this dl opener file entry reference.
	 *
	 * @param referenceKey the reference key of this dl opener file entry reference
	 */
	public void setReferenceKey(String referenceKey);

	/**
	 * Returns the reference type of this dl opener file entry reference.
	 *
	 * @return the reference type of this dl opener file entry reference
	 */
	@AutoEscape
	public String getReferenceType();

	/**
	 * Sets the reference type of this dl opener file entry reference.
	 *
	 * @param referenceType the reference type of this dl opener file entry reference
	 */
	public void setReferenceType(String referenceType);

	/**
	 * Returns the file entry ID of this dl opener file entry reference.
	 *
	 * @return the file entry ID of this dl opener file entry reference
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this dl opener file entry reference.
	 *
	 * @param fileEntryId the file entry ID of this dl opener file entry reference
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the type of this dl opener file entry reference.
	 *
	 * @return the type of this dl opener file entry reference
	 */
	public int getType();

	/**
	 * Sets the type of this dl opener file entry reference.
	 *
	 * @param type the type of this dl opener file entry reference
	 */
	public void setType(int type);

	@Override
	public DLOpenerFileEntryReference cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}