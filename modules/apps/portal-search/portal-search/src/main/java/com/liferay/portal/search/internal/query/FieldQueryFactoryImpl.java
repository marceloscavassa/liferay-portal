/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.query;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.query.FieldQueryFactory;
import com.liferay.portal.search.analysis.FieldQueryBuilder;
import com.liferay.portal.search.analysis.FieldQueryBuilderFactory;
import com.liferay.portal.search.internal.analysis.DescriptionFieldQueryBuilder;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = FieldQueryFactory.class)
public class FieldQueryFactoryImpl implements FieldQueryFactory {

	@Override
	public Query createQuery(
		String fieldName, String keywords, boolean like,
		boolean splitKeywords) {

		FieldQueryBuilder fieldQueryBuilder = _getQueryBuilder(fieldName);

		return fieldQueryBuilder.build(fieldName, keywords);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerList = ServiceTrackerListFactory.open(
			bundleContext, FieldQueryBuilderFactory.class);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerList.close();
	}

	private FieldQueryBuilder _getQueryBuilder(String fieldName) {
		for (FieldQueryBuilderFactory fieldQueryBuilderFactory :
				_serviceTrackerList) {

			FieldQueryBuilder fieldQueryBuilder =
				fieldQueryBuilderFactory.getQueryBuilder(fieldName);

			if (fieldQueryBuilder != null) {
				return fieldQueryBuilder;
			}
		}

		return _descriptionFieldQueryBuilder;
	}

	@Reference
	private DescriptionFieldQueryBuilder _descriptionFieldQueryBuilder;

	private ServiceTrackerList<FieldQueryBuilderFactory> _serviceTrackerList;

}