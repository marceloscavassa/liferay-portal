/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.entry.rel.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AssetEntryAssetCategoryRel service. Represents a row in the &quot;AssetEntryAssetCategoryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetCategoryRel
 * @generated
 */
@ProviderType
public interface AssetEntryAssetCategoryRelModel
	extends BaseModel<AssetEntryAssetCategoryRel>,
			CTModel<AssetEntryAssetCategoryRel>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset entry asset category rel model instance should use the {@link AssetEntryAssetCategoryRel} interface instead.
	 */

	/**
	 * Returns the primary key of this asset entry asset category rel.
	 *
	 * @return the primary key of this asset entry asset category rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset entry asset category rel.
	 *
	 * @param primaryKey the primary key of this asset entry asset category rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this asset entry asset category rel.
	 *
	 * @return the mvcc version of this asset entry asset category rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this asset entry asset category rel.
	 *
	 * @param mvccVersion the mvcc version of this asset entry asset category rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this asset entry asset category rel.
	 *
	 * @return the ct collection ID of this asset entry asset category rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this asset entry asset category rel.
	 *
	 * @param ctCollectionId the ct collection ID of this asset entry asset category rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the asset entry asset category rel ID of this asset entry asset category rel.
	 *
	 * @return the asset entry asset category rel ID of this asset entry asset category rel
	 */
	public long getAssetEntryAssetCategoryRelId();

	/**
	 * Sets the asset entry asset category rel ID of this asset entry asset category rel.
	 *
	 * @param assetEntryAssetCategoryRelId the asset entry asset category rel ID of this asset entry asset category rel
	 */
	public void setAssetEntryAssetCategoryRelId(
		long assetEntryAssetCategoryRelId);

	/**
	 * Returns the company ID of this asset entry asset category rel.
	 *
	 * @return the company ID of this asset entry asset category rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this asset entry asset category rel.
	 *
	 * @param companyId the company ID of this asset entry asset category rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the asset entry ID of this asset entry asset category rel.
	 *
	 * @return the asset entry ID of this asset entry asset category rel
	 */
	public long getAssetEntryId();

	/**
	 * Sets the asset entry ID of this asset entry asset category rel.
	 *
	 * @param assetEntryId the asset entry ID of this asset entry asset category rel
	 */
	public void setAssetEntryId(long assetEntryId);

	/**
	 * Returns the asset category ID of this asset entry asset category rel.
	 *
	 * @return the asset category ID of this asset entry asset category rel
	 */
	public long getAssetCategoryId();

	/**
	 * Sets the asset category ID of this asset entry asset category rel.
	 *
	 * @param assetCategoryId the asset category ID of this asset entry asset category rel
	 */
	public void setAssetCategoryId(long assetCategoryId);

	/**
	 * Returns the priority of this asset entry asset category rel.
	 *
	 * @return the priority of this asset entry asset category rel
	 */
	public int getPriority();

	/**
	 * Sets the priority of this asset entry asset category rel.
	 *
	 * @param priority the priority of this asset entry asset category rel
	 */
	public void setPriority(int priority);

	@Override
	public AssetEntryAssetCategoryRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}