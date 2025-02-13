/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.sync.internal.change.tracking.spi.listener;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.spi.listener.CTEventListener;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.sync.constants.DLSyncConstants;
import com.liferay.document.library.sync.model.DLSyncEvent;
import com.liferay.document.library.sync.service.DLSyncEventLocalService;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = CTEventListener.class)
public class SyncCTEventListener implements CTEventListener {

	@Override
	public void onAfterPublish(long ctCollectionId) {
		_processPublish(
			ctCollectionId,
			_classNameLocalService.getClassNameId(DLFolder.class),
			DLSyncConstants.TYPE_FOLDER);

		_processPublish(
			ctCollectionId,
			_classNameLocalService.getClassNameId(DLFileEntry.class),
			DLSyncConstants.TYPE_FILE);
	}

	private String _getEvent(CTEntry ctEntry) {
		if (ctEntry.getChangeType() == CTConstants.CT_CHANGE_TYPE_ADDITION) {
			return DLSyncConstants.EVENT_ADD;
		}

		if (ctEntry.getChangeType() == CTConstants.CT_CHANGE_TYPE_DELETION) {
			return DLSyncConstants.EVENT_DELETE;
		}

		return DLSyncConstants.EVENT_UPDATE;
	}

	private void _processPublish(
		long ctCollectionId, long classNameId, String type) {

		for (CTEntry ctEntry :
				_ctEntryLocalService.getCTEntries(
					ctCollectionId, classNameId)) {

			String event = _getEvent(ctEntry);

			DLSyncEvent dlSyncEvent = _dlSyncEventLocalService.addDLSyncEvent(
				event, type, ctEntry.getModelClassPK());

			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					Message message = new Message();

					message.setValues(
						HashMapBuilder.<String, Object>put(
							"event", event
						).put(
							"modifiedTime", dlSyncEvent.getModifiedTime()
						).put(
							"type", type
						).put(
							"typePK", dlSyncEvent.getTypePK()
						).build());

					_messageBus.sendMessage(
						DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
						message);

					return null;
				});
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CTEntryLocalService _ctEntryLocalService;

	@Reference
	private DLSyncEventLocalService _dlSyncEventLocalService;

	@Reference
	private MessageBus _messageBus;

}