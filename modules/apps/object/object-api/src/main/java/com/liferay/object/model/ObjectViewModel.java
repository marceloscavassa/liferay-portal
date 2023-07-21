/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectView service. Represents a row in the &quot;ObjectView&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectViewModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectViewImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectView
 * @generated
 */
@ProviderType
public interface ObjectViewModel
	extends BaseModel<ObjectView>, LocalizedModel, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object view model instance should use the {@link ObjectView} interface instead.
	 */

	/**
	 * Returns the primary key of this object view.
	 *
	 * @return the primary key of this object view
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object view.
	 *
	 * @param primaryKey the primary key of this object view
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object view.
	 *
	 * @return the mvcc version of this object view
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object view.
	 *
	 * @param mvccVersion the mvcc version of this object view
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object view.
	 *
	 * @return the uuid of this object view
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object view.
	 *
	 * @param uuid the uuid of this object view
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the object view ID of this object view.
	 *
	 * @return the object view ID of this object view
	 */
	public long getObjectViewId();

	/**
	 * Sets the object view ID of this object view.
	 *
	 * @param objectViewId the object view ID of this object view
	 */
	public void setObjectViewId(long objectViewId);

	/**
	 * Returns the company ID of this object view.
	 *
	 * @return the company ID of this object view
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object view.
	 *
	 * @param companyId the company ID of this object view
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object view.
	 *
	 * @return the user ID of this object view
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object view.
	 *
	 * @param userId the user ID of this object view
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object view.
	 *
	 * @return the user uuid of this object view
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object view.
	 *
	 * @param userUuid the user uuid of this object view
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object view.
	 *
	 * @return the user name of this object view
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object view.
	 *
	 * @param userName the user name of this object view
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object view.
	 *
	 * @return the create date of this object view
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object view.
	 *
	 * @param createDate the create date of this object view
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object view.
	 *
	 * @return the modified date of this object view
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object view.
	 *
	 * @param modifiedDate the modified date of this object view
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the object definition ID of this object view.
	 *
	 * @return the object definition ID of this object view
	 */
	public long getObjectDefinitionId();

	/**
	 * Sets the object definition ID of this object view.
	 *
	 * @param objectDefinitionId the object definition ID of this object view
	 */
	public void setObjectDefinitionId(long objectDefinitionId);

	/**
	 * Returns the default object view of this object view.
	 *
	 * @return the default object view of this object view
	 */
	public boolean getDefaultObjectView();

	/**
	 * Returns <code>true</code> if this object view is default object view.
	 *
	 * @return <code>true</code> if this object view is default object view; <code>false</code> otherwise
	 */
	public boolean isDefaultObjectView();

	/**
	 * Sets whether this object view is default object view.
	 *
	 * @param defaultObjectView the default object view of this object view
	 */
	public void setDefaultObjectView(boolean defaultObjectView);

	/**
	 * Returns the name of this object view.
	 *
	 * @return the name of this object view
	 */
	public String getName();

	/**
	 * Returns the localized name of this object view in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this object view
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this object view in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this object view. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this object view in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this object view
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this object view in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this object view
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this object view.
	 *
	 * @return the locales and localized names of this object view
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this object view.
	 *
	 * @param name the name of this object view
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this object view in the language.
	 *
	 * @param name the localized name of this object view
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this object view in the language, and sets the default locale.
	 *
	 * @param name the localized name of this object view
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this object view from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this object view
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this object view from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this object view
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public ObjectView cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}