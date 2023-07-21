/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the MicroblogsEntry service. Represents a row in the &quot;MicroblogsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.microblogs.model.impl.MicroblogsEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntry
 * @generated
 */
@ProviderType
public interface MicroblogsEntryModel
	extends AuditedModel, BaseModel<MicroblogsEntry>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a microblogs entry model instance should use the {@link MicroblogsEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this microblogs entry.
	 *
	 * @return the primary key of this microblogs entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this microblogs entry.
	 *
	 * @param primaryKey the primary key of this microblogs entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the microblogs entry ID of this microblogs entry.
	 *
	 * @return the microblogs entry ID of this microblogs entry
	 */
	public long getMicroblogsEntryId();

	/**
	 * Sets the microblogs entry ID of this microblogs entry.
	 *
	 * @param microblogsEntryId the microblogs entry ID of this microblogs entry
	 */
	public void setMicroblogsEntryId(long microblogsEntryId);

	/**
	 * Returns the company ID of this microblogs entry.
	 *
	 * @return the company ID of this microblogs entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this microblogs entry.
	 *
	 * @param companyId the company ID of this microblogs entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this microblogs entry.
	 *
	 * @return the user ID of this microblogs entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this microblogs entry.
	 *
	 * @param userId the user ID of this microblogs entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this microblogs entry.
	 *
	 * @return the user uuid of this microblogs entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this microblogs entry.
	 *
	 * @param userUuid the user uuid of this microblogs entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this microblogs entry.
	 *
	 * @return the user name of this microblogs entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this microblogs entry.
	 *
	 * @param userName the user name of this microblogs entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this microblogs entry.
	 *
	 * @return the create date of this microblogs entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this microblogs entry.
	 *
	 * @param createDate the create date of this microblogs entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this microblogs entry.
	 *
	 * @return the modified date of this microblogs entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this microblogs entry.
	 *
	 * @param modifiedDate the modified date of this microblogs entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the creator class name ID of this microblogs entry.
	 *
	 * @return the creator class name ID of this microblogs entry
	 */
	public long getCreatorClassNameId();

	/**
	 * Sets the creator class name ID of this microblogs entry.
	 *
	 * @param creatorClassNameId the creator class name ID of this microblogs entry
	 */
	public void setCreatorClassNameId(long creatorClassNameId);

	/**
	 * Returns the creator class pk of this microblogs entry.
	 *
	 * @return the creator class pk of this microblogs entry
	 */
	public long getCreatorClassPK();

	/**
	 * Sets the creator class pk of this microblogs entry.
	 *
	 * @param creatorClassPK the creator class pk of this microblogs entry
	 */
	public void setCreatorClassPK(long creatorClassPK);

	/**
	 * Returns the content of this microblogs entry.
	 *
	 * @return the content of this microblogs entry
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this microblogs entry.
	 *
	 * @param content the content of this microblogs entry
	 */
	public void setContent(String content);

	/**
	 * Returns the type of this microblogs entry.
	 *
	 * @return the type of this microblogs entry
	 */
	public int getType();

	/**
	 * Sets the type of this microblogs entry.
	 *
	 * @param type the type of this microblogs entry
	 */
	public void setType(int type);

	/**
	 * Returns the parent microblogs entry ID of this microblogs entry.
	 *
	 * @return the parent microblogs entry ID of this microblogs entry
	 */
	public long getParentMicroblogsEntryId();

	/**
	 * Sets the parent microblogs entry ID of this microblogs entry.
	 *
	 * @param parentMicroblogsEntryId the parent microblogs entry ID of this microblogs entry
	 */
	public void setParentMicroblogsEntryId(long parentMicroblogsEntryId);

	/**
	 * Returns the social relation type of this microblogs entry.
	 *
	 * @return the social relation type of this microblogs entry
	 */
	public int getSocialRelationType();

	/**
	 * Sets the social relation type of this microblogs entry.
	 *
	 * @param socialRelationType the social relation type of this microblogs entry
	 */
	public void setSocialRelationType(int socialRelationType);

	@Override
	public MicroblogsEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}