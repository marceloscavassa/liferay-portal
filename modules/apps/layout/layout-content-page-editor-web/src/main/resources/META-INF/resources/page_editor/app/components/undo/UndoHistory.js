/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayDropDown, {Align} from '@clayui/drop-down';
import {
	ReactPortal,
	useEventListener,
	useIsMounted,
} from '@liferay/frontend-js-react-web';
import React, {useState} from 'react';

import {SELECT_SEGMENTS_EXPERIENCE} from '../../../plugins/experience/actions';
import {UNDO_TYPES} from '../../config/constants/undoTypes';
import {config} from '../../config/index';
import {useDispatch, useSelector} from '../../contexts/StoreContext';
import multipleUndo from '../../thunks/multipleUndo';
import getSegmentsExperienceName from '../../utils/getSegmentsExperienceName';
import getActionLabel from './getActionLabel';

export default function UndoHistory() {
	const dispatch = useDispatch();
	const store = useSelector((state) => state);
	const redoHistory = useSelector((state) => state.redoHistory || []);
	const undoHistory = useSelector((state) => state.undoHistory || []);

	const isMounted = useIsMounted();

	const [active, setActive] = useState(false);
	const [loading, setLoading] = useState(false);

	const onHistoryItemClick = (event, numberOfActions, type) => {
		event.preventDefault();

		setLoading(true);

		dispatch(
			multipleUndo({
				numberOfActions,
				store,
				type,
			})
		).finally(() => {
			if (isMounted()) {
				setLoading(false);
			}
		});
	};

	return (
		<>
			<ClayDropDown
				active={active}
				alignmentPosition={Align.BottomRight}
				className="ml-2"
				menuElementAttrs={{
					className: 'page-editor__undo-history',
					containerProps: {
						className: 'cadmin',
					},
				}}
				onActiveChange={setActive}
				trigger={
					<ClayButtonWithIcon
						aria-label={Liferay.Language.get('history')}
						aria-pressed={active}
						disabled={!undoHistory.length && !redoHistory.length}
						displayType="secondary"
						size="sm"
						symbol="time"
						title={Liferay.Language.get('history')}
					/>
				}
			>
				<ClayDropDown.ItemList>
					<History
						actions={redoHistory}
						onHistoryItemClick={onHistoryItemClick}
						type={UNDO_TYPES.redo}
					/>

					<History
						actions={undoHistory}
						onHistoryItemClick={onHistoryItemClick}
						type={UNDO_TYPES.undo}
					/>

					<ClayDropDown.Divider />

					<ClayDropDown.Item
						disabled={!undoHistory.length}
						onClick={(event) =>
							onHistoryItemClick(
								event,
								undoHistory.length,
								UNDO_TYPES.undo
							)
						}
					>
						{Liferay.Language.get('undo-all')}
					</ClayDropDown.Item>
				</ClayDropDown.ItemList>
			</ClayDropDown>

			{loading && (
				<ReactPortal className="cadmin">
					<Overlay />
				</ReactPortal>
			)}
		</>
	);
}

const Overlay = () => {
	useEventListener(
		'keydown',
		(event) => {
			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();
		},
		true,
		window
	);

	return (
		<div
			className="page-editor__undo-history__overlay"
			onClickCapture={(event) => {
				event.preventDefault();
				event.stopPropagation();
			}}
		></div>
	);
};
const History = ({actions = [], type, onHistoryItemClick}) => {
	const store = useSelector((state) => state);

	const isSelectedAction = (index) => type === UNDO_TYPES.undo && index === 0;

	const actionList =
		type === UNDO_TYPES.undo ? actions : [...actions].reverse();

	return actionList.map((action, index) => (
		<ClayDropDown.Item
			disabled={isSelectedAction(index)}
			key={action.actionId}
			onClick={(event) => {
				const numberOfActions =
					type === UNDO_TYPES.undo
						? index
						: actionList.length - index;

				onHistoryItemClick(event, numberOfActions, type);
			}}
			symbolRight={isSelectedAction(index) ? 'check' : ''}
		>
			{getActionLabel(action, type, {
				availableSegmentsExperiences:
					store.availableSegmentsExperiences,
			})}

			{action.type !== SELECT_SEGMENTS_EXPERIENCE &&
				action.segmentsExperienceId !==
					config.defaultSegmentsExperienceId &&
				!config.singleSegmentsExperienceMode && (
					<span>
						{getSegmentsExperienceName(
							action.segmentsExperienceId,
							store.availableSegmentsExperiences
						)}
					</span>
				)}
		</ClayDropDown.Item>
	));
};
