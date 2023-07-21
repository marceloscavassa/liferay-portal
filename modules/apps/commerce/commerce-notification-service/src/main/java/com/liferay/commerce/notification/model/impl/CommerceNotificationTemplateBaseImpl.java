/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.model.impl;

import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceNotificationTemplate service. Represents a row in the &quot;CommerceNotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceNotificationTemplateImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationTemplateImpl
 * @see CommerceNotificationTemplate
 * @generated
 */
public abstract class CommerceNotificationTemplateBaseImpl
	extends CommerceNotificationTemplateModelImpl
	implements CommerceNotificationTemplate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce notification template model instance should use the <code>CommerceNotificationTemplate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceNotificationTemplateLocalServiceUtil.
				addCommerceNotificationTemplate(this);
		}
		else {
			CommerceNotificationTemplateLocalServiceUtil.
				updateCommerceNotificationTemplate(this);
		}
	}

}