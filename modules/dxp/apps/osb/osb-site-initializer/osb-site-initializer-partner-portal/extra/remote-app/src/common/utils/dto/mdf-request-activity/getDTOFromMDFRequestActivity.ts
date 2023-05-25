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

import MDFRequestActivityDTO from '../../../interfaces/dto/mdfRequestActivityDTO';
import LiferayAccountBrief from '../../../interfaces/liferayAccountBrief';
import MDFRequestActivity from '../../../interfaces/mdfRequestActivity';

export default function getDTOFromMDFRequestActivity(
	mdfRequestActivity: MDFRequestActivity,
	externalReferenceCode?: string,
	company?: LiferayAccountBrief,
	mdfRequestExternalReferenceCode?: string
): MDFRequestActivityDTO {
	const {activityDescription, ...newMDFRequestActivity} = mdfRequestActivity;

	delete activityDescription?.creator;
	delete activityDescription?.externalReferenceCode;
	delete activityDescription?.status;

	return {
		...activityDescription,
		activityStatus: mdfRequestActivity.activityStatus,
		currency: mdfRequestActivity.currency,
		...newMDFRequestActivity,
		externalReferenceCode,
		leadFollowUpStrategies: activityDescription?.leadFollowUpStrategies?.join(
			', '
		),
		mdfRequestExternalReferenceCode,
		r_accToActs_accountEntryId: company?.id,
		r_mdfReqToActs_c_mdfRequestERC: mdfRequestExternalReferenceCode,
	};
}
