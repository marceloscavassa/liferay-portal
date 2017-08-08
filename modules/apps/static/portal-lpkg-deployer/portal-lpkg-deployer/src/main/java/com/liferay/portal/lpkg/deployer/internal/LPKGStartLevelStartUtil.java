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

package com.liferay.portal.lpkg.deployer.internal;

import java.util.Dictionary;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.osgi.framework.startlevel.FrameworkStartLevel;

/**
 * @author Matthew Tambara
 */
public class LPKGStartLevelStartUtil {

	public static void setStartLevelAndStart(
			Bundle bundle, int startLevel, BundleContext bundleContext)
		throws BundleException {

		Bundle systemBundle = bundleContext.getBundle(0);

		FrameworkStartLevel frameworkStartLevel = systemBundle.adapt(
			FrameworkStartLevel.class);

		int frameStartLevel = frameworkStartLevel.getStartLevel();

		BundleStartLevel bundleStartLevel = bundle.adapt(
			BundleStartLevel.class);

		if (frameStartLevel >= startLevel) {
			_startBundle(bundle);

			bundleStartLevel.setStartLevel(startLevel);
		}
		else {
			bundleStartLevel.setStartLevel(startLevel);

			_startBundle(bundle);
		}
	}

	private static void _startBundle(Bundle bundle) throws BundleException {
		Dictionary<String, String> headers = bundle.getHeaders();

		String fragmentHost = headers.get(Constants.FRAGMENT_HOST);

		if (fragmentHost == null) {
			bundle.start();
		}
	}

}