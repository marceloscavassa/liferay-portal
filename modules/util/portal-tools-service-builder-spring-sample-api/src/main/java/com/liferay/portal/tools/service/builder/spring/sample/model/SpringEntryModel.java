/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.spring.sample.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SpringEntry service. Represents a row in the &quot;SpringEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.spring.sample.model.impl.SpringEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.spring.sample.model.impl.SpringEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SpringEntry
 * @generated
 */
@ProviderType
public interface SpringEntryModel
	extends BaseModel<SpringEntry>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a spring entry model instance should use the {@link SpringEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this spring entry.
	 *
	 * @return the primary key of this spring entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this spring entry.
	 *
	 * @param primaryKey the primary key of this spring entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this spring entry.
	 *
	 * @return the mvcc version of this spring entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this spring entry.
	 *
	 * @param mvccVersion the mvcc version of this spring entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this spring entry.
	 *
	 * @return the uuid of this spring entry
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this spring entry.
	 *
	 * @param uuid the uuid of this spring entry
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the spring entry ID of this spring entry.
	 *
	 * @return the spring entry ID of this spring entry
	 */
	public long getSpringEntryId();

	/**
	 * Sets the spring entry ID of this spring entry.
	 *
	 * @param springEntryId the spring entry ID of this spring entry
	 */
	public void setSpringEntryId(long springEntryId);

	/**
	 * Returns the company ID of this spring entry.
	 *
	 * @return the company ID of this spring entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this spring entry.
	 *
	 * @param companyId the company ID of this spring entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this spring entry.
	 *
	 * @return the create date of this spring entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this spring entry.
	 *
	 * @param createDate the create date of this spring entry
	 */
	public void setCreateDate(Date createDate);

	@Override
	public SpringEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}