import ClayButton from '@clayui/button';
import ClayDropDown, {Align} from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import React from 'react';
import URLConstants from 'shared/util/url-constants';
import {close, modalTypes, open} from 'shared/actions/modals';
import {connect, ConnectedProps} from 'react-redux';
import {formatSubscriptions} from 'shared/util/subscriptions';
import {Map} from 'immutable';
import {Modal} from 'shared/types';
import {RootState} from 'shared/store';

const getDropdownItems = ({
	close,
	groupId,
	open,
	showModal
}: {
	close: Modal.close;
	groupId: string;
	open: Modal.open;
	showModal: boolean;
}): {href?: string; label: string; onClick?: () => void; target?: string}[] => [
	showModal
		? {
				label: Liferay.Language.get('report-an-issue'),
				onClick: () => {
					open(modalTypes.HELP_WIDGET_MODAL, {
						groupId,
						onClose: close
					});
				}
		  }
		: {
				href: URLConstants.TicketPageLink,
				label: Liferay.Language.get('report-an-issue'),
				onClick: () => {
					analytics.track('Clicked Paid Tier Ticket Link', {
						currentUrl: window.location.href
					});
				},
				target: '_blank'
		  },
	{
		href: URLConstants.DocumentationLink,
		label: Liferay.Language.get('help-center'),
		onClick: () => {
			analytics.track('Clicked Help Center Link', {
				currentUrl: window.location.href
			});
		},
		target: '_blank'
	}
];

const connector = connect(
	(store: RootState, {groupId}: {groupId: string}) => ({
		faroSubscriptionIMap: store.getIn(
			['projects', groupId, 'data', 'faroSubscription'],
			Map()
		)
	}),
	{close, open}
);

type PropsFromRedux = ConnectedProps<typeof connector>;

interface IHelpWidgetProps extends PropsFromRedux {
	groupId: string;
}

const HelpWidget: React.FC<IHelpWidgetProps> = ({
	close,
	faroSubscriptionIMap,
	groupId,
	open
}) => {
	const {plans} = formatSubscriptions();
	const basicTier = faroSubscriptionIMap.get('name') === plans.basic.name;

	return (
		<div className='help-widget-root'>
			<ClayDropDown
				alignmentPosition={Align.TopLeft}
				menuElementAttrs={{
					className: 'help-dropdown-root'
				}}
				trigger={
					<ClayButton
						aria-label={Liferay.Language.get('help')}
						borderless
						className='button-root help-button'
						displayType='info'
						size='sm'
					>
						<ClayIcon
							className='icon-root'
							symbol='ac-question-mark'
						/>
					</ClayButton>
				}
			>
				{getDropdownItems({
					close,
					groupId,
					open,
					showModal: basicTier
				}).map((item, index) => (
					<ClayDropDown.Item key={index}>
						{item.href ? (
							<a
								href={item.href}
								onClick={() => item.onClick}
								style={{color: 'gray', textDecoration: 'none'}}
								target={item.target}
							>
								{item.label}
							</a>
						) : (
							<span
								onClick={() => item.onClick}
								onKeyDown={() => {}}
								role='button'
								tabIndex={0}
							>
								{item.label}
							</span>
						)}
					</ClayDropDown.Item>
				))}
			</ClayDropDown>
		</div>
	);
};

export default connector(HelpWidget);
