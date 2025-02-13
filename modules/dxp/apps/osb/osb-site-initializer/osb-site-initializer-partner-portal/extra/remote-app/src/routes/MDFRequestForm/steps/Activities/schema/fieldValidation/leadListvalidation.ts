/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {array, string} from 'yup';

const leadListFieldsValidation = {
	detailsLeadFollowUp: string().when('leadGenerated', {
		is: (leadGenerated: string) => leadGenerated === 'true',
		then: (schema) =>
			schema
				.max(255, 'You have exceeded the character limit')
				.trim()
				.required('Required'),
	}),
	leadFollowUpStrategies: array().when('leadGenerated', {
		is: (leadGenerated: string) => leadGenerated === 'true',
		then: (schema) => schema.min(1, 'Required'),
	}),
	leadGenerated: string().required('Required'),
	targetOfLeads: string().when('leadGenerated', {
		is: (leadGenerated: string) => leadGenerated === 'true',
		then: (schema) =>
			schema
				.max(255, 'You have exceeded the character limit')
				.trim()
				.required('Required'),
	}),
};

export default leadListFieldsValidation;
