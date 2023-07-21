/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const STATUS_COMPLETED = 2;
export const STATUS_DRAFT = 0;
export const STATUS_FINISHED_NO_WINNER = 4;
export const STATUS_FINISHED_WINNER = 3;
export const STATUS_PAUSED = 5;
export const STATUS_RUNNING = 1;
export const STATUS_SCHEDULED = 7;
export const STATUS_TERMINATED = 6;

const STATUS_TO_TYPE = {
	[STATUS_COMPLETED]: 'success',
	[STATUS_DRAFT]: 'secondary',
	[STATUS_FINISHED_NO_WINNER]: 'secondary',
	[STATUS_FINISHED_WINNER]: 'success',
	[STATUS_PAUSED]: 'warning',
	[STATUS_RUNNING]: 'primary',
	[STATUS_SCHEDULED]: 'warning',
	[STATUS_TERMINATED]: 'danger',
};

export function statusToLabelDisplayType(status) {
	return STATUS_TO_TYPE[status];
}
