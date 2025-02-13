/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.util.comparator;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Jürgen Kappler
 */
public class FragmentEntryCreateDateComparator
	extends OrderByComparator<FragmentEntry> {

	public static final String ORDER_BY_ASC = "FragmentEntry.createDate ASC";

	public static final String ORDER_BY_DESC = "FragmentEntry.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public FragmentEntryCreateDateComparator() {
		this(true);
	}

	public FragmentEntryCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		FragmentEntry fragmentEntry1, FragmentEntry fragmentEntry2) {

		int value = DateUtil.compareTo(
			fragmentEntry1.getCreateDate(), fragmentEntry2.getCreateDate());

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