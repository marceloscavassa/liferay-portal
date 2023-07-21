/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AMImageEntry service. Represents a row in the &quot;AMImageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AMImageEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.adaptive.media.image.model.impl.AMImageEntryImpl"
)
@ProviderType
public interface AMImageEntry extends AMImageEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.adaptive.media.image.model.impl.AMImageEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AMImageEntry, Long>
		AM_IMAGE_ENTRY_ID_ACCESSOR = new Accessor<AMImageEntry, Long>() {

			@Override
			public Long get(AMImageEntry amImageEntry) {
				return amImageEntry.getAmImageEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AMImageEntry> getTypeClass() {
				return AMImageEntry.class;
			}

		};

}