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

import PRMForm from '../../..';
import LiferayFile from '../../../../../interfaces/liferayFile';
import PRMFormik from '../../../../PRMFormik';
import PRMFormFieldProps from '../common/interfaces/prmFormFieldProps';

interface IProps {
	acceptedFilesExtensions: string;
	description: string;
	label: string;
	name: string;
	onAccept: (liferayFiles: LiferayFile[]) => void;
	value?: LiferayFile[] | Object[];
}

const InputMultipleFilesListing = ({
	acceptedFilesExtensions,
	description,
	label,
	name,
	onAccept,
	required,
	value,
}: PRMFormFieldProps & IProps) => (
	<>
		<PRMFormik.Field
			acceptedFilesExtensions={acceptedFilesExtensions}
			component={PRMForm.InputMultipleFiles}
			description={description}
			label={label}
			name={name}
			onAccept={onAccept}
			required={required}
			value={value}
		/>
	</>
);

export default InputMultipleFilesListing;
