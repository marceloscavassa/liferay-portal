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

import ClayButton from '@clayui/button';
import DropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import {useState} from 'react';
import Skeleton from '../../../../../../../../../../../../../common/components/Skeleton';

const AccountSubscriptionGroupsDropdown = ({
	accountSubscriptionGroups,
	disabled,
	loading,
	onSelect,
	selectedIndex,
}) => {
	const [active, setActive] = useState(false);

	return (
		<DropDown
			active={active}
			closeOnClickOutside
			menuWidth="shrink"
			onActiveChange={setActive}
			trigger={
				<ClayButton
					borderless
					className="align-items-center d-flex px-2"
					data-testid="subscriptionDropDown"
					disabled={disabled}
					size="sm"
				>
					{loading ? (
						<Skeleton height={16} width={80} />
					) : (
						accountSubscriptionGroups[selectedIndex]?.name
					)}

					<span className="inline-item-after">
						<ClayIcon symbol="caret-bottom" />
					</span>
				</ClayButton>
			}
		>
			{accountSubscriptionGroups?.map(
				(accountSubscriptionGroup, index) => (
					<DropDown.Item
						className="pr-6"
						disabled={index === selectedIndex || disabled}
						key={`${index}-${index}`}
						onClick={() => {
							onSelect(index);
							setActive(false);
						}}
						symbolRight={index === selectedIndex && 'check'}
					>
						{accountSubscriptionGroup.name}
					</DropDown.Item>
				)
			)}
		</DropDown>
	);
};

export default AccountSubscriptionGroupsDropdown;
