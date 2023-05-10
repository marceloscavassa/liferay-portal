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

package com.liferay.portal.tools.db.virtual.instance.migration.internal.util;

import com.liferay.portal.tools.db.virtual.instance.migration.internal.Release;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * @author Luis Ortiz
 */
public class DatabaseTest {

	@Test
	public void testGetReleaseEntries() throws SQLException {
		Release module1Release = new Release(
			"module1", Version.parseVersion("14.2.4"), true);
		Release module2Release = new Release(
			"module2", Version.parseVersion("2.0.1"), false);

		Mockito.when(
			_connection.prepareStatement(
				"select servletContextName, schemaVersion, verified from " +
					"Release_")
		).thenReturn(
			_preparedStatement
		);

		Mockito.when(
			_preparedStatement.executeQuery()
		).thenReturn(
			_resultSet
		);

		Mockito.when(
			_resultSet.next()
		).thenReturn(
			true
		).thenReturn(
			true
		).thenReturn(
			false
		);

		Mockito.when(
			_resultSet.getString(1)
		).thenReturn(
			module1Release.getServletContextName()
		).thenReturn(
			module2Release.getServletContextName()
		);

		Version module1Version = module1Release.getSchemaVersion();
		Version module2Version = module2Release.getSchemaVersion();

		Mockito.when(
			_resultSet.getString(2)
		).thenReturn(
			module1Version.toString()
		).thenReturn(
			module2Version.toString()
		);

		Mockito.when(
			_resultSet.getBoolean(3)
		).thenReturn(
			module1Release.getVerified()
		).thenReturn(
			module2Release.getVerified()
		);

		List<Release> releaseEntries = Database.getReleaseEntries(_connection);

		Assert.assertTrue(releaseEntries.size() == 2);

		Release module1Entry = releaseEntries.get(0);

		Assert.assertTrue(module1Entry.equals(module1Release));

		Release module2Entry = releaseEntries.get(1);

		Assert.assertTrue(module2Entry.equals(module2Release));
	}

	@Test
	public void testGetReleaseEntryFound() throws SQLException {
		Release testRelease = new Release(
			"module", Version.parseVersion("14.2.4"), true);

		_mockGetReleaseEntry(testRelease, true);

		Release release = Database.getReleaseEntry(_connection, "module");

		Assert.assertNotNull(release);

		Assert.assertTrue(release.equals(testRelease));

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_preparedStatement
		).setString(
			Mockito.eq(1), valueCapture.capture()
		);

		Assert.assertEquals("module", valueCapture.getValue());
	}

	@Test
	public void testGetReleaseEntryNotFound() throws SQLException {
		Release testRelease = new Release(
			"module", Version.parseVersion("14.2.4"), true);

		_mockGetReleaseEntry(testRelease, false);

		Release release = Database.getReleaseEntry(_connection, "module");

		Assert.assertNull(release);

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_preparedStatement
		).setString(
			Mockito.eq(1), valueCapture.capture()
		);

		Assert.assertEquals("module", valueCapture.getValue());
	}

	@Test
	public void testGetTables() throws SQLException {
		Mockito.when(
			_connection.getMetaData()
		).thenReturn(
			_databaseMetaData
		);

		Mockito.when(
			_databaseMetaData.getTables(
				Mockito.nullable(String.class), Mockito.nullable(String.class),
				Mockito.nullable(String.class), Mockito.any(String[].class))
		).thenReturn(
			_resultSet
		);

		Mockito.when(
			_resultSet.next()
		).thenReturn(
			true
		).thenReturn(
			true
		).thenReturn(
			true
		).thenReturn(
			false
		);

		Mockito.when(
			_resultSet.getString("TABLE_NAME")
		).thenReturn(
			"Table1"
		).thenReturn(
			"Company"
		).thenReturn(
			"Table2"
		).thenReturn(
			"Object_x_25000"
		);

		PreparedStatement preparedStatement1 = Mockito.mock(
			PreparedStatement.class);

		ResultSet resultSet1 = Mockito.mock(ResultSet.class);

		Mockito.when(
			_connection.prepareStatement("select companyId from CompanyInfo")
		).thenReturn(
			preparedStatement1
		);

		Mockito.when(
			preparedStatement1.executeQuery()
		).thenReturn(
			resultSet1
		);

		Mockito.when(
			resultSet1.next()
		).thenReturn(
			false
		);

		PreparedStatement preparedStatement2 = Mockito.mock(
			PreparedStatement.class);

		ResultSet resultSet2 = Mockito.mock(ResultSet.class);

		Mockito.when(
			_connection.prepareStatement("select companyId from Company")
		).thenReturn(
			preparedStatement2
		);

		Mockito.when(
			preparedStatement2.executeQuery()
		).thenReturn(
			resultSet2
		);

		Mockito.when(
			resultSet2.next()
		).thenReturn(
			true
		).thenReturn(
			false
		);

		Mockito.when(
			resultSet2.getLong("companyId")
		).thenReturn(
			25000L
		);

		ResultSet resultSet3 = Mockito.mock(ResultSet.class);

		Mockito.when(
			_databaseMetaData.getColumns(
				Mockito.nullable(String.class), Mockito.nullable(String.class),
				Mockito.nullable(String.class), Mockito.nullable(String.class))
		).thenReturn(
			resultSet3
		);

		Mockito.when(
			resultSet3.next()
		).thenReturn(
			true
		);

		List<String> tables = Database.getTables(_connection);

		Assert.assertTrue(tables.size() == 2);

		Assert.assertTrue(tables.contains("Table1"));
		Assert.assertTrue(tables.contains("Table2"));
		Assert.assertFalse(tables.contains("Company"));
		Assert.assertFalse(tables.contains("Object_x_25000"));
	}

	@Test
	public void testHasNotWebId() throws SQLException {
		_mockWebId(false);

		Assert.assertFalse(Database.hasWebId(_connection, "portlet2"));

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_preparedStatement
		).setString(
			Mockito.eq(1), valueCapture.capture()
		);
		Assert.assertEquals("portlet2", valueCapture.getValue());
	}

	@Test
	public void testHasWebId() throws SQLException {
		_mockWebId(true);

		Assert.assertTrue(Database.hasWebId(_connection, "portlet1"));

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_preparedStatement
		).setString(
			Mockito.eq(1), valueCapture.capture()
		);
		Assert.assertEquals("portlet1", valueCapture.getValue());
	}

	@Test
	public void testInvalidStateServlets() throws SQLException {
		_mockServletState(false);

		List<String> releaseEntries = Database.getInvalidStateServlets(
			_connection);

		Assert.assertTrue(releaseEntries.size() == 2);

		Assert.assertTrue(releaseEntries.contains("module1"));
		Assert.assertTrue(releaseEntries.contains("module2"));
	}

	@Test
	public void testIsDefaultPartition() throws SQLException {
		_mockDefaultPartition(true);

		Assert.assertTrue(Database.isDefaultPartition(_connection));

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_databaseMetaData, Mockito.times(2)
		).getTables(
			Mockito.nullable(String.class), Mockito.nullable(String.class),
			valueCapture.capture(), Mockito.any(String[].class)
		);

		List<String> capturedValues = valueCapture.getAllValues();

		Assert.assertTrue(capturedValues.size() == 2);
		Assert.assertTrue(capturedValues.contains("company"));
		Assert.assertTrue(capturedValues.contains("virtualhost"));
	}

	@Test
	public void testIsNotDefaultPartition() throws SQLException {
		_mockDefaultPartition(false);

		Assert.assertFalse(Database.isDefaultPartition(_connection));

		ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(
			String.class);

		Mockito.verify(
			_databaseMetaData, Mockito.times(1)
		).getTables(
			Mockito.nullable(String.class), Mockito.nullable(String.class),
			valueCapture.capture(), Mockito.any(String[].class)
		);

		List<String> capturedValues = valueCapture.getAllValues();

		Assert.assertTrue(capturedValues.size() == 1);
		Assert.assertTrue(capturedValues.contains("company"));
	}

	@Test
	public void testIsNotSingleVirtualInstance() throws SQLException {
		_mockSingleVirtualInstance(false);

		Assert.assertFalse(Database.isSingleVirtualInstance(_connection));
	}

	@Test
	public void testIsSingleVirtualInstance() throws SQLException {
		_mockSingleVirtualInstance(true);

		Assert.assertTrue(Database.isSingleVirtualInstance(_connection));
	}

	@Test
	public void testNoInvalidStateServlets() throws SQLException {
		_mockServletState(true);

		List<String> releaseEntries = Database.getInvalidStateServlets(
			_connection);

		Assert.assertTrue(releaseEntries.isEmpty());
	}

	private void _mockDefaultPartition(boolean defaultPartition)
		throws SQLException {

		Mockito.when(
			_connection.getMetaData()
		).thenReturn(
			_databaseMetaData
		);

		Mockito.when(
			_databaseMetaData.getTables(
				Mockito.nullable(String.class), Mockito.nullable(String.class),
				Mockito.anyString(), Mockito.eq(new String[] {"TABLE"}))
		).thenReturn(
			_resultSet
		);

		Mockito.when(
			_databaseMetaData.storesLowerCaseIdentifiers()
		).thenReturn(
			true
		);

		Mockito.when(
			_resultSet.next()
		).thenReturn(
			defaultPartition
		);
	}

	private void _mockGetReleaseEntry(Release release, boolean found)
		throws SQLException {

		Mockito.when(
			_connection.prepareStatement(
				"select servletContextName, schemaVersion, verified from " +
					"Release_ where servletContextName = ?")
		).thenReturn(
			_preparedStatement
		);

		Mockito.when(
			_preparedStatement.executeQuery()
		).thenReturn(
			_resultSet
		);

		if (found) {
			Mockito.when(
				_resultSet.next()
			).thenReturn(
				true
			).thenReturn(
				false
			);

			Mockito.when(
				_resultSet.getString(1)
			).thenReturn(
				release.getServletContextName()
			);

			Version releaseVersion = release.getSchemaVersion();

			Mockito.when(
				_resultSet.getString(2)
			).thenReturn(
				releaseVersion.toString()
			);

			Mockito.when(
				_resultSet.getBoolean(3)
			).thenReturn(
				release.getVerified()
			);
		}
		else {
			Mockito.when(
				_resultSet.next()
			).thenReturn(
				false
			);
		}
	}

	private void _mockServletState(boolean servletsOK) throws SQLException {
		Mockito.when(
			_connection.prepareStatement(
				"select servletContextName from Release_ where state_ != 0;")
		).thenReturn(
			_preparedStatement
		);

		Mockito.when(
			_preparedStatement.executeQuery()
		).thenReturn(
			_resultSet
		);

		if (servletsOK) {
			Mockito.when(
				_resultSet.next()
			).thenReturn(
				false
			);
		}
		else {
			Mockito.when(
				_resultSet.next()
			).thenReturn(
				true
			).thenReturn(
				true
			).thenReturn(
				false
			);

			Mockito.when(
				_resultSet.getString(1)
			).thenReturn(
				"module1"
			).thenReturn(
				"module2"
			);
		}
	}

	private void _mockSingleVirtualInstance(boolean singleVirtualInstance)
		throws SQLException {

		Mockito.when(
			_connection.prepareStatement("select count(1) from CompanyInfo")
		).thenReturn(
			_preparedStatement
		);

		Mockito.when(
			_preparedStatement.executeQuery()
		).thenReturn(
			_resultSet
		);

		Mockito.when(
			_resultSet.getInt(1)
		).thenReturn(
			singleVirtualInstance ? 1 : 4
		);

		Mockito.when(
			_resultSet.next()
		).thenReturn(
			true
		);
	}

	private void _mockWebId(boolean hasWebId) throws SQLException {
		Mockito.when(
			_connection.prepareStatement(
				"select companyId from Company where webId = ?")
		).thenReturn(
			_preparedStatement
		);

		Mockito.when(
			_preparedStatement.executeQuery()
		).thenReturn(
			_resultSet
		);

		Mockito.when(
			_resultSet.next()
		).thenReturn(
			hasWebId
		);
	}

	private final Connection _connection = Mockito.mock(Connection.class);
	private final DatabaseMetaData _databaseMetaData = Mockito.mock(
		DatabaseMetaData.class);
	private final PreparedStatement _preparedStatement = Mockito.mock(
		PreparedStatement.class);
	private final ResultSet _resultSet = Mockito.mock(ResultSet.class);

}