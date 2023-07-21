/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.model.impl;

import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceLocalServiceUtil;

/**
 * The extended model base implementation for the Source service. Represents a row in the &quot;Reports_Source&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SourceImpl
 * @see Source
 * @generated
 */
public abstract class SourceBaseImpl extends SourceModelImpl implements Source {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a source model instance should use the <code>Source</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SourceLocalServiceUtil.addSource(this);
		}
		else {
			SourceLocalServiceUtil.updateSource(this);
		}
	}

}