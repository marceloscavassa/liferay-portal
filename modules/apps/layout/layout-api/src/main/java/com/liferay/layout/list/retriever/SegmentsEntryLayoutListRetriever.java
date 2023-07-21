/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.list.retriever;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Mikel Lorza
 */
@ProviderType
public interface SegmentsEntryLayoutListRetriever
	<T extends ListObjectReference> {

	public long getDefaultVariationSegmentsEntryId(T t);

	public boolean hasSegmentsEntryVariation(T t, long segmentsEntryId);

}