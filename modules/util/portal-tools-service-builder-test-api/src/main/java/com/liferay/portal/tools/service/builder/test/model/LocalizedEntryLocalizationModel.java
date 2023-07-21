/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LocalizedEntryLocalization service. Represents a row in the &quot;LocalizedEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.LocalizedEntryLocalizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.LocalizedEntryLocalizationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LocalizedEntryLocalization
 * @generated
 */
@ProviderType
public interface LocalizedEntryLocalizationModel
	extends BaseModel<LocalizedEntryLocalization>, MVCCModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a localized entry localization model instance should use the {@link LocalizedEntryLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this localized entry localization.
	 *
	 * @return the primary key of this localized entry localization
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this localized entry localization.
	 *
	 * @param primaryKey the primary key of this localized entry localization
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this localized entry localization.
	 *
	 * @return the mvcc version of this localized entry localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this localized entry localization.
	 *
	 * @param mvccVersion the mvcc version of this localized entry localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the localized entry localization ID of this localized entry localization.
	 *
	 * @return the localized entry localization ID of this localized entry localization
	 */
	public long getLocalizedEntryLocalizationId();

	/**
	 * Sets the localized entry localization ID of this localized entry localization.
	 *
	 * @param localizedEntryLocalizationId the localized entry localization ID of this localized entry localization
	 */
	public void setLocalizedEntryLocalizationId(
		long localizedEntryLocalizationId);

	/**
	 * Returns the localized entry ID of this localized entry localization.
	 *
	 * @return the localized entry ID of this localized entry localization
	 */
	public long getLocalizedEntryId();

	/**
	 * Sets the localized entry ID of this localized entry localization.
	 *
	 * @param localizedEntryId the localized entry ID of this localized entry localization
	 */
	public void setLocalizedEntryId(long localizedEntryId);

	/**
	 * Returns the language ID of this localized entry localization.
	 *
	 * @return the language ID of this localized entry localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this localized entry localization.
	 *
	 * @param languageId the language ID of this localized entry localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the title of this localized entry localization.
	 *
	 * @return the title of this localized entry localization
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this localized entry localization.
	 *
	 * @param title the title of this localized entry localization
	 */
	public void setTitle(String title);

	/**
	 * Returns the content of this localized entry localization.
	 *
	 * @return the content of this localized entry localization
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this localized entry localization.
	 *
	 * @param content the content of this localized entry localization
	 */
	public void setContent(String content);

	@Override
	public LocalizedEntryLocalization cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}