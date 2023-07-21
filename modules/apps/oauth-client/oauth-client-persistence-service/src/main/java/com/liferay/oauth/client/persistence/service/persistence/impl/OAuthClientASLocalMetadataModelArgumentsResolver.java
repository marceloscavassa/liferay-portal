/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.client.persistence.service.persistence.impl;

import com.liferay.oauth.client.persistence.model.OAuthClientASLocalMetadataTable;
import com.liferay.oauth.client.persistence.model.impl.OAuthClientASLocalMetadataImpl;
import com.liferay.oauth.client.persistence.model.impl.OAuthClientASLocalMetadataModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from OAuthClientASLocalMetadata.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.liferay.oauth.client.persistence.model.impl.OAuthClientASLocalMetadataImpl",
		"table.name=OAuthClientASLocalMetadata"
	},
	service = ArgumentsResolver.class
)
public class OAuthClientASLocalMetadataModelArgumentsResolver
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

		OAuthClientASLocalMetadataModelImpl
			oAuthClientASLocalMetadataModelImpl =
				(OAuthClientASLocalMetadataModelImpl)baseModel;

		long columnBitmask =
			oAuthClientASLocalMetadataModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				oAuthClientASLocalMetadataModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					oAuthClientASLocalMetadataModelImpl.getColumnBitmask(
						columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				oAuthClientASLocalMetadataModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return OAuthClientASLocalMetadataImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return OAuthClientASLocalMetadataTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		OAuthClientASLocalMetadataModelImpl oAuthClientASLocalMetadataModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					oAuthClientASLocalMetadataModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] =
					oAuthClientASLocalMetadataModelImpl.getColumnValue(
						columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}