/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.test.util;

import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * @author Alejandro Tardín
 */
public class GroupConfigurationTemporarySwapper implements AutoCloseable {

	public GroupConfigurationTemporarySwapper(
			long groupId, String pid, Dictionary<String, Object> properties,
			SettingsFactory settingsFactory)
		throws Exception {

		_groupId = groupId;
		_pid = pid;
		_settingsFactory = settingsFactory;

		Settings settings = settingsFactory.getSettings(
			new GroupServiceSettingsLocator(_groupId, _pid));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		_initialProperties = new HashMapDictionary();

		Enumeration<String> keysEnumeration = properties.keys();

		while (keysEnumeration.hasMoreElements()) {
			String key = keysEnumeration.nextElement();

			_initialProperties.put(key, modifiableSettings.getValue(key, null));

			modifiableSettings.setValue(
				key, String.valueOf(properties.get(key)));
		}

		modifiableSettings.store();
	}

	@Override
	public void close() throws Exception {
		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(_groupId, _pid));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		Enumeration<String> keysEnumeration = _initialProperties.keys();

		while (keysEnumeration.hasMoreElements()) {
			String key = keysEnumeration.nextElement();

			modifiableSettings.setValue(
				key, String.valueOf(_initialProperties.get(key)));
		}

		modifiableSettings.store();
	}

	private final long _groupId;
	private final Dictionary<String, Object> _initialProperties;
	private final String _pid;
	private final SettingsFactory _settingsFactory;

}