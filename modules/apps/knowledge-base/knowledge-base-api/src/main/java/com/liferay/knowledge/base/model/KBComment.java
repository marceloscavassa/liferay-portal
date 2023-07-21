/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the KBComment service. Represents a row in the &quot;KBComment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentModel
 * @generated
 */
@ImplementationClassName("com.liferay.knowledge.base.model.impl.KBCommentImpl")
@ProviderType
public interface KBComment extends KBCommentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.knowledge.base.model.impl.KBCommentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KBComment, Long> KB_COMMENT_ID_ACCESSOR =
		new Accessor<KBComment, Long>() {

			@Override
			public Long get(KBComment kbComment) {
				return kbComment.getKbCommentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KBComment> getTypeClass() {
				return KBComment.class;
			}

		};

}