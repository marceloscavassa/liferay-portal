/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.model.impl;

import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model base implementation for the BookmarksFolder service. Represents a row in the &quot;BookmarksFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BookmarksFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderImpl
 * @see BookmarksFolder
 * @generated
 */
public abstract class BookmarksFolderBaseImpl
	extends BookmarksFolderModelImpl implements BookmarksFolder {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a bookmarks folder model instance should use the <code>BookmarksFolder</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			BookmarksFolderLocalServiceUtil.addBookmarksFolder(this);
		}
		else {
			BookmarksFolderLocalServiceUtil.updateBookmarksFolder(this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public String buildTreePath() throws PortalException {
		List<BookmarksFolder> bookmarksFolders =
			new ArrayList<BookmarksFolder>();

		BookmarksFolder bookmarksFolder = this;

		while (bookmarksFolder != null) {
			bookmarksFolders.add(bookmarksFolder);

			bookmarksFolder =
				BookmarksFolderLocalServiceUtil.fetchBookmarksFolder(
					bookmarksFolder.getParentFolderId());
		}

		StringBundler sb = new StringBundler((bookmarksFolders.size() * 2) + 1);

		sb.append("/");

		for (int i = bookmarksFolders.size() - 1; i >= 0; i--) {
			bookmarksFolder = bookmarksFolders.get(i);

			sb.append(bookmarksFolder.getFolderId());
			sb.append("/");
		}

		return sb.toString();
	}

	@Override
	public void updateTreePath(String treePath) {
		BookmarksFolder bookmarksFolder = this;

		bookmarksFolder.setTreePath(treePath);

		BookmarksFolderLocalServiceUtil.updateBookmarksFolder(bookmarksFolder);
	}

}