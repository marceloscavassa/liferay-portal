/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Website service. Represents a row in the &quot;Website&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.WebsiteModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.WebsiteImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Website
 * @generated
 */
@ProviderType
public interface WebsiteModel
	extends AttachedModel, BaseModel<Website>, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a website model instance should use the {@link Website} interface instead.
	 */

	/**
	 * Returns the primary key of this website.
	 *
	 * @return the primary key of this website
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this website.
	 *
	 * @param primaryKey the primary key of this website
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this website.
	 *
	 * @return the mvcc version of this website
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this website.
	 *
	 * @param mvccVersion the mvcc version of this website
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this website.
	 *
	 * @return the uuid of this website
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this website.
	 *
	 * @param uuid the uuid of this website
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the website ID of this website.
	 *
	 * @return the website ID of this website
	 */
	public long getWebsiteId();

	/**
	 * Sets the website ID of this website.
	 *
	 * @param websiteId the website ID of this website
	 */
	public void setWebsiteId(long websiteId);

	/**
	 * Returns the company ID of this website.
	 *
	 * @return the company ID of this website
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this website.
	 *
	 * @param companyId the company ID of this website
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this website.
	 *
	 * @return the user ID of this website
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this website.
	 *
	 * @param userId the user ID of this website
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this website.
	 *
	 * @return the user uuid of this website
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this website.
	 *
	 * @param userUuid the user uuid of this website
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this website.
	 *
	 * @return the user name of this website
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this website.
	 *
	 * @param userName the user name of this website
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this website.
	 *
	 * @return the create date of this website
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this website.
	 *
	 * @param createDate the create date of this website
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this website.
	 *
	 * @return the modified date of this website
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this website.
	 *
	 * @param modifiedDate the modified date of this website
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this website.
	 *
	 * @return the fully qualified class name of this website
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this website.
	 *
	 * @return the class name ID of this website
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this website.
	 *
	 * @param classNameId the class name ID of this website
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this website.
	 *
	 * @return the class pk of this website
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this website.
	 *
	 * @param classPK the class pk of this website
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the url of this website.
	 *
	 * @return the url of this website
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this website.
	 *
	 * @param url the url of this website
	 */
	public void setUrl(String url);

	/**
	 * Returns the list type ID of this website.
	 *
	 * @return the list type ID of this website
	 */
	public long getListTypeId();

	/**
	 * Sets the list type ID of this website.
	 *
	 * @param listTypeId the list type ID of this website
	 */
	public void setListTypeId(long listTypeId);

	/**
	 * Returns the primary of this website.
	 *
	 * @return the primary of this website
	 */
	public boolean getPrimary();

	/**
	 * Returns <code>true</code> if this website is primary.
	 *
	 * @return <code>true</code> if this website is primary; <code>false</code> otherwise
	 */
	public boolean isPrimary();

	/**
	 * Sets whether this website is primary.
	 *
	 * @param primary the primary of this website
	 */
	public void setPrimary(boolean primary);

	/**
	 * Returns the last publish date of this website.
	 *
	 * @return the last publish date of this website
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this website.
	 *
	 * @param lastPublishDate the last publish date of this website
	 */
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public Website cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}