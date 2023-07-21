/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.util.comparator;

import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Shinn Lok
 */
public class FolderIdComparator extends OrderByComparator<JournalFolder> {

	public static final String ORDER_BY_ASC = "JournalFolder.folderId ASC";

	public static final String ORDER_BY_DESC = "JournalFolder.folderId DESC";

	public static final String[] ORDER_BY_FIELDS = {"folderId"};

	public FolderIdComparator() {
		this(false);
	}

	public FolderIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(JournalFolder folder1, JournalFolder folder2) {
		long folderId1 = folder1.getFolderId();
		long folderId2 = folder2.getFolderId();

		int value = 0;

		if (folderId1 < folderId2) {
			value = -1;
		}
		else if (folderId1 > folderId2) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}