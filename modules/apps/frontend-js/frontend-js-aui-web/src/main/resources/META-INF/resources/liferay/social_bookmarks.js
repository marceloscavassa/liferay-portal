/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Social Bookmarks Component.
 *
 * @deprecated As of Mueller (7.2.x)
 * @module liferay-social-bookmarks
 */

AUI.add(
	'liferay-social-bookmarks',
	(A) => {
		const NAME = 'social-bookmarks';

		const SHARE_WINDOW_HEIGHT = 436;

		const SHARE_WINDOW_WIDTH = 626;

		const WIN = A.getWin();

		/**
		 * A class to manage the different URLs provided by the registered
		 * social bookmarks plugins.
		 *
		 * @class Liferay.SocialBookmarks
		 * @extends Base
		 * @param {Object} config object literal specifying
		 * widget configuration properties.
		 * @constructor
		 */

		const SocialBookmarks = A.Component.create({

			/**
			 * A static property used to define the default attribute
			 * configuration for `SocialBookmarks`.
			 *
			 * @property ATTRS
			 * @type Object
			 * @static
			 */

			ATTRS: {

				/**
				 * The direct descendant of a widget's bounding box
				 * and houses its content.
				 *
				 * @attribute contentBox
				 * @type String | Node
				 */

				contentBox: {
					setter: A.one,
				},
			},

			/**
			 * Extend the AUI Base module.
			 *
			 * @property EXTENDS
			 * @type Object
			 * @static
			 */

			EXTENDS: A.Base,

			/**
			 * Static property provides a string to identify the class.
			 *
			 * @property NAME
			 * @type String
			 * @static
			 */

			NAME,

			prototype: {

				/**
				 * Construction lifecycle implementation executed during
				 * `SocialBookmarks` instantiation.
				 *
				 * @method initializer
				 * @protected
				 */

				initializer() {
					const instance = this;

					const contentBox = instance.get('contentBox');

					const dropdownMenu = contentBox.one('.dropdown-menu');

					const id = dropdownMenu.guid();

					if (!SocialBookmarks.registered[id]) {
						dropdownMenu.delegate(
							'click',
							(event) => {
								event.preventDefault();

								const shareWindowFeatures = [
									'left=' +
										WIN.get('innerWidth') / 2 -
										SHARE_WINDOW_WIDTH / 2,
									'height=' + SHARE_WINDOW_HEIGHT,
									'toolbar=0',
									'top=' +
										WIN.get('innerHeight') / 2 -
										SHARE_WINDOW_HEIGHT / 2,
									'status=0',
									'width=' + SHARE_WINDOW_WIDTH,
								];

								const url = event.currentTarget.attr('href');

								WIN.getDOM()
									.open(url, null, shareWindowFeatures.join())
									.focus();
							},
							'.social-bookmark'
						);

						SocialBookmarks.registered[id] = true;
					}
				},
			},

			registered: {},
		});

		Liferay.SocialBookmarks = SocialBookmarks;
	},
	'',
	{
		requires: ['aui-component', 'aui-node'],
	}
);
