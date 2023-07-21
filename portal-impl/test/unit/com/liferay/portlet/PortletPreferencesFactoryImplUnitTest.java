/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.xml.SecureXMLFactoryProviderUtil;
import com.liferay.portal.security.xml.SecureXMLFactoryProviderImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.tools.ToolDependencies;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class PortletPreferencesFactoryImplUnitTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		ToolDependencies.wireCaches();
	}

	@Before
	public void setUp() {
		SecureXMLFactoryProviderUtil secureXMLFactoryProviderUtil =
			new SecureXMLFactoryProviderUtil();

		secureXMLFactoryProviderUtil.setSecureXMLFactoryProvider(
			new SecureXMLFactoryProviderImpl());

		PortletPreferencesFactoryUtil portletPreferencesFactoryUtil =
			new PortletPreferencesFactoryUtil();

		portletPreferencesFactoryUtil.setPortletPreferencesFactory(
			new PortletPreferencesFactoryImpl());
	}

	@Test
	public void testBlankPreference() throws Exception {
		String expectedXML = _getPortletPreferencesXML(
			"name", new String[] {StringPool.BLANK});

		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setValue("name", "");

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		Assert.assertEquals(expectedXML, actualXML);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(expectedXML);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 1, preferencesMap.size());

		Collection<Preference> preferencesCollection = preferencesMap.values();

		Iterator<Preference> iterator = preferencesCollection.iterator();

		Preference preference = iterator.next();

		Assert.assertEquals("name", preference.getName());

		String[] values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 1, values.length);
		Assert.assertEquals("", values[0]);
	}

	@Test
	public void testComplexPortletPreferences() throws Exception {
		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		// Blank

		portletPreferencesImpl.setValue("", "");

		// Empty

		portletPreferencesImpl.setValues("name1", new String[0]);

		// Multiple

		portletPreferencesImpl.setValues("name2", new String[] {"", "value1"});

		// Read only

		Preference preference = new Preference(
			"name3", new String[] {"value2", "value3"}, true);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		preferencesMap.put("name3", preference);

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(actualXML);

		preferencesMap = portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 4, preferencesMap.size());

		// Blank

		preference = preferencesMap.get("");

		Assert.assertNotNull(preference);
		Assert.assertEquals("", preference.getName());

		String[] values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 1, values.length);
		Assert.assertEquals("", values[0]);

		Assert.assertFalse(preference.isReadOnly());

		// Empty

		preference = preferencesMap.get("name1");

		Assert.assertNotNull(preference);
		Assert.assertEquals("name1", preference.getName());

		values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 0, values.length);

		Assert.assertFalse(preference.isReadOnly());

		// Multiple

		preference = preferencesMap.get("name2");

		Assert.assertNotNull(preference);
		Assert.assertEquals("name2", preference.getName());

		values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 2, values.length);
		Assert.assertEquals("", values[0]);
		Assert.assertEquals("value1", values[1]);

		Assert.assertFalse(preference.isReadOnly());

		// Read only

		preference = preferencesMap.get("name3");

		Assert.assertNotNull(preference);
		Assert.assertEquals("name3", preference.getName());

		values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 2, values.length);
		Assert.assertEquals("value2", values[0]);
		Assert.assertEquals("value3", values[1]);

		Assert.assertTrue(preference.isReadOnly());
	}

	@Test
	public void testEmptyPortletPreferences() {
		String expectedXML = PortletConstants.DEFAULT_PREFERENCES;

		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		Assert.assertEquals(expectedXML, actualXML);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(expectedXML);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 0, preferencesMap.size());
	}

	@Test
	public void testEmptyPreference() throws Exception {
		String expectedXML = _getPortletPreferencesXML("name", new String[0]);

		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setValues("name", new String[0]);

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		Assert.assertEquals(expectedXML, actualXML);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(expectedXML);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 1, preferencesMap.size());

		Collection<Preference> preferencesCollection = preferencesMap.values();

		Iterator<Preference> iterator = preferencesCollection.iterator();

		Preference preference = iterator.next();

		Assert.assertEquals("name", preference.getName());

		String[] values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 0, values.length);

		Assert.assertFalse(preference.isReadOnly());
	}

	@Test
	public void testMultiplePreferences() throws Exception {
		String[] values = {"value1", "value2"};

		String expectedXML = _getPortletPreferencesXML("name", values);

		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setValues("name", values);

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		Assert.assertEquals(expectedXML, actualXML);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(expectedXML);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 1, preferencesMap.size());

		Collection<Preference> preferencesCollection = preferencesMap.values();

		Iterator<Preference> iterator = preferencesCollection.iterator();

		Preference preference = iterator.next();

		Assert.assertEquals("name", preference.getName());

		values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 2, values.length);
		Assert.assertEquals("value1", values[0]);
		Assert.assertEquals("value2", values[1]);
	}

	@Test
	public void testSinglePreference() throws Exception {
		String expectedXML = _getPortletPreferencesXML(
			"name", new String[] {"value"});

		PortletPreferencesImpl portletPreferencesImpl =
			new PortletPreferencesImpl();

		portletPreferencesImpl.setValue("name", "value");

		String actualXML = PortletPreferencesFactoryUtil.toXML(
			portletPreferencesImpl);

		Assert.assertEquals(expectedXML, actualXML);

		portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(expectedXML);

		Map<String, Preference> preferencesMap =
			portletPreferencesImpl.getPreferences();

		Assert.assertEquals(
			preferencesMap.toString(), 1, preferencesMap.size());

		Collection<Preference> preferencesCollection = preferencesMap.values();

		Iterator<Preference> iterator = preferencesCollection.iterator();

		Preference preference = iterator.next();

		Assert.assertEquals("name", preference.getName());

		String[] values = preference.getValues();

		Assert.assertEquals(Arrays.toString(values), 1, values.length);
		Assert.assertEquals("value", values[0]);
	}

	private String _getPortletPreferencesXML(String name, String[] values) {
		StringBundler sb = new StringBundler();

		sb.append("<portlet-preferences>");

		if ((name != null) || (values != null)) {
			sb.append("<preference>");

			if (name != null) {
				sb.append("<name>");
				sb.append(name);
				sb.append("</name>");
			}

			if (values != null) {
				for (String value : values) {
					sb.append("<value>");
					sb.append(value);
					sb.append("</value>");
				}
			}

			sb.append("</preference>");
		}

		sb.append("</portlet-preferences>");

		return sb.toString();
	}

}