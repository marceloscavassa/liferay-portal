import { useLazyQuery, useMutation } from '@apollo/client';
import ClayForm from '@clayui/form';
import { useFormikContext } from 'formik';
import { useContext, useEffect, useMemo, useState } from 'react';
import BaseButton from '../../../../common/components/BaseButton';
import Input from '../../../../common/components/Input';
import Select from '../../../../common/components/Select';
import { LiferayTheme } from '../../../../common/services/liferay';
import {
	addSetupDXP,
	getSetupDXPInfo
} from '../../../../common/services/liferay/graphql/queries';
import { PARAMS_KEYS } from '../../../../common/services/liferay/search-params';
import { API_BASE_URL } from '../../../../common/utils';
import { isLowercaseAndNumbers } from '../../../../common/utils/validations.form';
import { AppContext } from '../../../../routes/onboarding/context';
import AdminInputs from '../../components/AdminInputs';
import Layout from '../../components/Layout';
import { actionTypes } from '../../context/reducer';
import { getInitialDxpAdmin, steps } from '../../utils/constants';

const SetupDXP = () => {
	const [{ project, userAccount }, dispatch] = useContext(AppContext);
	const { errors, setFieldValue, touched, values } = useFormikContext();
	const [baseButtonDisabled, setBaseButtonDisabled] = useState(true);

	const [fetchSetupDXPInfo, { data }] = useLazyQuery(getSetupDXPInfo);

	useEffect(() => {
		fetchSetupDXPInfo({
			variables: {
				accountSubscriptionsFilter: `(${userAccount.accountBriefs
					.map(
						(
							{ externalReferenceCode },
							index,
							{ length: totalAccountBriefs }
						) =>
							`accountKey eq '${externalReferenceCode}' ${index + 1 < totalAccountBriefs ? ' or ' : ' '
							}`
					)
					.join('')}) and (contains(name, 'HA DR') or contains(name, 'Std DR'))`,
				koroneikiAccountsFilter: `accountKey eq '${project.accountKey}'`
			}
		})
	// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [project, userAccount]);

	const dXPCDataCenterRegions = useMemo(() => (data?.c?.dXPCDataCenterRegions?.items.map(
		({ dxpcDataCenterRegionId, name }) => ({
			label: name,
			value: dxpcDataCenterRegionId,
		})
	) || []), [data])
	const hasDisasterRecovery = data?.c?.accountSubscriptions?.items?.length;
	const projectBrief = data?.c?.koroneikiAccounts?.items?.map(
		({ code, dxpVersion }) => ({
			code,
			dxpVersion,
		})
	)[0];

	useEffect(() => {
		if (dXPCDataCenterRegions.length) {
			setFieldValue('dxp.dataCenterRegion', dXPCDataCenterRegions[0]);
	
			if (hasDisasterRecovery) {
				setFieldValue('dxp.disasterDataCenterRegion', dXPCDataCenterRegions[0]);
			}
		}
	// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [dXPCDataCenterRegions, hasDisasterRecovery]);

	function handleSkip() {
		if (userAccount.accountBriefs.length === 1) {
			window.location.href = `${API_BASE_URL}${LiferayTheme.getLiferaySiteName()}/overview?${
				PARAMS_KEYS.PROJECT_APPLICATION_EXTERNAL_REFERENCE_CODE
			}=${userAccount.accountBriefs[0].externalReferenceCode}`;
		} else {
			window.location.href = `${API_BASE_URL}${LiferayTheme.getLiferaySiteName()}`;
		}
	}

	useEffect(() => {
		const hasTouched = !Object.keys(touched).length;
		const hasError = Object.keys(errors).length;

		setBaseButtonDisabled(hasTouched || hasError);
	}, [touched, errors]);

	const [sendEmailData, { called, error }] = useMutation(addSetupDXP);

	function sendEmail() {
		const dxp = values?.dxp;

		if (!called && dxp) {
			sendEmailData({
				variables: {
					SetupDXP: {
						admins: JSON.stringify(dxp.admins),
						dataCenterRegion: JSON.stringify(dxp.dataCenterRegion),
						disasterDataCenterRegion: JSON.stringify(
							dxp.disasterDataCenterRegion
						),
						projectId: JSON.stringify(dxp.projectId),
					},
					scopeKey: LiferayTheme.getScopeGroupId(),
				},
			});

			if (!error) {
				dispatch({
					payload: steps.success,
					type: actionTypes.CHANGE_STEP,
				});
			}
		}
	}

	return (
		<Layout
			className="pl-3 pt-1"
			footerProps={{
				leftButton: (
					<BaseButton borderless onClick={handleSkip}>
						Skip for now
					</BaseButton>
				),
				middleButton: (
					<BaseButton
						disabled={baseButtonDisabled}
						displayType="primary"
						onClick={() => sendEmail()}
					>
						Submit
					</BaseButton>
				),
			}}
			headerProps={{
				helper:
					'We’ll need a few details to finish building your DXP environment(s).',
				title: 'Set up DXP Cloud',
			}}
		>
			<div className="d-flex justify-content-between mb-2 pb-1 pl-3">
				<div className="flex-fill">
					<label>Project Name</label>

					<p className="text-neutral-3 text-paragraph-lg">
						<strong>
							{projectBrief ? projectBrief.code : ''}
						</strong>
					</p>
				</div>

				<div className="flex-fill">
					<label>Liferay DXP Version</label>

					<p className="text-neutral-3 text-paragraph-lg">
						<strong>
							{projectBrief ? projectBrief.dxpVersion : ''}
						</strong>
					</p>
				</div>
			</div>

			<ClayForm.Group className="mb-0">
				<ClayForm.Group className="mb-0 pb-1">
					<Input
						groupStyle="pb-1"
						helper={
							!errors?.dxp?.projectId &&
							'Lowercase letters and numbers only. Project IDs cannot be change.'
						}
						label="Project ID"
						name="dxp.projectId"
						placeholder="superbank1"
						required
						type="text"
						validations={[(value) => isLowercaseAndNumbers(value)]}
					/>

					<Select
						groupStyle="mb-0"
						label="Primary Data Center Region"
						name="dxp.dataCenterRegion"
						options={dXPCDataCenterRegions}
						required
					/>

					{!!hasDisasterRecovery && (
						<Select
							groupStyle="mb-0 pt-2"
							label="Disaster Recovery Data Center Region"
							name="dxp.disasterDataCenterRegion"
							options={dXPCDataCenterRegions}
							required
						/>
					)}
				</ClayForm.Group>

				{values.dxp.admins.map((admin, index) => (
					<AdminInputs admin={admin} id={index} key={index} />
				))}
			</ClayForm.Group>

			<BaseButton
				borderless
				className="ml-3 my-2 text-brand-primary"
				onClick={() => {
					setFieldValue('dxp.admins', [
						...values.dxp.admins,
						getInitialDxpAdmin(),
					]);
					setBaseButtonDisabled(true);
				}}
				prependIcon="plus"
				small
			>
				Add Another Admin
			</BaseButton>
		</Layout>
	);
};

export default SetupDXP;
