/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.util;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.util.comparator.CPOptionCategoryModifiedDateComparator;
import com.liferay.commerce.product.util.comparator.CPOptionCategoryPriorityComparator;
import com.liferay.commerce.product.util.comparator.CPOptionCategoryTitleComparator;
import com.liferay.commerce.product.util.comparator.CPOptionModifiedDateComparator;
import com.liferay.commerce.product.util.comparator.CPOptionNameComparator;
import com.liferay.commerce.product.util.comparator.CPOptionValueNameComparator;
import com.liferay.commerce.product.util.comparator.CPOptionValuePriorityComparator;
import com.liferay.commerce.product.util.comparator.CPSpecificationOptionModifiedDateComparator;
import com.liferay.commerce.product.util.comparator.CPSpecificationOptionTitleComparator;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 */
public class CPOptionsPortletUtil {

	public static OrderByComparator<CPOptionCategory>
		getCPOptionCategoryOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CPOptionCategory> orderByComparator = null;

		if (orderByCol.equals("modified-date")) {
			orderByComparator = new CPOptionCategoryModifiedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("group")) {
			orderByComparator = new CPOptionCategoryTitleComparator(orderByAsc);
		}
		else if (orderByCol.equals("priority")) {
			orderByComparator = new CPOptionCategoryPriorityComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCPOptionCategorySort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("title")) {
			sort = SortFactoryUtil.create(
				Field.TITLE, Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("modified-date")) {
			sort = SortFactoryUtil.create(
				Field.MODIFIED_DATE + "_sortable", reverse);
		}

		return sort;
	}

	public static OrderByComparator<CPOption> getCPOptionOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CPOption> orderByComparator = null;

		if (orderByCol.equals("modified-date")) {
			orderByComparator = new CPOptionModifiedDateComparator(orderByAsc);
		}

		if (orderByCol.equals("name")) {
			orderByComparator = new CPOptionNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<CPOptionValue>
		getCPOptionValueOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CPOptionValue> orderByComparator = null;

		if (orderByCol.equals("priority")) {
			orderByComparator = new CPOptionValuePriorityComparator(orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new CPOptionValueNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCPOptionValueSort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("name")) {
			sort = SortFactoryUtil.create(
				Field.NAME, Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("priority")) {
			sort = SortFactoryUtil.create("priority", Sort.INT_TYPE, reverse);
		}

		return sort;
	}

	public static OrderByComparator<CPSpecificationOption>
		getCPSpecificationOptionOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CPSpecificationOption> orderByComparator = null;

		if (orderByCol.equals("label")) {
			orderByComparator = new CPSpecificationOptionTitleComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new CPSpecificationOptionModifiedDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCPSpecificationOptionSort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("group")) {
			sort = SortFactoryUtil.create(
				"cpOptionCategoryTitle", Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("label")) {
			sort = SortFactoryUtil.create(
				Field.TITLE, Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("modified-date")) {
			sort = SortFactoryUtil.create(
				Field.MODIFIED_DATE + "_sortable", reverse);
		}

		return sort;
	}

}