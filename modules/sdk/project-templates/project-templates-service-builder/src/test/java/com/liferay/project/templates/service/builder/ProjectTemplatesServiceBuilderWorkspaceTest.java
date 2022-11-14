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

package com.liferay.project.templates.service.builder;

import com.liferay.maven.executor.MavenExecutor;
import com.liferay.project.templates.BaseProjectTemplatesTestCase;
import com.liferay.project.templates.extensions.util.Validator;
import com.liferay.project.templates.extensions.util.VersionUtil;
import com.liferay.project.templates.util.FileTestUtil;

import java.io.File;

import java.net.URI;

import java.util.Arrays;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Gregory Amerson
 * @author Lawrence Lee
 */
@RunWith(Parameterized.class)
public class ProjectTemplatesServiceBuilderWorkspaceTest
	implements BaseProjectTemplatesTestCase {

	@ClassRule
	public static final MavenExecutor mavenExecutor = new MavenExecutor();

	@Parameterized.Parameters(
		name = "Testcase-{index}: testing {0}, {1}, {2}, {3}, {4}"
	)
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{
					"spring", "guestbook", "com.liferay.docs.guestbook",
					"7.0.10.17", "dxp"
				},
				{
					"spring", "guestbook", "com.liferay.docs.guestbook",
					"7.1.10.7", "dxp"
				},
				{
					"ds", "guestbook", "com.liferay.docs.guestbook", "7.2.10.7",
					"dxp"
				},
				{
					"ds", "guestbook", "com.liferay.docs.guestbook", "7.3.7",
					"portal"
				},
				{
					"ds", "guestbook", "com.liferay.docs.guestbook", "7.4.3.36",
					"portal"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.0.10.17", "dxp"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.1.10.7", "dxp"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.2.10.7", "dxp"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.3.7", "portal"
				},
				{
					"ds", "backend-integration", "com.liferay.docs.guestbook",
					"7.4.3.36", "portal"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.2.10.7", "dxp"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.3.7", "portal"
				},
				{
					"spring", "backend-integration",
					"com.liferay.docs.guestbook", "7.4.3.36", "portal"
				},
				{"spring", "sample", "com.test.sample", "7.0.10.17", "dxp"},
				{"spring", "sample", "com.test.sample", "7.1.10.7", "dxp"},
				{"ds", "sample", "com.test.sample", "7.2.10.7", "dxp"},
				{"ds", "sample", "com.test.sample", "7.3.7", "portal"},
				{"ds", "sample", "com.test.sample", "7.4.3.36", "portal"}
			});
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		String gradleDistribution = System.getProperty("gradle.distribution");

		if (Validator.isNull(gradleDistribution)) {
			Properties properties = FileTestUtil.readProperties(
				"gradle-wrapper/gradle/wrapper/gradle-wrapper.properties");

			gradleDistribution = properties.getProperty("distributionUrl");
		}

		Assert.assertTrue(gradleDistribution.contains(GRADLE_WRAPPER_VERSION));

		_gradleDistribution = URI.create(gradleDistribution);
	}

	public ProjectTemplatesServiceBuilderWorkspaceTest(
		String dependencyInjector, String name, String packageName,
		String liferayVersion, String liferayProduct) {

		_dependencyInjector = dependencyInjector;
		_name = name;
		_packageName = packageName;
		_liferayVersion = liferayVersion;
		_liferayProduct = liferayProduct;
	}

	@Test
	public void testBuildTemplateServiceBuilderWorkspace() throws Exception {
		String template = "service-builder";

		File gradleWorkspaceDir = buildWorkspace(
			temporaryFolder, "gradle", "gradleWS", _liferayVersion,
			mavenExecutor);

		File gradleWorkspaceModulesDir = new File(
			gradleWorkspaceDir, "modules");

		if (_name.contains("sample")) {
			gradleWorkspaceModulesDir = new File(
				gradleWorkspaceDir, "modules/nested/path");

			Assert.assertTrue(gradleWorkspaceModulesDir.mkdirs());
		}

		File gradleProjectDir = buildTemplateWithGradle(
			gradleWorkspaceModulesDir, template, _name, "--dependency-injector",
			_dependencyInjector, "--liferay-product", _liferayProduct,
			"--liferay-version", _liferayVersion, "--package-name",
			_packageName);

		if (_name.contains("sample")) {
			testContains(
				gradleProjectDir, "sample-service/build.gradle",
				"compile project(\":modules:nested:path:sample:sample-api\")");
		}

		if (_dependencyInjector.equals("ds")) {
			testContains(
				gradleProjectDir, _name + "-service/service.xml",
				"dependency-injector=\"ds\"");
			testContains(
				gradleProjectDir, _name + "-service/bnd.bnd",
				"-dsannotations-options: inherit");
		}

		if (_dependencyInjector.equals("spring")) {
			testNotContains(
				gradleProjectDir, _name + "-service/bnd.bnd",
				"-dsannotations-options: inherit");
			testNotContains(
				gradleProjectDir, _name + "-service/service.xml",
				"dependency-injector=\"ds\"");
		}

		if (VersionUtil.getMinorVersion(_liferayVersion) < 3) {
			testContains(
				gradleProjectDir, _name + "-api/build.gradle",
				DEPENDENCY_RELEASE_DXP_API);
			testContains(
				gradleProjectDir, _name + "-service/build.gradle",
				DEPENDENCY_RELEASE_DXP_API);
		}
		else {
			testContains(
				gradleProjectDir, _name + "-api/build.gradle",
				DEPENDENCY_RELEASE_PORTAL_API);
			testContains(
				gradleProjectDir, _name + "-service/build.gradle",
				DEPENDENCY_RELEASE_PORTAL_API);
		}

		File gradleUADModuleDir = new File(gradleProjectDir, _name + "-uad");

		testNotExists(gradleUADModuleDir, "bnd.bnd");

		File mavenWorkspaceDir = buildWorkspace(
			temporaryFolder, "maven", "mavenWS", _liferayVersion,
			mavenExecutor);

		File mavenModulesDir = new File(mavenWorkspaceDir, "modules");

		if (_name.contains("sample")) {
			mavenModulesDir = new File(
				mavenWorkspaceDir, "modules/nested/path");

			Assert.assertTrue(mavenModulesDir.mkdirs());
		}

		File mavenProjectDir = buildTemplateWithMaven(
			mavenModulesDir, mavenModulesDir, template, _name, "com.test",
			mavenExecutor, "-DdependencyInjector=" + _dependencyInjector,
			"-DliferayProduct=" + _liferayProduct,
			"-DliferayVersion=" + _liferayVersion, "-Dpackage=" + _packageName);

		File mavenUADModuleDir = new File(mavenProjectDir, _name + "-uad");

		testNotExists(mavenUADModuleDir, "bnd.bnd");

		if (isBuildProjects()) {
			String projectPath;

			if (_name.contains("sample")) {
				projectPath = ":modules:nested:path:sample";
			}
			else {
				projectPath = ":modules:" + _name;
			}

			testBuildTemplateServiceBuilder(
				gradleProjectDir, mavenProjectDir, gradleWorkspaceDir, _name,
				_packageName, projectPath, _gradleDistribution, mavenExecutor);
		}
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static URI _gradleDistribution;

	private final String _dependencyInjector;
	private final String _liferayProduct;
	private final String _liferayVersion;
	private final String _name;
	private final String _packageName;

}