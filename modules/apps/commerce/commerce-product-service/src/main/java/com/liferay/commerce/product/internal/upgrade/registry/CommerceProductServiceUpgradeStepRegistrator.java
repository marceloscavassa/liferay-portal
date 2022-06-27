/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.internal.upgrade.registry;

import com.liferay.account.settings.AccountEntryGroupSettings;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.internal.upgrade.v1_10_1.CommerceSiteTypeUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_11_0.CPAttachmentFileEntryGroupUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_11_1.CPDisplayLayoutUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPDefinitionLinkUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPDefinitionOptionRelUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CPFriendlyURLEntryUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.CProductUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_3_0.util.CProductTable;
import com.liferay.commerce.product.internal.upgrade.v1_4_0.CPDefinitionSpecificationOptionValueUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_5_0.CProductExternalReferenceCodeUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.CPDefinitionTrashEntriesUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.CommerceCatalogUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.util.CommerceCatalogTable;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.util.CommerceChannelRelTable;
import com.liferay.commerce.product.internal.upgrade.v1_6_0.util.CommerceChannelTable;
import com.liferay.commerce.product.internal.upgrade.v2_0_0.CPInstanceOptionValueRelUpgradeProcess;
import com.liferay.commerce.product.internal.upgrade.v2_0_0.util.CPInstanceOptionValueRelTable;
import com.liferay.commerce.product.internal.upgrade.v2_2_0.util.CPDefinitionOptionRelTable;
import com.liferay.commerce.product.internal.upgrade.v2_2_0.util.CPDefinitionOptionValueRelTable;
import com.liferay.commerce.product.internal.upgrade.v2_5_0.FriendlyURLEntryUpgradeProcess;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.BaseExternalReferenceCodeUpgradeProcess;
import com.liferay.portal.kernel.upgrade.BaseUuidUpgradeProcess;
import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.uuid.PortalUUID;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@Component(
	enabled = false, immediate = true, service = UpgradeStepRegistrator.class
)
public class CommerceProductServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("Commerce product upgrade step registrator started");
		}

		registry.register("1.0.0", "1.1.0", new DummyUpgradeProcess());

		registry.register(
			"1.1.0", "1.2.0",
			UpgradeProcessFactory.addColumns(
				"CPDefinition", "subscriptionEnabled BOOLEAN",
				"subscriptionLength INTEGER", "subscriptionType VARCHAR(75)",
				"subscriptionTypeSettings TEXT", "maxSubscriptionCycles LONG"),
			UpgradeProcessFactory.addColumns(
				"CPInstance", "overrideSubscriptionInfo BOOLEAN",
				"subscriptionEnabled BOOLEAN", "subscriptionLength INTEGER",
				"subscriptionType VARCHAR(75)", "subscriptionTypeSettings TEXT",
				"maxSubscriptionCycles LONG"));

		registry.register(
			"1.2.0", "1.3.0",
			new com.liferay.commerce.product.internal.upgrade.v1_3_0.
				CPAttachmentFileEntryUpgradeProcess(),
			new CPDefinitionLinkUpgradeProcess(),
			new CPDefinitionOptionRelUpgradeProcess(),
			UpgradeProcessFactory.addColumns(
				"CPDefinition", "CProductId LONG", "version INTEGER"),
			CProductTable.create(), new CProductUpgradeProcess(),
			new CPFriendlyURLEntryUpgradeProcess(_classNameLocalService),
			UpgradeProcessFactory.addColumns(
				"CPInstance", "CPInstanceUuid VARCHAR(75)"),
			UpgradeProcessFactory.runSQL(
				"update CPInstance set CPInstanceUuid = uuid_"));

		registry.register(
			"1.3.0", "1.4.0",
			new CPDefinitionSpecificationOptionValueUpgradeProcess());

		registry.register(
			"1.4.0", "1.5.0",
			new CProductExternalReferenceCodeUpgradeProcess());

		registry.register(
			"1.5.0", "1.6.0", CommerceCatalogTable.create(),
			CommerceChannelRelTable.create(), CommerceChannelTable.create(),
			new CommerceCatalogUpgradeProcess(
				_classNameLocalService, _groupLocalService),
			new CPDefinitionTrashEntriesUpgradeProcess(_classNameLocalService));

		registry.register(
			"1.6.0", "1.7.0",
			UpgradeProcessFactory.addColumns(
				"CPDefinition", "accountGroupFilterEnabled BOOLEAN",
				"channelFilterEnabled BOOLEAN"),
			UpgradeProcessFactory.runSQL(
				"update CPDefinition set channelFilterEnabled = [$TRUE$]"));

		registry.register(
			"1.7.0", "1.8.0",
			new com.liferay.commerce.product.internal.upgrade.v1_8_0.
				CPAttachmentFileEntryUpgradeProcess(_classNameLocalService));

		registry.register(
			"1.8.0", "1.9.0",
			new com.liferay.commerce.product.internal.upgrade.v1_9_0.
				CPDefinitionOptionRelUpgradeProcess());

		registry.register(
			"1.9.0", "1.10.0",
			new com.liferay.commerce.product.internal.upgrade.v1_10_0.
				CPAttachmentFileEntryUpgradeProcess(_jsonFactory),
			new com.liferay.commerce.product.internal.upgrade.v1_10_0.
				CPInstanceUpgradeProcess(_jsonFactory));

		registry.register(
			"1.10.0", "1.10.1",
			new CommerceSiteTypeUpgradeProcess(
				_classNameLocalService, _groupLocalService,
				_configurationProvider, _settingsFactory));

		registry.register(
			"1.10.1", "1.11.0",
			new CPAttachmentFileEntryGroupUpgradeProcess(
				_assetCategoryLocalService, _classNameLocalService));

		registry.register(
			"1.11.0", "1.11.1",
			new CPDisplayLayoutUpgradeProcess(_layoutLocalService));

		registry.register(
			"1.11.1", "1.11.2",
			new com.liferay.commerce.product.internal.upgrade.v1_11_2.
				CPDefinitionLinkUpgradeProcess());

		registry.register(
			"1.11.2", "2.0.0", CPInstanceOptionValueRelTable.create(),
			new CPInstanceOptionValueRelUpgradeProcess(
				_jsonFactory, _portalUUID));

		registry.register(
			"2.0.0", "2.1.0",
			UpgradeProcessFactory.alterColumnName(
				"CommerceCatalog", "system", "system_ BOOLEAN"),
			UpgradeProcessFactory.addColumns(
				"CPDefinition", "deliverySubscriptionEnabled BOOLEAN",
				"deliverySubscriptionLength INTEGER",
				"deliverySubscriptionType VARCHAR(75)",
				"deliverySubTypeSettings TEXT",
				"deliveryMaxSubscriptionCycles LONG"),
			UpgradeProcessFactory.addColumns(
				"CPInstance", "deliverySubscriptionEnabled BOOLEAN",
				"deliverySubscriptionLength INTEGER",
				"deliverySubscriptionType VARCHAR(75)",
				"deliverySubTypeSettings TEXT",
				"deliveryMaxSubscriptionCycles LONG"));

		registry.register(
			"2.1.0", "2.2.0",
			UpgradeProcessFactory.addColumns(
				"CPDefinitionOptionValueRel", "CPInstanceUuid VARCHAR(75)",
				"CProductId LONG", "quantity INTEGER", "price DECIMAL(30, 16)"),
			UpgradeProcessFactory.addColumns(
				"CPDefinitionOptionRel", "priceType VARCHAR(75)"),
			UpgradeProcessFactory.runSQL(
				String.format(
					"update %s set priceType = '%s'",
					CPDefinitionOptionRelTable.TABLE_NAME,
					CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC)),
			UpgradeProcessFactory.runSQL(
				String.format(
					"update %s set price = 0",
					CPDefinitionOptionValueRelTable.TABLE_NAME)));

		registry.register(
			"2.2.0", "2.2.1",
			new com.liferay.commerce.product.internal.upgrade.v2_2_1.
				CPDefinitionOptionValueRelUpgradeProcess());

		registry.register("2.2.1", "2.2.2", new DummyUpgradeProcess());

		registry.register(
			"2.2.2", "2.3.0",
			UpgradeProcessFactory.addColumns(
				"CommerceChannel", "priceDisplayType VARCHAR(75)",
				"discountsTargetNetPrice BOOLEAN"),
			UpgradeProcessFactory.runSQL(
				"update CommerceChannel set priceDisplayType = '" +
					CommercePricingConstants.TAX_EXCLUDED_FROM_PRICE + "'"),
			UpgradeProcessFactory.runSQL(
				"update CommerceChannel set discountsTargetNetPrice = " +
					"[$TRUE$]"));

		registry.register(
			"2.3.0", "2.4.0",
			UpgradeProcessFactory.addColumns(
				"CPDefinitionOptionValueRel", "preselected BOOLEAN"));

		registry.register(
			"2.4.0", "2.5.0",
			new FriendlyURLEntryUpgradeProcess(_groupLocalService));

		registry.register(
			"2.5.0", "2.6.0",
			UpgradeProcessFactory.addColumns(
				"CPInstance", "unspsc VARCHAR(75)"));

		registry.register("2.6.0", "2.6.1", new DummyUpgradeProcess());

		registry.register(
			"2.6.1", "3.0.0",
			new com.liferay.commerce.product.internal.upgrade.v3_0_0.
				CPFriendlyURLEntryUpgradeProcess());

		registry.register(
			"3.0.0", "3.1.0",
			UpgradeProcessFactory.addColumns(
				"CPTaxCategory", "externalReferenceCode VARCHAR(75)"));

		registry.register(
			"3.1.0", "3.2.0",
			new com.liferay.commerce.product.internal.upgrade.v3_2_0.
				FriendlyURLEntryUpgradeProcess(
					_classNameLocalService, _groupLocalService));

		registry.register("3.2.0", "3.2.1", new DummyUpgradeProcess());

		registry.register(
			"3.2.1", "3.3.0",
			UpgradeProcessFactory.addColumns(
				"CPAttachmentFileEntry", "cdnEnabled BOOLEAN",
				"cdnURL VARCHAR(255)"));

		registry.register(
			"3.3.0", "3.4.0",
			new com.liferay.commerce.product.internal.upgrade.v3_4_0.
				CommerceChannelUpgradeProcess(
					_accountEntryGroupSettings, _configurationProvider));

		registry.register(
			"3.4.0", "3.5.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"CPAttachmentFileEntry", "CPDSpecificationOptionValue",
						"CPDefinition", "CPDefinitionLink",
						"CPDefinitionLocalization", "CPDefinitionOptionRel",
						"CPDefinitionOptionValueRel", "CPDisplayLayout",
						"CPInstance", "CPInstanceOptionValueRel",
						"CPMeasurementUnit", "CPOption", "CPOptionCategory",
						"CPOptionValue", "CPSpecificationOption",
						"CPTaxCategory", "CProduct", "CommerceCatalog",
						"CommerceChannel", "CommerceChannelRel"
					};
				}

			});

		registry.register("3.5.0", "3.5.1", new DummyUpgradeStep());

		registry.register(
			"3.5.1", "3.6.0",
			UpgradeProcessFactory.addColumns(
				"CPInstance", "discontinued BOOLEAN",
				"replacementCPInstanceUuid VARCHAR(75)",
				"replacementCProductId LONG", "discontinuedDate DATE"));

		registry.register(
			"3.6.0", "3.7.0",
			new CTModelUpgradeProcess(
				"CommerceCatalog", "CommerceChannel", "CommerceChannelRel",
				"CPAttachmentFileEntry", "CPDefinition", "CPDefinitionLink",
				"CPDefinitionLocalization", "CPDefinitionOptionRel",
				"CPDefinitionOptionValueRel", "CPDSpecificationOptionValue",
				"CPDisplayLayout", "CPInstance", "CPInstanceOptionValueRel",
				"CPMeasurementUnit", "CPOption", "CPOptionCategory",
				"CPOptionValue", "CProduct", "CPSpecificationOption",
				"CPTaxCategory"));

		registry.register(
			"3.7.0", "3.8.0",
			UpgradeProcessFactory.addColumns(
				"CPMeasurementUnit", "externalReferenceCode VARCHAR(75)"));

		registry.register(
			"3.8.0", "3.8.1",
			UpgradeProcessFactory.alterColumnTypes(
				"CPAttachmentFileEntry", "STRING null", "cdnURL"));

		registry.register(
			"3.8.1", "3.9.0",
			new BaseUuidUpgradeProcess() {

				@Override
				protected String[][] getTableAndPrimaryKeyColumnNames() {
					return new String[][] {
						{"CommerceCatalog", "commerceCatalogId"},
						{"CommerceChannel", "commerceChannelId"},
						{"CPTaxCategory", "CPTaxCategoryId"}
					};
				}

			});

		registry.register(
			"3.9.0", "3.9.1",
			new BaseExternalReferenceCodeUpgradeProcess() {

				@Override
				protected String[][] getTableAndPrimaryKeyColumnNames() {
					return new String[][] {
						{"CommerceCatalog", "commerceCatalogId"},
						{"CommerceChannel", "commerceChannelId"},
						{"CPAttachmentFileEntry", "CPAttachmentFileEntryId"},
						{"CPInstance", "CPInstanceId"},
						{"CPOption", "CPOptionId"},
						{"CPOptionValue", "CPOptionValueId"},
						{"CProduct", "CProductId"},
						{"CPTaxCategory", "CPTaxCategoryId"}
					};
				}

			});

		if (_log.isInfoEnabled()) {
			_log.info("Commerce product upgrade step registrator finished");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductServiceUpgradeStepRegistrator.class);

	@Reference
	private AccountEntryGroupSettings _accountEntryGroupSettings;

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private PortalUUID _portalUUID;

	@Reference
	private SettingsFactory _settingsFactory;

}