/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.uad.exporter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.model.SystemEvent;
import com.liferay.portal.kernel.service.SystemEventLocalService;
import com.liferay.portal.uad.constants.PortalUADConstants;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the system event UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link SystemEventUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseSystemEventUADExporter
	extends DynamicQueryUADExporter<SystemEvent> {

	@Override
	public Class<SystemEvent> getTypeClass() {
		return SystemEvent.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return systemEventLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return PortalUADConstants.USER_ID_FIELD_NAMES_SYSTEM_EVENT;
	}

	@Override
	protected String toXmlString(SystemEvent systemEvent) {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.kernel.model.SystemEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>systemEventId</column-name><column-value><![CDATA[");
		sb.append(systemEvent.getSystemEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(systemEvent.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(systemEvent.getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected SystemEventLocalService systemEventLocalService;

}