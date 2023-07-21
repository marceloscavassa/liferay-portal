/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayModal, {useModal} from '@clayui/modal';
import {ImageEditor} from 'item-selector-taglib';
import PropTypes from 'prop-types';
import React from 'react';

import updatePreviewImage from '../../../app/actions/updatePreviewImage';
import {config} from '../../../app/config/index';
import {useDispatch, useSelector} from '../../../app/contexts/StoreContext';
import selectLanguageId from '../../../app/selectors/selectLanguageId';
import FragmentService from '../../../app/services/FragmentService';
import ImageService from '../../../app/services/ImageService';

export function updateFragmentsPreviewImage({
	dispatch,
	fileEntryId,
	fragmentEntryLinks,
	languageId,
}) {
	const fragmentsToUpdate = Object.values(fragmentEntryLinks).filter(
		(fragmentEntryLink) => {
			const editableValues = Object.values(
				fragmentEntryLink.editableValues
			).reduce(
				(acc, editable) => [...acc, ...Object.entries(editable)],
				[]
			);

			return editableValues.some(
				([, value]) =>
					!fragmentEntryLink.removed &&
					typeof value === 'object' &&
					(value.classPK === fileEntryId ||
						value[languageId]?.classPK === fileEntryId ||
						value[config.defaultLanguageId]?.classPK ===
							fileEntryId)
			);
		}
	);

	const getFileEntryPromise = ImageService.getFileEntry({
		fileEntryId,
	});

	const getFragmentEntryLinkContentPromises = fragmentsToUpdate.map(
		({fragmentEntryLinkId}) =>
			FragmentService.renderFragmentEntryLinkContent({
				fragmentEntryLinkId,
			}).then(({content}) => ({content, fragmentEntryLinkId}))
	);

	return Promise.all([
		getFileEntryPromise,
		...getFragmentEntryLinkContentPromises,
	]).then(([{fileEntryURL}, ...contents]) => {
		dispatch(
			updatePreviewImage({
				contents,
				fileEntryId,
				previewURL: fileEntryURL,
			})
		);
	});
}

export default function ImageEditorModal({
	editImageURL,
	fileEntryId,
	fragmentEntryLinks,
	onCloseModal,
	previewURL,
}) {
	const dispatch = useDispatch();
	const languageId = useSelector(selectLanguageId);

	const {observer, onClose} = useModal({
		onClose: onCloseModal,
	});

	const onSave = (response) => {
		if (response?.success) {
			onClose();

			updateFragmentsPreviewImage({
				dispatch,
				fileEntryId,
				fragmentEntryLinks,
				languageId,
			});
		}
	};

	return (
		<ClayModal
			className="image-editor-modal"
			containerProps={{className: 'cadmin'}}
			observer={observer}
			size="full-screen"
		>
			<ClayModal.Header>
				{Liferay.Language.get('edit-image')}
			</ClayModal.Header>

			<ClayModal.Body>
				{previewURL ? (
					<ImageEditor
						imageId={fileEntryId}
						imageSrc={previewURL}
						onCancel={onClose}
						onSave={onSave}
						saveURL={editImageURL}
					/>
				) : (
					<ClayLoadingIndicator />
				)}
			</ClayModal.Body>
		</ClayModal>
	);
}

ImageEditorModal.propTypes = {
	editImageURL: PropTypes.string,
	fileEntryId: PropTypes.string,
	fragmentEntryLinks: PropTypes.object,
	onCloseModal: PropTypes.func,
	previewURLsRef: PropTypes.string,
};
