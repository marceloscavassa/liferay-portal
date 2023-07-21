/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTabs from '@clayui/tabs';
import {
	API,
	SidePanelForm,
	SidebarCategory,
	openToast,
	saveAndReload,
} from '@liferay/object-js-components-web';
import React, {useEffect, useState} from 'react';

import {defaultLanguageId} from '../../utils/constants';
import {BasicInfo} from './BasicInfo';
import {Conditions} from './Conditions';
import {
	ObjectValidationErrors,
	useObjectValidationForm,
} from './useObjectValidationForm';

interface EditObjectValidationProps {
	objectValidationRule: ObjectValidation;
	objectValidationRuleElements: SidebarCategory[];
	readOnly: boolean;
}

interface ErrorDetails extends Error {
	detail?: string;
}

const TABS = [
	{
		Component: BasicInfo,
		label: Liferay.Language.get('basic-info'),
	},
	{
		Component: Conditions,
		label: Liferay.Language.get('conditions'),
	},
];

export default function EditObjectValidation({
	objectValidationRule: initialValues,
	objectValidationRuleElements,
	readOnly,
}: EditObjectValidationProps) {
	const [activeIndex, setActiveIndex] = useState<number>(0);
	const [errorMessage, setErrorMessage] = useState<ObjectValidationErrors>(
		{}
	);

	const onSubmit = async (objectValidation: ObjectValidation) => {
		delete objectValidation.lineCount;

		try {
			await API.save(
				`/o/object-admin/v1.0/object-validation-rules/${objectValidation.id}`,
				objectValidation
			);
			saveAndReload();
			openToast({
				message: Liferay.Language.get(
					'the-object-validation-was-updated-successfully'
				),
			});
		}
		catch (error) {
			const {detail, message} = error as ErrorDetails;
			const {fieldName, message: detailMessage} = JSON.parse(
				detail as string
			) as {
				fieldName: keyof ObjectValidationErrors;
				message: string;
			};

			setErrorMessage({[fieldName]: detailMessage});

			openToast({message, type: 'danger'});
		}
	};

	const {
		errors,
		handleChange,
		handleSubmit,
		setValues,
		values,
	} = useObjectValidationForm({initialValues, onSubmit});

	useEffect(() => {
		if (initialValues.script === 'script_placeholder') {
			initialValues.script = '';
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	useEffect(() => {
		if (Object.keys(errors).length) {
			openToast({
				message: Liferay.Language.get(
					'please-fill-out-all-required-fields'
				),
				type: 'danger',
			});
		}
	}, [errors]);

	return (
		<SidePanelForm
			onSubmit={handleSubmit}
			title={initialValues.name?.[defaultLanguageId]!}
		>
			<ClayTabs className="side-panel-iframe__tabs">
				{TABS.map(({label}, index) => (
					<ClayTabs.Item
						active={activeIndex === index}
						key={index}
						onClick={() => setActiveIndex(index)}
					>
						{label}
					</ClayTabs.Item>
				))}
			</ClayTabs>

			<ClayTabs.Content activeIndex={activeIndex} fade>
				{TABS.map(({Component, label}, index) =>
					activeIndex === index ? (
						<ClayTabs.TabPane key={index}>
							<Component
								componentLabel={label}
								disabled={readOnly}
								errors={
									Object.keys(errors).length !== 0
										? errors
										: errorMessage
								}
								handleChange={handleChange}
								objectValidationRuleElements={
									objectValidationRuleElements
								}
								setValues={setValues}
								values={values}
							/>
						</ClayTabs.TabPane>
					) : (
						<React.Fragment key={index} />
					)
				)}
			</ClayTabs.Content>
		</SidePanelForm>
	);
}
