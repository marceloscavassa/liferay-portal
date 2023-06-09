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

package com.liferay.document.library.internal.configuration.admin.service;

import com.liferay.document.library.internal.configuration.DLFileOrderConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sam Ziemer
 */
@Component(
	configurationPid = "com.liferay.document.library.internal.configuration.DLFileOrderConfiguration",
	property = Constants.SERVICE_PID + "=com.liferay.document.library.internal.configuration.DLFileOrderConfiguration.scoped",
	service = {
		DLFileOrderManagedServiceFactory.class, ManagedServiceFactory.class
	}
)
public class DLFileOrderManagedServiceFactory implements ManagedServiceFactory {

	@Override
	public void deleted(String pid) {
		_unmapPid(pid);
	}

	public String getCompanyOrderByColumn(long companyId) {
		DLFileOrderConfiguration dlFileOrderConfiguration =
			_getCompanyDLFileOrderConfiguration(companyId);

		return dlFileOrderConfiguration.orderByColumn();
	}

	public String getCompanySortBy(long companyId) {
		DLFileOrderConfiguration dlFileOrderConfiguration =
			_getCompanyDLFileOrderConfiguration(companyId);

		return dlFileOrderConfiguration.sortBy();
	}

	public String getGroupOrderByColumn(long groupId) {
		DLFileOrderConfiguration dlFileOrderConfiguration =
			_getGroupDLFileOrderConfiguration(groupId);

		return dlFileOrderConfiguration.orderByColumn();
	}

	public String getGroupSortBy(long groupId) {
		DLFileOrderConfiguration dlFileOrderConfiguration =
			_getGroupDLFileOrderConfiguration(groupId);

		return dlFileOrderConfiguration.sortBy();
	}

	@Override
	public String getName() {
		return "com.liferay.document.library.internal.configuration." +
			"DLFileOrderConfiguration.scoped";
	}

	public String getSystemOrderByColumn() {
		return _systemDLFileOrderConfiguration.orderByColumn();
	}

	public String getSystemSortBy() {
		return _systemDLFileOrderConfiguration.sortBy();
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> dictionary)
		throws ConfigurationException {

		_unmapPid(pid);

		long companyId = GetterUtil.getLong(
			dictionary.get("companyId"), CompanyConstants.SYSTEM);

		if (companyId != CompanyConstants.SYSTEM) {
			_updateCompanyConfiguration(companyId, pid, dictionary);
		}

		long groupId = GetterUtil.getLong(
			dictionary.get("groupId"), GroupConstants.DEFAULT_PARENT_GROUP_ID);

		if (groupId != GroupConstants.DEFAULT_PARENT_GROUP_ID) {
			_updateGroupConfiguration(groupId, pid, dictionary);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_systemDLFileOrderConfiguration = ConfigurableUtil.createConfigurable(
			DLFileOrderConfiguration.class, properties);
	}

	private DLFileOrderConfiguration _getCompanyDLFileOrderConfiguration(
		long companyId) {

		return _getDLFileOrderConfiguration(
			companyId, _companyConfigurationBeans,
			() -> _systemDLFileOrderConfiguration);
	}

	private DLFileOrderConfiguration _getDLFileOrderConfiguration(
		long key, Map<Long, DLFileOrderConfiguration> configurationBeans,
		Supplier<DLFileOrderConfiguration> supplier) {

		if (configurationBeans.containsKey(key)) {
			return configurationBeans.get(key);
		}

		return supplier.get();
	}

	private DLFileOrderConfiguration _getGroupDLFileOrderConfiguration(
		long groupId) {

		return _getDLFileOrderConfiguration(
			groupId, _groupConfigurationBeans,
			() -> {
				Group group = _groupLocalService.fetchGroup(groupId);

				long companyId = CompanyThreadLocal.getCompanyId();

				if (group != null) {
					companyId = group.getCompanyId();
				}

				return _getCompanyDLFileOrderConfiguration(companyId);
			});
	}

	private void _unmapPid(String pid) {
		if (_companyIds.containsKey(pid)) {
			long companyId = _companyIds.remove(pid);

			_companyConfigurationBeans.remove(companyId);

			_groupConfigurationBeans.clear();
			_groupIds.clear();
		}
		else if (_groupIds.containsKey(pid)) {
			long groupId = _groupIds.remove(pid);

			_groupConfigurationBeans.remove(groupId);
		}
	}

	private void _updateCompanyConfiguration(
		long companyId, String pid, Dictionary<String, ?> dictionary) {

		_companyConfigurationBeans.put(
			companyId,
			ConfigurableUtil.createConfigurable(
				DLFileOrderConfiguration.class, dictionary));
		_companyIds.put(pid, companyId);
	}

	private void _updateGroupConfiguration(
		long groupId, String pid, Dictionary<String, ?> dictionary) {

		_groupConfigurationBeans.put(
			groupId,
			ConfigurableUtil.createConfigurable(
				DLFileOrderConfiguration.class, dictionary));
		_groupIds.put(pid, groupId);
	}

	private final Map<Long, DLFileOrderConfiguration>
		_companyConfigurationBeans = new ConcurrentHashMap<>();
	private final Map<String, Long> _companyIds = new ConcurrentHashMap<>();
	private final Map<Long, DLFileOrderConfiguration> _groupConfigurationBeans =
		new ConcurrentHashMap<>();
	private final Map<String, Long> _groupIds = new ConcurrentHashMap<>();

	@Reference
	private GroupLocalService _groupLocalService;

	private volatile DLFileOrderConfiguration _systemDLFileOrderConfiguration;

}