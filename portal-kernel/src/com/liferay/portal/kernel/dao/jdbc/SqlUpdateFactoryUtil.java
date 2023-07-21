/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.jdbc;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class SqlUpdateFactoryUtil {

	public static SqlUpdate getSqlUpdate(
		DataSource dataSource, String sql, ParamSetter... paramSetters) {

		return _sqlUpdateFactory.getSqlUpdate(dataSource, sql, paramSetters);
	}

	public static SqlUpdateFactory getSqlUpdateFactory() {
		return _sqlUpdateFactory;
	}

	public void setSqlUpdateFactory(SqlUpdateFactory sqlUpdateFactory) {
		_sqlUpdateFactory = sqlUpdateFactory;
	}

	private static SqlUpdateFactory _sqlUpdateFactory;

}