/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './UserRolesModal.soy';

import 'clay-modal';

import './RoleInputItem.es';

import './RoleListItem.es';

import '../css/main.scss';

class UserRolesModal extends Component {
	syncSelectedRoles() {
		const contentWrapper = this.element.querySelector(
			'.autocomplete-input__content'
		);
		this.element.querySelector('.autocomplete-input__box').focus();
		if (contentWrapper.scrollTo) {
			contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
		}
	}

	_handleCloseModal(event) {
		event.preventDefault();
		this._modalVisible = false;
	}

	syncQuery() {
		return this._filterRoles();
	}

	_handleFormSubmit(event) {
		event.preventDefault();
		let result = false;

		if (this.filteredRoles.length) {
			this._toggleItem(this.filteredRoles[0]);
			this.query = '';
			result = true;
		}

		return result;
	}

	_handleInputBox(event) {
		if (event.keyCode === 8 && !this.query.length) {
			this.selectedRoles = this.selectedRoles.slice(0, -1);
		}
		else {
			this.query = event.target.value;
		}

		return event;
	}

	_toggleItem(item) {
		if (!item.id) {
			this.query = '';
		}

		const roleAlreadyAdded = this.selectedRoles.reduce(
			(alreadyAdded, role) => alreadyAdded || role.id === item.id,
			false
		);

		this.selectedRoles = roleAlreadyAdded
			? this.selectedRoles.filter((role) => role.id !== item.id)
			: [...this.selectedRoles, item];

		return this.selectedRoles;
	}

	_filterRoles() {
		this.filteredRoles = this.roles.filter(
			(role) =>
				role.name.toLowerCase().indexOf(this.query.toLowerCase()) > -1
		);

		return this.filteredRoles;
	}

	_updateRoles() {
		this.emit('updateRoles', this.selectedRoles);
	}

	toggle() {
		this._modalVisible = !this._modalVisible;

		return this._modalVisible;
	}

	open() {
		this._modalVisible = true;

		return this._modalVisible;
	}

	close() {
		this._modalVisible = false;

		return this._modalVisible;
	}
}

Soy.register(UserRolesModal, template);

const ROLE_SCHEMA = Config.shapeOf({
	id: Config.oneOfType([Config.number(), Config.string()]).required(),
	name: Config.string().required(),
});

UserRolesModal.STATE = {
	_modalVisible: Config.bool().internal().value(false),
	filteredRoles: Config.array(ROLE_SCHEMA).value([]),
	query: Config.string().value(''),
	roles: Config.array(ROLE_SCHEMA).value([]),
	selectedRoles: Config.array(ROLE_SCHEMA).value([]),
	spritemap: Config.string(),
};

export {UserRolesModal};
export default UserRolesModal;
