/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CommerceOrderPayment service. Represents a row in the &quot;CommerceOrderPayment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderPaymentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.model.impl.CommerceOrderPaymentImpl"
)
@ProviderType
public interface CommerceOrderPayment
	extends CommerceOrderPaymentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.model.impl.CommerceOrderPaymentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceOrderPayment, Long>
		COMMERCE_ORDER_PAYMENT_ID_ACCESSOR =
			new Accessor<CommerceOrderPayment, Long>() {

				@Override
				public Long get(CommerceOrderPayment commerceOrderPayment) {
					return commerceOrderPayment.getCommerceOrderPaymentId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceOrderPayment> getTypeClass() {
					return CommerceOrderPayment.class;
				}

			};

}