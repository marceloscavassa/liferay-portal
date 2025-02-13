/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.onedrive.web.internal.portlet.action;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.opener.model.DLOpenerFileEntryReference;
import com.liferay.document.library.opener.onedrive.web.internal.DLOpenerOneDriveManager;
import com.liferay.document.library.opener.onedrive.web.internal.constants.DLOpenerOneDriveConstants;
import com.liferay.document.library.opener.onedrive.web.internal.exception.GraphServicePortalException;
import com.liferay.document.library.opener.service.DLOpenerFileEntryReferenceLocalService;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"mvc.command.name=/document_library/one_drive_background_task_status"
	},
	service = MVCResourceCommand.class
)
public class OneDriveBackgroundTaskStatusMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		boolean complete = false;
		boolean error = false;

		long backgroundTaskId = ParamUtil.getLong(
			resourceRequest, "backgroundTaskId");

		BackgroundTaskStatus backgroundTaskStatus =
			_backgroundTaskStatusRegistry.getBackgroundTaskStatus(
				backgroundTaskId);

		long fileEntryId = ParamUtil.getLong(resourceRequest, "fileEntryId");

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

		DLOpenerFileEntryReference dlOpenerFileEntryReference =
			_dlOpenerFileEntryReferenceLocalService.
				fetchDLOpenerFileEntryReference(
					DLOpenerOneDriveConstants.ONE_DRIVE_REFERENCE_TYPE,
					fileEntry);

		if (backgroundTaskStatus == null) {
			if (dlOpenerFileEntryReference == null) {
				complete = false;
				error = true;
			}
			else {
				complete = true;
				error = false;
			}
		}
		else {
			complete = GetterUtil.getBoolean(
				backgroundTaskStatus.getAttribute("complete"));
			error = GetterUtil.getBoolean(
				backgroundTaskStatus.getAttribute("error"));
		}

		JSONObject jsonObject = JSONUtil.put(
			"complete", complete
		).put(
			"error", error
		);

		if (complete && (dlOpenerFileEntryReference != null)) {
			if (Validator.isNull(
					dlOpenerFileEntryReference.getReferenceKey())) {

				jsonObject.put("error", true);
			}
			else {
				try {
					String office365EditURL =
						_dlOpenerOneDriveManager.getOneDriveFileURL(
							_portal.getUserId(resourceRequest), fileEntry);

					jsonObject.put("office365EditURL", office365EditURL);
				}
				catch (GraphServicePortalException
							graphServicePortalException) {

					if (_log.isDebugEnabled()) {
						_log.debug(graphServicePortalException);
					}

					jsonObject.put("error", true);
				}
			}
		}

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OneDriveBackgroundTaskStatusMVCResourceCommand.class);

	@Reference
	private BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLOpenerFileEntryReferenceLocalService
		_dlOpenerFileEntryReferenceLocalService;

	@Reference
	private DLOpenerOneDriveManager _dlOpenerOneDriveManager;

	@Reference
	private Portal _portal;

}