/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceOrderType service. Represents a row in the &quot;CommerceOrderType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.model.impl.CommerceOrderTypeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.model.impl.CommerceOrderTypeImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderType
 * @generated
 */
@ProviderType
public interface CommerceOrderTypeModel
	extends BaseModel<CommerceOrderType>, LocalizedModel, MVCCModel,
			ShardedModel, StagedAuditedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce order type model instance should use the {@link CommerceOrderType} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce order type.
	 *
	 * @return the primary key of this commerce order type
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce order type.
	 *
	 * @param primaryKey the primary key of this commerce order type
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce order type.
	 *
	 * @return the mvcc version of this commerce order type
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce order type.
	 *
	 * @param mvccVersion the mvcc version of this commerce order type
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this commerce order type.
	 *
	 * @return the uuid of this commerce order type
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce order type.
	 *
	 * @param uuid the uuid of this commerce order type
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this commerce order type.
	 *
	 * @return the external reference code of this commerce order type
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce order type.
	 *
	 * @param externalReferenceCode the external reference code of this commerce order type
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce order type ID of this commerce order type.
	 *
	 * @return the commerce order type ID of this commerce order type
	 */
	public long getCommerceOrderTypeId();

	/**
	 * Sets the commerce order type ID of this commerce order type.
	 *
	 * @param commerceOrderTypeId the commerce order type ID of this commerce order type
	 */
	public void setCommerceOrderTypeId(long commerceOrderTypeId);

	/**
	 * Returns the company ID of this commerce order type.
	 *
	 * @return the company ID of this commerce order type
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce order type.
	 *
	 * @param companyId the company ID of this commerce order type
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce order type.
	 *
	 * @return the user ID of this commerce order type
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce order type.
	 *
	 * @param userId the user ID of this commerce order type
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce order type.
	 *
	 * @return the user uuid of this commerce order type
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce order type.
	 *
	 * @param userUuid the user uuid of this commerce order type
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce order type.
	 *
	 * @return the user name of this commerce order type
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce order type.
	 *
	 * @param userName the user name of this commerce order type
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce order type.
	 *
	 * @return the create date of this commerce order type
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce order type.
	 *
	 * @param createDate the create date of this commerce order type
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce order type.
	 *
	 * @return the modified date of this commerce order type
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce order type.
	 *
	 * @param modifiedDate the modified date of this commerce order type
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this commerce order type.
	 *
	 * @return the name of this commerce order type
	 */
	public String getName();

	/**
	 * Returns the localized name of this commerce order type in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this commerce order type
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this commerce order type in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce order type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this commerce order type in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this commerce order type
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this commerce order type in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce order type
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this commerce order type.
	 *
	 * @return the locales and localized names of this commerce order type
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this commerce order type.
	 *
	 * @param name the name of this commerce order type
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this commerce order type in the language.
	 *
	 * @param name the localized name of this commerce order type
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this commerce order type in the language, and sets the default locale.
	 *
	 * @param name the localized name of this commerce order type
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this commerce order type from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this commerce order type
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this commerce order type from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this commerce order type
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this commerce order type.
	 *
	 * @return the description of this commerce order type
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this commerce order type in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this commerce order type
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this commerce order type in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce order type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this commerce order type in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this commerce order type
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this commerce order type in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce order type
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this commerce order type.
	 *
	 * @return the locales and localized descriptions of this commerce order type
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this commerce order type.
	 *
	 * @param description the description of this commerce order type
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this commerce order type in the language.
	 *
	 * @param description the localized description of this commerce order type
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this commerce order type in the language, and sets the default locale.
	 *
	 * @param description the localized description of this commerce order type
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this commerce order type from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce order type
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this commerce order type from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce order type
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the active of this commerce order type.
	 *
	 * @return the active of this commerce order type
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this commerce order type is active.
	 *
	 * @return <code>true</code> if this commerce order type is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this commerce order type is active.
	 *
	 * @param active the active of this commerce order type
	 */
	public void setActive(boolean active);

	/**
	 * Returns the display date of this commerce order type.
	 *
	 * @return the display date of this commerce order type
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this commerce order type.
	 *
	 * @param displayDate the display date of this commerce order type
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the display order of this commerce order type.
	 *
	 * @return the display order of this commerce order type
	 */
	public int getDisplayOrder();

	/**
	 * Sets the display order of this commerce order type.
	 *
	 * @param displayOrder the display order of this commerce order type
	 */
	public void setDisplayOrder(int displayOrder);

	/**
	 * Returns the expiration date of this commerce order type.
	 *
	 * @return the expiration date of this commerce order type
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this commerce order type.
	 *
	 * @param expirationDate the expiration date of this commerce order type
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the last publish date of this commerce order type.
	 *
	 * @return the last publish date of this commerce order type
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce order type.
	 *
	 * @param lastPublishDate the last publish date of this commerce order type
	 */
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this commerce order type.
	 *
	 * @return the status of this commerce order type
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this commerce order type.
	 *
	 * @param status the status of this commerce order type
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this commerce order type.
	 *
	 * @return the status by user ID of this commerce order type
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this commerce order type.
	 *
	 * @param statusByUserId the status by user ID of this commerce order type
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this commerce order type.
	 *
	 * @return the status by user uuid of this commerce order type
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this commerce order type.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce order type
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this commerce order type.
	 *
	 * @return the status by user name of this commerce order type
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this commerce order type.
	 *
	 * @param statusByUserName the status by user name of this commerce order type
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this commerce order type.
	 *
	 * @return the status date of this commerce order type
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this commerce order type.
	 *
	 * @param statusDate the status date of this commerce order type
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this commerce order type is approved.
	 *
	 * @return <code>true</code> if this commerce order type is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this commerce order type is denied.
	 *
	 * @return <code>true</code> if this commerce order type is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this commerce order type is a draft.
	 *
	 * @return <code>true</code> if this commerce order type is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this commerce order type is expired.
	 *
	 * @return <code>true</code> if this commerce order type is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this commerce order type is inactive.
	 *
	 * @return <code>true</code> if this commerce order type is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this commerce order type is incomplete.
	 *
	 * @return <code>true</code> if this commerce order type is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this commerce order type is pending.
	 *
	 * @return <code>true</code> if this commerce order type is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this commerce order type is scheduled.
	 *
	 * @return <code>true</code> if this commerce order type is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public CommerceOrderType cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}