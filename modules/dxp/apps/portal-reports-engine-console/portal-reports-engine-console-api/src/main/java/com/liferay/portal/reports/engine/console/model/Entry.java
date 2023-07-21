/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Entry service. Represents a row in the &quot;Reports_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.reports.engine.console.model.impl.EntryImpl"
)
@ProviderType
public interface Entry extends EntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.reports.engine.console.model.impl.EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Entry, Long> ENTRY_ID_ACCESSOR =
		new Accessor<Entry, Long>() {

			@Override
			public Long get(Entry entry) {
				return entry.getEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Entry> getTypeClass() {
				return Entry.class;
			}

		};

	public String getAttachmentsDir();

	public String[] getAttachmentsFileNames()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getJobName();

	public com.liferay.portal.kernel.cal.TZSRecurrence getRecurrenceObj();

	public String getSchedulerRequestName();

}