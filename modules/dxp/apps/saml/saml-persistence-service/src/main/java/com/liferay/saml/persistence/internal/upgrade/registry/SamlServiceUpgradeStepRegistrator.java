/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.persistence.internal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeServiceModuleRelease;
import com.liferay.saml.persistence.internal.upgrade.v2_4_0.util.SamlPeerBindingTable;
import com.liferay.saml.persistence.internal.upgrade.v3_0_1.SamlSpIdpConnectionDataUpgradeProcess;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class SamlServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeServiceModuleRelease baseUpgradeServiceModuleRelease =
				new BaseUpgradeServiceModuleRelease() {

					@Override
					protected String getNamespace() {
						return "Saml";
					}

					@Override
					protected String getNewBundleSymbolicName() {
						return "com.liferay.saml.persistence.service";
					}

					@Override
					protected String getOldBundleSymbolicName() {
						return "saml-portlet";
					}

				};

			baseUpgradeServiceModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		registry.register(
			"1.0.0", "1.1.0",
			UpgradeProcessFactory.alterColumnTypes(
				"SamlIdpSpSession", "VARCHAR(1024) null", "nameIdFormat",
				"nameIdValue", "samlSpEntityId"),
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpAuthRequest", "VARCHAR(1024) null", "samlIdpEntityId"),
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpMessage", "VARCHAR(1024) null", "samlIdpEntityId"),
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpSession", "VARCHAR(1024) null", "nameIdFormat",
				"nameIdValue"));

		registry.register(
			"1.1.0", "1.1.1",
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpSession", "TEXT null", "assertionXml"),
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpSession", "VARCHAR(75) null", "samlSpSessionKey",
				"sessionIndex"));

		registry.register(
			"1.1.1", "1.1.2",
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpSession", "VARCHAR(200) null", "jSessionId"));

		registry.register(
			"1.1.2", "1.1.3",
			UpgradeProcessFactory.alterColumnTypes(
				"SamlSpIdpConnection", "BOOLEAN", "forceAuthn"));

		registry.register(
			"1.1.3", "1.1.4",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_4.
				UpgradeClassNames());

		registry.register(
			"1.1.4", "2.0.0",
			UpgradeProcessFactory.addColumns(
				"SamlSpSession", "samlIdpEntityId VARCHAR(1024) null"),
			new com.liferay.saml.persistence.internal.upgrade.v2_0_0.
				SamlSpSessionDataUpgradeProcess(_configurationAdmin));

		registry.register(
			"2.0.0", "2.1.0",
			UpgradeProcessFactory.addColumns(
				"SamlIdpSpConnection", "encryptionForced BOOLEAN"));

		registry.register(
			"2.1.0", "2.2.0",
			UpgradeProcessFactory.addColumns(
				"SamlSpIdpConnection", "unknownUsersAreStrangers BOOLEAN"));

		registry.register(
			"2.2.0", "2.3.0",
			UpgradeProcessFactory.addColumns(
				"SamlSpIdpConnection",
				"userIdentifierExpression VARCHAR(200) null"));

		registry.register("2.3.0", "2.4.0", SamlPeerBindingTable.create());

		registry.register(
			"2.4.0", "2.5.0",
			new com.liferay.saml.persistence.internal.upgrade.v3_0_0.
				SamlIdpSpSessionUpgradeProcess(),
			new com.liferay.saml.persistence.internal.upgrade.v3_0_0.
				SamlSpSessionUpgradeProcess());

		registry.register(
			"2.5.0", "3.0.0",
			UpgradeProcessFactory.dropColumns(
				"SamlIdpSpSession", "nameIdFormat"),
			UpgradeProcessFactory.dropColumns(
				"SamlIdpSpSession", "nameIdValue"),
			UpgradeProcessFactory.dropColumns(
				"SamlIdpSpSession", "samlSpEntityId"),
			UpgradeProcessFactory.dropColumns("SamlSpSession", "nameIdFormat"),
			UpgradeProcessFactory.dropColumns(
				"SamlSpSession", "nameIdNameQualifier"),
			UpgradeProcessFactory.dropColumns(
				"SamlSpSession", "nameIdSPNameQualifier"),
			UpgradeProcessFactory.dropColumns("SamlSpSession", "nameIdValue"),
			UpgradeProcessFactory.dropColumns(
				"SamlSpSession", "samlIdpEntityId"));

		registry.register(
			"3.0.0", "3.0.1", new SamlSpIdpConnectionDataUpgradeProcess());
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

}