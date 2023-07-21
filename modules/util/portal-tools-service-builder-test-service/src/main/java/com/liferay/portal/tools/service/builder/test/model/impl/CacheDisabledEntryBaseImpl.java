/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.portal.tools.service.builder.test.model.CacheDisabledEntry;
import com.liferay.portal.tools.service.builder.test.service.CacheDisabledEntryLocalServiceUtil;

/**
 * The extended model base implementation for the CacheDisabledEntry service. Represents a row in the &quot;CacheDisabledEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CacheDisabledEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheDisabledEntryImpl
 * @see CacheDisabledEntry
 * @generated
 */
public abstract class CacheDisabledEntryBaseImpl
	extends CacheDisabledEntryModelImpl implements CacheDisabledEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cache disabled entry model instance should use the <code>CacheDisabledEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CacheDisabledEntryLocalServiceUtil.addCacheDisabledEntry(this);
		}
		else {
			CacheDisabledEntryLocalServiceUtil.updateCacheDisabledEntry(this);
		}
	}

}