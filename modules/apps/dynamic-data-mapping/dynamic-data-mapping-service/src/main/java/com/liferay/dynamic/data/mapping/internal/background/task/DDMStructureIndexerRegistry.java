/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.background.task;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.DDMStructureIndexer;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Fabiano Nazar
 * @author Lucas Marques de Paula
 */
@Component(service = DDMStructureIndexerRegistry.class)
public class DDMStructureIndexerRegistry {

	public DDMStructureIndexer getDDMStructureIndexer(String className)
		throws PortalException {

		DDMStructureIndexer ddmStructureIndexer = _serviceTrackerMap.getService(
			className);

		if (ddmStructureIndexer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No dynamic data mapping structure indexer exists for " +
						className);
			}

			return null;
		}

		return ddmStructureIndexer;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, DDMStructureIndexer.class,
			"ddm.structure.indexer.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMStructureIndexerRegistry.class);

	private ServiceTrackerMap<String, DDMStructureIndexer> _serviceTrackerMap;

}