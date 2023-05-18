/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.frontend.editor.alloyeditor.web.internal;

import com.liferay.portal.kernel.editor.Editor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ambrín Chaudhary
 */
@Component(
	property = "name=" + AlloyEditorBBCodeEditor.EDITOR_NAME,
	service = Editor.class
)
public class AlloyEditorBBCodeEditor extends BaseAlloyEditor {

	public static final String EDITOR_NAME = "alloyeditor_bbcode";

	@Override
	public String getName() {
		return EDITOR_NAME;
	}

}