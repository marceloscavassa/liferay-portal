/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import request from './request';

export function fetchConnection(token: string) {
	return request('/data-sources', {
		body: JSON.stringify({
			token,
		}),
		method: 'POST',
	});
}

export function fetchContactsOrganization() {
	return request('/contacts/organizations', {
		method: 'GET',
	});
}

export function fetchContactsUsersGroup() {
	return request('/contacts/user-groups', {
		method: 'GET',
	});
}

export function deleteConnection() {
	return request('/data-sources', {method: 'DELETE'});
}

export function fetchProperties() {
	return request('/channels', {method: 'GET'});
}

export function createProperty(name: string) {
	return request('/channels', {
		body: JSON.stringify({
			name,
		}),
		method: 'POST',
	});
}

export function updateProperty({
	channelId,
	commerceChannelIds,
	commerceSyncEnabled,
	dataSourceId,
	siteIds,
}: {
	channelId: string;
	commerceChannelIds: number[];
	commerceSyncEnabled?: boolean;
	dataSourceId: string;
	siteIds: number[];
}) {
	return request('/channels', {
		body: JSON.stringify({
			channelId,
			commerceSyncEnabled,
			dataSources: [
				{
					commerceChannelIds,
					dataSourceId,
					siteIds,
				},
			],
		}),
		method: 'PATCH',
	});
}

export function fetchChannels(queryString?: string) {
	return request(`/commerce-channels?${queryString}`, {
		method: 'GET',
	});
}

export function fetchSites(queryString?: string) {
	return request(`/sites?${queryString}`, {
		method: 'GET',
	});
}

export function updatePeopleData(
	syncAllAccounts: boolean,
	syncAllContacts: boolean,
	syncedAccountGroupIds: string[],
	syncedOrganizationIds: string[],
	syncedUserGroupIds: string[]
) {
	return request('/contacts/configuration', {
		body: JSON.stringify({
			syncAllAccounts,
			syncAllContacts,
			syncedAccountGroupIds,
			syncedOrganizationIds,
			syncedUserGroupIds,
		}),
		method: 'PUT',
	});
}
