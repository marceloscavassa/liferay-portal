/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function FrontendDataSet({
	actionParameterName,
	activeViewSettings,
	apiURL,
	appURL,
	bulkActions,
	creationMenu,
	currentURL,
	customDataRenderers,
	customViews,
	customViewsEnabled,
	emptyState,
	filters,
	formId,
	formName,
	id,
	initialSelectedItemsValues,
	inlineAddingSettings,
	inlineEditingSettings,
	items,
	itemsActions,
	namespace,
	nestedItemsKey,
	nestedItemsReferenceKey,
	onActionDropdownItemClick,
	onBulkActionItemClick,
	onSelect,
	overrideEmptyResultView,
	pagination,
	portletId,
	selectedItemsKey,
	selectionType,
	showManagementBar,
	showPagination,
	showSearch,
	sidePanelId,
	sorting,
	style,
	views,
}: IFrontendDataSetProps): JSX.Element;

export function DateTimeRenderer({
	options,
	value,
}: DateTimeRendererProps): string;

type DateTimeRendererProps = {
	options?: {
		format: {
			day?: string;
			hour?: string;
			minute?: string;
			month?: string;
			second?: string;
			timeZone?: string;
			year?: string;
		};
	};
	value: string;
};

type TDelta = {
	href?: string;
	label: number;
};

type TInlineEditingSettings = {alwaysOn: boolean; defaultBodyContent: object};

type TItemsActions = {
	data?: {
		confirmationMessage?: string;
		id?: string;
		method?: 'delete' | 'get';
		permissionKey?: string;
	};
	href?: string;
	icon?: string;
	id?: string;
	label?: string;
	onClick?: Function;
	target?: 'async' | 'headless' | 'link' | 'modal' | 'sidePanel' | 'event';
};

type TSorting = {
	direction?: 'asc' | 'desc';
	key?: string;
};

type TViews = {
	component?: any;
	contentRenderer?: string;
	contentRendererClientExtension?: boolean;
	contentRendererModuleURL?: string;
	label?: string;
	name?: string;
	schema?: object;
	thumbnail?: string;
};

export interface IFrontendDataSetProps {
	actionParameterName?: string;
	activeViewSettings?: string;
	apiURL?: string;
	appURL?: string;
	bulkActions?: any[];
	creationMenu?: {
		primaryItems?: any[];
		secondaryItems?: any[];
	};
	currentURL?: string;
	customDataRenderers?: any;
	customViews?: string;
	customViewsEnabled?: boolean;
	emptyState?: {
		description?: string;
		image?: string;
		title?: string;
	};
	enableInlineAddModeSetting?: {
		defaultBodyContent?: object;
	};
	filters?: any;
	formId?: string;
	formName?: string;
	id: string;
	initialSelectedItemsValues?: any[];
	inlineAddingSettings?: {
		apiURL: string;
		defaultBodyContent: object;
	};
	inlineEditingSettings?: boolean | TInlineEditingSettings;
	items?: any[];
	itemsActions?: TItemsActions[];
	namespace?: string;
	nestedItemsKey?: string;
	nestedItemsReferenceKey?: string;
	onActionDropdownItemClick?: any;
	onBulkActionItemClick?: any;
	onSelect?: Function;
	overrideEmptyResultView?: boolean;
	pagination?: {
		deltas?: TDelta[];
		initialDelta?: number;
		initialPageNumber?: number;
	};
	portletId?: string;
	selectedItemsKey?: string;
	selectionType?: 'single' | 'multiple';
	showManagementBar?: boolean;
	showPagination?: boolean;
	showSearch?: boolean;
	sidePanelId?: string;
	sorting?: TSorting[];
	style?: 'default' | 'fluid' | 'stacked';
	views: TViews[];
}

export {
	INTERNAL_CELL_RENDERERS as FDS_INTERNAL_CELL_RENDERERS,
	InternalCellRenderer as FDSInternalCellRenderer,
} from './cell_renderers/InternalCellRenderer';

export {InternalRenderer} from './utils/renderer';
