/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLabel from '@clayui/label';
import ClayList from '@clayui/list';
import {openConfirmModal} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React from 'react';

import SegmentsExperimentContext from '../context.es';
import {SegmentsExperimentType} from '../types.es';
import {NO_EXPERIMENT_ILLUSTRATION_FILE_NAME} from '../util/contants.es';
import {statusToLabelDisplayType} from '../util/statuses.es';

const {useContext} = React;

function ExperimentsHistory({experimentHistory, onDeleteSegmentsExperiment}) {
	const {imagesPath} = useContext(SegmentsExperimentContext);

	const noHistoryIllustration = `${imagesPath}${NO_EXPERIMENT_ILLUSTRATION_FILE_NAME}`;

	return !experimentHistory.length ? (
		<div className="text-center">
			<img
				alt=""
				className="my-3"
				src={noHistoryIllustration}
				width="120px"
			/>

			<h4>
				{Liferay.Language.get(
					'there-is-no-test-history-for-experience'
				)}
			</h4>

			<p className="text-secondary">
				{Liferay.Language.get(
					'completed-or-terminated-tests-will-be-archived-here'
				)}
			</p>
		</div>
	) : (
		<ClayList>
			{experimentHistory.map((experiment) => {
				return (
					<ClayList.Item
						className="py-3"
						flex
						key={experiment.segmentsExperimentId}
					>
						<ClayList.ItemField expand>
							<ClayList.ItemTitle>
								{experiment.name}
							</ClayList.ItemTitle>

							<ClayList.ItemText className="text-secondary">
								{experiment.description}
							</ClayList.ItemText>

							<ClayList.ItemText>
								<ClayLabel
									displayType={statusToLabelDisplayType(
										experiment.status.value
									)}
								>
									{experiment.status.label}
								</ClayLabel>
							</ClayList.ItemText>
						</ClayList.ItemField>

						<ClayList.ItemField>
							<ClayList.QuickActionMenu>
								<ClayList.QuickActionMenu.Item
									onClick={() =>
										_handleDeleteExperiment(
											experiment.segmentsExperimentId
										)
									}
									symbol="times-circle"
								/>
							</ClayList.QuickActionMenu>
						</ClayList.ItemField>
					</ClayList.Item>
				);
			})}
		</ClayList>
	);

	function _handleDeleteExperiment(experimentId) {
		openConfirmModal({
			message: Liferay.Language.get(
				'are-you-sure-you-want-to-delete-this'
			),
			onConfirm: (isConfirmed) => {
				if (isConfirmed) {
					return onDeleteSegmentsExperiment(experimentId);
				}
			},
		});
	}
}

ExperimentsHistory.propTypes = {
	experimentHistory: PropTypes.arrayOf(SegmentsExperimentType).isRequired,
	onDeleteSegmentsExperiment: PropTypes.func.isRequired,
};

export default ExperimentsHistory;
