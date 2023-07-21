/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.model.impl;

import com.liferay.analytics.message.storage.model.AnalyticsMessage;
import com.liferay.analytics.message.storage.service.AnalyticsMessageLocalServiceUtil;

/**
 * The extended model base implementation for the AnalyticsMessage service. Represents a row in the &quot;AnalyticsMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnalyticsMessageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsMessageImpl
 * @see AnalyticsMessage
 * @generated
 */
public abstract class AnalyticsMessageBaseImpl
	extends AnalyticsMessageModelImpl implements AnalyticsMessage {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a analytics message model instance should use the <code>AnalyticsMessage</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AnalyticsMessageLocalServiceUtil.addAnalyticsMessage(this);
		}
		else {
			AnalyticsMessageLocalServiceUtil.updateAnalyticsMessage(this);
		}
	}

}