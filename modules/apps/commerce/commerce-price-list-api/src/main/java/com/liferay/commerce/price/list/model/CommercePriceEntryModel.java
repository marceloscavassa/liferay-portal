/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.math.BigDecimal;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePriceEntry service. Represents a row in the &quot;CommercePriceEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.price.list.model.impl.CommercePriceEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.price.list.model.impl.CommercePriceEntryImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceEntry
 * @generated
 */
@ProviderType
public interface CommercePriceEntryModel
	extends BaseModel<CommercePriceEntry>, CTModel<CommercePriceEntry>,
			MVCCModel, ShardedModel, StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price entry model instance should use the {@link CommercePriceEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price entry.
	 *
	 * @return the primary key of this commerce price entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price entry.
	 *
	 * @param primaryKey the primary key of this commerce price entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce price entry.
	 *
	 * @return the mvcc version of this commerce price entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce price entry.
	 *
	 * @param mvccVersion the mvcc version of this commerce price entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this commerce price entry.
	 *
	 * @return the ct collection ID of this commerce price entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this commerce price entry.
	 *
	 * @param ctCollectionId the ct collection ID of this commerce price entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this commerce price entry.
	 *
	 * @return the uuid of this commerce price entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce price entry.
	 *
	 * @param uuid the uuid of this commerce price entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this commerce price entry.
	 *
	 * @return the external reference code of this commerce price entry
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce price entry.
	 *
	 * @param externalReferenceCode the external reference code of this commerce price entry
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce price entry ID of this commerce price entry.
	 *
	 * @return the commerce price entry ID of this commerce price entry
	 */
	public long getCommercePriceEntryId();

	/**
	 * Sets the commerce price entry ID of this commerce price entry.
	 *
	 * @param commercePriceEntryId the commerce price entry ID of this commerce price entry
	 */
	public void setCommercePriceEntryId(long commercePriceEntryId);

	/**
	 * Returns the company ID of this commerce price entry.
	 *
	 * @return the company ID of this commerce price entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price entry.
	 *
	 * @param companyId the company ID of this commerce price entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price entry.
	 *
	 * @return the user ID of this commerce price entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price entry.
	 *
	 * @param userId the user ID of this commerce price entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price entry.
	 *
	 * @return the user uuid of this commerce price entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price entry.
	 *
	 * @param userUuid the user uuid of this commerce price entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price entry.
	 *
	 * @return the user name of this commerce price entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price entry.
	 *
	 * @param userName the user name of this commerce price entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price entry.
	 *
	 * @return the create date of this commerce price entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price entry.
	 *
	 * @param createDate the create date of this commerce price entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price entry.
	 *
	 * @return the modified date of this commerce price entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price entry.
	 *
	 * @param modifiedDate the modified date of this commerce price entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce price list ID of this commerce price entry.
	 *
	 * @return the commerce price list ID of this commerce price entry
	 */
	public long getCommercePriceListId();

	/**
	 * Sets the commerce price list ID of this commerce price entry.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price entry
	 */
	public void setCommercePriceListId(long commercePriceListId);

	/**
	 * Returns the cp instance uuid of this commerce price entry.
	 *
	 * @return the cp instance uuid of this commerce price entry
	 */
	@AutoEscape
	public String getCPInstanceUuid();

	/**
	 * Sets the cp instance uuid of this commerce price entry.
	 *
	 * @param CPInstanceUuid the cp instance uuid of this commerce price entry
	 */
	public void setCPInstanceUuid(String CPInstanceUuid);

	/**
	 * Returns the c product ID of this commerce price entry.
	 *
	 * @return the c product ID of this commerce price entry
	 */
	public long getCProductId();

	/**
	 * Sets the c product ID of this commerce price entry.
	 *
	 * @param CProductId the c product ID of this commerce price entry
	 */
	public void setCProductId(long CProductId);

	/**
	 * Returns the bulk pricing of this commerce price entry.
	 *
	 * @return the bulk pricing of this commerce price entry
	 */
	public boolean getBulkPricing();

	/**
	 * Returns <code>true</code> if this commerce price entry is bulk pricing.
	 *
	 * @return <code>true</code> if this commerce price entry is bulk pricing; <code>false</code> otherwise
	 */
	public boolean isBulkPricing();

	/**
	 * Sets whether this commerce price entry is bulk pricing.
	 *
	 * @param bulkPricing the bulk pricing of this commerce price entry
	 */
	public void setBulkPricing(boolean bulkPricing);

	/**
	 * Returns the discount discovery of this commerce price entry.
	 *
	 * @return the discount discovery of this commerce price entry
	 */
	public boolean getDiscountDiscovery();

	/**
	 * Returns <code>true</code> if this commerce price entry is discount discovery.
	 *
	 * @return <code>true</code> if this commerce price entry is discount discovery; <code>false</code> otherwise
	 */
	public boolean isDiscountDiscovery();

	/**
	 * Sets whether this commerce price entry is discount discovery.
	 *
	 * @param discountDiscovery the discount discovery of this commerce price entry
	 */
	public void setDiscountDiscovery(boolean discountDiscovery);

	/**
	 * Returns the discount level1 of this commerce price entry.
	 *
	 * @return the discount level1 of this commerce price entry
	 */
	public BigDecimal getDiscountLevel1();

	/**
	 * Sets the discount level1 of this commerce price entry.
	 *
	 * @param discountLevel1 the discount level1 of this commerce price entry
	 */
	public void setDiscountLevel1(BigDecimal discountLevel1);

	/**
	 * Returns the discount level2 of this commerce price entry.
	 *
	 * @return the discount level2 of this commerce price entry
	 */
	public BigDecimal getDiscountLevel2();

	/**
	 * Sets the discount level2 of this commerce price entry.
	 *
	 * @param discountLevel2 the discount level2 of this commerce price entry
	 */
	public void setDiscountLevel2(BigDecimal discountLevel2);

	/**
	 * Returns the discount level3 of this commerce price entry.
	 *
	 * @return the discount level3 of this commerce price entry
	 */
	public BigDecimal getDiscountLevel3();

	/**
	 * Sets the discount level3 of this commerce price entry.
	 *
	 * @param discountLevel3 the discount level3 of this commerce price entry
	 */
	public void setDiscountLevel3(BigDecimal discountLevel3);

	/**
	 * Returns the discount level4 of this commerce price entry.
	 *
	 * @return the discount level4 of this commerce price entry
	 */
	public BigDecimal getDiscountLevel4();

	/**
	 * Sets the discount level4 of this commerce price entry.
	 *
	 * @param discountLevel4 the discount level4 of this commerce price entry
	 */
	public void setDiscountLevel4(BigDecimal discountLevel4);

	/**
	 * Returns the display date of this commerce price entry.
	 *
	 * @return the display date of this commerce price entry
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this commerce price entry.
	 *
	 * @param displayDate the display date of this commerce price entry
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this commerce price entry.
	 *
	 * @return the expiration date of this commerce price entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this commerce price entry.
	 *
	 * @param expirationDate the expiration date of this commerce price entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the has tier price of this commerce price entry.
	 *
	 * @return the has tier price of this commerce price entry
	 */
	public boolean getHasTierPrice();

	/**
	 * Returns <code>true</code> if this commerce price entry is has tier price.
	 *
	 * @return <code>true</code> if this commerce price entry is has tier price; <code>false</code> otherwise
	 */
	public boolean isHasTierPrice();

	/**
	 * Sets whether this commerce price entry is has tier price.
	 *
	 * @param hasTierPrice the has tier price of this commerce price entry
	 */
	public void setHasTierPrice(boolean hasTierPrice);

	/**
	 * Returns the price of this commerce price entry.
	 *
	 * @return the price of this commerce price entry
	 */
	public BigDecimal getPrice();

	/**
	 * Sets the price of this commerce price entry.
	 *
	 * @param price the price of this commerce price entry
	 */
	public void setPrice(BigDecimal price);

	/**
	 * Returns the price on application of this commerce price entry.
	 *
	 * @return the price on application of this commerce price entry
	 */
	public boolean getPriceOnApplication();

	/**
	 * Returns <code>true</code> if this commerce price entry is price on application.
	 *
	 * @return <code>true</code> if this commerce price entry is price on application; <code>false</code> otherwise
	 */
	public boolean isPriceOnApplication();

	/**
	 * Sets whether this commerce price entry is price on application.
	 *
	 * @param priceOnApplication the price on application of this commerce price entry
	 */
	public void setPriceOnApplication(boolean priceOnApplication);

	/**
	 * Returns the promo price of this commerce price entry.
	 *
	 * @return the promo price of this commerce price entry
	 */
	public BigDecimal getPromoPrice();

	/**
	 * Sets the promo price of this commerce price entry.
	 *
	 * @param promoPrice the promo price of this commerce price entry
	 */
	public void setPromoPrice(BigDecimal promoPrice);

	/**
	 * Returns the quantity of this commerce price entry.
	 *
	 * @return the quantity of this commerce price entry
	 */
	public BigDecimal getQuantity();

	/**
	 * Sets the quantity of this commerce price entry.
	 *
	 * @param quantity the quantity of this commerce price entry
	 */
	public void setQuantity(BigDecimal quantity);

	/**
	 * Returns the unit of measure key of this commerce price entry.
	 *
	 * @return the unit of measure key of this commerce price entry
	 */
	@AutoEscape
	public String getUnitOfMeasureKey();

	/**
	 * Sets the unit of measure key of this commerce price entry.
	 *
	 * @param unitOfMeasureKey the unit of measure key of this commerce price entry
	 */
	public void setUnitOfMeasureKey(String unitOfMeasureKey);

	/**
	 * Returns the last publish date of this commerce price entry.
	 *
	 * @return the last publish date of this commerce price entry
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce price entry.
	 *
	 * @param lastPublishDate the last publish date of this commerce price entry
	 */
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this commerce price entry.
	 *
	 * @return the status of this commerce price entry
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this commerce price entry.
	 *
	 * @param status the status of this commerce price entry
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this commerce price entry.
	 *
	 * @return the status by user ID of this commerce price entry
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this commerce price entry.
	 *
	 * @param statusByUserId the status by user ID of this commerce price entry
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this commerce price entry.
	 *
	 * @return the status by user uuid of this commerce price entry
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this commerce price entry.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce price entry
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this commerce price entry.
	 *
	 * @return the status by user name of this commerce price entry
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this commerce price entry.
	 *
	 * @param statusByUserName the status by user name of this commerce price entry
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this commerce price entry.
	 *
	 * @return the status date of this commerce price entry
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this commerce price entry.
	 *
	 * @param statusDate the status date of this commerce price entry
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this commerce price entry is approved.
	 *
	 * @return <code>true</code> if this commerce price entry is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this commerce price entry is denied.
	 *
	 * @return <code>true</code> if this commerce price entry is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this commerce price entry is a draft.
	 *
	 * @return <code>true</code> if this commerce price entry is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this commerce price entry is expired.
	 *
	 * @return <code>true</code> if this commerce price entry is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this commerce price entry is inactive.
	 *
	 * @return <code>true</code> if this commerce price entry is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this commerce price entry is incomplete.
	 *
	 * @return <code>true</code> if this commerce price entry is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this commerce price entry is pending.
	 *
	 * @return <code>true</code> if this commerce price entry is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this commerce price entry is scheduled.
	 *
	 * @return <code>true</code> if this commerce price entry is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public CommercePriceEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}