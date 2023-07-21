/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

import CriteriaSidebarSearchBar from '../../../../src/main/resources/META-INF/resources/js/components/criteria_sidebar/CriteriaSidebarSearchBar';

const SEARCH_BUTTON_TESTID = 'search-button';

const SEARCH_INPUT_TESTID = 'search-input';

class TestComponent extends Component {
	static propTypes = {
		initialValue: PropTypes.string,
	};

	static defaultProps = {
		initialValue: '',
	};

	constructor(props) {
		super(props);
		this.state = {
			value: props.initialValue,
		};
	}

	_handleChange = (value) => this.setState({value});

	render() {
		return (
			<CriteriaSidebarSearchBar
				onChange={this._handleChange}
				searchValue={this.state.value}
			/>
		);
	}
}

describe('CriteriaSidebarSearchBar', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = render(
			<CriteriaSidebarSearchBar onChange={jest.fn()} />
		);

		expect(asFragment()).toMatchSnapshot();
	});

	it('renders with a blank search input with no search value', () => {
		const {getByTestId} = render(
			<CriteriaSidebarSearchBar onChange={jest.fn()} />
		);

		const searchInput = getByTestId(SEARCH_INPUT_TESTID);

		expect(searchInput.value).toEqual('');
	});

	it('renders with the value in the search input', () => {
		const {getByTestId} = render(
			<CriteriaSidebarSearchBar onChange={jest.fn()} searchValue="test" />
		);

		const searchInput = getByTestId(SEARCH_INPUT_TESTID);

		expect(searchInput.value).toEqual('test');
	});

	it('renders a button with a times icon when an input is entered', () => {
		const {getByTestId} = render(
			<CriteriaSidebarSearchBar onChange={jest.fn()} searchValue="test" />
		);

		const searchButton = getByTestId(SEARCH_BUTTON_TESTID);

		expect(searchButton).toMatchSnapshot();
	});

	it('clears the input when the times icon is clicked', () => {
		const {getByTestId} = render(<TestComponent initialValue="test" />);

		const searchButton = getByTestId(SEARCH_BUTTON_TESTID);
		const searchInput = getByTestId(SEARCH_INPUT_TESTID);

		expect(searchInput.value).toEqual('test');

		fireEvent.click(searchButton);

		expect(searchInput.value).toEqual('');
	});
});
