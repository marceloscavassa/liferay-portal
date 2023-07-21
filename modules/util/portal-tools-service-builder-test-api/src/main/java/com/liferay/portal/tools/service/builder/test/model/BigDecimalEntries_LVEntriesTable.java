/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;BigDecimalEntries_LVEntries&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see BigDecimalEntry
 * @see LVEntry
 * @generated
 */
public class BigDecimalEntries_LVEntriesTable
	extends BaseTable<BigDecimalEntries_LVEntriesTable> {

	public static final BigDecimalEntries_LVEntriesTable INSTANCE =
		new BigDecimalEntries_LVEntriesTable();

	public final Column<BigDecimalEntries_LVEntriesTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<BigDecimalEntries_LVEntriesTable, Long>
		bigDecimalEntryId = createColumn(
			"bigDecimalEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<BigDecimalEntries_LVEntriesTable, Long> lvEntryId =
		createColumn(
			"lvEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);

	private BigDecimalEntries_LVEntriesTable() {
		super(
			"BigDecimalEntries_LVEntries",
			BigDecimalEntries_LVEntriesTable::new);
	}

}