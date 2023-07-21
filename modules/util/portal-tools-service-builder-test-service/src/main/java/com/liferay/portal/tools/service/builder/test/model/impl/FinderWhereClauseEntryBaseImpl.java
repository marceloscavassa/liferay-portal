/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.portal.tools.service.builder.test.model.FinderWhereClauseEntry;
import com.liferay.portal.tools.service.builder.test.service.FinderWhereClauseEntryLocalServiceUtil;

/**
 * The extended model base implementation for the FinderWhereClauseEntry service. Represents a row in the &quot;FinderWhereClauseEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FinderWhereClauseEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinderWhereClauseEntryImpl
 * @see FinderWhereClauseEntry
 * @generated
 */
public abstract class FinderWhereClauseEntryBaseImpl
	extends FinderWhereClauseEntryModelImpl implements FinderWhereClauseEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a finder where clause entry model instance should use the <code>FinderWhereClauseEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			FinderWhereClauseEntryLocalServiceUtil.addFinderWhereClauseEntry(
				this);
		}
		else {
			FinderWhereClauseEntryLocalServiceUtil.updateFinderWhereClauseEntry(
				this);
		}
	}

}