/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.internal.spi.reference;

import com.liferay.change.tracking.spi.reference.TableReferenceDefinition;
import com.liferay.change.tracking.spi.reference.builder.ChildTableReferenceInfoBuilder;
import com.liferay.change.tracking.spi.reference.builder.ParentTableReferenceInfoBuilder;
import com.liferay.portal.kernel.model.PortalPreferenceValueTable;
import com.liferay.portal.kernel.model.PortalPreferencesTable;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.PortalPreferenceValuePersistence;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = TableReferenceDefinition.class)
public class PortalPreferenceValueTableReferenceDefinition
	implements TableReferenceDefinition<PortalPreferenceValueTable> {

	@Override
	public void defineChildTableReferences(
		ChildTableReferenceInfoBuilder<PortalPreferenceValueTable>
			childTableReferenceInfoBuilder) {
	}

	@Override
	public void defineParentTableReferences(
		ParentTableReferenceInfoBuilder<PortalPreferenceValueTable>
			parentTableReferenceInfoBuilder) {

		parentTableReferenceInfoBuilder.singleColumnReference(
			PortalPreferenceValueTable.INSTANCE.portalPreferencesId,
			PortalPreferencesTable.INSTANCE.portalPreferencesId);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _portalPreferenceValuePersistence;
	}

	@Override
	public PortalPreferenceValueTable getTable() {
		return PortalPreferenceValueTable.INSTANCE;
	}

	@Reference
	private PortalPreferenceValuePersistence _portalPreferenceValuePersistence;

}