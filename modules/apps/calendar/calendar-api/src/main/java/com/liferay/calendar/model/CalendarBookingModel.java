/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CalendarBooking service. Represents a row in the &quot;CalendarBooking&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.calendar.model.impl.CalendarBookingModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.calendar.model.impl.CalendarBookingImpl</code>.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBooking
 * @generated
 */
@ProviderType
public interface CalendarBookingModel
	extends BaseModel<CalendarBooking>, CTModel<CalendarBooking>,
			LocalizedModel, MVCCModel, ShardedModel, StagedGroupedModel,
			TrashedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a calendar booking model instance should use the {@link CalendarBooking} interface instead.
	 */

	/**
	 * Returns the primary key of this calendar booking.
	 *
	 * @return the primary key of this calendar booking
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this calendar booking.
	 *
	 * @param primaryKey the primary key of this calendar booking
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this calendar booking.
	 *
	 * @return the mvcc version of this calendar booking
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this calendar booking.
	 *
	 * @param mvccVersion the mvcc version of this calendar booking
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this calendar booking.
	 *
	 * @return the ct collection ID of this calendar booking
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this calendar booking.
	 *
	 * @param ctCollectionId the ct collection ID of this calendar booking
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this calendar booking.
	 *
	 * @return the uuid of this calendar booking
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this calendar booking.
	 *
	 * @param uuid the uuid of this calendar booking
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the calendar booking ID of this calendar booking.
	 *
	 * @return the calendar booking ID of this calendar booking
	 */
	public long getCalendarBookingId();

	/**
	 * Sets the calendar booking ID of this calendar booking.
	 *
	 * @param calendarBookingId the calendar booking ID of this calendar booking
	 */
	public void setCalendarBookingId(long calendarBookingId);

	/**
	 * Returns the group ID of this calendar booking.
	 *
	 * @return the group ID of this calendar booking
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this calendar booking.
	 *
	 * @param groupId the group ID of this calendar booking
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this calendar booking.
	 *
	 * @return the company ID of this calendar booking
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this calendar booking.
	 *
	 * @param companyId the company ID of this calendar booking
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this calendar booking.
	 *
	 * @return the user ID of this calendar booking
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this calendar booking.
	 *
	 * @param userId the user ID of this calendar booking
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this calendar booking.
	 *
	 * @return the user uuid of this calendar booking
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this calendar booking.
	 *
	 * @param userUuid the user uuid of this calendar booking
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this calendar booking.
	 *
	 * @return the user name of this calendar booking
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this calendar booking.
	 *
	 * @param userName the user name of this calendar booking
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this calendar booking.
	 *
	 * @return the create date of this calendar booking
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this calendar booking.
	 *
	 * @param createDate the create date of this calendar booking
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this calendar booking.
	 *
	 * @return the modified date of this calendar booking
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this calendar booking.
	 *
	 * @param modifiedDate the modified date of this calendar booking
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the calendar ID of this calendar booking.
	 *
	 * @return the calendar ID of this calendar booking
	 */
	public long getCalendarId();

	/**
	 * Sets the calendar ID of this calendar booking.
	 *
	 * @param calendarId the calendar ID of this calendar booking
	 */
	public void setCalendarId(long calendarId);

	/**
	 * Returns the calendar resource ID of this calendar booking.
	 *
	 * @return the calendar resource ID of this calendar booking
	 */
	public long getCalendarResourceId();

	/**
	 * Sets the calendar resource ID of this calendar booking.
	 *
	 * @param calendarResourceId the calendar resource ID of this calendar booking
	 */
	public void setCalendarResourceId(long calendarResourceId);

	/**
	 * Returns the parent calendar booking ID of this calendar booking.
	 *
	 * @return the parent calendar booking ID of this calendar booking
	 */
	public long getParentCalendarBookingId();

	/**
	 * Sets the parent calendar booking ID of this calendar booking.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID of this calendar booking
	 */
	public void setParentCalendarBookingId(long parentCalendarBookingId);

	/**
	 * Returns the recurring calendar booking ID of this calendar booking.
	 *
	 * @return the recurring calendar booking ID of this calendar booking
	 */
	public long getRecurringCalendarBookingId();

	/**
	 * Sets the recurring calendar booking ID of this calendar booking.
	 *
	 * @param recurringCalendarBookingId the recurring calendar booking ID of this calendar booking
	 */
	public void setRecurringCalendarBookingId(long recurringCalendarBookingId);

	/**
	 * Returns the v event uid of this calendar booking.
	 *
	 * @return the v event uid of this calendar booking
	 */
	@AutoEscape
	public String getVEventUid();

	/**
	 * Sets the v event uid of this calendar booking.
	 *
	 * @param vEventUid the v event uid of this calendar booking
	 */
	public void setVEventUid(String vEventUid);

	/**
	 * Returns the title of this calendar booking.
	 *
	 * @return the title of this calendar booking
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this calendar booking
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this calendar booking
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this calendar booking
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this calendar booking.
	 *
	 * @return the locales and localized titles of this calendar booking
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this calendar booking.
	 *
	 * @param title the title of this calendar booking
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this calendar booking in the language.
	 *
	 * @param title the localized title of this calendar booking
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this calendar booking in the language, and sets the default locale.
	 *
	 * @param title the localized title of this calendar booking
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this calendar booking from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this calendar booking
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this calendar booking from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this calendar booking
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this calendar booking.
	 *
	 * @return the description of this calendar booking
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this calendar booking
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this calendar booking. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this calendar booking in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this calendar booking
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this calendar booking in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this calendar booking
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this calendar booking.
	 *
	 * @return the locales and localized descriptions of this calendar booking
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this calendar booking.
	 *
	 * @param description the description of this calendar booking
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this calendar booking in the language.
	 *
	 * @param description the localized description of this calendar booking
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this calendar booking in the language, and sets the default locale.
	 *
	 * @param description the localized description of this calendar booking
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this calendar booking from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this calendar booking
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this calendar booking from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this calendar booking
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the location of this calendar booking.
	 *
	 * @return the location of this calendar booking
	 */
	@AutoEscape
	public String getLocation();

	/**
	 * Sets the location of this calendar booking.
	 *
	 * @param location the location of this calendar booking
	 */
	public void setLocation(String location);

	/**
	 * Returns the start time of this calendar booking.
	 *
	 * @return the start time of this calendar booking
	 */
	public long getStartTime();

	/**
	 * Sets the start time of this calendar booking.
	 *
	 * @param startTime the start time of this calendar booking
	 */
	public void setStartTime(long startTime);

	/**
	 * Returns the end time of this calendar booking.
	 *
	 * @return the end time of this calendar booking
	 */
	public long getEndTime();

	/**
	 * Sets the end time of this calendar booking.
	 *
	 * @param endTime the end time of this calendar booking
	 */
	public void setEndTime(long endTime);

	/**
	 * Returns the all day of this calendar booking.
	 *
	 * @return the all day of this calendar booking
	 */
	public boolean getAllDay();

	/**
	 * Returns <code>true</code> if this calendar booking is all day.
	 *
	 * @return <code>true</code> if this calendar booking is all day; <code>false</code> otherwise
	 */
	public boolean isAllDay();

	/**
	 * Sets whether this calendar booking is all day.
	 *
	 * @param allDay the all day of this calendar booking
	 */
	public void setAllDay(boolean allDay);

	/**
	 * Returns the recurrence of this calendar booking.
	 *
	 * @return the recurrence of this calendar booking
	 */
	@AutoEscape
	public String getRecurrence();

	/**
	 * Sets the recurrence of this calendar booking.
	 *
	 * @param recurrence the recurrence of this calendar booking
	 */
	public void setRecurrence(String recurrence);

	/**
	 * Returns the first reminder of this calendar booking.
	 *
	 * @return the first reminder of this calendar booking
	 */
	public long getFirstReminder();

	/**
	 * Sets the first reminder of this calendar booking.
	 *
	 * @param firstReminder the first reminder of this calendar booking
	 */
	public void setFirstReminder(long firstReminder);

	/**
	 * Returns the first reminder type of this calendar booking.
	 *
	 * @return the first reminder type of this calendar booking
	 */
	@AutoEscape
	public String getFirstReminderType();

	/**
	 * Sets the first reminder type of this calendar booking.
	 *
	 * @param firstReminderType the first reminder type of this calendar booking
	 */
	public void setFirstReminderType(String firstReminderType);

	/**
	 * Returns the second reminder of this calendar booking.
	 *
	 * @return the second reminder of this calendar booking
	 */
	public long getSecondReminder();

	/**
	 * Sets the second reminder of this calendar booking.
	 *
	 * @param secondReminder the second reminder of this calendar booking
	 */
	public void setSecondReminder(long secondReminder);

	/**
	 * Returns the second reminder type of this calendar booking.
	 *
	 * @return the second reminder type of this calendar booking
	 */
	@AutoEscape
	public String getSecondReminderType();

	/**
	 * Sets the second reminder type of this calendar booking.
	 *
	 * @param secondReminderType the second reminder type of this calendar booking
	 */
	public void setSecondReminderType(String secondReminderType);

	/**
	 * Returns the last publish date of this calendar booking.
	 *
	 * @return the last publish date of this calendar booking
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this calendar booking.
	 *
	 * @param lastPublishDate the last publish date of this calendar booking
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this calendar booking.
	 *
	 * @return the status of this calendar booking
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this calendar booking.
	 *
	 * @param status the status of this calendar booking
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this calendar booking.
	 *
	 * @return the status by user ID of this calendar booking
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this calendar booking.
	 *
	 * @param statusByUserId the status by user ID of this calendar booking
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this calendar booking.
	 *
	 * @return the status by user uuid of this calendar booking
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this calendar booking.
	 *
	 * @param statusByUserUuid the status by user uuid of this calendar booking
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this calendar booking.
	 *
	 * @return the status by user name of this calendar booking
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this calendar booking.
	 *
	 * @param statusByUserName the status by user name of this calendar booking
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this calendar booking.
	 *
	 * @return the status date of this calendar booking
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this calendar booking.
	 *
	 * @param statusDate the status date of this calendar booking
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the class primary key of the trash entry for this calendar booking.
	 *
	 * @return the class primary key of the trash entry for this calendar booking
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns <code>true</code> if this calendar booking is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this calendar booking is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if this calendar booking is approved.
	 *
	 * @return <code>true</code> if this calendar booking is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this calendar booking is denied.
	 *
	 * @return <code>true</code> if this calendar booking is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this calendar booking is a draft.
	 *
	 * @return <code>true</code> if this calendar booking is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this calendar booking is expired.
	 *
	 * @return <code>true</code> if this calendar booking is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this calendar booking is inactive.
	 *
	 * @return <code>true</code> if this calendar booking is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this calendar booking is incomplete.
	 *
	 * @return <code>true</code> if this calendar booking is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this calendar booking is pending.
	 *
	 * @return <code>true</code> if this calendar booking is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this calendar booking is scheduled.
	 *
	 * @return <code>true</code> if this calendar booking is scheduled; <code>false</code> otherwise
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
	public CalendarBooking cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}