/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const PartnershipIcon = (props) => (
	<svg
		fill="none"
		height="24"
		viewBox="0 0 24 24"
		width="24"
		xmlns="http://www.w3.org/2000/svg"
		{...props}
	>
		<rect fill="white" height="24" width="24" />

		<path
			clipRule="evenodd"
			d="M0 1.778C0 0.796 0.796 0 1.778 0H22.222C23.204 0 24 0.796 24 1.778V22.222C24 23.204 23.204 24 22.222 24H1.778C0.796038 24 0 23.204 0 22.222V1.778ZM3.556 4C3.556 3.755 3.755 3.556 4 3.556H6.667C6.912 3.556 7.111 3.755 7.111 4V6.667C7.111 6.78476 7.06422 6.89769 6.98096 6.98096C6.89769 7.06422 6.78476 7.111 6.667 7.111H4C3.75479 7.111 3.556 6.91221 3.556 6.667V4ZM8.444 3.556C8.19879 3.556 8 3.75479 8 4V6.667C8 6.912 8.199 7.111 8.444 7.111H11.111C11.2289 7.11127 11.3421 7.0646 11.4256 6.98131C11.5091 6.89801 11.556 6.78493 11.556 6.667V4C11.556 3.88207 11.5091 3.76899 11.4256 3.68569C11.3421 3.6024 11.2289 3.55573 11.111 3.556H8.444ZM12.444 4C12.444 3.755 12.644 3.556 12.889 3.556H15.556C15.801 3.556 16 3.755 16 4V6.667C16 6.78476 15.9532 6.89769 15.87 6.98096C15.7867 7.06422 15.6738 7.111 15.556 7.111H12.889C12.7711 7.11127 12.6579 7.0646 12.5744 6.98131C12.4909 6.89801 12.444 6.78493 12.444 6.667V4ZM4 8C3.88224 8 3.76931 8.04678 3.68604 8.13004C3.60278 8.21331 3.556 8.32624 3.556 8.444V11.111C3.556 11.357 3.755 11.556 4 11.556H6.667C6.78493 11.556 6.89802 11.5091 6.98131 11.4256C7.0646 11.3421 7.11127 11.2289 7.111 11.111V8.444C7.111 8.32624 7.06422 8.21331 6.98096 8.13004C6.89769 8.04678 6.78476 8 6.667 8H4ZM8 8.444C8 8.2 8.199 8 8.444 8H11.111C11.357 8 11.556 8.199 11.556 8.444V11.111C11.5563 11.2291 11.5095 11.3424 11.426 11.426C11.3424 11.5095 11.2291 11.5563 11.111 11.556H8.444C8.3259 11.556 8.21266 11.5089 8.12934 11.4252C8.04601 11.3415 7.99947 11.2281 8 11.11V8.444ZM17.333 8C17.2152 8 17.1023 8.04678 17.019 8.13004C16.9358 8.21331 16.889 8.32624 16.889 8.444V11.111C16.889 11.357 17.088 11.556 17.333 11.556H20C20.1179 11.556 20.231 11.5091 20.3143 11.4256C20.3976 11.3421 20.4443 11.2289 20.444 11.111V8.444C20.444 8.32624 20.3972 8.21331 20.314 8.13004C20.2307 8.04678 20.1178 8 20 8H17.333ZM3.556 12.889C3.556 12.643 3.755 12.444 4 12.444H6.667C6.912 12.444 7.111 12.644 7.111 12.889V15.556C7.111 15.6738 7.06422 15.7867 6.98096 15.87C6.89769 15.9532 6.78476 16 6.667 16H4C3.75479 16 3.556 15.8012 3.556 15.556V12.889ZM12.889 12.444C12.7709 12.4437 12.6576 12.4905 12.574 12.574C12.4905 12.6576 12.4437 12.7709 12.444 12.889V15.556C12.444 15.801 12.644 16 12.889 16H15.556C15.8012 16 16 15.8012 16 15.556V12.889C16.0003 12.7711 15.9536 12.6579 15.8703 12.5744C15.787 12.4909 15.6739 12.444 15.556 12.444H12.889ZM16.889 12.889C16.889 12.643 17.088 12.444 17.333 12.444H20C20.245 12.444 20.444 12.644 20.444 12.889V15.556C20.444 15.8012 20.2452 16 20 16H17.333C17.0878 16 16.889 15.8012 16.889 15.556V12.889ZM8.444 16.889C8.19879 16.889 8 17.0878 8 17.333V20C8 20.245 8.199 20.444 8.444 20.444H11.111C11.2289 20.4443 11.3421 20.3976 11.4256 20.3143C11.5091 20.231 11.556 20.1179 11.556 20V17.333C11.556 17.2151 11.5091 17.102 11.4256 17.0187C11.3421 16.9354 11.2289 16.8887 11.111 16.889H8.444ZM12.444 17.333C12.444 17.088 12.644 16.889 12.889 16.889H15.556C15.801 16.889 16 17.088 16 17.333V20C16 20.2452 15.8012 20.444 15.556 20.444H12.889C12.7711 20.4443 12.6579 20.3976 12.5744 20.3143C12.4909 20.231 12.444 20.1179 12.444 20V17.333ZM17.334 16.889C17.2161 16.8887 17.1029 16.9354 17.0194 17.0187C16.9359 17.102 16.889 17.2151 16.889 17.333V20C16.889 20.245 17.088 20.444 17.333 20.444H20C20.2452 20.444 20.444 20.2452 20.444 20V17.333C20.4434 17.0882 20.2448 16.89 20 16.89H17.333L17.334 16.889Z"
			fill="#0B63CE"
			fillRule="evenodd"
		/>
	</svg>
);

export {PartnershipIcon};
