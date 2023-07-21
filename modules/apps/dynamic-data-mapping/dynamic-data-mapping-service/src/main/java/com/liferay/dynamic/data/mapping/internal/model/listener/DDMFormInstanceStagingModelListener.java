/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.model.listener;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.staging.model.listener.StagingModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Akos Thurzo
 */
@Component(service = ModelListener.class)
public class DDMFormInstanceStagingModelListener
	extends BaseModelListener<DDMFormInstance> {

	@Override
	public void onAfterCreate(DDMFormInstance ddmFormInstance)
		throws ModelListenerException {

		_stagingModelListener.onAfterCreate(ddmFormInstance);
	}

	@Override
	public void onAfterRemove(DDMFormInstance ddmFormInstance)
		throws ModelListenerException {

		_stagingModelListener.onAfterRemove(ddmFormInstance);
	}

	@Override
	public void onAfterUpdate(
			DDMFormInstance originalDDMFormInstance,
			DDMFormInstance ddmFormInstance)
		throws ModelListenerException {

		_stagingModelListener.onAfterUpdate(ddmFormInstance);
	}

	@Reference
	private StagingModelListener<DDMFormInstance> _stagingModelListener;

}