/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.spring;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.ParamSetter;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class MappingSqlQueryImpl<T> implements MappingSqlQuery<T> {

	public MappingSqlQueryImpl(
		DataSource dataSource, String sql, RowMapper<T> rowMapper,
		ParamSetter... paramSetters) {

		_dataSource = dataSource;
		_sql = sql;
		_rowMapper = rowMapper;
		_paramSetters = paramSetters;
	}

	@Override
	public List<T> execute(Object... params) throws SQLException {
		if (_paramSetters.length != params.length) {
			throw new IllegalArgumentException(
				StringBundler.concat(
					"Expected ", _paramSetters.length,
					" parameters instead of ", params.length, " parameters"));
		}

		try (Connection connection = ConnectionUtil.getConnection(_dataSource);
			PreparedStatement preparedStatement = connection.prepareStatement(
				_sql)) {

			for (int i = 0; i < _paramSetters.length; i++) {
				ParamSetter paramSetter = _paramSetters[i];

				paramSetter.set(preparedStatement, i + 1, params[i]);
			}

			List<T> results = null;

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					if (results == null) {
						results = new ArrayList<>();
					}

					results.add(_rowMapper.mapRow(resultSet));
				}
			}

			if (results == null) {
				results = Collections.emptyList();
			}

			return results;
		}
	}

	private final DataSource _dataSource;
	private final ParamSetter[] _paramSetters;
	private final RowMapper<T> _rowMapper;
	private final String _sql;

}