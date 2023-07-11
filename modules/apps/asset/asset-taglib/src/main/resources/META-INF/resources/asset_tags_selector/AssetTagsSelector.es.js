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

import ClayButton from '@clayui/button';
import {useResource} from '@clayui/data-provider';
import ClayForm, {ClayInput} from '@clayui/form';
import ClayMultiSelect from '@clayui/multi-select';
import {usePrevious} from '@liferay/frontend-js-react-web';
import {useId} from '@liferay/layout-content-page-editor-web';
import {fetch, sub} from 'frontend-js-web';
import {openItemSelectorModal} from 'item-selector-web';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

const noop = () => {};

function AssetTagsSelector({
	addCallback,
	formGroupClassName = '',
	groupIds = [],
	helpText = '',
	id,
	inputName,
	inputValue,
	label = Liferay.Language.get('tags'),
	onInputValueChange = noop,
	onSelectedItemsChange = noop,
	portletURL,
	removeCallback,
	selectedItems = [],
	showLabel = true,
	showSelectButton,
}) {
	const selectButtonRef = useRef();
	const tagsId = useId();

	const [networkStatus, setNetworkStatus] = useState(4);
	const {refetch, resource} = useResource({
		fetch,
		fetchOptions: {
			body: new URLSearchParams({
				cmd: JSON.stringify({
					'/assettag/search': {
						end: 20,
						groupIds,
						name: `%${inputValue === '*' ? '' : inputValue}%`,
						start: 0,
						tagProperties: '',
					},
				}),
				p_auth: Liferay.authToken,
			}),
			method: 'POST',
		},
		fetchPolicy: 'cache-first',
		link: `${
			window.location.origin
		}${themeDisplay.getPathContext()}/api/jsonws/invoke`,
		onNetworkStatusChange: setNetworkStatus,
	});

	const previousInputValue = usePrevious(inputValue);

	useEffect(() => {
		if (inputValue !== previousInputValue) {
			refetch();
		}

		// The intended `refetch` method has no reference stabilization, adding
		// this to deps will cause a loop and we only want to invoke the
		// `useEffect` when the value changes.
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [groupIds, inputValue, previousInputValue]);

	const callGlobalCallback = (callback, item) => {
		if (callback && typeof window[callback] === 'function') {
			window[callback](item);
		}
	};

	const handleInputBlur = () => {
		const filteredItems = resource && resource.map((tag) => tag.value);

		if (!filteredItems || !filteredItems.length) {
			if (inputValue) {
				if (!selectedItems.find((item) => item.label === inputValue)) {
					onSelectedItemsChange(
						selectedItems.concat({
							label: inputValue,
							value: inputValue,
						})
					);
				}
				onInputValueChange('');
			}
		}
	};

	const handleItemsChange = (items) => {
		const addedItems = items.filter(
			(item) =>
				!selectedItems.find(
					(selectedItem) => selectedItem.value === item.value
				)
		);

		const removedItems = selectedItems.filter(
			(selectedItem) =>
				!items.find((item) => item.value === selectedItem.value)
		);

		const current = [...selectedItems, ...addedItems].filter(
			(item) =>
				!removedItems.find(
					(removedItem) => removedItem.value === item.value
				)
		);

		onSelectedItemsChange(current);

		addedItems.forEach((item) => callGlobalCallback(addCallback, item));

		removedItems.forEach((item) =>
			callGlobalCallback(removeCallback, item)
		);
	};

	const handleSelectButtonClick = () => {
		openItemSelectorModal({
			buttonAddLabel: Liferay.Language.get('done'),
			getSelectedItemsOnly: false,
			multiple: true,
			onSelect: (dialogSelectedItems) => {
				if (!dialogSelectedItems?.length) {
					return;
				}

				let [newValues, removedValues] = dialogSelectedItems.reduce(
					([checked, unchecked], item) => {
						const selectedValue = JSON.parse(item.value);

						if (item.checked) {
							return [
								[
									...checked,
									{
										label: selectedValue.tagName,
										value: selectedValue.tagName,
									},
								],
								unchecked,
							];
						}
						else {
							return [
								checked,
								[
									...unchecked,
									{
										label: selectedValue.tagName,
										value: selectedValue.tagName,
									},
								],
							];
						}
					},
					[[], []]
				);

				newValues = newValues.filter(
					(newValue) =>
						!selectedItems.find(
							(selectedItem) =>
								selectedItem.label === newValue.label
						)
				);

				removedValues = selectedItems.filter((selectedItem) =>
					removedValues.find(
						(removedValue) =>
							removedValue.label === selectedItem.label
					)
				);

				const allSelectedItems = selectedItems
					.concat(newValues)
					.filter((item) => !removedValues.includes(item));

				onSelectedItemsChange(allSelectedItems);

				newValues.forEach((item) =>
					callGlobalCallback(addCallback, item)
				);

				removedValues.forEach((item) =>
					callGlobalCallback(removeCallback, item)
				);
			},
			params: {
				selectedTagNames: selectedItems
					.map((item) => item.value)
					.join(),
			},
			title: Liferay.Language.get('tags'),
			url: portletURL,
		});
	};

	return (
		<div id={id}>
			<ClayForm.Group
				aria-labelledby={tagsId}
				className={formGroupClassName}
				role="group"
			>
				<div
					className="border-0 mb-0 sheet-subtitle text-uppercase"
					id={tagsId}
				>
					{Liferay.Language.get('other-metadata')}
				</div>

				<label
					className={showLabel ? '' : 'sr-only'}
					htmlFor={inputName + '_MultiSelect'}
				>
					{label}
				</label>

				<ClayInput.Group style={{minHeight: '2.125rem'}}>
					<ClayInput.GroupItem>
						<ClayMultiSelect
							aria-describedby={
								helpText
									? `${inputName}_MultiSelectHelpText`
									: undefined
							}
							id={inputName + '_MultiSelect'}
							inputName={inputName}
							items={selectedItems}
							loadingState={networkStatus}
							onBlur={handleInputBlur}
							onChange={onInputValueChange}
							onItemsChange={handleItemsChange}
							sourceItems={
								resource
									? resource.map((tag) => {
											return {
												label: tag.text,
												value: tag.value,
											};
									  })
									: []
							}
							value={inputValue}
						/>
					</ClayInput.GroupItem>

					{showSelectButton && (
						<ClayInput.GroupItem shrink>
							<ClayButton
								aria-haspopup="dialog"
								aria-label={sub(
									Liferay.Language.get('select-x'),
									label
								)}
								displayType="secondary"
								onClick={handleSelectButtonClick}
								ref={selectButtonRef}
							>
								{Liferay.Language.get('select')}
							</ClayButton>
						</ClayInput.GroupItem>
					)}
				</ClayInput.Group>

				{helpText ? (
					<p
						className="m-0 mt-1 small text-secondary"
						id={`${inputName}_MultiSelectHelpText`}
					>
						{helpText}
					</p>
				) : null}
			</ClayForm.Group>
		</div>
	);
}

AssetTagsSelector.propTypes = {
	addCallback: PropTypes.string,
	formGroupClassName: PropTypes.string,
	groupIds: PropTypes.array,
	helpText: PropTypes.string,
	id: PropTypes.string,
	inputName: PropTypes.string,
	inputValue: PropTypes.string,
	label: PropTypes.string,
	onInputValueChange: PropTypes.func,
	onSelectedItemsChange: PropTypes.func,
	portletURL: PropTypes.string,
	removeCallback: PropTypes.string,
	selectedItems: PropTypes.array,
	showLabel: PropTypes.bool,
};

export default AssetTagsSelector;
