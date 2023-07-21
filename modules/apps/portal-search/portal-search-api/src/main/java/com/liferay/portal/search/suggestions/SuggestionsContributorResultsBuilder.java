/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.suggestions;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Petteri Karttunen
 */
@ProviderType
public interface SuggestionsContributorResultsBuilder {

	public SuggestionsContributorResultsBuilder attribute(
		String name, Object value);

	public SuggestionsContributorResults build();

	public SuggestionsContributorResultsBuilder displayGroupName(
		String displayGroupName);

	public SuggestionsContributorResultsBuilder suggestions(
		List<Suggestion> suggestions);

}