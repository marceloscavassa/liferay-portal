/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SocialActivityLimit service. Represents a row in the &quot;SocialActivityLimit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.social.model.impl.SocialActivityLimitModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.social.model.impl.SocialActivityLimitImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLimit
 * @generated
 */
@ProviderType
public interface SocialActivityLimitModel
	extends AttachedModel, BaseModel<SocialActivityLimit>,
			CTModel<SocialActivityLimit>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social activity limit model instance should use the {@link SocialActivityLimit} interface instead.
	 */

	/**
	 * Returns the primary key of this social activity limit.
	 *
	 * @return the primary key of this social activity limit
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social activity limit.
	 *
	 * @param primaryKey the primary key of this social activity limit
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this social activity limit.
	 *
	 * @return the mvcc version of this social activity limit
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this social activity limit.
	 *
	 * @param mvccVersion the mvcc version of this social activity limit
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this social activity limit.
	 *
	 * @return the ct collection ID of this social activity limit
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this social activity limit.
	 *
	 * @param ctCollectionId the ct collection ID of this social activity limit
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the activity limit ID of this social activity limit.
	 *
	 * @return the activity limit ID of this social activity limit
	 */
	public long getActivityLimitId();

	/**
	 * Sets the activity limit ID of this social activity limit.
	 *
	 * @param activityLimitId the activity limit ID of this social activity limit
	 */
	public void setActivityLimitId(long activityLimitId);

	/**
	 * Returns the group ID of this social activity limit.
	 *
	 * @return the group ID of this social activity limit
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social activity limit.
	 *
	 * @param groupId the group ID of this social activity limit
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social activity limit.
	 *
	 * @return the company ID of this social activity limit
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this social activity limit.
	 *
	 * @param companyId the company ID of this social activity limit
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this social activity limit.
	 *
	 * @return the user ID of this social activity limit
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this social activity limit.
	 *
	 * @param userId the user ID of this social activity limit
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this social activity limit.
	 *
	 * @return the user uuid of this social activity limit
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this social activity limit.
	 *
	 * @param userUuid the user uuid of this social activity limit
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the fully qualified class name of this social activity limit.
	 *
	 * @return the fully qualified class name of this social activity limit
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this social activity limit.
	 *
	 * @return the class name ID of this social activity limit
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social activity limit.
	 *
	 * @param classNameId the class name ID of this social activity limit
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this social activity limit.
	 *
	 * @return the class pk of this social activity limit
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this social activity limit.
	 *
	 * @param classPK the class pk of this social activity limit
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the activity type of this social activity limit.
	 *
	 * @return the activity type of this social activity limit
	 */
	public int getActivityType();

	/**
	 * Sets the activity type of this social activity limit.
	 *
	 * @param activityType the activity type of this social activity limit
	 */
	public void setActivityType(int activityType);

	/**
	 * Returns the activity counter name of this social activity limit.
	 *
	 * @return the activity counter name of this social activity limit
	 */
	@AutoEscape
	public String getActivityCounterName();

	/**
	 * Sets the activity counter name of this social activity limit.
	 *
	 * @param activityCounterName the activity counter name of this social activity limit
	 */
	public void setActivityCounterName(String activityCounterName);

	/**
	 * Returns the value of this social activity limit.
	 *
	 * @return the value of this social activity limit
	 */
	@AutoEscape
	public String getValue();

	/**
	 * Sets the value of this social activity limit.
	 *
	 * @param value the value of this social activity limit
	 */
	public void setValue(String value);

	@Override
	public SocialActivityLimit cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}