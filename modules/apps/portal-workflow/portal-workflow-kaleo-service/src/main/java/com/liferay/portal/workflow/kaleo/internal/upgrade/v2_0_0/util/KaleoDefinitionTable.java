/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v2_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class KaleoDefinitionTable {

	public static final String TABLE_NAME = "KaleoDefinition";

	public static final Object[][] TABLE_COLUMNS = {
		{"kaleoDefinitionId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR},
		{"title", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"content", Types.CLOB},
		{"version", Types.INTEGER},
		{"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("content", Types.CLOB);

TABLE_COLUMNS_MAP.put("version", Types.INTEGER);

TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);

}
	public static final String TABLE_SQL_CREATE = "create table KaleoDefinition (kaleoDefinitionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(200) null,title STRING null,description STRING null,content TEXT null,version INTEGER,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table KaleoDefinition";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_408542BA on KaleoDefinition (companyId, active_)",
		"create index IX_4C23F11B on KaleoDefinition (companyId, name[$COLUMN_LENGTH:200$], active_)",
		"create index IX_EC14F81A on KaleoDefinition (companyId, name[$COLUMN_LENGTH:200$], version)"
	};

}