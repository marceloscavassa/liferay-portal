/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import React from 'react';

import {normalizeRating, stripHTML} from '../utils/utils.es';
import ArticleBodyRenderer from './ArticleBodyRenderer.es';
import EditedTimestamp from './EditedTimestamp.es';
import Link from './Link.es';
import QuestionBadge from './QuestionsBadge.es';
import SectionLabel from './SectionLabel.es';
import TagList from './TagList.es';
import UserIcon from './UserIcon.es';

export default function QuestionRow({
	context,
	creatorId,
	currentSection,
	items,
	linkProps,
	question,
	rowSelected,
	showSectionLabel,
}) {
	const sectionTitle =
		currentSection || currentSection === '0'
			? currentSection
			: question.messageBoardSection &&
			  question.messageBoardSection.title;

	const creatorInformation = question.creator
		? {
				link: `/questions/all/creator/${question.creator.id}`,
				name: question.creator.name,
				portraitURL: question.creator.image,
				userId: String(question.creator.id),
		  }
		: {
				link: `/questions/${sectionTitle}`,
				name: '',
				portraitURL: '',
				userId: '0',
		  };

	const isRowSelected = question.friendlyUrlPath === rowSelected;

	return (
		<div
			className={classNames(
				'c-mt-4 c-p-3 position-relative question-row text-secondary',
				{'question-row-selected': isRowSelected}
			)}
		>
			<div className="align-items-center d-flex flex-wrap justify-content-between">
				<span>
					{showSectionLabel && (
						<SectionLabel section={question.messageBoardSection} />
					)}
				</span>

				<ul className="c-mb-0 d-flex flex-wrap list-unstyled stretched-link-layer">
					<li>
						<QuestionBadge
							symbol={
								normalizeRating(question.aggregateRating) < 0
									? 'caret-bottom'
									: 'caret-top'
							}
							tooltip={Liferay.Language.get('votes')}
							value={normalizeRating(question.aggregateRating)}
						/>
					</li>

					<li>
						<QuestionBadge
							symbol="view"
							tooltip={Liferay.Language.get('view-count')}
							value={question.viewCount}
						/>
					</li>

					<li data-testid="has-valid-answer-badge">
						<QuestionBadge
							className={
								question.hasValidAnswer
									? 'alert-success border-0'
									: ''
							}
							symbol={
								question.hasValidAnswer
									? 'check-circle-full'
									: 'message'
							}
							tooltip={Liferay.Language.get('number-of-replies')}
							value={question.numberOfMessageBoardMessages}
						/>
					</li>

					{items && !!items.length && (
						<li>
							<ClayDropDownWithItems
								className="c-py-1"
								items={items}
								trigger={
									<ClayButtonWithIcon
										displayType="unstyled"
										small
										symbol="ellipsis-v"
									/>
								}
							/>
						</li>
					)}
				</ul>
			</div>

			<Link
				className="questions-title stretched-link"
				to={`/questions/${sectionTitle}/${question.friendlyUrlPath}`}
				{...linkProps}
			>
				<h2
					className={classNames(
						'c-mb-0',
						'stretched-link-layer',
						'text-dark',
						{
							'question-seen':
								question.seen ||
								context?.questionsVisited?.includes(
									question.id
								),
						}
					)}
				>
					{question.headline}

					{question.status && question.status !== 'approved' && (
						<span className="c-ml-2">
							<ClayLabel displayType="info">
								{question.status}
							</ClayLabel>
						</span>
					)}

					{!!question.locked && (
						<span className="c-ml-2">
							<ClayIcon
								data-tooltip-align="top"
								symbol="lock"
								title={Liferay.Language.get(
									'this-question-is-closed-new-answers-and-comments-are-disabled'
								)}
							/>
						</span>
					)}
				</h2>
			</Link>

			<div className="c-mb-0 c-mt-3 question-row-article-body stretched-link-layer text-truncate">
				<ArticleBodyRenderer
					{...question}
					articleBody={stripHTML(question.articleBody)}
					compactMode={true}
				/>
			</div>

			<div className="align-items-sm-center align-items-start d-flex flex-column-reverse flex-sm-row justify-content-between">
				<div className="c-mt-3 c-mt-sm-0 stretched-link-layer">
					<Link
						className={classNames({
							'disabled-link': !!creatorId,
						})}
						to={creatorInformation.link}
					>
						<UserIcon
							fullName={creatorInformation.name}
							portraitURL={creatorInformation.portraitURL}
							size="sm"
							userId={creatorInformation.userId}
						/>

						<strong className="c-ml-2 text-dark">
							{creatorInformation.name ||
								Liferay.Language.get(
									'anonymous-user-configuration-name'
								)}
						</strong>
					</Link>

					<EditedTimestamp
						dateCreated={question.dateCreated}
						dateModified={question.dateModified}
						operationText={Liferay.Language.get('asked')}
					/>
				</div>

				<TagList sectionTitle={sectionTitle} tags={question.keywords} />
			</div>
		</div>
	);
}
