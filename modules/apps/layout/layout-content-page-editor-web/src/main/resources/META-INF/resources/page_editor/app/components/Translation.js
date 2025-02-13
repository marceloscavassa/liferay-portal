/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import {sub} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useMemo} from 'react';

import {updateLanguageId} from '../actions/index';
import {BACKGROUND_IMAGE_FRAGMENT_ENTRY_PROCESSOR} from '../config/constants/backgroundImageFragmentEntryProcessor';
import {EDITABLE_FRAGMENT_ENTRY_PROCESSOR} from '../config/constants/editableFragmentEntryProcessor';
import {TRANSLATION_STATUS_TYPE} from '../config/constants/translationStatusType';

const getEditableValues = (fragmentEntryLinks) =>
	Object.values(fragmentEntryLinks)
		.filter(
			(fragmentEntryLink) =>
				!fragmentEntryLink.masterLayout &&
				!fragmentEntryLink.removed &&
				fragmentEntryLink.editableValues
		)
		.map((fragmentEntryLink) => [
			...Object.values(
				fragmentEntryLink.editableValues[
					EDITABLE_FRAGMENT_ENTRY_PROCESSOR
				] || {}
			),
			...Object.values(
				fragmentEntryLink.editableValues[
					BACKGROUND_IMAGE_FRAGMENT_ENTRY_PROCESSOR
				] || {}
			),
		])
		.reduce(
			(editableValuesA, editableValuesB) => [
				...editableValuesA,
				...editableValuesB,
			],
			[]
		);

const isTranslated = (editableValue, languageId) => editableValue[languageId];

const getTranslationStatus = ({
	editableValuesLength,
	isDefault,
	translatedValuesLength,
}) => {
	if (isDefault) {
		return TRANSLATION_STATUS_TYPE.default;
	}
	else if (translatedValuesLength === 0) {
		return TRANSLATION_STATUS_TYPE.untranslated;
	}
	else if (translatedValuesLength < editableValuesLength) {
		return TRANSLATION_STATUS_TYPE.translating;
	}
	else if (translatedValuesLength === editableValuesLength) {
		return TRANSLATION_STATUS_TYPE.translated;
	}
};

const TRANSLATION_STATUS_LANGUAGE = {
	[TRANSLATION_STATUS_TYPE.default]: Liferay.Language.get('default'),
	[TRANSLATION_STATUS_TYPE.translated]: Liferay.Language.get('translated'),
	[TRANSLATION_STATUS_TYPE.translating]: Liferay.Language.get('translating'),
	[TRANSLATION_STATUS_TYPE.untranslated]: Liferay.Language.get(
		'not-translated'
	),
};

const TRANSLATION_STATUS_DISPLAY_TYPE = {
	[TRANSLATION_STATUS_TYPE.default]: 'info',
	[TRANSLATION_STATUS_TYPE.translated]: 'success',
	[TRANSLATION_STATUS_TYPE.translating]: 'warning',
	[TRANSLATION_STATUS_TYPE.untranslated]: 'warning',
};

const TranslationItem = ({
	editableValuesLength,
	isDefault,
	language,
	languageIcon,
	languageId,
	languageLabel,
	onClick,
	translatedValuesLength,
}) => {
	const status = getTranslationStatus({
		editableValuesLength,
		isDefault,
		translatedValuesLength,
	});

	return (
		<ClayDropDown.Item onClick={onClick} symbolLeft={languageIcon}>
			{languageId === language.languageId ? (
				<strong>{languageLabel}</strong>
			) : (
				<span>{languageLabel}</span>
			)}

			<span className="dropdown-item-indicator-end w-auto">
				<ClayLabel
					displayType={TRANSLATION_STATUS_DISPLAY_TYPE[status]}
				>
					{TRANSLATION_STATUS_LANGUAGE[status]}

					{TRANSLATION_STATUS_TYPE[status] ===
						TRANSLATION_STATUS_TYPE.translating &&
						` ${translatedValuesLength}/${editableValuesLength}`}
				</ClayLabel>
			</span>
		</ClayDropDown.Item>
	);
};

export default function Translation({
	availableLanguages,
	defaultLanguageId,
	dispatch,
	fragmentEntryLinks,
	languageId,
	showNotTranslated = true,
}) {
	const editableValues = useMemo(
		() => getEditableValues(fragmentEntryLinks),
		[fragmentEntryLinks]
	);
	const languageValues = useMemo(() => {
		const availableLanguagesMut = {...availableLanguages};

		const defaultLanguage = availableLanguages[defaultLanguageId];

		delete availableLanguagesMut[defaultLanguageId];

		return Object.keys({
			[defaultLanguageId]: defaultLanguage,
			...availableLanguagesMut,
		})
			.filter(
				(languageId) =>
					showNotTranslated ||
					!!editableValues.filter(
						(editableValue) =>
							isTranslated(editableValue, languageId) ||
							languageId === defaultLanguageId
					).length
			)
			.map((languageId) => ({
				languageId,
				values: editableValues.filter((editableValue) =>
					isTranslated(editableValue, languageId)
				),
			}));
	}, [
		availableLanguages,
		defaultLanguageId,
		editableValues,
		showNotTranslated,
	]);

	const {languageIcon, w3cLanguageId} = availableLanguages[languageId];

	return (
		<ClayDropDown
			closeOnClick
			hasLeftSymbols
			hasRightSymbols
			menuElementAttrs={{
				className: 'page-editor__translation',
				containerProps: {
					className: 'cadmin',
				},
			}}
			trigger={
				<ClayButton
					className="btn-monospaced"
					displayType="secondary"
					size="sm"
				>
					<ClayIcon symbol={languageIcon} />

					<span className="sr-only">
						{sub(
							Liferay.Language.get(
								'select-a-language.-current-language-x'
							),
							w3cLanguageId
						)}
					</span>
				</ClayButton>
			}
		>
			<ClayDropDown.ItemList>
				{languageValues.map((language) => (
					<TranslationItem
						editableValuesLength={editableValues.length}
						isDefault={language.languageId === defaultLanguageId}
						key={language.languageId}
						language={language}
						languageIcon={
							availableLanguages[language.languageId].languageIcon
						}
						languageId={languageId}
						languageLabel={
							availableLanguages[language.languageId]
								.w3cLanguageId
						}
						onClick={() => {
							dispatch(
								updateLanguageId({
									languageId: language.languageId,
								})
							);
						}}
						translatedValuesLength={language.values.length}
					/>
				))}
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);
}

Translation.propTypes = {
	availableLanguages: PropTypes.objectOf(
		PropTypes.shape({
			languageIcon: PropTypes.string.isRequired,
			w3cLanguageId: PropTypes.string.isRequired,
		})
	).isRequired,
	defaultLanguageId: PropTypes.string.isRequired,
	dispatch: PropTypes.func.isRequired,
	fragmentEntryLinks: PropTypes.object.isRequired,
	languageId: PropTypes.string.isRequired,
};
