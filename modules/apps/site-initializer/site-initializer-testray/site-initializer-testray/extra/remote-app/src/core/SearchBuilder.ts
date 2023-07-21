/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {RendererFields} from '../components/Form/Renderer';
import {FilterVariables} from '../schema/filter';

type Filter = {
	[key: string]: string | number | string[] | number[];
};
type Key = string;
type Value = string | number | boolean;

export type Operators =
	| 'contains'
	| 'eq'
	| 'ge'
	| 'gt'
	| 'le'
	| 'lt'
	| 'ne'
	| 'startsWith';

export interface SearchBuilderConstructor {
	useURIEncode?: boolean;
}

/**
 * @description
 * Based in the following article https://help.liferay.com/hc/pt/articles/360031163631-Filter-Sort-and-Search
 */

export default class SearchBuilder {
	private lock: boolean = false;
	private query: string = '';
	private useURIEncode?: boolean = true;

	constructor({useURIEncode}: SearchBuilderConstructor = {}) {
		this.useURIEncode = useURIEncode;
	}

	/**
	 * @description Contains
	 * @example contains(title,'edmon')
	 */

	static contains(key: Key, value: Value) {
		return `contains(${key}, '${value}')`;
	}

	static eq(key: Key, value: Value) {
		return `${key} eq ${typeof value === 'boolean' ? value : `'${value}'`}`;
	}

	/**
	 * @description In [values]
	 * @example addressLocality in ('London', 'Recife')
	 */
	static in(key: Key, values: Value[]) {
		if (values) {
			const operator = `${key} in ({values})`;

			return operator
				.replace(
					'{values}',
					values.map((value) => `'${value}'`).join(',')
				)
				.trim();
		}

		return '';
	}

	/**
	 * @description Not equal
	 * @example addressLocality ne 'London'
	 */
	static ne(key: Key, value: Value) {
		return `${key} ne '${value}'`;
	}

	static gt(key: Key, value: Value) {
		return `${key} gt ${value}`;
	}

	static ge(key: Key, value: Value) {
		return `${key} ge ${value}`;
	}

	static lt(key: Key, value: Value) {
		return `${key} lt ${value}`;
	}

	static le(key: Key, value: Value) {
		return `${key} le ${value}`;
	}

	static group(type: 'CLOSE' | 'OPEN') {
		return type === 'OPEN' ? '(' : ')';
	}

	static startsWith(key: Key, value: Value) {
		return `${key} startsWith '${value}'`;
	}

	static removeEmptyFilter(filter: Filter) {
		const _filter: Filter = {};

		for (const key in filter) {
			const value = filter[key];

			if (!value) {
				continue;
			}

			_filter[key] = value;
		}

		return _filter;
	}

	static createFilter({
		appliedFilter,
		defaultFilter,
		filterSchema,
	}: FilterVariables) {
		const _filter = defaultFilter ? [defaultFilter] : [];

		for (const key in appliedFilter) {
			let searchCondition = '';
			let value = appliedFilter[key];

			if (!value) {
				continue;
			}
			const schema = filterSchema.fields.find(
				({name}) => key === name
			) as RendererFields;

			const removeQuoteMark =
				schema?.removeQuoteMark || schema?.type === 'number';

			const customOperator = schema?.operator;

			if (customOperator && SearchBuilder[customOperator]) {
				if (schema.type === 'date') {
					value = new Date(value).toISOString();
				}

				searchCondition = SearchBuilder[customOperator](
					key.replace('$', ''),
					value
				);
			}
			else {
				searchCondition = Array.isArray(value)
					? SearchBuilder.in(
							key,
							value.map((_value) =>
								typeof _value === 'object'
									? _value.value
									: _value
							)
					  )
					: SearchBuilder.eq(key, value);
			}

			_filter.push(
				removeQuoteMark
					? searchCondition.replaceAll(`'`, '')
					: searchCondition
			);
		}

		return _filter.join(' and ');
	}

	public and() {
		return this.setContext('and');
	}

	public build() {
		const query = this.query.trim();

		if (query.endsWith('or') || query.endsWith('and')) {
			return query.substring(0, query.length - 3);
		}

		this.lock = true;

		return this.useURIEncode ? encodeURIComponent(query) : query;
	}

	public contains(key: Key, value: Value) {
		return this.setContext(SearchBuilder.contains(key, value));
	}

	public eq(key: Key, value: Value) {
		return this.setContext(SearchBuilder.eq(key, value));
	}

	public in(key: Key, values: Value[]) {
		return this.setContext(SearchBuilder.in(key, values));
	}

	public inEqualNumbers(key: Key, values: Value[]) {
		if (!values.length) {
			return this;
		}

		this.setContext(SearchBuilder.group('OPEN'));

		const lastIndex = values.length - 1;

		values.map((value, index) => {
			this.setContext(SearchBuilder.eq(key, value).replaceAll("'", ''));

			if (lastIndex !== index) {
				this.or();
			}
		});

		return this.group('CLOSE');
	}

	public ne(key: Key, value: Value) {
		return this.setContext(SearchBuilder.ne(key, value));
	}

	public group(type: 'CLOSE' | 'OPEN') {
		return this.setContext(SearchBuilder.group(type));
	}

	private setContext(query: string) {
		if (!this.lock) {
			this.query += ` ${query}`;
		}

		return this;
	}

	public or() {
		return this.setContext('or');
	}
}
