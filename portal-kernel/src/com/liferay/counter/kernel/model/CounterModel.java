/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.counter.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Counter service. Represents a row in the &quot;Counter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.counter.model.impl.CounterModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.counter.model.impl.CounterImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Counter
 * @generated
 */
@ProviderType
public interface CounterModel extends BaseModel<Counter> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a counter model instance should use the {@link Counter} interface instead.
	 */

	/**
	 * Returns the primary key of this counter.
	 *
	 * @return the primary key of this counter
	 */
	public String getPrimaryKey();

	/**
	 * Sets the primary key of this counter.
	 *
	 * @param primaryKey the primary key of this counter
	 */
	public void setPrimaryKey(String primaryKey);

	/**
	 * Returns the name of this counter.
	 *
	 * @return the name of this counter
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this counter.
	 *
	 * @param name the name of this counter
	 */
	public void setName(String name);

	/**
	 * Returns the current ID of this counter.
	 *
	 * @return the current ID of this counter
	 */
	public long getCurrentId();

	/**
	 * Sets the current ID of this counter.
	 *
	 * @param currentId the current ID of this counter
	 */
	public void setCurrentId(long currentId);

	@Override
	public Counter cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}