/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shop.by.diagram.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CSDiagramEntry service. Represents a row in the &quot;CSDiagramEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramEntryImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CSDiagramEntry
 * @generated
 */
@ProviderType
public interface CSDiagramEntryModel
	extends AuditedModel, BaseModel<CSDiagramEntry>, CTModel<CSDiagramEntry>,
			MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cs diagram entry model instance should use the {@link CSDiagramEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this cs diagram entry.
	 *
	 * @return the primary key of this cs diagram entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cs diagram entry.
	 *
	 * @param primaryKey the primary key of this cs diagram entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this cs diagram entry.
	 *
	 * @return the mvcc version of this cs diagram entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this cs diagram entry.
	 *
	 * @param mvccVersion the mvcc version of this cs diagram entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this cs diagram entry.
	 *
	 * @return the ct collection ID of this cs diagram entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this cs diagram entry.
	 *
	 * @param ctCollectionId the ct collection ID of this cs diagram entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the cs diagram entry ID of this cs diagram entry.
	 *
	 * @return the cs diagram entry ID of this cs diagram entry
	 */
	public long getCSDiagramEntryId();

	/**
	 * Sets the cs diagram entry ID of this cs diagram entry.
	 *
	 * @param CSDiagramEntryId the cs diagram entry ID of this cs diagram entry
	 */
	public void setCSDiagramEntryId(long CSDiagramEntryId);

	/**
	 * Returns the company ID of this cs diagram entry.
	 *
	 * @return the company ID of this cs diagram entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cs diagram entry.
	 *
	 * @param companyId the company ID of this cs diagram entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cs diagram entry.
	 *
	 * @return the user ID of this cs diagram entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cs diagram entry.
	 *
	 * @param userId the user ID of this cs diagram entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cs diagram entry.
	 *
	 * @return the user uuid of this cs diagram entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cs diagram entry.
	 *
	 * @param userUuid the user uuid of this cs diagram entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cs diagram entry.
	 *
	 * @return the user name of this cs diagram entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cs diagram entry.
	 *
	 * @param userName the user name of this cs diagram entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cs diagram entry.
	 *
	 * @return the create date of this cs diagram entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cs diagram entry.
	 *
	 * @param createDate the create date of this cs diagram entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cs diagram entry.
	 *
	 * @return the modified date of this cs diagram entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cs diagram entry.
	 *
	 * @param modifiedDate the modified date of this cs diagram entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition ID of this cs diagram entry.
	 *
	 * @return the cp definition ID of this cs diagram entry
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cs diagram entry.
	 *
	 * @param CPDefinitionId the cp definition ID of this cs diagram entry
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the cp instance ID of this cs diagram entry.
	 *
	 * @return the cp instance ID of this cs diagram entry
	 */
	public long getCPInstanceId();

	/**
	 * Sets the cp instance ID of this cs diagram entry.
	 *
	 * @param CPInstanceId the cp instance ID of this cs diagram entry
	 */
	public void setCPInstanceId(long CPInstanceId);

	/**
	 * Returns the c product ID of this cs diagram entry.
	 *
	 * @return the c product ID of this cs diagram entry
	 */
	public long getCProductId();

	/**
	 * Sets the c product ID of this cs diagram entry.
	 *
	 * @param CProductId the c product ID of this cs diagram entry
	 */
	public void setCProductId(long CProductId);

	/**
	 * Returns the diagram of this cs diagram entry.
	 *
	 * @return the diagram of this cs diagram entry
	 */
	public boolean getDiagram();

	/**
	 * Returns <code>true</code> if this cs diagram entry is diagram.
	 *
	 * @return <code>true</code> if this cs diagram entry is diagram; <code>false</code> otherwise
	 */
	public boolean isDiagram();

	/**
	 * Sets whether this cs diagram entry is diagram.
	 *
	 * @param diagram the diagram of this cs diagram entry
	 */
	public void setDiagram(boolean diagram);

	/**
	 * Returns the quantity of this cs diagram entry.
	 *
	 * @return the quantity of this cs diagram entry
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this cs diagram entry.
	 *
	 * @param quantity the quantity of this cs diagram entry
	 */
	public void setQuantity(int quantity);

	/**
	 * Returns the sequence of this cs diagram entry.
	 *
	 * @return the sequence of this cs diagram entry
	 */
	@AutoEscape
	public String getSequence();

	/**
	 * Sets the sequence of this cs diagram entry.
	 *
	 * @param sequence the sequence of this cs diagram entry
	 */
	public void setSequence(String sequence);

	/**
	 * Returns the sku of this cs diagram entry.
	 *
	 * @return the sku of this cs diagram entry
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this cs diagram entry.
	 *
	 * @param sku the sku of this cs diagram entry
	 */
	public void setSku(String sku);

	@Override
	public CSDiagramEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}