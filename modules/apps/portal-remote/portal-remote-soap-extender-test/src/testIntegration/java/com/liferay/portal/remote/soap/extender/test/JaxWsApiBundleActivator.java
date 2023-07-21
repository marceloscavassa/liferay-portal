/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.soap.extender.test;

import com.liferay.petra.string.StringBundler;

import javax.xml.ws.Endpoint;
import javax.xml.ws.spi.Provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Carlos Sierra Andrés
 */
public class JaxWsApiBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		_configurationAdminBundleActivator =
			new ConfigurationAdminBundleActivator();

		_configurationAdminBundleActivator.start(bundleContext);

		String filterString = StringBundler.concat(
			"(&(objectClass=", Provider.class.getName(), ")(",
			HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH,
			"=/soap-test))");

		WaiterUtil.waitForFilter(bundleContext, filterString, 10_000);

		try {
			_endpoint = Endpoint.publish("/greeterApi", new GreeterImpl());
		}
		catch (Exception exception) {
			cleanUp(bundleContext);

			throw exception;
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		cleanUp(bundleContext);
	}

	protected void cleanUp(BundleContext bundleContext) throws Exception {
		try {
			_endpoint.stop();
		}
		catch (Exception exception) {
		}

		_configurationAdminBundleActivator.stop(bundleContext);
	}

	private ConfigurationAdminBundleActivator
		_configurationAdminBundleActivator;
	private Endpoint _endpoint;

}