/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Image service. Represents a row in the &quot;Image&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ImageModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ImageImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Image
 * @generated
 */
@ProviderType
public interface ImageModel
	extends BaseModel<Image>, CTModel<Image>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a image model instance should use the {@link Image} interface instead.
	 */

	/**
	 * Returns the primary key of this image.
	 *
	 * @return the primary key of this image
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this image.
	 *
	 * @param primaryKey the primary key of this image
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this image.
	 *
	 * @return the mvcc version of this image
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this image.
	 *
	 * @param mvccVersion the mvcc version of this image
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this image.
	 *
	 * @return the ct collection ID of this image
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this image.
	 *
	 * @param ctCollectionId the ct collection ID of this image
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the image ID of this image.
	 *
	 * @return the image ID of this image
	 */
	public long getImageId();

	/**
	 * Sets the image ID of this image.
	 *
	 * @param imageId the image ID of this image
	 */
	public void setImageId(long imageId);

	/**
	 * Returns the company ID of this image.
	 *
	 * @return the company ID of this image
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this image.
	 *
	 * @param companyId the company ID of this image
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the modified date of this image.
	 *
	 * @return the modified date of this image
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this image.
	 *
	 * @param modifiedDate the modified date of this image
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the type of this image.
	 *
	 * @return the type of this image
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this image.
	 *
	 * @param type the type of this image
	 */
	public void setType(String type);

	/**
	 * Returns the height of this image.
	 *
	 * @return the height of this image
	 */
	public int getHeight();

	/**
	 * Sets the height of this image.
	 *
	 * @param height the height of this image
	 */
	public void setHeight(int height);

	/**
	 * Returns the width of this image.
	 *
	 * @return the width of this image
	 */
	public int getWidth();

	/**
	 * Sets the width of this image.
	 *
	 * @param width the width of this image
	 */
	public void setWidth(int width);

	/**
	 * Returns the size of this image.
	 *
	 * @return the size of this image
	 */
	public int getSize();

	/**
	 * Sets the size of this image.
	 *
	 * @param size the size of this image
	 */
	public void setSize(int size);

	@Override
	public Image cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}