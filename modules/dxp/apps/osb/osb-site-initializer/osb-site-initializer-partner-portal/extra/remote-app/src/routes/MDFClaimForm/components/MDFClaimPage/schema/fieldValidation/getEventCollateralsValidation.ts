/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {array, object} from 'yup';

import {validateDocument} from '../constants/validateDocument';

export const getEventCollateralsValidation = {
	eventCollaterals: array()
		.of(
			object()
				.test(
					'fileSize',
					validateDocument.fileSize.message,
					(eventCollateralsFile) => {
						if (eventCollateralsFile && !eventCollateralsFile.id) {
							return eventCollateralsFile
								? Math.ceil(eventCollateralsFile.size / 1000) <=
										validateDocument.fileSize.maxSize
								: false;
						}

						return true;
					}
				)
				.test(
					'fileType',
					validateDocument.document.message,
					(eventCollateralsFile) => {
						if (eventCollateralsFile && !eventCollateralsFile.id) {
							return eventCollateralsFile
								? validateDocument.document.types.includes(
										eventCollateralsFile.type
								  )
								: false;
						}

						return true;
					}
				)
		)
		.min(1)
		.required('Required'),
};
