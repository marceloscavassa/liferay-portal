/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Module service. Represents a row in the &quot;Marketplace_Module&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.marketplace.model.impl.ModuleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.marketplace.model.impl.ModuleImpl</code>.
 * </p>
 *
 * @author Ryan Park
 * @see Module
 * @generated
 */
@ProviderType
public interface ModuleModel extends BaseModel<Module>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a module model instance should use the {@link Module} interface instead.
	 */

	/**
	 * Returns the primary key of this module.
	 *
	 * @return the primary key of this module
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this module.
	 *
	 * @param primaryKey the primary key of this module
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this module.
	 *
	 * @return the uuid of this module
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this module.
	 *
	 * @param uuid the uuid of this module
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the module ID of this module.
	 *
	 * @return the module ID of this module
	 */
	public long getModuleId();

	/**
	 * Sets the module ID of this module.
	 *
	 * @param moduleId the module ID of this module
	 */
	public void setModuleId(long moduleId);

	/**
	 * Returns the company ID of this module.
	 *
	 * @return the company ID of this module
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this module.
	 *
	 * @param companyId the company ID of this module
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the app ID of this module.
	 *
	 * @return the app ID of this module
	 */
	public long getAppId();

	/**
	 * Sets the app ID of this module.
	 *
	 * @param appId the app ID of this module
	 */
	public void setAppId(long appId);

	/**
	 * Returns the bundle symbolic name of this module.
	 *
	 * @return the bundle symbolic name of this module
	 */
	@AutoEscape
	public String getBundleSymbolicName();

	/**
	 * Sets the bundle symbolic name of this module.
	 *
	 * @param bundleSymbolicName the bundle symbolic name of this module
	 */
	public void setBundleSymbolicName(String bundleSymbolicName);

	/**
	 * Returns the bundle version of this module.
	 *
	 * @return the bundle version of this module
	 */
	@AutoEscape
	public String getBundleVersion();

	/**
	 * Sets the bundle version of this module.
	 *
	 * @param bundleVersion the bundle version of this module
	 */
	public void setBundleVersion(String bundleVersion);

	/**
	 * Returns the context name of this module.
	 *
	 * @return the context name of this module
	 */
	@AutoEscape
	public String getContextName();

	/**
	 * Sets the context name of this module.
	 *
	 * @param contextName the context name of this module
	 */
	public void setContextName(String contextName);

	@Override
	public Module cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}