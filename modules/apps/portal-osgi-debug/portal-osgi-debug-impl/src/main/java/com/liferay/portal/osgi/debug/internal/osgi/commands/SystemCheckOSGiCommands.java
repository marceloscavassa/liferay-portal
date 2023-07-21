/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.debug.internal.osgi.commands;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dependency.manager.DependencyManagerSyncUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.osgi.debug.SystemChecker;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Tina Tian
 */
@Component(
	property = {"osgi.command.function=check", "osgi.command.scope=system"},
	service = {}
)
public class SystemCheckOSGiCommands {

	public void check() {
		_check(true);
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_serviceTracker = new ServiceTracker<>(
			bundleContext, SystemChecker.class, null);

		_serviceTracker.open();

		if (_log.isInfoEnabled()) {
			_log.info(
				"System check is enabled. You can run a system check with " +
					"the command \"system:check\" in Gogo shell.");
		}

		boolean checkEnabled = GetterUtil.getBoolean(
			_props.get(PropsKeys.INITIAL_SYSTEM_CHECK_ENABLED), true);

		if (checkEnabled) {
			DependencyManagerSyncUtil.sync();

			if (_log.isInfoEnabled()) {
				_log.info("Running system check");
			}

			_check(false);
		}

		Dictionary<String, Object> osgiCommandProperties =
			new HashMapDictionary<>();

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith("osgi.command.")) {
				osgiCommandProperties.put(key, entry.getValue());
			}
		}

		_serviceRegistration = bundleContext.registerService(
			SystemCheckOSGiCommands.class, this, osgiCommandProperties);
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();

		_serviceTracker.close();
	}

	private void _check(boolean useSystemOut) {
		Map<ServiceReference<SystemChecker>, SystemChecker> systemCheckerMap =
			_serviceTracker.getTracked();

		Collection<SystemChecker> systemCheckers = systemCheckerMap.values();

		if (useSystemOut) {
			System.out.println("Available checkers: " + systemCheckers);
		}
		else if (_log.isInfoEnabled()) {
			_log.info("Available checkers :" + systemCheckers);
		}

		for (SystemChecker systemChecker : systemCheckers) {
			StringBundler sb = new StringBundler(5);

			sb.append("Running \"");
			sb.append(systemChecker.getName());
			sb.append("\". You can run this by itself with command \"");
			sb.append(systemChecker.getOSGiCommand());
			sb.append("\" in gogo shell.");

			if (useSystemOut) {
				System.out.println(sb.toString());
			}
			else if (_log.isInfoEnabled()) {
				_log.info(sb.toString());
			}

			String result = systemChecker.check();

			if (Validator.isNull(result)) {
				if (useSystemOut) {
					System.out.println(
						systemChecker.getName() +
							" check result: No issues were found.");
				}
				else if (_log.isInfoEnabled()) {
					_log.info(
						systemChecker.getName() +
							" check result: No issues were found.");
				}
			}
			else {
				if (useSystemOut) {
					System.out.println(
						systemChecker.getName() + " check result: " + result);
				}
				else if (_log.isWarnEnabled()) {
					_log.warn(
						systemChecker.getName() + " check result: " + result);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SystemCheckOSGiCommands.class);

	@Reference(target = ModuleServiceLifecycle.SYSTEM_CHECK)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private Props _props;

	private ServiceRegistration<?> _serviceRegistration;
	private ServiceTracker<SystemChecker, SystemChecker> _serviceTracker;

}