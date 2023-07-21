/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.velocity.internal;

import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResourceLoader;
import com.liferay.portal.template.BaseTemplateResourceLoader;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Spasic
 * @author Peter Fellwock
 */
@Component(service = TemplateResourceLoader.class)
public class VelocityTemplateResourceLoader extends BaseTemplateResourceLoader {

	@Activate
	@Modified
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		init(
			bundleContext, TemplateConstants.LANG_TYPE_VM,
			_velocityTemplateResourceCache);
	}

	@Deactivate
	protected void deactivate() {
		destroy();
	}

	@Reference
	private VelocityTemplateResourceCache _velocityTemplateResourceCache;

}