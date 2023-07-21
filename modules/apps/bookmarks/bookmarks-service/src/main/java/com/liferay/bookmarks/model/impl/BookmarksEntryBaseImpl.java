/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.model.impl;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;

/**
 * The extended model base implementation for the BookmarksEntry service. Represents a row in the &quot;BookmarksEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BookmarksEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksEntryImpl
 * @see BookmarksEntry
 * @generated
 */
public abstract class BookmarksEntryBaseImpl
	extends BookmarksEntryModelImpl implements BookmarksEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a bookmarks entry model instance should use the <code>BookmarksEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			BookmarksEntryLocalServiceUtil.addBookmarksEntry(this);
		}
		else {
			BookmarksEntryLocalServiceUtil.updateBookmarksEntry(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) {
		BookmarksEntry bookmarksEntry = this;

		bookmarksEntry.setTreePath(treePath);

		BookmarksEntryLocalServiceUtil.updateBookmarksEntry(bookmarksEntry);
	}

}