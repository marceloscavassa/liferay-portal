/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.persistence.impl;

import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRelTable;
import com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelImpl;
import com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CommercePriceListCommerceAccountGroupRel.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.commerce.price.list.model.impl.CommercePriceListCommerceAccountGroupRelImpl",
		"table.name=CPLCommerceGroupAccountRel"
	},
	service = ArgumentsResolver.class
)
public class CommercePriceListCommerceAccountGroupRelModelArgumentsResolver
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

		CommercePriceListCommerceAccountGroupRelModelImpl
			commercePriceListCommerceAccountGroupRelModelImpl =
				(CommercePriceListCommerceAccountGroupRelModelImpl)baseModel;

		long columnBitmask =
			commercePriceListCommerceAccountGroupRelModelImpl.
				getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				commercePriceListCommerceAccountGroupRelModelImpl, columnNames,
				original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					commercePriceListCommerceAccountGroupRelModelImpl.
						getColumnBitmask(columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(CommercePriceListCommerceAccountGroupRelPersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				commercePriceListCommerceAccountGroupRelModelImpl, columnNames,
				original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CommercePriceListCommerceAccountGroupRelImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CommercePriceListCommerceAccountGroupRelTable.INSTANCE.
			getTableName();
	}

	private static Object[] _getValue(
		CommercePriceListCommerceAccountGroupRelModelImpl
			commercePriceListCommerceAccountGroupRelModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					commercePriceListCommerceAccountGroupRelModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					commercePriceListCommerceAccountGroupRelModelImpl.
						getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

	private static final long _ORDER_BY_COLUMNS_BITMASK;

	static {
		long orderByColumnsBitmask = 0;

		orderByColumnsBitmask |=
			CommercePriceListCommerceAccountGroupRelModelImpl.getColumnBitmask(
				"order_");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}