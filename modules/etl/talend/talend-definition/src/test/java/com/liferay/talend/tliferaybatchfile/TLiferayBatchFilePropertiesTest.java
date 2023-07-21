/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.talend.tliferaybatchfile;

import com.liferay.talend.BasePropertiesTestCase;
import com.liferay.talend.common.schema.constants.BatchSchemaConstants;

import java.util.Objects;

import org.apache.avro.Schema;

import org.junit.Assert;
import org.junit.Test;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.common.SchemaProperties;

/**
 * @author Igor Beslic
 */
public class TLiferayBatchFilePropertiesTest extends BasePropertiesTestCase {

	@Test
	public void testComponentConnectors() throws Exception {
		TLiferayBatchFileDefinition tLiferayBatchFileDefinition =
			new TLiferayBatchFileDefinition();

		Class<? extends ComponentProperties> propertyClass =
			tLiferayBatchFileDefinition.getPropertyClass();

		Assert.assertEquals(
			"Component properties implementation class",
			TLiferayBatchFileProperties.class, propertyClass);

		ComponentProperties componentProperties =
			getDefaultInitializedComponentPropertiesInstance(propertyClass);

		for (Connector outputConnector :
				componentProperties.getPossibleConnectors(true)) {

			PropertyPathConnector propertyPathConnector =
				(PropertyPathConnector)outputConnector;

			Schema expectedSchema = SchemaProperties.EMPTY_SCHEMA;

			if (Objects.equals(
					outputConnector.getName(), Connector.MAIN_NAME)) {

				expectedSchema = BatchSchemaConstants.SCHEMA;
			}

			assertEquals(
				componentProperties, propertyPathConnector.getPropertyPath(),
				expectedSchema);
		}

		for (Connector inputConnector :
				componentProperties.getPossibleConnectors(false)) {

			PropertyPathConnector propertyPathConnector =
				(PropertyPathConnector)inputConnector;

			assertEquals(
				componentProperties, propertyPathConnector.getPropertyPath(),
				SchemaProperties.EMPTY_SCHEMA);
		}
	}

}