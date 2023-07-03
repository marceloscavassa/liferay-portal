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

package com.liferay.object.web.internal.deployer;

import com.liferay.application.list.PanelApp;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.frontend.data.set.view.FDSView;
import com.liferay.frontend.data.set.view.table.FDSTableSchemaBuilderFactory;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.item.action.executor.InfoItemActionExecutor;
import com.liferay.info.item.capability.InfoItemCapability;
import com.liferay.info.item.creator.InfoItemCreator;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.provider.InfoItemCapabilitiesProvider;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.info.item.provider.InfoItemPermissionProvider;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererRegistry;
import com.liferay.info.item.updater.InfoItemFieldValuesUpdater;
import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.info.permission.provider.InfoPermissionProvider;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.item.selector.criteria.info.item.criterion.InfoItemItemSelectorCriterion;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.page.template.info.item.capability.DisplayPageInfoItemCapability;
import com.liferay.layout.page.template.info.item.capability.EditPageInfoItemCapability;
import com.liferay.layout.page.template.info.item.provider.DisplayPageInfoItemFieldSetProvider;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.deployer.ObjectDefinitionDeployer;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.related.models.ObjectRelatedModelsProviderRegistry;
import com.liferay.object.rest.context.path.RESTContextPathResolverRegistry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManagerRegistry;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFieldSettingLocalService;
import com.liferay.object.service.ObjectLayoutLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.service.ObjectViewLocalService;
import com.liferay.object.web.internal.asset.model.ObjectEntryAssetRendererFactory;
import com.liferay.object.web.internal.info.collection.provider.ObjectEntrySingleFormVariationInfoCollectionProvider;
import com.liferay.object.web.internal.info.item.action.ObjectEntryInfoItemActionExecutor;
import com.liferay.object.web.internal.info.item.creator.ObjectEntryInfoItemCreator;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemCapabilitiesProvider;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemDetailsProvider;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemFieldValuesProvider;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemFormProvider;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemObjectProvider;
import com.liferay.object.web.internal.info.item.provider.ObjectEntryInfoItemPermissionProvider;
import com.liferay.object.web.internal.info.item.renderer.ObjectEntryRowInfoItemRenderer;
import com.liferay.object.web.internal.info.item.updater.ObjectEntryInfoItemFieldValuesUpdater;
import com.liferay.object.web.internal.info.list.renderer.ObjectEntryTableInfoListRenderer;
import com.liferay.object.web.internal.info.permission.provider.ObjectEntryInfoPermissionProvider;
import com.liferay.object.web.internal.item.selector.ObjectEntryItemSelectorView;
import com.liferay.object.web.internal.layout.display.page.ObjectEntryLayoutDisplayPageProvider;
import com.liferay.object.web.internal.notifications.ObjectUserNotificationsDefinition;
import com.liferay.object.web.internal.notifications.ObjectUserNotificationsHandler;
import com.liferay.object.web.internal.object.entries.application.list.ObjectEntriesPanelApp;
import com.liferay.object.web.internal.object.entries.display.context.ObjectEntryDisplayContextFactory;
import com.liferay.object.web.internal.object.entries.frontend.data.set.filter.factory.ObjectFieldFDSFilterFactoryRegistry;
import com.liferay.object.web.internal.object.entries.frontend.data.set.view.table.ObjectEntriesTableFDSView;
import com.liferay.object.web.internal.object.entries.portlet.ObjectEntriesPortlet;
import com.liferay.object.web.internal.object.entries.portlet.action.EditObjectEntryMVCActionCommand;
import com.liferay.object.web.internal.object.entries.portlet.action.EditObjectEntryMVCRenderCommand;
import com.liferay.object.web.internal.object.entries.portlet.action.EditObjectEntryRelatedModelMVCActionCommand;
import com.liferay.object.web.internal.object.entries.portlet.action.UploadAttachmentMVCActionCommand;
import com.liferay.object.web.internal.object.entries.upload.AttachmentUploadFileEntryHandler;
import com.liferay.object.web.internal.object.entries.upload.AttachmentUploadResponseHandler;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermission;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.template.info.item.capability.TemplateInfoItemCapability;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;
import com.liferay.upload.UploadHandler;

import java.util.Collections;
import java.util.List;

import javax.portlet.Portlet;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = ObjectDefinitionDeployer.class)
public class ObjectDefinitionDeployerImpl implements ObjectDefinitionDeployer {

	@Override
	public List<ServiceRegistration<?>> deploy(
		ObjectDefinition objectDefinition) {

		if (objectDefinition.isUnmodifiableSystemObject()) {
			return Collections.emptyList();
		}

		InfoItemFormProvider<ObjectEntry> infoItemFormProvider =
			new ObjectEntryInfoItemFormProvider(
				_displayPageInfoItemFieldSetProvider, objectDefinition,
				_infoItemFieldReaderFieldSetProvider,
				_listTypeEntryLocalService, _objectActionLocalService,
				_objectDefinitionLocalService, _objectFieldLocalService,
				_objectFieldSettingLocalService,
				_objectRelationshipLocalService, _objectScopeProviderRegistry,
				_restContextPathResolverRegistry,
				_templateInfoItemFieldSetProvider, _userLocalService);

		PortletResourcePermission portletResourcePermission =
			_getPortletResourcePermission(objectDefinition.getResourceName());

		InfoPermissionProvider infoPermissionProvider =
			new ObjectEntryInfoPermissionProvider(
				objectDefinition, _portletLocalService, _portletPermission,
				portletResourcePermission);

		List<ServiceRegistration<?>> serviceRegistrations = ListUtil.fromArray(
			_bundleContext.registerService(
				AssetRendererFactory.class,
				new ObjectEntryAssetRendererFactory(
					_assetDisplayPageFriendlyURLProvider, objectDefinition,
					_objectEntryDisplayContextFactory, _objectEntryLocalService,
					_objectEntryService, _servletContext),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).build()),
			_bundleContext.registerService(
				FDSView.class,
				new ObjectEntriesTableFDSView(
					_fdsTableSchemaBuilderFactory, objectDefinition,
					_objectDefinitionLocalService, _objectFieldLocalService,
					_objectRelationshipLocalService, _objectViewLocalService,
					_userLocalService),
				HashMapDictionaryBuilder.put(
					"frontend.data.set.name", objectDefinition.getPortletId()
				).build()),
			_bundleContext.registerService(
				InfoCollectionProvider.class,
				new ObjectEntrySingleFormVariationInfoCollectionProvider(
					_assetCategoryLocalService, _assetTagLocalService,
					_assetVocabularyLocalService, _groupLocalService,
					_listTypeEntryLocalService, objectDefinition,
					_objectEntryLocalService, _objectEntryManagerRegistry,
					_objectFieldLocalService, _objectLayoutLocalService,
					_objectScopeProviderRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemActionExecutor.class,
				new ObjectEntryInfoItemActionExecutor(
					_objectActionLocalService, objectDefinition,
					_objectEntryManagerRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemCapabilitiesProvider.class,
				new ObjectEntryInfoItemCapabilitiesProvider(
					_displayPageInfoItemCapability, _editPageInfoItemCapability,
					_templateInfoItemCapability),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemCreator.class,
				new ObjectEntryInfoItemCreator(
					infoItemFormProvider, objectDefinition,
					_objectEntryLocalService, _objectEntryManagerRegistry,
					_objectScopeProviderRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemDetailsProvider.class,
				new ObjectEntryInfoItemDetailsProvider(objectDefinition),
				HashMapDictionaryBuilder.<String, Object>put(
					Constants.SERVICE_RANKING, 10
				).put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemFieldValuesProvider.class,
				new ObjectEntryInfoItemFieldValuesProvider(
					_assetDisplayPageFriendlyURLProvider,
					_displayPageInfoItemFieldSetProvider, _dlAppLocalService,
					_dlFileEntryLocalService, _dlURLHelper,
					_infoItemFieldReaderFieldSetProvider, _jsonFactory,
					_listTypeEntryLocalService, _objectActionLocalService,
					objectDefinition, _objectDefinitionLocalService,
					_objectEntryLocalService, _objectEntryManagerRegistry,
					_objectFieldLocalService, _objectRelationshipLocalService,
					_templateInfoItemFieldSetProvider, _userLocalService),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemFieldValuesUpdater.class,
				new ObjectEntryInfoItemFieldValuesUpdater(
					infoItemFormProvider, objectDefinition,
					_objectEntryManagerRegistry, _objectScopeProviderRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemFormProvider.class, infoItemFormProvider,
				HashMapDictionaryBuilder.<String, Object>put(
					Constants.SERVICE_RANKING, 10
				).put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemObjectProvider.class,
				new ObjectEntryInfoItemObjectProvider(
					objectDefinition, _objectEntryLocalService,
					_objectEntryManagerRegistry),
				HashMapDictionaryBuilder.<String, Object>put(
					Constants.SERVICE_RANKING, 100
				).put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"info.item.identifier",
					new String[] {
						"com.liferay.info.item.ClassPKInfoItemIdentifier",
						"com.liferay.info.item.ERCInfoItemIdentifier"
					}
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemPermissionProvider.class,
				new ObjectEntryInfoItemPermissionProvider(
					objectDefinition,
					_objectEntryManagerRegistry.getObjectEntryManager(
						objectDefinition.getStorageType()),
					_objectEntryService),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoItemRenderer.class,
				new ObjectEntryRowInfoItemRenderer(
					_assetDisplayPageFriendlyURLProvider, _dlAppService,
					_dlFileEntryLocalService, _dlURLHelper,
					_listTypeEntryLocalService, _objectDefinitionLocalService,
					_objectEntryLocalService, _objectFieldLocalService,
					_objectRelationshipLocalService, _portal, _servletContext),
				HashMapDictionaryBuilder.<String, Object>put(
					Constants.SERVICE_RANKING, 100
				).put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).put(
					"osgi.web.symbolicname", "com.liferay.object.web"
				).build()),
			_bundleContext.registerService(
				InfoListRenderer.class,
				new ObjectEntryTableInfoListRenderer(
					_infoItemRendererRegistry, _objectFieldLocalService),
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				InfoPermissionProvider.class, infoPermissionProvider,
				HashMapDictionaryBuilder.<String, Object>put(
					"company.id", objectDefinition.getCompanyId()
				).put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				ItemSelectorView.class,
				new ObjectEntryItemSelectorView(
					infoPermissionProvider, _itemSelectorViewDescriptorRenderer,
					objectDefinition,
					_objectEntryManagerRegistry.getObjectEntryManager(
						objectDefinition.getStorageType()),
					_objectRelatedModelsProviderRegistry, _portal),
				HashMapDictionaryBuilder.<String, Object>put(
					"item.selector.view.order", 500
				).build()),
			_bundleContext.registerService(
				LayoutDisplayPageProvider.class,
				new ObjectEntryLayoutDisplayPageProvider(
					objectDefinition, _objectEntryLocalService,
					_objectEntryManagerRegistry.getObjectEntryManager(
						objectDefinition.getStorageType()),
					_userLocalService),
				HashMapDictionaryBuilder.<String, Object>put(
					"item.class.name", objectDefinition.getClassName()
				).build()),
			_bundleContext.registerService(
				Portlet.class,
				new ObjectEntriesPortlet(
					_objectActionLocalService,
					objectDefinition.getObjectDefinitionId(),
					_objectDefinitionLocalService,
					_objectFieldFDSFilterFactoryRegistry,
					_objectFieldLocalService, _objectScopeProviderRegistry,
					_objectViewLocalService, _portal,
					portletResourcePermission),
				HashMapDictionaryBuilder.<String, Object>put(
					"com.liferay.portlet.company",
					objectDefinition.getCompanyId()
				).put(
					"com.liferay.portlet.display-category",
					() -> {
						if (objectDefinition.isPortlet()) {
							return "category.object";
						}

						return "category.hidden";
					}
				).put(
					"javax.portlet.display-name",
					objectDefinition.getPluralLabel(LocaleUtil.getSiteDefault())
				).put(
					"javax.portlet.init-param.view-template",
					"/object_entries/view_object_entries.jsp"
				).put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).put(
					"javax.portlet.version", "3.0"
				).build()),
			_bundleContext.registerService(
				MVCActionCommand.class,
				new EditObjectEntryMVCActionCommand(
					_objectDefinitionLocalService, _objectEntryService,
					_objectRelatedModelsProviderRegistry,
					_objectRelationshipLocalService,
					_objectScopeProviderRegistry, _portal),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).put(
					"mvc.command.name", "/object_entries/edit_object_entry"
				).build()),
			_bundleContext.registerService(
				MVCActionCommand.class,
				new EditObjectEntryRelatedModelMVCActionCommand(
					_objectDefinitionLocalService,
					_objectRelationshipLocalService, _portal),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).put(
					"mvc.command.name",
					"/object_entries/edit_object_entry_related_model"
				).build()),
			_bundleContext.registerService(
				MVCActionCommand.class,
				new UploadAttachmentMVCActionCommand(
					_attachmentUploadFileEntryHandler,
					_attachmentUploadResponseHandler, _uploadHandler),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).put(
					"mvc.command.name", "/object_entries/upload_attachment"
				).build()),
			_bundleContext.registerService(
				MVCRenderCommand.class,
				new EditObjectEntryMVCRenderCommand(
					_objectEntryDisplayContextFactory, _portal),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).put(
					"mvc.command.name", "/object_entries/edit_object_entry"
				).build()),
			_bundleContext.registerService(
				UserNotificationDefinition.class,
				new ObjectUserNotificationsDefinition(
					objectDefinition.getPortletId(),
					_portal.getClassNameId(objectDefinition.getClassName()),
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).build()),
			_bundleContext.registerService(
				UserNotificationHandler.class,
				new ObjectUserNotificationsHandler(
					_assetDisplayPageFriendlyURLProvider, objectDefinition),
				HashMapDictionaryBuilder.<String, Object>put(
					"javax.portlet.name", objectDefinition.getPortletId()
				).build()));

		// Register ObjectEntriesPanelApp after ObjectEntriesPortlet. See
		// LPS-140379.

		serviceRegistrations.add(
			_bundleContext.registerService(
				PanelApp.class,
				new ObjectEntriesPanelApp(
					objectDefinition,
					() -> _portletLocalService.getPortletById(
						objectDefinition.getPortletId())),
				HashMapDictionaryBuilder.<String, Object>put(
					"panel.app.order:Integer",
					objectDefinition.getPanelAppOrder()
				).put(
					"panel.category.key", objectDefinition.getPanelCategoryKey()
				).build()));

		return serviceRegistrations;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, PortletResourcePermission.class, "resource.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private PortletResourcePermission _getPortletResourcePermission(
		String resourceName) {

		PortletResourcePermission portletResourcePermission =
			_serviceTrackerMap.getService(resourceName);

		if (portletResourcePermission == null) {
			throw new IllegalArgumentException(
				"No portlet resource permission found with resource name " +
					resourceName);
		}

		return portletResourcePermission;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private AttachmentUploadFileEntryHandler _attachmentUploadFileEntryHandler;

	@Reference
	private AttachmentUploadResponseHandler _attachmentUploadResponseHandler;

	private BundleContext _bundleContext;

	@Reference(
		target = "(info.item.capability.key=" + DisplayPageInfoItemCapability.KEY + ")"
	)
	private InfoItemCapability _displayPageInfoItemCapability;

	@Reference
	private DisplayPageInfoItemFieldSetProvider
		_displayPageInfoItemFieldSetProvider;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference(
		target = "(info.item.capability.key=" + EditPageInfoItemCapability.KEY + ")"
	)
	private InfoItemCapability _editPageInfoItemCapability;

	@Reference
	private FDSTableSchemaBuilderFactory _fdsTableSchemaBuilderFactory;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private InfoItemFieldReaderFieldSetProvider
		_infoItemFieldReaderFieldSetProvider;

	@Reference
	private InfoItemRendererRegistry _infoItemRendererRegistry;

	@Reference
	private ItemSelectorViewDescriptorRenderer<InfoItemItemSelectorCriterion>
		_itemSelectorViewDescriptorRenderer;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ListTypeEntryLocalService _listTypeEntryLocalService;

	@Reference
	private ObjectActionLocalService _objectActionLocalService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryDisplayContextFactory _objectEntryDisplayContextFactory;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryManagerRegistry _objectEntryManagerRegistry;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference
	private ObjectFieldFDSFilterFactoryRegistry
		_objectFieldFDSFilterFactoryRegistry;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectFieldSettingLocalService _objectFieldSettingLocalService;

	@Reference
	private ObjectLayoutLocalService _objectLayoutLocalService;

	@Reference
	private ObjectRelatedModelsProviderRegistry
		_objectRelatedModelsProviderRegistry;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private ObjectViewLocalService _objectViewLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private PortletPermission _portletPermission;

	@Reference
	private RESTContextPathResolverRegistry _restContextPathResolverRegistry;

	private ServiceTrackerMap<String, PortletResourcePermission>
		_serviceTrackerMap;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.object.web)")
	private ServletContext _servletContext;

	@Reference(
		target = "(info.item.capability.key=" + TemplateInfoItemCapability.KEY + ")"
	)
	private InfoItemCapability _templateInfoItemCapability;

	@Reference
	private TemplateInfoItemFieldSetProvider _templateInfoItemFieldSetProvider;

	@Reference
	private UploadHandler _uploadHandler;

	@Reference
	private UserLocalService _userLocalService;

}