/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.web.internal.notifications;

import com.liferay.microblogs.constants.MicroblogsEntryConstants;
import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = "javax.portlet.name=" + MicroblogsPortletKeys.MICROBLOGS,
	service = UserNotificationDefinition.class
)
public class MicroblogsReplyToTaggedUserNotificationDefinition
	extends UserNotificationDefinition {

	public MicroblogsReplyToTaggedUserNotificationDefinition() {
		super(
			MicroblogsPortletKeys.MICROBLOGS, 0,
			MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY_TO_TAGGED,
			"receive-a-notification-when-someone-comments-on-a-microblog-you-" +
				"are-tagged-in");

		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"mobile", UserNotificationDeliveryConstants.TYPE_PUSH, true,
				true));
		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"website", UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				true));
	}

}