/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FragmentComposition service. Represents a row in the &quot;FragmentComposition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.fragment.model.impl.FragmentCompositionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.fragment.model.impl.FragmentCompositionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentComposition
 * @generated
 */
@ProviderType
public interface FragmentCompositionModel
	extends BaseModel<FragmentComposition>, CTModel<FragmentComposition>,
			MVCCModel, ShardedModel, StagedGroupedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a fragment composition model instance should use the {@link FragmentComposition} interface instead.
	 */

	/**
	 * Returns the primary key of this fragment composition.
	 *
	 * @return the primary key of this fragment composition
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this fragment composition.
	 *
	 * @param primaryKey the primary key of this fragment composition
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this fragment composition.
	 *
	 * @return the mvcc version of this fragment composition
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this fragment composition.
	 *
	 * @param mvccVersion the mvcc version of this fragment composition
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this fragment composition.
	 *
	 * @return the ct collection ID of this fragment composition
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this fragment composition.
	 *
	 * @param ctCollectionId the ct collection ID of this fragment composition
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this fragment composition.
	 *
	 * @return the uuid of this fragment composition
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this fragment composition.
	 *
	 * @param uuid the uuid of this fragment composition
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the fragment composition ID of this fragment composition.
	 *
	 * @return the fragment composition ID of this fragment composition
	 */
	public long getFragmentCompositionId();

	/**
	 * Sets the fragment composition ID of this fragment composition.
	 *
	 * @param fragmentCompositionId the fragment composition ID of this fragment composition
	 */
	public void setFragmentCompositionId(long fragmentCompositionId);

	/**
	 * Returns the group ID of this fragment composition.
	 *
	 * @return the group ID of this fragment composition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this fragment composition.
	 *
	 * @param groupId the group ID of this fragment composition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this fragment composition.
	 *
	 * @return the company ID of this fragment composition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this fragment composition.
	 *
	 * @param companyId the company ID of this fragment composition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this fragment composition.
	 *
	 * @return the user ID of this fragment composition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this fragment composition.
	 *
	 * @param userId the user ID of this fragment composition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this fragment composition.
	 *
	 * @return the user uuid of this fragment composition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this fragment composition.
	 *
	 * @param userUuid the user uuid of this fragment composition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this fragment composition.
	 *
	 * @return the user name of this fragment composition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this fragment composition.
	 *
	 * @param userName the user name of this fragment composition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this fragment composition.
	 *
	 * @return the create date of this fragment composition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this fragment composition.
	 *
	 * @param createDate the create date of this fragment composition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this fragment composition.
	 *
	 * @return the modified date of this fragment composition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this fragment composition.
	 *
	 * @param modifiedDate the modified date of this fragment composition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fragment collection ID of this fragment composition.
	 *
	 * @return the fragment collection ID of this fragment composition
	 */
	public long getFragmentCollectionId();

	/**
	 * Sets the fragment collection ID of this fragment composition.
	 *
	 * @param fragmentCollectionId the fragment collection ID of this fragment composition
	 */
	public void setFragmentCollectionId(long fragmentCollectionId);

	/**
	 * Returns the fragment composition key of this fragment composition.
	 *
	 * @return the fragment composition key of this fragment composition
	 */
	@AutoEscape
	public String getFragmentCompositionKey();

	/**
	 * Sets the fragment composition key of this fragment composition.
	 *
	 * @param fragmentCompositionKey the fragment composition key of this fragment composition
	 */
	public void setFragmentCompositionKey(String fragmentCompositionKey);

	/**
	 * Returns the name of this fragment composition.
	 *
	 * @return the name of this fragment composition
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this fragment composition.
	 *
	 * @param name the name of this fragment composition
	 */
	public void setName(String name);

	/**
	 * Returns the description of this fragment composition.
	 *
	 * @return the description of this fragment composition
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this fragment composition.
	 *
	 * @param description the description of this fragment composition
	 */
	public void setDescription(String description);

	/**
	 * Returns the data of this fragment composition.
	 *
	 * @return the data of this fragment composition
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this fragment composition.
	 *
	 * @param data the data of this fragment composition
	 */
	public void setData(String data);

	/**
	 * Returns the preview file entry ID of this fragment composition.
	 *
	 * @return the preview file entry ID of this fragment composition
	 */
	public long getPreviewFileEntryId();

	/**
	 * Sets the preview file entry ID of this fragment composition.
	 *
	 * @param previewFileEntryId the preview file entry ID of this fragment composition
	 */
	public void setPreviewFileEntryId(long previewFileEntryId);

	/**
	 * Returns the last publish date of this fragment composition.
	 *
	 * @return the last publish date of this fragment composition
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this fragment composition.
	 *
	 * @param lastPublishDate the last publish date of this fragment composition
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this fragment composition.
	 *
	 * @return the status of this fragment composition
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this fragment composition.
	 *
	 * @param status the status of this fragment composition
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this fragment composition.
	 *
	 * @return the status by user ID of this fragment composition
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this fragment composition.
	 *
	 * @param statusByUserId the status by user ID of this fragment composition
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this fragment composition.
	 *
	 * @return the status by user uuid of this fragment composition
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this fragment composition.
	 *
	 * @param statusByUserUuid the status by user uuid of this fragment composition
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this fragment composition.
	 *
	 * @return the status by user name of this fragment composition
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this fragment composition.
	 *
	 * @param statusByUserName the status by user name of this fragment composition
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this fragment composition.
	 *
	 * @return the status date of this fragment composition
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this fragment composition.
	 *
	 * @param statusDate the status date of this fragment composition
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this fragment composition is approved.
	 *
	 * @return <code>true</code> if this fragment composition is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this fragment composition is denied.
	 *
	 * @return <code>true</code> if this fragment composition is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this fragment composition is a draft.
	 *
	 * @return <code>true</code> if this fragment composition is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this fragment composition is expired.
	 *
	 * @return <code>true</code> if this fragment composition is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this fragment composition is inactive.
	 *
	 * @return <code>true</code> if this fragment composition is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this fragment composition is incomplete.
	 *
	 * @return <code>true</code> if this fragment composition is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this fragment composition is pending.
	 *
	 * @return <code>true</code> if this fragment composition is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this fragment composition is scheduled.
	 *
	 * @return <code>true</code> if this fragment composition is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public FragmentComposition cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}