/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @module liferay-dd-proxy
 */
AUI.add(
	'liferay-dd-proxy',
	(A) => {
		// eslint-disable-next-line @liferay/aui/no-get-body
		const body = A.getBody();

		const DDM = A.DD.DDM;

		A.mix(
			DDM,
			{
				_createFrame() {
					if (!DDM._proxy) {
						DDM._proxy = true;

						const proxyNode = A.Node.create('<div></div>');

						proxyNode.setStyles({
							display: 'none',
							left: '-999px',
							position: 'absolute',
							top: '-999px',
							zIndex: '999',
						});

						body.prepend(proxyNode);

						proxyNode.set('id', A.guid());

						proxyNode.addClass(DDM.CSS_PREFIX + '-proxy');

						DDM._proxy = proxyNode;
					}
				},

				_setFrame(drag) {
					let cursor = 'auto';

					let dragNode = drag.get('dragNode');
					const node = drag.get('node');

					const activeHandle = DDM.activeDrag.get('activeHandle');

					if (activeHandle) {
						cursor = activeHandle.getStyle('cursor');
					}

					if (cursor === 'auto') {
						cursor = DDM.get('dragCursor');
					}

					dragNode.setStyles({
						border: drag.proxy.get('borderStyle'),
						cursor,
						display: 'block',
						visibility: 'hidden',
					});

					if (drag.proxy.get('cloneNode')) {
						dragNode = drag.proxy.clone();
					}

					if (drag.proxy.get('resizeFrame')) {
						const size = node.invoke('getBoundingClientRect');

						dragNode.setStyles({
							height: Math.ceil(size.height),
							width: Math.ceil(size.width),
						});
					}

					if (drag.proxy.get('positionProxy')) {
						dragNode.setXY(drag.nodeXY);
					}

					dragNode.setStyle('visibility', 'visible');
				},
			},
			true
		);
	},
	'',
	{
		requires: ['dd-proxy'],
	}
);
