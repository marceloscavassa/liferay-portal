/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;

/**
 * The extended model base implementation for the KaleoInstance service. Represents a row in the &quot;KaleoInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoInstanceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceImpl
 * @see KaleoInstance
 * @generated
 */
public abstract class KaleoInstanceBaseImpl
	extends KaleoInstanceModelImpl implements KaleoInstance {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo instance model instance should use the <code>KaleoInstance</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoInstanceLocalServiceUtil.addKaleoInstance(this);
		}
		else {
			KaleoInstanceLocalServiceUtil.updateKaleoInstance(this);
		}
	}

}