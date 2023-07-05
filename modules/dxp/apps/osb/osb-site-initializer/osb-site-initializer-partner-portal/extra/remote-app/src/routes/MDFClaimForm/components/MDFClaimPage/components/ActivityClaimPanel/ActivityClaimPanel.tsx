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

import ClayIcon from '@clayui/icon';
import Link from '@clayui/link';
import ClayPanel from '@clayui/panel';
import {FormikContextType} from 'formik';
import {useCallback, useState} from 'react';

import PRMForm from '../../../../../../common/components/PRMForm';
import PRMFormik from '../../../../../../common/components/PRMFormik';
import {TypeActivityKey} from '../../../../../../common/enums/TypeActivityKey';
import LiferayFile from '../../../../../../common/interfaces/liferayFile';
import MDFClaim from '../../../../../../common/interfaces/mdfClaim';
import MDFClaimActivity from '../../../../../../common/interfaces/mdfClaimActivity';
import {Liferay} from '../../../../../../common/services/liferay';
import {Status} from '../../../../../../common/utils/constants/status';
import {getFileFromLiferayDocument} from '../../../../../../common/utils/dto/mdf-claim/getFileFromLiferayDocument';
import getIntlNumberFormat from '../../../../../../common/utils/getIntlNumberFormat';
import uploadDocument from '../../../../utils/uploadDocument';
import BudgetClaimPanel from './components/BudgetClaimPanel';
import ContentMarketingPopFields from './components/ContentMarketingPopFields';
import DigitalMarketingPopFields from './components/DigitalMarketingPopFields';
import EventPopFields from './components/EventPopFields';
import MiscellaneousMarketingPopFields from './components/MiscellaneousMarketingPopFields';
import PanelBody from './components/PanelBody';
import PanelHeader from './components/PanelHeader';
import useBudgetsAmount from './hooks/useBudgetsAmount';

interface IProps {
	activity: MDFClaimActivity;
	activityIndex: number;
	claimParentFolderId: number;
	overallCampaignDescription: string;
}

type TypeActivityComponent = {
	[key in string]?: JSX.Element;
};

const ActivityStatus = {
	ACTIVE: 'active',
	APPROVED: 'approved',
	CLAIMED: 'claimed',
	EXPIRED: 'expired',
	SUBMITTED: 'submitted',
	UNCLAIMED: 'unclaimed',
};

const activityStatusClassName = {
	[ActivityStatus.ACTIVE]: 'label label-tonal-info ml-2',
	[ActivityStatus.SUBMITTED]: 'label label-tonal-warning ml-2',
	[ActivityStatus.APPROVED]: 'label label-tonal-success ml-2',
	[ActivityStatus.EXPIRED]: 'label label-tonal-danger ml-2',
};

const activityClaimStatusClassName = {
	[ActivityStatus.CLAIMED]: 'ml-3 label label-tonal-info ml-2',
	[ActivityStatus.UNCLAIMED]: 'ml-3 label label-tonal-warning ml-2',
};

const ActivityClaimPanel = ({
	activity,
	activityIndex,
	claimParentFolderId,
	overallCampaignDescription,
	setFieldValue,
}: IProps & Pick<FormikContextType<MDFClaim>, 'setFieldValue'>) => {
	const [expanded, setExpanded] = useState<boolean>(!activity.selected);

	const siteURL = Liferay.ThemeDisplay.getLayoutRelativeControlPanelURL().split(
		'/'
	)[2];

	useBudgetsAmount(
		activity.budgets,
		useCallback(
			(amountValue) =>
				setFieldValue(
					`activities[${activityIndex}].totalCost`,
					amountValue
				),
			[activityIndex, setFieldValue]
		)
	);

	const claimableActivityByStatus =
		(activity.activityStatus?.key === Status.APPROVED.key ||
			activity.activityStatus?.key === Status.ACTIVE.key) &&
		!activity.claimed;

	const editableClaimActivityByStatus = activity.id && activity.selected;

	const displayActivityClaimCheckbox =
		claimableActivityByStatus || editableClaimActivityByStatus;

	const typeActivityComponents: TypeActivityComponent = {
		[TypeActivityKey.DIGITAL_MARKETING]: (
			<DigitalMarketingPopFields
				activity={activity}
				claimParentFolderId={claimParentFolderId}
				currentActivityIndex={activityIndex}
				setFieldValue={setFieldValue}
			/>
		),
		[TypeActivityKey.CONTENT_MARKETING]: (
			<ContentMarketingPopFields
				activity={activity}
				claimParentFolderId={claimParentFolderId}
				currentActivityIndex={activityIndex}
				setFieldValue={setFieldValue}
			/>
		),
		[TypeActivityKey.EVENT]: (
			<EventPopFields
				activity={activity}
				claimParentFolderId={claimParentFolderId}
				currentActivityIndex={activityIndex}
				setFieldValue={setFieldValue}
			/>
		),
		[TypeActivityKey.MISCELLANEOUS_MARKETING]: (
			<MiscellaneousMarketingPopFields
				activity={activity}
				claimParentFolderId={claimParentFolderId}
				currentActivityIndex={activityIndex}
				setFieldValue={setFieldValue}
			/>
		),
	};

	return (
		<>
			<ClayPanel
				className="bg-neutral-1 border-brand-primary-lighten-2 mb-4 text-neutral-7"
				displayType="secondary"
				expanded={activity.selected && expanded}
			>
				<PanelHeader
					expanded={activity.selected && expanded}
					onClick={() => {
						if (activity.selected) {
							setExpanded(
								(previousExpanded) => !previousExpanded
							);
						}
					}}
				>
					{displayActivityClaimCheckbox && (
						<div
							onClick={() =>
								activity.budgets?.map((_, index) =>
									setFieldValue(
										`activities[${activityIndex}].budgets[${index}].selected`,
										false
									)
								)
							}
						>
							<PRMFormik.Field
								component={PRMForm.Checkbox}
								name={`activities[${activityIndex}].selected`}
							/>
						</div>
					)}

					<div className="flex-grow-1 mx-3">
						<p className="mb-1 text-neutral-7 text-paragraph-sm">
							{overallCampaignDescription}
						</p>

						<h5 className="text-neutral-10">
							{`${activity.name} (${activity.r_actToMDFClmActs_c_activityId})`}
						</h5>

						<div className="align-items-center d-sm-flex mb-1 text-neutral-7 text-weight-semi-bold">
							<div className="mb-0">
								Claim Status:
								<div
									className={
										activityClaimStatusClassName[
											activity.claimed
												? 'claimed'
												: 'unclaimed'
										]
									}
								>
									{activity.claimed ? 'Claimed' : 'Unclaimed'}
								</div>
							</div>
						</div>

						<div className="align-items-center d-sm-flex mb-1 text-neutral-7 text-weight-semi-bold">
							<div className="mb-0">
								Request Status:
								<div
									className={
										activityStatusClassName[
											activity.activityStatus
												?.key as string
										]
									}
								>
									{activity.activityStatus?.name}
								</div>
							</div>
						</div>

						<div className="d-flex justify-content-end">
							<h5 className="mb-0 text-neutral-10">
								{getIntlNumberFormat(activity.currency).format(
									activity.totalCost
								)}
							</h5>
						</div>
					</div>
				</PanelHeader>

				<PanelBody expanded={activity.selected && expanded}>
					<ClayPanel.Body className="mx-2 px-5 py-5">
						{activity.budgets?.map((budget, index) => (
							<BudgetClaimPanel
								activityIndex={activityIndex}
								budget={budget}
								budgetIndex={index}
								claimParentFolderId={claimParentFolderId}
								key={`${budget.id}-${index}`}
								setFieldValue={setFieldValue}
							/>
						))}

						<div className="align-items-center d-flex justify-content-between">
							<PRMFormik.Field
								component={PRMForm.InputFile}
								description="You can downloaded the Excel Template, fill it out, and upload it back here"
								displayType="secondary"
								label="List of Qualified Leads"
								name={`activities[${activityIndex}].listOfQualifiedLeads`}
								onAccept={async (liferayFile: LiferayFile) => {
									const uploadedLiferayDocument = await uploadDocument(
										liferayFile,
										claimParentFolderId
									);

									if (uploadedLiferayDocument) {
										setFieldValue(
											`activities[${activityIndex}].listOfQualifiedLeads`,
											getFileFromLiferayDocument(
												uploadedLiferayDocument
											)
										);
									}
								}}
								outline
								required={
									activity.typeActivity.key ===
										TypeActivityKey.EVENT ||
									activity.typeActivity.key ===
										TypeActivityKey.MISCELLANEOUS_MARKETING
								}
								small
							/>

							<div className="bg-neutral-0 mb-3">
								<Link
									button
									displayType="secondary"
									download
									href={`${Liferay.ThemeDisplay.getPortalURL()}/documents/d/${siteURL}/qualified_leads_template-xlsx`}
									small
									target="_blank"
								>
									<span className="bg-neutral-0 inline-item inline-item-before">
										<ClayIcon symbol="download" />
									</span>
									Download template
								</Link>
							</div>
						</div>

						{
							typeActivityComponents[
								String(activity.typeActivity?.key) || ''
							]
						}
					</ClayPanel.Body>
				</PanelBody>
			</ClayPanel>
		</>
	);
};

export default ActivityClaimPanel;
