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
import {ClayDropDownWithItems} from '@clayui/drop-down';
import {ClaySelectWithOption} from '@clayui/form';
import ClayTable from '@clayui/table';
import classNames from 'classnames';
import {openSelectionModal} from 'frontend-js-web';
import React, {useMemo, useState} from 'react';

import {GlobalCETOptionsDropDown} from './GlobalCETOptionsDropDown';
import {GlobalCETOrderHelpIcon} from './GlobalCETOrderHelpIcon';

const DEFAULT_LOAD_TYPE_OPTION: ILoadTypeOptions = 'default';

const LOAD_TYPE_OPTIONS: Record<
	ILoadTypeOptions,
	React.OptionHTMLAttributes<HTMLOptionElement>
> = {
	async: {label: 'async', value: 'async'},
	default: {label: 'default', value: 'default'},
	defer: {label: 'defer', value: 'defer'},
};

const SCRIPT_LOCATION_LABELS: Record<IScriptLocationOptions, string> = {
	bottom: Liferay.Language.get('page-bottom'),
	head: Liferay.Language.get('page-head'),
};

const DEFAULT_SCRIPT_LOCATION_OPTION: IScriptLocationOptions = 'bottom';

export default function GlobalJSCETsConfiguration({
	globalJSCETSelectorURL,
	globalJSCETs: initialGlobalJSCETs,
	portletNamespace,
	selectGlobalJSCETsEventName,
}: IProps) {
	const fixedGlobalJSCETs = useMemo(
		() =>
			initialGlobalJSCETs.filter((globalJSCET) => globalJSCET.inherited),
		[initialGlobalJSCETs]
	);

	const [globalJSCETs, setGlobalJSCETs] = useState(() =>
		initialGlobalJSCETs.filter((globalJSCET) => !globalJSCET.inherited)
	);

	const allGlobalJSCETs = useMemo(() => {
		const globalJSCETsGroups = new Map<
			IScriptLocationOptions,
			Array<{globalJSCET: IGlobalJSCET; order: number}>
		>();

		let order = 1;

		[...fixedGlobalJSCETs, ...globalJSCETs].forEach((globalJSCET) => {
			const groupId =
				globalJSCET.scriptLocation || DEFAULT_SCRIPT_LOCATION_OPTION;

			if (!globalJSCETsGroups.has(groupId)) {
				globalJSCETsGroups.set(groupId, []);
			}

			const list = globalJSCETsGroups.get(groupId)!;

			list.push({globalJSCET, order});

			order = order + 1;
		});

		return globalJSCETsGroups;
	}, [fixedGlobalJSCETs, globalJSCETs]);

	const deleteGlobalJSCET = (deletedGlobalJSCET: IGlobalJSCET) => {
		setGlobalJSCETs((previousGlobalJSCETs) =>
			previousGlobalJSCETs.filter(
				(globalJSCET) =>
					globalJSCET.cetExternalReferenceCode !==
					deletedGlobalJSCET.cetExternalReferenceCode
			)
		);
	};

	const updateGlobalJSCET = <T extends keyof IGlobalJSCET>(
		globalJSCET: IGlobalJSCET,
		propName: T,
		value: IGlobalJSCET[T]
	) => {
		setGlobalJSCETs((previousGlobalJSCETs) =>
			previousGlobalJSCETs.map((oldGlobalJSCET) =>
				oldGlobalJSCET === globalJSCET
					? {...globalJSCET, [propName]: value}
					: oldGlobalJSCET
			)
		);
	};

	const addGlobalJSCET = (scriptLocation: IScriptLocationOptions) => {
		openSelectionModal<{value: string[]}>({
			multiple: true,
			onSelect(selectedItems) {
				if (!selectedItems.value) {
					return;
				}

				const items = selectedItems.value.map((selectedItem) => ({
					...JSON.parse(selectedItem),
					scriptLocation,
				}));

				setGlobalJSCETs((previousGlobalJSCETs) => [
					...previousGlobalJSCETs.filter(
						(previousGlobalJSCET) =>
							!items.some(
								(globalJSCET) =>
									globalJSCET.cetExternalReferenceCode ===
									previousGlobalJSCET.cetExternalReferenceCode
							)
					),
					...items,
				]);
			},
			selectEventName: selectGlobalJSCETsEventName,
			title: Liferay.Language.get('select-javascript-extensions'),
			url: globalJSCETSelectorURL,
		});
	};

	return (
		<div className="global-js-cets-configuration">
			{globalJSCETs.map(
				({cetExternalReferenceCode, loadType, scriptLocation}) => (
					<input
						key={cetExternalReferenceCode}
						name={`${portletNamespace}globalJSCETExternalReferenceCodes`}
						type="hidden"
						value={`${cetExternalReferenceCode}_${
							loadType || DEFAULT_LOAD_TYPE_OPTION
						}_${scriptLocation || DEFAULT_SCRIPT_LOCATION_OPTION}`}
					/>
				)
			)}

			<h3 className="sheet-subtitle">
				{Liferay.Language.get('javascript-extensions')}
			</h3>

			<AddExtensionButton
				addGlobalJSCET={addGlobalJSCET}
				portletNamespace={portletNamespace}
			/>

			{globalJSCETs.length ? (
				<ClayTable>
					<ClayTable.Head>
						<ClayTable.Row>
							<ClayTable.Cell headingCell>
								<GlobalCETOrderHelpIcon
									buttonId={`${portletNamespace}_GlobalJSCETsConfigurationOrderHelpIcon`}
									title={Liferay.Language.get(
										'loading-order'
									)}
								>
									{Liferay.Language.get(
										'numbers-indicate-in-which-order-extensions-are-loaded-extensions-inherited-from-master-will-always-be-loaded-in-first-place'
									)}

									{Liferay.Language.get(
										'also-head-insertions-will-be-loaded-before-body-bottom-ones'
									)}
								</GlobalCETOrderHelpIcon>
							</ClayTable.Cell>

							<ClayTable.Cell expanded headingCell>
								{Liferay.Language.get('name')}
							</ClayTable.Cell>

							<ClayTable.Cell expanded headingCell>
								{Liferay.Language.get('load-type')}
							</ClayTable.Cell>

							<ClayTable.Cell headingCell noWrap>
								{Liferay.Language.get('inherited')}
							</ClayTable.Cell>

							<ClayTable.Cell headingCell>
								<span className="sr-only">
									{Liferay.Language.get('options')}
								</span>
							</ClayTable.Cell>
						</ClayTable.Row>
					</ClayTable.Head>

					<ClayTable.Body>
						{[...allGlobalJSCETs.entries()].map(
							([scriptLocation, globalJSCETs]) => {
								return (
									<React.Fragment key={scriptLocation}>
										<ClayTable.Row>
											<ClayTable.Cell
												className="list-group-header-title py-2"
												colSpan={5}
											>
												{Liferay.Util.sub(
													Liferay.Language.get(
														'x-js-extensions'
													),
													SCRIPT_LOCATION_LABELS[
														scriptLocation
													]
												)}
											</ClayTable.Cell>
										</ClayTable.Row>

										{globalJSCETs.map(
											({globalJSCET, order}) => (
												<ExtensionRow
													deleteGlobalJSCET={
														deleteGlobalJSCET
													}
													globalJSCET={globalJSCET}
													key={
														globalJSCET.cetExternalReferenceCode
													}
													order={order}
													portletNamespace={
														portletNamespace
													}
													updateGlobalJSCET={
														updateGlobalJSCET
													}
												/>
											)
										)}
									</React.Fragment>
								);
							}
						)}
					</ClayTable.Body>
				</ClayTable>
			) : (
				<p className="text-secondary">
					{Liferay.Language.get(
						'no-javascript-extensions-were-loaded'
					)}
				</p>
			)}
		</div>
	);
}

interface IAddExtensionButton {
	addGlobalJSCET: (scriptLocation: IScriptLocationOptions) => unknown;
	portletNamespace: string;
}

function AddExtensionButton({
	addGlobalJSCET,
	portletNamespace,
}: IAddExtensionButton) {
	const [active, setActive] = useState(false);
	const dropdownTriggerId = `${portletNamespace}_GlobalJSCETsConfigurationAddExtensionButton`;

	return (
		<ClayDropDownWithItems
			active={active}
			items={[...Object.entries(SCRIPT_LOCATION_LABELS)].map(
				([scriptLocation, label]) => ({
					label: Liferay.Util.sub(
						Liferay.Language.get('in-x'),
						label
					),
					onClick: () =>
						addGlobalJSCET(
							scriptLocation as IScriptLocationOptions
						),
					role: 'menuitem',
				})
			)}
			menuElementAttrs={{
				'aria-labelledby': dropdownTriggerId,
				'role': 'menu',
			}}
			onActiveChange={setActive}
			trigger={
				<ClayButton
					aria-expanded={active}
					aria-haspopup="true"
					className="mb-3"
					displayType="secondary"
					small
					type="button"
				>
					{Liferay.Language.get('add-javascript-extensions')}
				</ClayButton>
			}
		/>
	);
}

interface IExtensionRowProps {
	deleteGlobalJSCET: (globalJSCET: IGlobalJSCET) => unknown;
	globalJSCET: IGlobalJSCET;
	order: number;
	portletNamespace: string;
	updateGlobalJSCET: <T extends keyof IGlobalJSCET>(
		globalJSCET: IGlobalJSCET,
		propName: T,
		value: IGlobalJSCET[T]
	) => unknown;
}

function ExtensionRow({
	deleteGlobalJSCET,
	globalJSCET,
	order,
	portletNamespace,
	updateGlobalJSCET,
}: IExtensionRowProps) {
	const disabled = globalJSCET.inherited;
	const dropdownTriggerId = `${portletNamespace}_GlobalJSCETsConfigurationOptionsButton_${globalJSCET.cetExternalReferenceCode}`;

	const dropdownItems = [
		{
			label: Liferay.Language.get('delete'),
			onClick: () => deleteGlobalJSCET(globalJSCET),
			symbolLeft: 'trash',
		},
	];

	return (
		<ClayTable.Row
			className={classNames({disabled})}
			key={globalJSCET.cetExternalReferenceCode}
		>
			<ClayTable.Cell>{order}</ClayTable.Cell>

			<ClayTable.Cell expanded headingTitle>
				{globalJSCET.name}
			</ClayTable.Cell>

			<ClayTable.Cell expanded>
				<ClaySelectWithOption
					defaultValue={
						globalJSCET.loadType || DEFAULT_LOAD_TYPE_OPTION
					}
					onChange={(event) =>
						updateGlobalJSCET(
							globalJSCET,
							'loadType',
							event.target.value as ILoadTypeOptions
						)
					}
					options={Object.values(LOAD_TYPE_OPTIONS)}
					sizing="sm"
				/>
			</ClayTable.Cell>

			<ClayTable.Cell noWrap>{globalJSCET.inheritedLabel}</ClayTable.Cell>

			<ClayTable.Cell>
				{disabled ? null : (
					<GlobalCETOptionsDropDown
						dropdownItems={dropdownItems}
						dropdownTriggerId={dropdownTriggerId}
					/>
				)}
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

type ILoadTypeOptions = 'default' | 'async' | 'defer';
type IScriptLocationOptions = 'head' | 'bottom';

interface IGlobalJSCET {
	cetExternalReferenceCode: string;
	inherited: boolean;
	inheritedLabel: string;
	loadType?: ILoadTypeOptions;
	name: string;
	scriptLocation?: IScriptLocationOptions;
}

interface IProps {
	globalJSCETSelectorURL: string;
	globalJSCETs: IGlobalJSCET[];
	portletNamespace: string;
	selectGlobalJSCETsEventName: string;
}
