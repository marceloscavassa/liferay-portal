/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {createContext} from 'react';

const DEFAULT_STATE = {
	createExperimentModal: {active: false},
	editExperimentModal: {active: false},
	errors: {},
	experiences: [],
	experiment: null,
	experimentHistory: [],
	reviewExperimentModal: {active: false},
	selectedExperienceId: null,
	variants: [],
};

export function getInitialState(firstState) {
	const {
		initialExperimentHistory,
		initialSegmentsExperiment,
		initialSegmentsVariants,
		initialSelectedSegmentsExperienceId,
		winnerSegmentsVariantId,
	} = firstState;

	const state = {
		experiment: initialSegmentsExperiment,
		experimentHistory: initialExperimentHistory || [],
		selectedExperienceId: initialSelectedSegmentsExperienceId,
		variants: initialSegmentsVariants.map((initialVariant) => {
			if (
				winnerSegmentsVariantId === initialVariant.segmentsExperienceId
			) {
				return {...initialVariant, winner: true};
			}

			return initialVariant;
		}),
		viewExperimentDetailsURL: initialSegmentsExperiment?.detailsURL || '',
	};

	return {
		...DEFAULT_STATE,
		...state,
	};
}

export const DispatchContext = createContext();
export const StateContext = createContext(DEFAULT_STATE);
