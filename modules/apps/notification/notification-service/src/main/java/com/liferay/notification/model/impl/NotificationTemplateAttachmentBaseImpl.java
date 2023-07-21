/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notification.model.impl;

import com.liferay.notification.model.NotificationTemplateAttachment;
import com.liferay.notification.service.NotificationTemplateAttachmentLocalServiceUtil;

/**
 * The extended model base implementation for the NotificationTemplateAttachment service. Represents a row in the &quot;NTemplateAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NotificationTemplateAttachmentImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationTemplateAttachmentImpl
 * @see NotificationTemplateAttachment
 * @generated
 */
public abstract class NotificationTemplateAttachmentBaseImpl
	extends NotificationTemplateAttachmentModelImpl
	implements NotificationTemplateAttachment {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notification template attachment model instance should use the <code>NotificationTemplateAttachment</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			NotificationTemplateAttachmentLocalServiceUtil.
				addNotificationTemplateAttachment(this);
		}
		else {
			NotificationTemplateAttachmentLocalServiceUtil.
				updateNotificationTemplateAttachment(this);
		}
	}

}