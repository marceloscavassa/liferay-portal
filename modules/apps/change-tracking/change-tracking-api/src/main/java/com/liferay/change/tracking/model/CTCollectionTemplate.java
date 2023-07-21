/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CTCollectionTemplate service. Represents a row in the &quot;CTCollectionTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CTCollectionTemplateModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.change.tracking.model.impl.CTCollectionTemplateImpl"
)
@ProviderType
public interface CTCollectionTemplate
	extends CTCollectionTemplateModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.change.tracking.model.impl.CTCollectionTemplateImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CTCollectionTemplate, Long>
		CT_COLLECTION_TEMPLATE_ID_ACCESSOR =
			new Accessor<CTCollectionTemplate, Long>() {

				@Override
				public Long get(CTCollectionTemplate ctCollectionTemplate) {
					return ctCollectionTemplate.getCtCollectionTemplateId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CTCollectionTemplate> getTypeClass() {
					return CTCollectionTemplate.class;
				}

			};

	public com.liferay.portal.kernel.json.JSONObject getJSONObject();

	public String getParsedPublicationDescription();

	public String getParsedPublicationName();

	public String getPublicationDescription();

	public String getPublicationName();

	public String getUserName();

}