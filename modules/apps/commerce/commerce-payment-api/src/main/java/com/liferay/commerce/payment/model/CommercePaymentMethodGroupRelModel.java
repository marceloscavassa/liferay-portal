/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.payment.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePaymentMethodGroupRel service. Represents a row in the &quot;CommercePaymentMethodGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentMethodGroupRel
 * @generated
 */
@ProviderType
public interface CommercePaymentMethodGroupRelModel
	extends BaseModel<CommercePaymentMethodGroupRel>, GroupedModel,
			LocalizedModel, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce payment method group rel model instance should use the {@link CommercePaymentMethodGroupRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce payment method group rel.
	 *
	 * @return the primary key of this commerce payment method group rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce payment method group rel.
	 *
	 * @param primaryKey the primary key of this commerce payment method group rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce payment method group rel.
	 *
	 * @return the mvcc version of this commerce payment method group rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce payment method group rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce payment method group rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce payment method group rel ID of this commerce payment method group rel.
	 *
	 * @return the commerce payment method group rel ID of this commerce payment method group rel
	 */
	public long getCommercePaymentMethodGroupRelId();

	/**
	 * Sets the commerce payment method group rel ID of this commerce payment method group rel.
	 *
	 * @param commercePaymentMethodGroupRelId the commerce payment method group rel ID of this commerce payment method group rel
	 */
	public void setCommercePaymentMethodGroupRelId(
		long commercePaymentMethodGroupRelId);

	/**
	 * Returns the group ID of this commerce payment method group rel.
	 *
	 * @return the group ID of this commerce payment method group rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce payment method group rel.
	 *
	 * @param groupId the group ID of this commerce payment method group rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce payment method group rel.
	 *
	 * @return the company ID of this commerce payment method group rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce payment method group rel.
	 *
	 * @param companyId the company ID of this commerce payment method group rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce payment method group rel.
	 *
	 * @return the user ID of this commerce payment method group rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce payment method group rel.
	 *
	 * @param userId the user ID of this commerce payment method group rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce payment method group rel.
	 *
	 * @return the user uuid of this commerce payment method group rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce payment method group rel.
	 *
	 * @param userUuid the user uuid of this commerce payment method group rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce payment method group rel.
	 *
	 * @return the user name of this commerce payment method group rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce payment method group rel.
	 *
	 * @param userName the user name of this commerce payment method group rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce payment method group rel.
	 *
	 * @return the create date of this commerce payment method group rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce payment method group rel.
	 *
	 * @param createDate the create date of this commerce payment method group rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce payment method group rel.
	 *
	 * @return the modified date of this commerce payment method group rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce payment method group rel.
	 *
	 * @param modifiedDate the modified date of this commerce payment method group rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this commerce payment method group rel.
	 *
	 * @return the name of this commerce payment method group rel
	 */
	public String getName();

	/**
	 * Returns the localized name of this commerce payment method group rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this commerce payment method group rel
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this commerce payment method group rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce payment method group rel. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this commerce payment method group rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this commerce payment method group rel
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this commerce payment method group rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce payment method group rel
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this commerce payment method group rel.
	 *
	 * @return the locales and localized names of this commerce payment method group rel
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this commerce payment method group rel.
	 *
	 * @param name the name of this commerce payment method group rel
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this commerce payment method group rel in the language.
	 *
	 * @param name the localized name of this commerce payment method group rel
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this commerce payment method group rel in the language, and sets the default locale.
	 *
	 * @param name the localized name of this commerce payment method group rel
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this commerce payment method group rel from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this commerce payment method group rel
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this commerce payment method group rel from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this commerce payment method group rel
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this commerce payment method group rel.
	 *
	 * @return the description of this commerce payment method group rel
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this commerce payment method group rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this commerce payment method group rel
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this commerce payment method group rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce payment method group rel. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this commerce payment method group rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this commerce payment method group rel
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this commerce payment method group rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce payment method group rel
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this commerce payment method group rel.
	 *
	 * @return the locales and localized descriptions of this commerce payment method group rel
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this commerce payment method group rel.
	 *
	 * @param description the description of this commerce payment method group rel
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this commerce payment method group rel in the language.
	 *
	 * @param description the localized description of this commerce payment method group rel
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this commerce payment method group rel in the language, and sets the default locale.
	 *
	 * @param description the localized description of this commerce payment method group rel
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this commerce payment method group rel from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce payment method group rel
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this commerce payment method group rel from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce payment method group rel
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the active of this commerce payment method group rel.
	 *
	 * @return the active of this commerce payment method group rel
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this commerce payment method group rel is active.
	 *
	 * @return <code>true</code> if this commerce payment method group rel is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this commerce payment method group rel is active.
	 *
	 * @param active the active of this commerce payment method group rel
	 */
	public void setActive(boolean active);

	/**
	 * Returns the image ID of this commerce payment method group rel.
	 *
	 * @return the image ID of this commerce payment method group rel
	 */
	public long getImageId();

	/**
	 * Sets the image ID of this commerce payment method group rel.
	 *
	 * @param imageId the image ID of this commerce payment method group rel
	 */
	public void setImageId(long imageId);

	/**
	 * Returns the payment integration key of this commerce payment method group rel.
	 *
	 * @return the payment integration key of this commerce payment method group rel
	 */
	@AutoEscape
	public String getPaymentIntegrationKey();

	/**
	 * Sets the payment integration key of this commerce payment method group rel.
	 *
	 * @param paymentIntegrationKey the payment integration key of this commerce payment method group rel
	 */
	public void setPaymentIntegrationKey(String paymentIntegrationKey);

	/**
	 * Returns the priority of this commerce payment method group rel.
	 *
	 * @return the priority of this commerce payment method group rel
	 */
	public double getPriority();

	/**
	 * Sets the priority of this commerce payment method group rel.
	 *
	 * @param priority the priority of this commerce payment method group rel
	 */
	public void setPriority(double priority);

	/**
	 * Returns the type settings of this commerce payment method group rel.
	 *
	 * @return the type settings of this commerce payment method group rel
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this commerce payment method group rel.
	 *
	 * @param typeSettings the type settings of this commerce payment method group rel
	 */
	public void setTypeSettings(String typeSettings);

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
	public CommercePaymentMethodGroupRel cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}