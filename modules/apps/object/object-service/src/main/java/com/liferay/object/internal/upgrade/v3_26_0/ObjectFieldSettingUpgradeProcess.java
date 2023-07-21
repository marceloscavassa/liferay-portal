/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.upgrade.v3_26_0;

import com.liferay.object.constants.ObjectFieldSettingConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Carolina Barbosa
 */
public class ObjectFieldSettingUpgradeProcess extends UpgradeProcess {

	public ObjectFieldSettingUpgradeProcess(PortalUUID portalUUID) {
		_portalUUID = portalUUID;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select ObjectField.objectFieldId, ObjectField.companyId, ",
					"ObjectField.userId, ObjectField.userName, ",
					"ObjectField.name from ObjectField where ",
					"ObjectField.relationshipType = '",
					ObjectRelationshipConstants.TYPE_ONE_TO_MANY, "'"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"insert into ObjectFieldSetting (uuid_, ",
						"objectFieldSettingId, companyId, userId, userName, ",
						"createDate, modifiedDate, objectFieldId, name, ",
						"value) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				preparedStatement2.setString(1, _portalUUID.generate());
				preparedStatement2.setLong(2, increment());
				preparedStatement2.setLong(3, resultSet.getLong("companyId"));
				preparedStatement2.setLong(4, resultSet.getLong("userId"));
				preparedStatement2.setString(
					5, resultSet.getString("userName"));

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());

				preparedStatement2.setTimestamp(6, timestamp);
				preparedStatement2.setTimestamp(7, timestamp);

				preparedStatement2.setLong(
					8, resultSet.getLong("objectFieldId"));
				preparedStatement2.setString(
					9,
					ObjectFieldSettingConstants.
						NAME_OBJECT_RELATIONSHIP_ERC_OBJECT_FIELD_NAME);
				preparedStatement2.setString(
					10,
					StringUtil.replaceLast(
						resultSet.getString("name"), "Id", "ERC"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private final PortalUUID _portalUUID;

}