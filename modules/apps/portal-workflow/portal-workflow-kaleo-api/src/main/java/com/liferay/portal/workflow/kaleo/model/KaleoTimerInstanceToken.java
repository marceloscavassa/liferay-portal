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
 * The extended model interface for the KaleoTimerInstanceToken service. Represents a row in the &quot;KaleoTimerInstanceToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl"
)
@ProviderType
public interface KaleoTimerInstanceToken
	extends KaleoTimerInstanceTokenModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoTimerInstanceToken, Long>
		KALEO_TIMER_INSTANCE_TOKEN_ID_ACCESSOR =
			new Accessor<KaleoTimerInstanceToken, Long>() {

				@Override
				public Long get(
					KaleoTimerInstanceToken kaleoTimerInstanceToken) {

					return kaleoTimerInstanceToken.
						getKaleoTimerInstanceTokenId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<KaleoTimerInstanceToken> getTypeClass() {
					return KaleoTimerInstanceToken.class;
				}

			};

	public KaleoInstanceToken getKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException;

	public KaleoTaskInstanceToken getKaleoTaskInstanceToken();

	public KaleoTimer getKaleoTimer()
		throws com.liferay.portal.kernel.exception.PortalException;

}