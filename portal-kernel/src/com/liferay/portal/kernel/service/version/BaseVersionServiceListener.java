/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.version;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.version.VersionModel;
import com.liferay.portal.kernel.model.version.VersionedModel;

/**
 * @author Preston Crary
 */
public abstract class BaseVersionServiceListener
	<E extends VersionedModel<V>, V extends VersionModel<E>>
		implements VersionServiceListener<E, V> {

	@Override
	public void afterCheckout(E draftVersionedModel, int version)
		throws PortalException {
	}

	@Override
	public void afterCreateDraft(E draftVersionedModel) throws PortalException {
	}

	@Override
	public void afterDelete(E publishedVersionedModel) throws PortalException {
	}

	@Override
	public void afterDeleteDraft(E draftVersionedModel) throws PortalException {
	}

	@Override
	public void afterDeleteVersion(V versionModel) throws PortalException {
	}

	@Override
	public void afterPublishDraft(E draftVersionedModel, int version)
		throws PortalException {
	}

	@Override
	public void afterUpdateDraft(E draftVersionedModel) throws PortalException {
	}

}