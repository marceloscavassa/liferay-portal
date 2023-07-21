/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;AccountEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntry
 * @generated
 */
public class AccountEntryTable extends BaseTable<AccountEntryTable> {

	public static final AccountEntryTable INSTANCE = new AccountEntryTable();

	public final Column<AccountEntryTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<AccountEntryTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> externalReferenceCode =
		createColumn(
			"externalReferenceCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> accountEntryId = createColumn(
		"accountEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<AccountEntryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> defaultBillingAddressId =
		createColumn(
			"defaultBillingAddressId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> defaultCPaymentMethodKey =
		createColumn(
			"defaultCPaymentMethodKey", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> defaultShippingAddressId =
		createColumn(
			"defaultShippingAddressId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> parentAccountEntryId =
		createColumn(
			"parentAccountEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> domains = createColumn(
		"domains", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> emailAddress = createColumn(
		"emailAddress", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> logoId = createColumn(
		"logoId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Boolean> restrictMembership =
		createColumn(
			"restrictMembership", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> taxExemptionCode =
		createColumn(
			"taxExemptionCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> taxIdNumber = createColumn(
		"taxIdNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Long> statusByUserId = createColumn(
		"statusByUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, String> statusByUserName =
		createColumn(
			"statusByUserName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<AccountEntryTable, Date> statusDate = createColumn(
		"statusDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private AccountEntryTable() {
		super("AccountEntry", AccountEntryTable::new);
	}

}