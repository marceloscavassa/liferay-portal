/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.chart.servlet.taglib.soy;

import com.liferay.frontend.taglib.chart.servlet.taglib.soy.base.BaseChartTag;

/**
 * @author Chema Balsas
 */
public class PieChartTag extends BaseChartTag {

	public PieChartTag() {
		super("PieChart", "ClayPieChart.render");
	}

}