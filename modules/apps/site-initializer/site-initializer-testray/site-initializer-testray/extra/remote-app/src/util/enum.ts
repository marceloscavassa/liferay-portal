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

export enum CONSENT_TYPE {
	FUNCTIONAL = 'CONSENT_TYPE_FUNCTIONAL',
	NECESSARY = 'CONSENT_TYPE_NECESSARY',
	PERFORMANCE = 'CONSENT_TYPE_PERFORMANCE',
	PERSONALIZATION = 'CONSENT_TYPE_PERSONALIZATION',
}

export const enum DISPATCH_TRIGGER_TYPE {
	AUTO_FILL = 'testray-autofill',
	CREATE_TASK_SUBTASK = 'testray-testflow',
	IMPORT_RESULTS = 'testray-import-results',
}
