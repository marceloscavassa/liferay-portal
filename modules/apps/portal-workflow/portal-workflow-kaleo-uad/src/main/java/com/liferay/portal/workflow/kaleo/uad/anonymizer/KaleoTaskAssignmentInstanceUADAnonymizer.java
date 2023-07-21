/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.uad.anonymizer;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = UADAnonymizer.class)
public class KaleoTaskAssignmentInstanceUADAnonymizer
	extends BaseKaleoTaskAssignmentInstanceUADAnonymizer {

	@Override
	public void autoAnonymize(
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
			long userId, User anonymousUser)
		throws PortalException {

		if (kaleoTaskAssignmentInstance.getUserId() == userId) {
			kaleoTaskAssignmentInstance.setUserId(anonymousUser.getUserId());
			kaleoTaskAssignmentInstance.setUserName(
				anonymousUser.getFullName());

			autoAnonymizeAssetEntry(kaleoTaskAssignmentInstance, anonymousUser);
		}

		if (kaleoTaskAssignmentInstance.getAssigneeClassPK() == userId) {
			kaleoTaskAssignmentInstance.setAssigneeClassPK(
				anonymousUser.getUserId());
		}

		kaleoTaskAssignmentInstanceLocalService.
			updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return new String[] {"assigneeClassPK", "userId"};
	}

}