/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {config} from '../config/index';
import serviceFetch from './serviceFetch';

/**
 * @typedef FragmentComment
 * @property {object} author
 * @property {string} body
 * @property {number} commentId
 * @property {string} dateDescription
 * @property {boolean} edited
 * @property {string} modifiedDescription
 * @property {boolean} resolved
 */

/**
 * @typedef FragmentEntryLink
 * @property {string} content
 * @property {string} fragmentEntryLinkId
 */

export default {

	/**
	 * Adds a new Fragment to the current layout
	 * @param {object} options
	 * @param {string} options.body Body of the comment
	 * @param {string} options.fragmentEntryLinkId Id of the Fragment
	 * @param {function} options.onNetworkStatus
	 * @param {number} [options.parentCommentId=0]
	 * @return {Promise<FragmentComment>} Created FragmentComment
	 */
	addComment({
		body,
		fragmentEntryLinkId,
		onNetworkStatus,
		parentCommentId = 0,
	}) {
		return serviceFetch(
			config.addFragmentEntryLinkCommentURL,
			{
				body: {
					body,
					fragmentEntryLinkId,
					parentCommentId,
				},
			},
			onNetworkStatus
		);
	},

	/**
	 * Adds a new Fragment Composition to the selected collection
	 * @param {object} options
	 * @param {string} options.description Fragment composition description
	 * @param {string} options.fragmentCollectionId Fragment collection ID
	 * @param {string} options.itemId Item ID to create a composition from
	 * @param {string} options.name Fragment composition name
	 * @param {function} options.onNetworkStatus
	 * @param {string} options.previewImageURL Fragment composition preview image url
	 * @param {boolean} options.saveInlineContent Save inline editable content
	 * @param {boolean} options.saveMappingConfiguration Save fields mapping configuration
	 * @param {string} options.segmentsExperienceId Current segmentsExperienceId
	 * @return {Promise<FragmentComposition>} Created FragmentComposition
	 */
	addFragmentComposition({
		description,
		fragmentCollectionId,
		itemId,
		name,
		onNetworkStatus,
		previewImageURL,
		saveInlineContent,
		saveMappingConfiguration,
		segmentsExperienceId,
	}) {
		return serviceFetch(
			config.addFragmentCompositionURL,
			{
				body: {
					description,
					fragmentCollectionId,
					itemId,
					name,
					previewImageURL,
					saveInlineContent,
					saveMappingConfiguration,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Adds a new Fragment to the current layout
	 * @param {object} options
	 * @param {string} options.fragmentEntryKey Key of the Fragment
	 * @param {string} options.groupId GroupId that wraps the Fragment
	 * @param {function} options.onNetworkStatus
	 * @param {string} options.parentItemId
	 * @param {number} options.position
	 * @param {string} options.segmentsExperienceId Current segmentsExperienceId
	 * @param {string} options.type Type of the Fragment to add
	 * @return {Promise<{ addedItemId: string, fragmentEntryLink: FragmentEntryLink, layoutData: object }>} Created FragmentEntryLink
	 */
	addFragmentEntryLink({
		fragmentEntryKey,
		groupId,
		onNetworkStatus,
		parentItemId,
		position,
		segmentsExperienceId,
	}) {
		return serviceFetch(
			config.addFragmentEntryLinkURL,
			{
				body: {
					fragmentEntryKey,
					groupId,
					parentItemId,
					position,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Adds a new Fragment Composition to the current layout
	 * @param {object} options
	 * @param {string} options.fragmentEntryKey Key of the Fragment composition
	 * @param {string} options.groupId GroupId that wraps the Fragment
	 * @param {function} options.onNetworkStatus
	 * @param {string} options.parentItemId
	 * @param {number} options.position
	 * @param {string} options.segmentsExperienceId Current segmentsExperienceId
	 * @param {string} options.type Type of the Fragment to add
	 * @return {Promise<{ fragmentEntryLinks: FragmentEntryLink[], layoutData: object }>} Created FragmentEntryLinks
	 */
	addFragmentEntryLinks({
		fragmentEntryKey,
		groupId,
		onNetworkStatus,
		parentItemId,
		position,
		segmentsExperienceId,
	}) {
		return serviceFetch(
			config.addFragmentEntryLinksURL,
			{
				body: {
					fragmentEntryKey,
					groupId,
					parentItemId,
					position,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Deletes a fragment comment
	 * @param {object} options
	 * @param {string} options.commentId Id of the comment
	 * @param {function} options.onNetworkStatus
	 * @return {Promise<void>}
	 */
	deleteComment({commentId, onNetworkStatus}) {
		return serviceFetch(
			config.deleteFragmentEntryLinkCommentURL,
			{
				body: {commentId},
			},
			onNetworkStatus
		);
	},

	/**
	 * Duplicates a fragmentEntryLink
	 * @param {object} options
	 * @param {string} options.itemId id of the item
	 * @param {function} options.onNetworkStatus
	 * @param {string} options.segmentsExperienceId Experience id
	 */
	duplicateItem({itemId, onNetworkStatus, segmentsExperienceId}) {
		return serviceFetch(
			config.duplicateItemURL,
			{
				body: {
					itemId,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Edits a fragment comment
	 * @param {object} options
	 * @param {string} options.body Body of the comment
	 * @param {string} options.commentId Id of the comment
	 * @param {function} options.onNetworkStatus
	 * @param {boolean} options.resolved Whether the comment should be marked as resolved or not
	 * @return {Promise<FragmentComment>} Created FragmentComment
	 */
	editComment({body, commentId, onNetworkStatus, resolved}) {
		return serviceFetch(
			config.editFragmentEntryLinkCommentURL,
			{
				body: {
					body,
					commentId,
					resolved,
				},
			},
			onNetworkStatus
		);
	},

	/**
	 * Render the content of a fragmentEntryLink
	 * @param {object} options
	 * @param {string} options.collectionItemIndex Index of the collection item
	 * @param {string} options.itemClassName Class name id of the collection item
	 * @param {string} options.itemClassPK Class PK of the collection item
	 * @param {string} options.fragmentEntryLinkId Id of the fragmentEntryLink
	 * @param {string} options.languageId Language id
	 * @param {function} options.onNetworkStatus
	 * @param {string} options.segmentsExperienceId Experience id
	 */
	renderFragmentEntryLinkContent({
		fragmentEntryLinkId,
		itemClassName,
		itemClassPK,
		itemExternalReferenceCode,
		languageId,
		onNetworkStatus,
		segmentsExperienceId,
	}) {
		const body = {
			fragmentEntryLinkId,
			itemClassName,
			languageId,
			segmentsExperienceId,
		};

		if (itemClassPK) {
			body.itemClassPK = itemClassPK;
		}

		if (itemExternalReferenceCode) {
			body.itemExternalReferenceCode = itemExternalReferenceCode;
		}

		return serviceFetch(
			config.renderFragmentEntryURL,
			{
				body,
			},
			onNetworkStatus
		);
	},

	/**
	 * Mark a fragment as highlighted if it wasn't, and unmark it if it was
	 * @param {object} options
	 * @param {string} options.fragmentEntryKey
	 * @param {boolean} options.highlighted
	 * @param {function} options.onNetworkStatus
	 */
	toggleFragmentHighlighted({
		fragmentEntryKey,
		groupId = '0',
		highlighted,
		onNetworkStatus,
	}) {
		return serviceFetch(
			config.updateFragmentsHighlightedConfigurationURL,
			{
				body: {
					fragmentEntryKey,
					groupId,
					highlighted,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Update configurationValues of the fragmentEntryLink with the given fragmentEntryLinkId
	 * @param {object} options
	 * @param {string} options.editableValues
	 * @param {string} options.fragmentEntryLinkId Id of the fragmentEntryLink
	 * @param {string} options.languageId Language id
	 * @param {function} options.onNetworkStatus
	 */
	updateConfigurationValues({
		editableValues,
		fragmentEntryLinkId,
		languageId,
		onNetworkStatus,
		segmentsExperienceId,
	}) {
		return serviceFetch(
			config.updateConfigurationValuesURL,
			{
				body: {
					editableValues: JSON.stringify(editableValues),
					fragmentEntryLinkId,
					languageId,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Update editableValues of the fragmentEntryLink with the given fragmentEntryLinkId
	 * @param {object} options
	 * @param {string} options.editableValues New editableValues
	 * @param {string} options.fragmentEntryLinkId Id of the fragmentEntryLink
	 * @param {function} options.onNetworkStatus
	 */
	updateEditableValues({
		editableValues,
		fragmentEntryLinkId,
		languageId,
		onNetworkStatus,
		segmentsExperienceId,
	}) {
		return serviceFetch(
			config.editFragmentEntryLinkURL,
			{
				body: {
					editableValues: JSON.stringify(editableValues),
					fragmentEntryLinkId,
					languageId,
					segmentsExperienceId,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},

	/**
	 * Update fragment and widgets sets sorting them according to user criteria
	 * @param {object} options
	 * @param {array} options.fragmentCollectionKeys
	 * @param {array} options.portletCategoryKeys
	 * @param {function} options.onNetworkStatus
	 */
	updateSetsOrder({
		fragmentCollectionKeys,
		onNetworkStatus,
		portletCategoryKeys,
	}) {
		return serviceFetch(
			config.updateFragmentPortletSetsSortURL,
			{
				body: {
					fragmentCollectionKeys:
						JSON.stringify(fragmentCollectionKeys) || null,
					portletCategoryKeys:
						JSON.stringify(portletCategoryKeys) || null,
				},
			},
			onNetworkStatus,
			{requestGenerateDraft: true}
		);
	},
};
