/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.uad.exporter;

import com.liferay.notifications.uad.constants.NotificationsUADConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.UserNotificationDelivery;
import com.liferay.portal.kernel.service.UserNotificationDeliveryLocalService;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the user notification delivery UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link UserNotificationDeliveryUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseUserNotificationDeliveryUADExporter
	extends DynamicQueryUADExporter<UserNotificationDelivery> {

	@Override
	public Class<UserNotificationDelivery> getTypeClass() {
		return UserNotificationDelivery.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return userNotificationDeliveryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return NotificationsUADConstants.
			USER_ID_FIELD_NAMES_USER_NOTIFICATION_DELIVERY;
	}

	@Override
	protected String toXmlString(
		UserNotificationDelivery userNotificationDelivery) {

		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.UserNotificationDelivery");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userNotificationDeliveryId</column-name><column-value><![CDATA[");
		sb.append(userNotificationDelivery.getUserNotificationDeliveryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(userNotificationDelivery.getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected UserNotificationDeliveryLocalService
		userNotificationDeliveryLocalService;

}