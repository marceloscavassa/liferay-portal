/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const webpack = require('webpack');

const components = require('./dev/components/index');
const {defineServerResponses} = require('./dev/fakeServerUtilities');

const outputPath = path.resolve(__dirname, './dev/public');

function getComponentPath(entry) {
	return path.join(__dirname, 'dev', 'components', entry);
}

const entry = [...components, {entry: 'Menu'}].reduce((comp, current) => {
	comp[current.entry] = getComponentPath(current.entry);

	return comp;
}, {});

module.exports = {
	devServer: {
		before(app) {
			defineServerResponses(app);
		},
		compress: false,
		contentBase: './dev/public',
		disableHostCheck: true,
		open: true,
		openPage: 'index.html',
		port: 8888,
		proxy: {
			'/image': {
				target: 'http://localhost:8080/',
			},
			'/o': {
				target: 'http://localhost:8080/',
			},
		},
		publicPath: '/',
	},
	devtool: 'inline-source-map',
	entry,
	mode: 'development',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.(js|jsx|ts|tsx)$/,
				use: [
					{
						loader: 'babel-loader',
					},
				],
			},
			{
				test: /\.(scss|css)$/,
				use: [
					{loader: 'style-loader'},
					{loader: 'css-loader'},
					{
						loader: 'sass-loader',
						options: {
							sassOptions: {
								importer: (url, _, done) => {
									if (url.includes('atlas-variables')) {
										done({
											file: path.resolve(
												__dirname,
												'../../../node_modules/@clayui/css/src/scss/atlas-variables.scss'
											),
										});
									}
									else {
										done({file: url});
									}
								},
							},
						},
					},
				],
			},
		],
	},
	output: {
		filename: '[name].js',
		path: outputPath,
	},
	plugins: [
		new webpack.optimize.ModuleConcatenationPlugin(),
		new HtmlWebpackPlugin({
			inject: false,
			template: path.resolve(__dirname, './dev/public/index.html'),
		}),
	],
	resolve: {
		alias: {
			'@liferay/frontend-data-set-web': path.resolve(
				__dirname,
				'../../../node_modules/@liferay/frontend-data-set-web/src/main/resources/META-INF/resources/index.js'
			),
			'@liferay/frontend-js-react-web': path.resolve(
				__dirname,
				'../../../node_modules/@liferay/frontend-js-react-web/src/main/resources/META-INF/resources/js/index.ts'
			),
			'@liferay/frontend-js-state-web': path.resolve(
				__dirname,
				'../../../node_modules/@liferay/frontend-js-state-web/src/main/resources/META-INF/resources/index.ts'
			),
			'frontend-js-web': path.resolve(
				__dirname,
				'../../../node_modules/frontend-js-web/src/main/resources/META-INF/resources/index.es.js'
			),
		},
		extensions: ['.js', '.jsx', '.ts', '.tsx'],
	},
};
