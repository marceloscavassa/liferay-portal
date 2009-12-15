/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.wiki.model;


/**
 * <a href="WikiPage.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the WikiPage table in the
 * database.
 * </p>
 *
 * <p>
 * Customize {@link com.liferay.portlet.wiki.model.impl.WikiPageImpl} and rerun the
 * ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WikiPageModel
 * @see       com.liferay.portlet.wiki.model.impl.WikiPageImpl
 * @see       com.liferay.portlet.wiki.model.impl.WikiPageModelImpl
 * @generated
 */
public interface WikiPage extends WikiPageModel {
	public java.lang.String getAttachmentsDir();

	public java.lang.String[] getAttachmentsFiles()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getChildPages();

	public com.liferay.portlet.wiki.model.WikiNode getNode();

	public com.liferay.portlet.wiki.model.WikiPage getParentPage();

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> getParentPages();

	public com.liferay.portlet.wiki.model.WikiPage getRedirectPage();

	public boolean isApproved();

	public void setAttachmentsDir(java.lang.String attachmentsDir);
}