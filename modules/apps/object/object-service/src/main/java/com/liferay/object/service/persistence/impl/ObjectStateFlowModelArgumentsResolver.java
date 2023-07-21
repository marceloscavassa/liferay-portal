/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service.persistence.impl;

import com.liferay.object.model.ObjectStateFlowTable;
import com.liferay.object.model.impl.ObjectStateFlowImpl;
import com.liferay.object.model.impl.ObjectStateFlowModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from ObjectStateFlow.
 *
 * @author Marco Leo
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.object.model.impl.ObjectStateFlowImpl",
		"table.name=ObjectStateFlow"
	},
	service = ArgumentsResolver.class
)
public class ObjectStateFlowModelArgumentsResolver
	implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		ObjectStateFlowModelImpl objectStateFlowModelImpl =
			(ObjectStateFlowModelImpl)baseModel;

		long columnBitmask = objectStateFlowModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(objectStateFlowModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					objectStateFlowModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(objectStateFlowModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return ObjectStateFlowImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return ObjectStateFlowTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		ObjectStateFlowModelImpl objectStateFlowModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = objectStateFlowModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = objectStateFlowModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}