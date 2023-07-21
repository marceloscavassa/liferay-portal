/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const PartnershipIconGray = (props) => (
	<svg
		fill="none"
		height="16"
		viewBox="0 0 16 16"
		width="16"
		xmlns="http://www.w3.org/2000/svg"
		{...props}
	>
		<rect fill="white" height="16" width="16" />

		<path
			clipRule="evenodd"
			d="M0 1.18533C0 0.530667 0.530667 0 1.18533 0H14.8147C15.4693 0 16 0.530667 16 1.18533V14.8147C16 15.4693 15.4693 16 14.8147 16H1.18533C0.530692 16 0 15.4693 0 14.8147V1.18533ZM2.37067 2.66667C2.37067 2.50333 2.50333 2.37067 2.66667 2.37067H4.44467C4.608 2.37067 4.74067 2.50333 4.74067 2.66667V4.44467C4.74067 4.52317 4.70948 4.59846 4.65397 4.65397C4.59846 4.70948 4.52317 4.74067 4.44467 4.74067H2.66667C2.50319 4.74067 2.37067 4.60814 2.37067 4.44467V2.66667ZM5.62933 2.37067C5.46586 2.37067 5.33333 2.50319 5.33333 2.66667V4.44467C5.33333 4.608 5.466 4.74067 5.62933 4.74067H7.40733C7.48595 4.74084 7.56141 4.70974 7.61707 4.65421C7.67272 4.59868 7.704 4.52329 7.704 4.44467V2.66667C7.704 2.58805 7.67272 2.51266 7.61707 2.45713C7.56141 2.4016 7.48595 2.37049 7.40733 2.37067H5.62933ZM8.296 2.66667C8.296 2.50333 8.42933 2.37067 8.59267 2.37067H10.3707C10.534 2.37067 10.6667 2.50333 10.6667 2.66667V4.44467C10.6667 4.52317 10.6355 4.59846 10.58 4.65397C10.5245 4.70948 10.4492 4.74067 10.3707 4.74067H8.59267C8.51405 4.74084 8.43859 4.70974 8.38293 4.65421C8.32728 4.59868 8.296 4.52329 8.296 4.44467V2.66667ZM2.66667 5.33333C2.58816 5.33333 2.51287 5.36452 2.45736 5.42003C2.40185 5.47554 2.37067 5.55083 2.37067 5.62933V7.40733C2.37067 7.57133 2.50333 7.704 2.66667 7.704H4.44467C4.52329 7.704 4.59868 7.67272 4.65421 7.61707C4.70974 7.56141 4.74084 7.48595 4.74067 7.40733V5.62933C4.74067 5.55083 4.70948 5.47554 4.65397 5.42003C4.59846 5.36452 4.52317 5.33333 4.44467 5.33333H2.66667ZM5.33333 5.62933C5.33333 5.46667 5.466 5.33333 5.62933 5.33333H7.40733C7.57133 5.33333 7.704 5.466 7.704 5.62933V7.40733C7.70418 7.48607 7.67298 7.56163 7.6173 7.6173C7.56163 7.67298 7.48607 7.70418 7.40733 7.704H5.62933C5.5506 7.704 5.47511 7.67263 5.41956 7.61683C5.36401 7.56103 5.33298 7.4854 5.33333 7.40667V5.62933ZM11.5553 5.33333C11.4768 5.33333 11.4015 5.36452 11.346 5.42003C11.2905 5.47554 11.2593 5.55083 11.2593 5.62933V7.40733C11.2593 7.57133 11.392 7.704 11.5553 7.704H13.3333C13.412 7.704 13.4873 7.67272 13.5429 7.61707C13.5984 7.56141 13.6295 7.48595 13.6293 7.40733V5.62933C13.6293 5.55083 13.5981 5.47554 13.5426 5.42003C13.4871 5.36452 13.4118 5.33333 13.3333 5.33333H11.5553ZM2.37067 8.59267C2.37067 8.42867 2.50333 8.296 2.66667 8.296H4.44467C4.608 8.296 4.74067 8.42933 4.74067 8.59267V10.3707C4.74067 10.4492 4.70948 10.5245 4.65397 10.58C4.59846 10.6355 4.52317 10.6667 4.44467 10.6667H2.66667C2.50319 10.6667 2.37067 10.5341 2.37067 10.3707V8.59267ZM8.59267 8.296C8.51393 8.29582 8.43837 8.32702 8.3827 8.3827C8.32702 8.43837 8.29582 8.51393 8.296 8.59267V10.3707C8.296 10.534 8.42933 10.6667 8.59267 10.6667H10.3707C10.5341 10.6667 10.6667 10.5341 10.6667 10.3707V8.59267C10.6668 8.51405 10.6357 8.43859 10.5802 8.38293C10.5247 8.32728 10.4493 8.296 10.3707 8.296H8.59267ZM11.2593 8.59267C11.2593 8.42867 11.392 8.296 11.5553 8.296H13.3333C13.4967 8.296 13.6293 8.42933 13.6293 8.59267V10.3707C13.6293 10.5341 13.4968 10.6667 13.3333 10.6667H11.5553C11.3919 10.6667 11.2593 10.5341 11.2593 10.3707V8.59267ZM5.62933 11.2593C5.46586 11.2593 5.33333 11.3919 5.33333 11.5553V13.3333C5.33333 13.4967 5.466 13.6293 5.62933 13.6293H7.40733C7.48595 13.6295 7.56141 13.5984 7.61707 13.5429C7.67272 13.4873 7.704 13.412 7.704 13.3333V11.5553C7.704 11.4767 7.67272 11.4013 7.61707 11.3458C7.56141 11.2903 7.48595 11.2592 7.40733 11.2593H5.62933ZM8.296 11.5553C8.296 11.392 8.42933 11.2593 8.59267 11.2593H10.3707C10.534 11.2593 10.6667 11.392 10.6667 11.5553V13.3333C10.6667 13.4968 10.5341 13.6293 10.3707 13.6293H8.59267C8.51405 13.6295 8.43859 13.5984 8.38293 13.5429C8.32728 13.4873 8.296 13.412 8.296 13.3333V11.5553ZM11.556 11.2593C11.4774 11.2592 11.4019 11.2903 11.3463 11.3458C11.2906 11.4013 11.2593 11.4767 11.2593 11.5553V13.3333C11.2593 13.4967 11.392 13.6293 11.5553 13.6293H13.3333C13.4968 13.6293 13.6293 13.4968 13.6293 13.3333V11.5553C13.629 11.3921 13.4965 11.26 13.3333 11.26H11.5553L11.556 11.2593Z"
			fill="#716C7F"
			fillRule="evenodd"
		/>
	</svg>
);

export {PartnershipIconGray};
