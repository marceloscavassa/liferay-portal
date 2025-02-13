/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.reading.time.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ReadingTimeEntry service. Represents a row in the &quot;ReadingTimeEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ReadingTimeEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.reading.time.model.impl.ReadingTimeEntryImpl"
)
@ProviderType
public interface ReadingTimeEntry
	extends PersistedModel, ReadingTimeEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.reading.time.model.impl.ReadingTimeEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReadingTimeEntry, Long>
		READING_TIME_ENTRY_ID_ACCESSOR =
			new Accessor<ReadingTimeEntry, Long>() {

				@Override
				public Long get(ReadingTimeEntry readingTimeEntry) {
					return readingTimeEntry.getReadingTimeEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ReadingTimeEntry> getTypeClass() {
					return ReadingTimeEntry.class;
				}

			};

}