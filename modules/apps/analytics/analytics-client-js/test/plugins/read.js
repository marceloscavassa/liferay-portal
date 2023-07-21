/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import fetchMock from 'fetch-mock';

import AnalyticsClient from '../../src/analytics';
import {
	getExpectedViewDuration,
	viewDurationByCharacters,
	viewDurationByWords,
} from '../../src/plugins/read';

const ENGLISH_TEXT =
	'But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain';
const LOGOGRAPHIC_TEXT =
	'范例文字，请取代此段落文字。此段落文字为范例文字内容，请务必取代。范例文字，请取代此段落文字。此段落文字为范例文字内容';

const PAGE_HEIGHT = 1000;
const SCROLL_HEIGHT = 2000;

const createMainContent = (isPhonological = true) => {
	const mainContent = document.createElement('div');
	mainContent.id = 'main-content';
	mainContent.innerText = isPhonological ? ENGLISH_TEXT : LOGOGRAPHIC_TEXT;
	document.body.appendChild(mainContent);

	return mainContent;
};

const createMetaTag = () => {
	const meta = document.createElement('meta');
	meta.name = 'data-analytics-readable-content';
	meta.content = 'true';
	document.getElementsByTagName('head')[0].appendChild(meta);

	return meta;
};

jest.useFakeTimers();

describe('Read Plugin', () => {
	let Analytics;

	beforeAll(createMetaTag);

	beforeEach(() => {

		// Force attaching DOM Content Loaded event

		Object.defineProperty(document, 'readyState', {
			value: 'loading',
		});

		// Avoid: "Error: Not implemented: window.scrollTo."

		window.scrollTo = (_x, y) => {
			window.pageYOffset = y;
		};

		Object.defineProperty(document.documentElement, 'clientHeight', {
			value: PAGE_HEIGHT,
		});

		Object.defineProperty(document.documentElement, 'scrollHeight', {
			value: SCROLL_HEIGHT,
			writable: true,
		});

		fetchMock.mock('*', () => 200);

		Analytics = AnalyticsClient.create();
	});

	afterEach(() => {
		Analytics.reset();
		Analytics.dispose();

		fetchMock.restore();
	});

	describe('readPage event', () => {
		it('is fired when reaches scroll and time', async () => {
			const blogElement = createMainContent();
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const domContentLoaded = new Event('DOMContentLoaded');
			document.dispatchEvent(domContentLoaded);

			window.scrollTo(0, SCROLL_HEIGHT);
			document.dispatchEvent(new Event('scroll'));

			await jest.advanceTimersByTime(expectedReadDuration);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'pageRead'
			);

			expect(events.length).toEqual(1);

			document.body.removeChild(blogElement);
		});

		it('is not fired when reaches scroll only', async () => {
			const blogElement = createMainContent();
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const domContentLoaded = new Event('DOMContentLoaded');
			document.dispatchEvent(domContentLoaded);

			window.scrollTo(0, SCROLL_HEIGHT);
			await document.dispatchEvent(new Event('scroll'));

			jest.advanceTimersByTime(expectedReadDuration / 2);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'pageRead'
			);

			expect(events.length).toEqual(0);

			document.body.removeChild(blogElement);
		});

		it('is not fired when reaches time only', async () => {
			const blogElement = createMainContent();
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const domContentLoaded = new Event('DOMContentLoaded');
			document.dispatchEvent(domContentLoaded);

			window.scrollTo(0, PAGE_HEIGHT / 2);
			await document.dispatchEvent(new Event('scroll'));

			jest.advanceTimersByTime(expectedReadDuration + 1000);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'pageRead'
			);

			expect(events.length).toEqual(0);

			document.body.removeChild(blogElement);
		});

		it('is fired when there is not scroll on the page and reaches time', async () => {

			// Redefining scrollHeight

			Object.defineProperty(document.documentElement, 'scrollHeight', {
				value: PAGE_HEIGHT,
			});

			// Restart Analytics

			Analytics.dispose();
			Analytics = AnalyticsClient.create();

			const blogElement = createMainContent();
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const domContentLoaded = new Event('DOMContentLoaded');
			document.dispatchEvent(domContentLoaded);

			await jest.advanceTimersByTime(expectedReadDuration);

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'pageRead'
			);

			expect(events.length).toEqual(1);

			document.body.removeChild(blogElement);
		});

		it('is not fired twice when reaches scroll 75 and 100', async () => {
			const blogElement = createMainContent();
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const domContentLoaded = new Event('DOMContentLoaded');
			document.dispatchEvent(domContentLoaded);

			window.scrollTo(0, SCROLL_HEIGHT * 0.5);
			document.dispatchEvent(new Event('scroll'));

			await jest.advanceTimersByTime(expectedReadDuration);

			window.scrollTo(0, SCROLL_HEIGHT);
			document.dispatchEvent(new Event('scroll'));

			const events = Analytics.getEvents().filter(
				({eventId}) => eventId === 'pageRead'
			);

			expect(events.length).toEqual(1);

			document.body.removeChild(blogElement);
		});

		it('set expectedViewDuration based on lang', () => {
			Object.defineProperty(document.documentElement, 'lang', {
				value: 'zh',
			});

			const blogElement = createMainContent(false);
			const expectedReadDuration = Math.trunc(
				getExpectedViewDuration(blogElement.innerText)
			);

			const durationByCharacters = viewDurationByCharacters(
				blogElement.innerText
			);
			const durationByWords = viewDurationByWords(blogElement.innerText);

			expect(expectedReadDuration).toEqual(durationByCharacters);
			expect(expectedReadDuration).not.toEqual(durationByWords);

			document.body.removeChild(blogElement);
		});
	});
});
