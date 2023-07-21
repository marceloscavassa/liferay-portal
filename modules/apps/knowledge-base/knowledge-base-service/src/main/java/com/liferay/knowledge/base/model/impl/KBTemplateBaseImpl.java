/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model.impl;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.service.KBTemplateLocalServiceUtil;

/**
 * The extended model base implementation for the KBTemplate service. Represents a row in the &quot;KBTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KBTemplateImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateImpl
 * @see KBTemplate
 * @generated
 */
public abstract class KBTemplateBaseImpl
	extends KBTemplateModelImpl implements KBTemplate {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kb template model instance should use the <code>KBTemplate</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			KBTemplateLocalServiceUtil.addKBTemplate(this);
		}
		else {
			KBTemplateLocalServiceUtil.updateKBTemplate(this);
		}
	}

}