/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.model.impl;

import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.service.SegmentsExperimentLocalServiceUtil;

/**
 * The extended model base implementation for the SegmentsExperiment service. Represents a row in the &quot;SegmentsExperiment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SegmentsExperimentImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsExperimentImpl
 * @see SegmentsExperiment
 * @generated
 */
public abstract class SegmentsExperimentBaseImpl
	extends SegmentsExperimentModelImpl implements SegmentsExperiment {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a segments experiment model instance should use the <code>SegmentsExperiment</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SegmentsExperimentLocalServiceUtil.addSegmentsExperiment(this);
		}
		else {
			SegmentsExperimentLocalServiceUtil.updateSegmentsExperiment(this);
		}
	}

}