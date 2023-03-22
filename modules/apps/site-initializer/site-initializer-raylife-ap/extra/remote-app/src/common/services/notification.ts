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

import {axios} from './liferay/api';

const headlessAPI = 'o/headless-user-notification/v1.0';

export function getUserNotification(
	pageSize: number,
	page: number,
	order?: string,
	sortBy?: string
) {
	if (sortBy) {
		return axios.get(
			`${headlessAPI}/my-user-notifications/?&pageSize=${pageSize}&page=${page}&sort=${sortBy}:${order}`
		);
	}

	return axios.get(
		`${headlessAPI}/my-user-notifications/?&pageSize=${pageSize}&page=${page}`
	);
}

export function putUserNotificationRead(userNotificationId: number) {
	return axios.put(
		`${headlessAPI}/user-notifications/${userNotificationId}/read`
	);
}
