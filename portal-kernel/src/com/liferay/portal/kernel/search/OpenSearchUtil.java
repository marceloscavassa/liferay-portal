/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.text.Format;

import java.util.Date;

/**
 * @author Charles May
 * @author Brian Wing Shun Chan
 */
public class OpenSearchUtil {

	public static final int DEFAULT_NAMESPACE = 0;

	public static final int LIFERAY_NAMESPACE = 4;

	public static final int NO_NAMESPACE = 3;

	public static final int OS_NAMESPACE = 1;

	public static final int RELEVANCE_NAMESPACE = 2;

	public static Element addElement(
		Element el, String name, int namespaceType) {

		return el.addElement(getQName(name, namespaceType));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, Date value) {

		return addElement(el, name, namespaceType, _dateFormat.format(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, double value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, int value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, long value) {

		return addElement(el, name, namespaceType, String.valueOf(value));
	}

	public static Element addElement(
		Element el, String name, int namespaceType, String value) {

		Element returnElement = el.addElement(getQName(name, namespaceType));

		returnElement.addCDATA(value);

		return returnElement;
	}

	public static void addLink(
		Element root, String searchURL, String rel, String keywords, int page,
		int itemsPerPage) {

		Element link = addElement(root, "link", DEFAULT_NAMESPACE);

		link.addAttribute("rel", rel);

		searchURL = HttpComponentsUtil.addParameter(
			searchURL, "keywords", URLCodec.encodeURL(keywords));
		searchURL = HttpComponentsUtil.addParameter(searchURL, "p", page);
		searchURL = HttpComponentsUtil.addParameter(
			searchURL, "c", itemsPerPage);
		searchURL = HttpComponentsUtil.addParameter(
			searchURL, "format", "atom");

		link.addAttribute("href", searchURL);

		link.addAttribute("type", "application/atom+xml");
	}

	public static Namespace getNamespace(int namespaceType) {
		Namespace namespace = null;

		if (namespaceType == DEFAULT_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"", "http://www.w3.org/2005/Atom");
		}
		else if (namespaceType == LIFERAY_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"liferay", "http://liferay.com/spec/liferay-search/1.0/");
		}
		else if (namespaceType == OS_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"opensearch", "http://a9.com/-/spec/opensearch/1.1/");
		}
		else if (namespaceType == RELEVANCE_NAMESPACE) {
			namespace = SAXReaderUtil.createNamespace(
				"relevance",
				"http://a9.com/-/opensearch/extensions/relevance/1.0/");
		}

		return namespace;
	}

	public static QName getQName(String name, int namespaceType) {
		if (NO_NAMESPACE == namespaceType) {
			return SAXReaderUtil.createQName(name);
		}

		return SAXReaderUtil.createQName(name, getNamespace(namespaceType));
	}

	private static final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:sszzz");

}