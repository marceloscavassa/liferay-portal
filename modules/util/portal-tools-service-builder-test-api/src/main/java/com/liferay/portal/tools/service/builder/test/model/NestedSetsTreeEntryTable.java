/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;NestedSetsTreeEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see NestedSetsTreeEntry
 * @generated
 */
public class NestedSetsTreeEntryTable
	extends BaseTable<NestedSetsTreeEntryTable> {

	public static final NestedSetsTreeEntryTable INSTANCE =
		new NestedSetsTreeEntryTable();

	public final Column<NestedSetsTreeEntryTable, Long> nestedSetsTreeEntryId =
		createColumn(
			"nestedSetsTreeEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<NestedSetsTreeEntryTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<NestedSetsTreeEntryTable, Long>
		parentNestedSetsTreeEntryId = createColumn(
			"parentNestedSetsTreeEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<NestedSetsTreeEntryTable, Long>
		leftNestedSetsTreeEntryId = createColumn(
			"leftNestedSetsTreeEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<NestedSetsTreeEntryTable, Long>
		rightNestedSetsTreeEntryId = createColumn(
			"rightNestedSetsTreeEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);

	private NestedSetsTreeEntryTable() {
		super("NestedSetsTreeEntry", NestedSetsTreeEntryTable::new);
	}

}