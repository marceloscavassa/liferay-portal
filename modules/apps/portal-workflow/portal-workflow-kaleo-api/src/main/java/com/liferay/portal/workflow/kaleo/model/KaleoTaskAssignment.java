/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the KaleoTaskAssignment service. Represents a row in the &quot;KaleoTaskAssignment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl"
)
@ProviderType
public interface KaleoTaskAssignment
	extends KaleoTaskAssignmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoTaskAssignment, Long>
		KALEO_TASK_ASSIGNMENT_ID_ACCESSOR =
			new Accessor<KaleoTaskAssignment, Long>() {

				@Override
				public Long get(KaleoTaskAssignment kaleoTaskAssignment) {
					return kaleoTaskAssignment.getKaleoTaskAssignmentId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<KaleoTaskAssignment> getTypeClass() {
					return KaleoTaskAssignment.class;
				}

			};

}