/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.display.context;

import com.liferay.message.boards.display.context.MBAdminListDisplayContext;
import com.liferay.message.boards.display.context.MBDisplayContextFactory;
import com.liferay.message.boards.display.context.MBHomeDisplayContext;
import com.liferay.message.boards.display.context.MBListDisplayContext;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 * @author Sergio González
 */
@Component(service = MBDisplayContextProvider.class)
public class MBDisplayContextProvider {

	public MBAdminListDisplayContext getMbAdminListDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, long categoryId) {

		MBAdminListDisplayContext mbAdminListDisplayContext =
			new DefaultMBAdminListDisplayContext(
				httpServletRequest, httpServletResponse, categoryId);

		for (MBDisplayContextFactory mbDisplayContextFactory :
				_serviceTrackerList) {

			mbAdminListDisplayContext =
				mbDisplayContextFactory.getMBAdminListDisplayContext(
					mbAdminListDisplayContext, httpServletRequest,
					httpServletResponse, categoryId);
		}

		return mbAdminListDisplayContext;
	}

	public MBHomeDisplayContext getMBHomeDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		MBHomeDisplayContext mbHomeDisplayContext =
			new DefaultMBHomeDisplayContext(
				httpServletRequest, httpServletResponse);

		for (MBDisplayContextFactory mbDisplayContextFactory :
				_serviceTrackerList) {

			mbHomeDisplayContext =
				mbDisplayContextFactory.getMBHomeDisplayContext(
					mbHomeDisplayContext, httpServletRequest,
					httpServletResponse);
		}

		return mbHomeDisplayContext;
	}

	public MBListDisplayContext getMbListDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, long categoryId,
		String mvcRenderCommandName) {

		MBListDisplayContext mbListDisplayContext =
			new DefaultMBListDisplayContext(
				httpServletRequest, httpServletResponse, categoryId,
				mvcRenderCommandName);

		for (MBDisplayContextFactory mbDisplayContextFactory :
				_serviceTrackerList) {

			mbListDisplayContext =
				mbDisplayContextFactory.getMBListDisplayContext(
					mbListDisplayContext, httpServletRequest,
					httpServletResponse, categoryId);
		}

		return mbListDisplayContext;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, MBDisplayContextFactory.class);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private ServiceTrackerList<MBDisplayContextFactory> _serviceTrackerList;

}