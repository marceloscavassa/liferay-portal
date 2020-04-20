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

package com.liferay.gradle.plugins;

import aQute.bnd.gradle.BndUtils;
import aQute.bnd.gradle.BundleTaskConvention;
import aQute.bnd.gradle.PropertiesWrapper;
import aQute.bnd.header.Parameters;
import aQute.bnd.osgi.Builder;
import aQute.bnd.osgi.Constants;
import aQute.bnd.osgi.Processor;
import aQute.bnd.version.MavenVersion;
import aQute.bnd.version.Version;

import aQute.lib.utf8properties.UTF8Properties;

import com.liferay.gradle.plugins.css.builder.CSSBuilderPlugin;
import com.liferay.gradle.plugins.extensions.BundleExtension;
import com.liferay.gradle.plugins.extensions.LiferayExtension;
import com.liferay.gradle.plugins.extensions.LiferayOSGiExtension;
import com.liferay.gradle.plugins.internal.AlloyTaglibDefaultsPlugin;
import com.liferay.gradle.plugins.internal.CSSBuilderDefaultsPlugin;
import com.liferay.gradle.plugins.internal.DBSupportDefaultsPlugin;
import com.liferay.gradle.plugins.internal.EclipseDefaultsPlugin;
import com.liferay.gradle.plugins.internal.IdeaDefaultsPlugin;
import com.liferay.gradle.plugins.internal.JSModuleConfigGeneratorDefaultsPlugin;
import com.liferay.gradle.plugins.internal.JavadocFormatterDefaultsPlugin;
import com.liferay.gradle.plugins.internal.RESTBuilderDefaultsPlugin;
import com.liferay.gradle.plugins.internal.ServiceBuilderDefaultsPlugin;
import com.liferay.gradle.plugins.internal.SpotBugsDefaultsPlugin;
import com.liferay.gradle.plugins.internal.TLDFormatterDefaultsPlugin;
import com.liferay.gradle.plugins.internal.TestIntegrationDefaultsPlugin;
import com.liferay.gradle.plugins.internal.UpgradeTableBuilderDefaultsPlugin;
import com.liferay.gradle.plugins.internal.WSDDBuilderDefaultsPlugin;
import com.liferay.gradle.plugins.internal.WatchOSGiPlugin;
import com.liferay.gradle.plugins.internal.util.FileUtil;
import com.liferay.gradle.plugins.internal.util.GradleUtil;
import com.liferay.gradle.plugins.internal.util.IncludeResourceCompileIncludeInstruction;
import com.liferay.gradle.plugins.internal.util.copy.RenameDependencyAction;
import com.liferay.gradle.plugins.jasper.jspc.JspCPlugin;
import com.liferay.gradle.plugins.javadoc.formatter.JavadocFormatterPlugin;
import com.liferay.gradle.plugins.js.module.config.generator.JSModuleConfigGeneratorPlugin;
import com.liferay.gradle.plugins.js.transpiler.JSTranspilerBasePlugin;
import com.liferay.gradle.plugins.js.transpiler.JSTranspilerPlugin;
import com.liferay.gradle.plugins.lang.builder.LangBuilderPlugin;
import com.liferay.gradle.plugins.node.NodePlugin;
import com.liferay.gradle.plugins.node.tasks.DownloadNodeModuleTask;
import com.liferay.gradle.plugins.node.tasks.NpmInstallTask;
import com.liferay.gradle.plugins.source.formatter.SourceFormatterPlugin;
import com.liferay.gradle.plugins.soy.SoyPlugin;
import com.liferay.gradle.plugins.soy.SoyTranslationPlugin;
import com.liferay.gradle.plugins.soy.tasks.BuildSoyTask;
import com.liferay.gradle.plugins.tasks.DirectDeployTask;
import com.liferay.gradle.plugins.test.integration.TestIntegrationPlugin;
import com.liferay.gradle.plugins.tld.formatter.TLDFormatterPlugin;
import com.liferay.gradle.plugins.tlddoc.builder.TLDDocBuilderPlugin;
import com.liferay.gradle.plugins.util.BndUtil;
import com.liferay.gradle.plugins.wsdd.builder.BuildWSDDTask;
import com.liferay.gradle.plugins.wsdd.builder.WSDDBuilderPlugin;
import com.liferay.gradle.plugins.wsdl.builder.WSDLBuilderPlugin;
import com.liferay.gradle.util.StringUtil;
import com.liferay.gradle.util.Validator;

import groovy.lang.Closure;

import java.io.File;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileCopyDetails;
import org.gradle.api.file.RelativePath;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.ApplicationPlugin;
import org.gradle.api.plugins.ApplicationPluginConvention;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.BasePluginConvention;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.Delete;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetOutput;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskInputs;
import org.gradle.api.tasks.TaskOutputs;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.api.tasks.bundling.War;
import org.gradle.api.tasks.compile.CompileOptions;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.api.tasks.javadoc.Javadoc;
import org.gradle.api.tasks.testing.Test;
import org.gradle.language.base.plugins.LifecycleBasePlugin;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;

/**
 * @author Andrea Di Giorgi
 * @author Raymond Augé
 */
public class LiferayOSGiPlugin implements Plugin<Project> {

	public static final String AUTO_CLEAN_PROPERTY_NAME = "autoClean";

	public static final String AUTO_UPDATE_XML_TASK_NAME = "autoUpdateXml";

	public static final String CLEAN_DEPLOYED_PROPERTY_NAME = "cleanDeployed";

	public static final String COMPILE_INCLUDE_CONFIGURATION_NAME =
		"compileInclude";

	public static final String DEPLOY_DEPENDENCIES_TASK_NAME =
		"deployDependencies";

	public static final String DEPLOY_FAST_TASK_NAME = "deployFast";

	public static final String PLUGIN_NAME = "liferayOSGi";

	@Override
	public void apply(final Project project) {

		// Plugins

		_applyPlugins(project);

		// Extensions

		BundleExtension bundleExtension = _addExtensionBundle(project);
		final LiferayOSGiExtension liferayOSGiExtension =
			GradleUtil.addExtension(
				project, PLUGIN_NAME, LiferayOSGiExtension.class);

		LiferayExtension liferayExtension = GradleUtil.getExtension(
			project, LiferayExtension.class);

		_configureExtensionBundle(project, bundleExtension);
		_configureExtensionLiferay(project, liferayExtension);

		// Configurations

		final Configuration compileIncludeConfiguration =
			_addConfigurationCompileInclude(project);

		// Tasks

		final TaskProvider<Copy> deployDependenciesTaskProvider =
			GradleUtil.addTaskProvider(
				project, DEPLOY_DEPENDENCIES_TASK_NAME, Copy.class);
		TaskProvider<Copy> deployFastTaskProvider = GradleUtil.addTaskProvider(
			project, DEPLOY_FAST_TASK_NAME, Copy.class);
		TaskProvider<DirectDeployTask> directDeployTaskProvider =
			GradleUtil.addTaskProvider(
				project, AUTO_UPDATE_XML_TASK_NAME, DirectDeployTask.class);

		TaskProvider<Task> classesTaskProvider = GradleUtil.getTaskProvider(
			project, JavaPlugin.CLASSES_TASK_NAME);
		TaskProvider<Delete> cleanTaskProvider = GradleUtil.getTaskProvider(
			project, BasePlugin.CLEAN_TASK_NAME, Delete.class);
		TaskProvider<JavaCompile> compileJSPTaskProvider =
			GradleUtil.getTaskProvider(
				project, JspCPlugin.COMPILE_JSP_TASK_NAME, JavaCompile.class);
		TaskProvider<Copy> deployTaskProvider = GradleUtil.getTaskProvider(
			project, LiferayBasePlugin.DEPLOY_TASK_NAME, Copy.class);
		TaskProvider<Jar> jarTaskProvider = GradleUtil.getTaskProvider(
			project, JavaPlugin.JAR_TASK_NAME, Jar.class);
		TaskProvider<Javadoc> javadocTaskProvider = GradleUtil.getTaskProvider(
			project, JavaPlugin.JAVADOC_TASK_NAME, Javadoc.class);
		TaskProvider<Copy> processResourcesTaskProvider =
			GradleUtil.getTaskProvider(
				project, JavaPlugin.PROCESS_RESOURCES_TASK_NAME, Copy.class);
		TaskProvider<Test> testTaskProvider = GradleUtil.getTaskProvider(
			project, JavaPlugin.TEST_TASK_NAME, Test.class);

		_configureTaskAutoUpdateXmlProvider(
			project, liferayExtension, liferayOSGiExtension,
			directDeployTaskProvider, jarTaskProvider);
		_configureTaskCleanProvider(project, cleanTaskProvider);
		_configureTaskDeployProvider(
			deployTaskProvider, deployDependenciesTaskProvider);
		_configureTaskDeployDependenciesProvider(
			liferayExtension, deployDependenciesTaskProvider);
		_configureTaskDeployFastProvider(
			project, liferayExtension, classesTaskProvider,
			compileJSPTaskProvider, deployFastTaskProvider,
			processResourcesTaskProvider);
		_configureTaskJarProvider(project, jarTaskProvider);
		_configureTaskJavadocProvider(project, javadocTaskProvider);
		_configureTaskTestProvider(testTaskProvider);

		_configureTaskCleanProvider(
			liferayExtension, cleanTaskProvider, deployTaskProvider,
			jarTaskProvider);
		_configureTaskDeployProvider(
			project, liferayExtension, deployTaskProvider, jarTaskProvider,
			false);

		// Other

		_configureConventionBasePlugin(project);
		_configureProject(project);
		_configureSourceSetMain(project);

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			BuildWSDDTask.class,
			buildWSDDTask -> {
				TaskProvider<Jar> buildWSDDJarTaskProvider =
					GradleUtil.addTaskProvider(
						project, buildWSDDTask.getName() + "Jar", Jar.class);

				_configureTaskBuildWSDDJarProvider(
					project, liferayExtension, liferayOSGiExtension,
					buildWSDDTask, buildWSDDJarTaskProvider, cleanTaskProvider,
					deployTaskProvider);
			});

		taskContainer.configureEach(
			task -> {
				if (task instanceof JavaCompile) {
					_configureTaskJavaCompile((JavaCompile)task, true);
				}

				if (task instanceof Test) {
					_configureTaskTest((Test)task);
				}
			});

		PluginContainer pluginContainer = project.getPlugins();

		pluginContainer.configureEach(
			plugin -> {
				if (plugin instanceof ApplicationPlugin) {
					_configurePluginApplication(
						project, compileIncludeConfiguration);
				}
			});

		project.afterEvaluate(
			curProject -> {
				_configureExtensionBundleAfterEvaluate(
					curProject, liferayOSGiExtension,
					compileIncludeConfiguration);
				_configureTaskDeployDependenciesProviderAfterEvaluate(
					deployDependenciesTaskProvider);
			});
	}

	private Configuration _addConfigurationCompileInclude(Project project) {
		Configuration configuration = GradleUtil.addConfiguration(
			project, COMPILE_INCLUDE_CONFIGURATION_NAME);

		configuration.setDescription(
			"Additional dependencies to include in the final JAR.");
		configuration.setVisible(false);

		Configuration compileOnlyConfiguration = GradleUtil.getConfiguration(
			project, JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME);

		compileOnlyConfiguration.extendsFrom(configuration);

		return configuration;
	}

	private BundleExtension _addExtensionBundle(Project project) {
		BundleExtension bundleExtension = new BundleExtension();

		ExtensionContainer extensionContainer = project.getExtensions();

		extensionContainer.add(
			BundleExtension.class, "bundle", bundleExtension);

		return bundleExtension;
	}

	private void _applyPlugins(Project project) {
		GradleUtil.applyPlugin(project, LiferayBasePlugin.class);

		GradleUtil.applyPlugin(project, JavaPlugin.class);

		GradleUtil.applyPlugin(project, CSSBuilderPlugin.class);

		GradleUtil.applyPlugin(project, NodePlugin.class);

		if (GradleUtil.hasTask(
				project, NodePlugin.PACKAGE_RUN_BUILD_TASK_NAME)) {

			GradleUtil.applyPlugin(project, JSTranspilerBasePlugin.class);
		}
		else {
			GradleUtil.applyPlugin(
				project, JSModuleConfigGeneratorPlugin.class);
			GradleUtil.applyPlugin(project, JSTranspilerPlugin.class);
		}

		GradleUtil.applyPlugin(project, EclipsePlugin.class);
		GradleUtil.applyPlugin(project, JavadocFormatterPlugin.class);
		GradleUtil.applyPlugin(project, JspCPlugin.class);
		GradleUtil.applyPlugin(project, LangBuilderPlugin.class);
		GradleUtil.applyPlugin(project, SourceFormatterPlugin.class);
		GradleUtil.applyPlugin(project, SoyPlugin.class);
		GradleUtil.applyPlugin(project, SoyTranslationPlugin.class);
		GradleUtil.applyPlugin(project, TLDDocBuilderPlugin.class);
		GradleUtil.applyPlugin(project, TLDFormatterPlugin.class);
		GradleUtil.applyPlugin(project, TestIntegrationPlugin.class);

		AlloyTaglibDefaultsPlugin.INSTANCE.apply(project);
		CSSBuilderDefaultsPlugin.INSTANCE.apply(project);
		DBSupportDefaultsPlugin.INSTANCE.apply(project);
		EclipseDefaultsPlugin.INSTANCE.apply(project);
		IdeaDefaultsPlugin.INSTANCE.apply(project);
		JSModuleConfigGeneratorDefaultsPlugin.INSTANCE.apply(project);
		JavadocFormatterDefaultsPlugin.INSTANCE.apply(project);
		JspCDefaultsPlugin.INSTANCE.apply(project);
		RESTBuilderDefaultsPlugin.INSTANCE.apply(project);
		ServiceBuilderDefaultsPlugin.INSTANCE.apply(project);
		SpotBugsDefaultsPlugin.INSTANCE.apply(project);
		TLDFormatterDefaultsPlugin.INSTANCE.apply(project);
		TestIntegrationDefaultsPlugin.INSTANCE.apply(project);
		UpgradeTableBuilderDefaultsPlugin.INSTANCE.apply(project);
		WSDDBuilderDefaultsPlugin.INSTANCE.apply(project);
		WatchOSGiPlugin.INSTANCE.apply(project);
	}

	private void _configureConventionBasePlugin(Project project) {
		BasePluginConvention basePluginConvention = GradleUtil.getConvention(
			project, BasePluginConvention.class);

		String bundleSymbolicName = BndUtil.getInstruction(
			project, Constants.BUNDLE_SYMBOLICNAME);

		if (Validator.isNull(bundleSymbolicName)) {
			return;
		}

		Parameters parameters = new Parameters(bundleSymbolicName);

		Set<String> keys = parameters.keySet();

		Iterator<String> iterator = keys.iterator();

		bundleSymbolicName = iterator.next();

		basePluginConvention.setArchivesBaseName(bundleSymbolicName);
	}

	private void _configureExtensionBundle(
		Project project, BundleExtension bundleExtension) {

		File file = project.file("bnd.bnd");

		if (!file.exists()) {
			return;
		}

		UTF8Properties utf8Properties = new UTF8Properties();

		try (Processor processor = new Processor()) {
			utf8Properties.load(file, processor);

			Enumeration<Object> keys = utf8Properties.keys();

			while (keys.hasMoreElements()) {
				String key = (String)keys.nextElement();

				String value = utf8Properties.getProperty(key);

				bundleExtension.put(key, value);
			}
		}
		catch (Exception exception) {
			throw new GradleException("Could not read " + file, exception);
		}
	}

	private void _configureExtensionBundleAfterEvaluate(
		Project project, final LiferayOSGiExtension liferayOSGiExtension,
		final Configuration compileIncludeConfiguration) {

		Map<String, Object> bundleInstructions = BndUtil.getInstructions(
			project);

		IncludeResourceCompileIncludeInstruction
			includeResourceCompileIncludeInstruction =
				new IncludeResourceCompileIncludeInstruction(
					() -> compileIncludeConfiguration,
					liferayOSGiExtension::isExpandCompileInclude);

		bundleInstructions.put(
			Constants.INCLUDERESOURCE + "." +
				compileIncludeConfiguration.getName(),
			includeResourceCompileIncludeInstruction);

		Map<String, Object> bundleDefaultInstructions =
			liferayOSGiExtension.getBundleDefaultInstructions();

		for (Map.Entry<String, Object> entry :
				bundleDefaultInstructions.entrySet()) {

			String key = entry.getKey();

			if (!bundleInstructions.containsKey(key)) {
				bundleInstructions.put(key, entry.getValue());
			}
		}
	}

	private void _configureExtensionLiferay(
		final Project project, final LiferayExtension liferayExtension) {

		liferayExtension.setDeployDir(
			(Callable<File>)() -> {
				File dir = new File(
					liferayExtension.getAppServerParentDir(), "osgi/modules");

				return GradleUtil.getProperty(project, "auto.deploy.dir", dir);
			});
	}

	private void _configurePluginApplication(
		Project project, final Configuration compileIncludeConfiguration) {

		ApplicationPluginConvention applicationPluginConvention =
			GradleUtil.getConvention(
				project, ApplicationPluginConvention.class);

		String mainClassName = BndUtil.getInstruction(project, "Main-Class");

		if (Validator.isNotNull(mainClassName)) {
			applicationPluginConvention.setMainClassName(mainClassName);
		}

		TaskProvider<JavaExec> runTaskProvider = GradleUtil.getTaskProvider(
			project, ApplicationPlugin.TASK_RUN_NAME, JavaExec.class);

		runTaskProvider.configure(
			runTask -> runTask.classpath(compileIncludeConfiguration));
	}

	private void _configureProject(Project project) {
		String description = BndUtil.getInstruction(
			project, Constants.BUNDLE_DESCRIPTION);

		if (Validator.isNull(description)) {
			description = BndUtil.getInstruction(
				project, Constants.BUNDLE_NAME);
		}

		if (Validator.isNotNull(description)) {
			project.setDescription(description);
		}

		String bundleVersion = BndUtil.getInstruction(
			project, Constants.BUNDLE_VERSION);

		if (Validator.isNotNull(bundleVersion)) {
			project.setVersion(bundleVersion);
		}
	}

	private void _configureSourceSetMain(Project project) {
		File docrootDir = project.file("docroot");

		if (!docrootDir.exists()) {
			return;
		}

		SourceSet sourceSet = GradleUtil.getSourceSet(
			project, SourceSet.MAIN_SOURCE_SET_NAME);

		File javaClassesDir = new File(docrootDir, "WEB-INF/classes");

		SourceDirectorySet javaSourceDirectorySet = sourceSet.getJava();

		javaSourceDirectorySet.setOutputDir(javaClassesDir);

		SourceSetOutput sourceSetOutput = sourceSet.getOutput();

		sourceSetOutput.setResourcesDir(javaClassesDir);

		File srcDir = new File(docrootDir, "WEB-INF/src");

		Set<File> srcDirs = Collections.singleton(srcDir);

		javaSourceDirectorySet.setSrcDirs(srcDirs);

		SourceDirectorySet resourcesSourceDirectorySet =
			sourceSet.getResources();

		resourcesSourceDirectorySet.setSrcDirs(srcDirs);
	}

	private void _configureTaskAutoUpdateXmlProvider(
		final Project project, final LiferayExtension liferayExtension,
		final LiferayOSGiExtension liferayOSGiExtension,
		TaskProvider<DirectDeployTask> directDeployTaskProvider,
		TaskProvider<Jar> jarTaskProvider) {

		directDeployTaskProvider.configure(
			directDeployTask -> {
				directDeployTask.setAppServerDeployDir(
					directDeployTask.getTemporaryDir());
				directDeployTask.setAppServerType("tomcat");

				final Jar jarTask = jarTaskProvider.get();

				directDeployTask.setWebAppFile(
					(Callable<File>)() -> FileUtil.replaceExtension(
						jarTask.getArchivePath(), War.WAR_EXTENSION));

				directDeployTask.setWebAppType("portlet");

				directDeployTask.doFirst(
					task -> {
						File jarFile = jarTask.getArchivePath();

						jarFile.renameTo(directDeployTask.getWebAppFile());
					});

				directDeployTask.doLast(
					task -> {
						Logger logger = task.getLogger();

						project.delete("liferay/logs");

						File liferayDir = project.file("liferay");

						boolean deleted = liferayDir.delete();

						if (!deleted && logger.isInfoEnabled()) {
							logger.info("Unable to delete " + liferayDir);
						}
					});

				directDeployTask.doLast(
					task -> {
						String deployedPluginDirName = FileUtil.stripExtension(
							jarTask.getArchiveName());

						File deployedPluginDir = new File(
							directDeployTask.getAppServerDeployDir(),
							deployedPluginDirName);

						if (!deployedPluginDir.exists()) {
							deployedPluginDir = new File(
								directDeployTask.getAppServerDeployDir(),
								project.getName());
						}

						if (!deployedPluginDir.exists()) {
							_logger.warn(
								"Unable to automatically update web.xml in " +
									jarTask.getArchivePath());

							return;
						}

						FileUtil.touchFiles(
							project, deployedPluginDir, 0,
							"WEB-INF/liferay-web.xml", "WEB-INF/web.xml",
							"WEB-INF/tld/*");

						deployedPluginDirName = project.relativePath(
							deployedPluginDir);

						String[][] filesets = {
							{
								project.relativePath(
									liferayExtension.getAppServerPortalDir()),
								"WEB-INF/tld/c.tld"
							},
							{
								deployedPluginDirName,
								"WEB-INF/liferay-web.xml,WEB-INF/web.xml"
							},
							{deployedPluginDirName, "WEB-INF/tld/*"}
						};

						File warFile = directDeployTask.getWebAppFile();

						FileUtil.jar(
							project, warFile, "preserve", true, filesets);

						warFile.renameTo(jarTask.getArchivePath());
					});

				directDeployTask.onlyIf(
					task -> {
						if (liferayOSGiExtension.isAutoUpdateXml() &&
							FileUtil.exists(
								project, "docroot/WEB-INF/portlet.xml")) {

							return true;
						}

						return false;
					});

				TaskInputs taskInputs = directDeployTask.getInputs();

				taskInputs.file((Callable<File>)jarTask::getArchivePath);
			});

		jarTaskProvider.configure(
			jar -> jar.finalizedBy(directDeployTaskProvider));
	}

	private void _configureTaskBuildWSDDJarProvider(
		final Project project, LiferayExtension liferayExtension,
		final LiferayOSGiExtension liferayOSGiExtension,
		final BuildWSDDTask buildWSDDTask,
		TaskProvider<Jar> buildWSDDJarTaskProvider,
		TaskProvider<Delete> cleanTaskProvider,
		TaskProvider<Copy> deployTaskProvider) {

		buildWSDDJarTaskProvider.configure(
			buildWSDDJarTask -> {
				buildWSDDJarTask.setActions(Collections.emptyList());

				buildWSDDJarTask.dependsOn(buildWSDDTask);

				buildWSDDJarTask.doLast(
					task -> {
						Logger logger = project.getLogger();

						Properties gradleProperties = new PropertiesWrapper();

						gradleProperties.put("project", project);
						gradleProperties.put("task", task);

						try (Builder builder = new Builder(
								new Processor(gradleProperties, false))) {

							Map<String, String> properties =
								_getBuilderProperties(
									project, liferayOSGiExtension,
									buildWSDDTask);

							File buildFile = project.getBuildFile();

							builder.setBase(buildFile.getParentFile());

							builder.putAll(properties, true);

							SourceSet sourceSet = GradleUtil.getSourceSet(
								project, SourceSet.MAIN_SOURCE_SET_NAME);

							SourceDirectorySet sourceDirectorySet =
								sourceSet.getJava();

							SourceSetOutput sourceSetOutput =
								sourceSet.getOutput();

							FileCollection buildDirs = project.files(
								sourceDirectorySet.getOutputDir(),
								sourceSetOutput.getResourcesDir());

							builder.setClasspath(
								buildDirs.getFiles(
								).toArray(
									new File[0]
								));
							builder.setProperty(
								"project.buildpath", buildDirs.getAsPath());

							if (logger.isDebugEnabled()) {
								logger.debug(
									"Builder Classpath: {}",
									buildDirs.getAsPath());
							}

							SourceDirectorySet allSource =
								sourceSet.getAllSource();

							Set<File> srcDirs = allSource.getSrcDirs();

							Stream<File> stream = srcDirs.stream();

							FileCollection sourceDirs = project.files(
								stream.filter(
									File::exists
								).collect(
									Collectors.toList()
								));

							builder.setProperty(
								"project.sourcepath", sourceDirs.getAsPath());
							builder.setSourcepath(
								sourceDirs.getFiles(
								).toArray(
									new File[0]
								));

							if (logger.isDebugEnabled()) {
								logger.debug(
									"Builder Sourcepath: {}",
									builder.getSourcePath());
							}

							String bundleSymbolicName = builder.getProperty(
								Constants.BUNDLE_SYMBOLICNAME);

							if (Validator.isNull(bundleSymbolicName) ||
								Constants.EMPTY_HEADER.equals(
									bundleSymbolicName)) {

								builder.setProperty(
									Constants.BUNDLE_SYMBOLICNAME,
									project.getName());
							}

							String bundleVersion = builder.getProperty(
								Constants.BUNDLE_VERSION);

							if ((Validator.isNull(bundleVersion) ||
								 Constants.EMPTY_HEADER.equals(
									 bundleVersion)) &&
								(project.getVersion() != null)) {

								Object version = project.getVersion();

								MavenVersion mavenVersion =
									MavenVersion.parseString(
										version.toString());

								Version osgiVersion =
									mavenVersion.getOSGiVersion();

								builder.setProperty(
									Constants.BUNDLE_VERSION,
									osgiVersion.toString());
							}

							if (logger.isDebugEnabled()) {
								logger.debug(
									"Builder Properties: {}", properties);
							}

							aQute.bnd.osgi.Jar bndJar = builder.build();

							if (!builder.isOk()) {
								BndUtils.logReport(builder, logger);

								new GradleException(buildWSDDTask + " failed");
							}

							TaskOutputs taskOutputs = task.getOutputs();

							FileCollection fileCollection =
								taskOutputs.getFiles();

							bndJar.write(fileCollection.getSingleFile());

							BndUtils.logReport(builder, logger);

							if (!builder.isOk()) {
								new GradleException(buildWSDDTask + " failed");
							}
						}
						catch (Exception exception) {
							new GradleException(
								buildWSDDTask + " failed", exception);
						}
					});

				String taskName = buildWSDDTask.getName();

				if (taskName.equals(WSDDBuilderPlugin.BUILD_WSDD_TASK_NAME)) {
					buildWSDDJarTask.setAppendix("wsdd");
				}
				else {
					buildWSDDJarTask.setAppendix("wsdd-" + taskName);
				}

				buildWSDDTask.finalizedBy(buildWSDDJarTask);
			});

		_configureTaskCleanProvider(
			liferayExtension, cleanTaskProvider, deployTaskProvider,
			buildWSDDJarTaskProvider);
		_configureTaskDeployProvider(
			project, liferayExtension, deployTaskProvider,
			buildWSDDJarTaskProvider, true);
	}

	private void _configureTaskCleanProvider(
		final LiferayExtension liferayExtension,
		TaskProvider<Delete> cleanTaskProvider,
		final TaskProvider<Copy> deployTaskProvider,
		final TaskProvider<Jar> jarTaskProvider) {

		cleanTaskProvider.configure(
			cleanTask -> cleanTask.delete(
				(Callable<File>)() -> {
					boolean cleanDeployed = GradleUtil.getProperty(
						cleanTask, CLEAN_DEPLOYED_PROPERTY_NAME, true);

					if (!cleanDeployed) {
						return null;
					}

					Copy deployTask = deployTaskProvider.get();
					Jar jarTask = jarTaskProvider.get();

					Closure<String> deployedFileNameClosure =
						liferayExtension.getDeployedFileNameClosure();

					return new File(
						deployTask.getDestinationDir(),
						deployedFileNameClosure.call(jarTask));
				}));
	}

	private void _configureTaskCleanProvider(
		final Project project, TaskProvider<Delete> cleanTaskProvider) {

		cleanTaskProvider.configure(
			cleanTask -> {
				Closure<Set<String>> c = new Closure<Set<String>>(project) {

					@SuppressWarnings("unused")
					public Set<String> doCall(Delete delete) {
						Set<String> cleanTaskNames = new HashSet<>();

						Project project = delete.getProject();

						for (Task task : project.getTasks()) {
							String taskName = task.getName();

							if (taskName.equals(DEPLOY_FAST_TASK_NAME) ||
								taskName.equals(
									LiferayBasePlugin.DEPLOY_TASK_NAME) ||
								taskName.equals("eclipseClasspath") ||
								taskName.equals("eclipseProject") ||
								taskName.equals("ideaModule") ||
								(task instanceof BuildSoyTask) ||
								(task instanceof DownloadNodeModuleTask) ||
								(task instanceof NpmInstallTask)) {

								continue;
							}

							if (GradleUtil.hasPlugin(
									project, _CACHE_PLUGIN_ID) &&
								taskName.startsWith("save") &&
								taskName.endsWith("Cache")) {

								continue;
							}

							if (GradleUtil.hasPlugin(
									project, WSDLBuilderPlugin.class) &&
								taskName.startsWith(
									WSDLBuilderPlugin.BUILD_WSDL_TASK_NAME +
										"Generate")) {

								continue;
							}

							boolean autoClean = GradleUtil.getProperty(
								task, AUTO_CLEAN_PROPERTY_NAME, true);

							if (!autoClean) {
								continue;
							}

							TaskOutputs taskOutputs = task.getOutputs();

							if (!taskOutputs.getHasOutput()) {
								continue;
							}

							cleanTaskNames.add(
								BasePlugin.CLEAN_TASK_NAME +
									StringUtil.capitalize(taskName));
						}

						return cleanTaskNames;
					}

				};

				cleanTask.dependsOn(c);
			});
	}

	private void _configureTaskDeployDependenciesProvider(
		final LiferayExtension liferayExtension,
		TaskProvider<Copy> deployDependenciesTaskProvider) {

		deployDependenciesTaskProvider.configure(
			deployDependenciesTask -> {
				boolean keepVersions = Boolean.getBoolean(
					"deploy.dependencies.keep.versions");

				GradleUtil.setProperty(
					deployDependenciesTask,
					LiferayOSGiPlugin.AUTO_CLEAN_PROPERTY_NAME, false);
				GradleUtil.setProperty(
					deployDependenciesTask, "keepVersions", keepVersions);

				String renameSuffix = ".jar";

				if (keepVersions) {
					renameSuffix = "-$1.jar";
				}

				GradleUtil.setProperty(
					deployDependenciesTask, "renameSuffix", renameSuffix);

				deployDependenciesTask.into(
					(Callable<File>)liferayExtension::getDeployDir);

				deployDependenciesTask.setDescription(
					"Deploys additional dependencies.");

				TaskOutputs taskOutputs = deployDependenciesTask.getOutputs();

				taskOutputs.upToDateWhen(task -> false);
			});
	}

	private void _configureTaskDeployDependenciesProviderAfterEvaluate(
		TaskProvider<Copy> deployDependenciesTaskProvider) {

		deployDependenciesTaskProvider.configure(
			deployDependenciesTask -> deployDependenciesTask.eachFile(
				new RenameDependencyAction(
					Boolean.getBoolean("deploy.dependencies.keep.versions"))));
	}

	private void _configureTaskDeployFastProvider(
		final Project project, final LiferayExtension liferayExtension,
		final TaskProvider<Task> classesTaskProvider,
		final TaskProvider<JavaCompile> compileJSPTaskProvider,
		TaskProvider<Copy> deployFastTaskProvider,
		TaskProvider<Copy> processResourcesTaskProvider) {

		deployFastTaskProvider.configure(
			deployFastTask -> {
				deployFastTask.setDescription(
					"Builds and deploys resources to the Liferay work " +
						"directory.");
				deployFastTask.setGroup(LifecycleBasePlugin.BUILD_GROUP);

				deployFastTask.setDestinationDir(
					liferayExtension.getLiferayHome());
				deployFastTask.setIncludeEmptyDirs(false);

				String bundleSymbolicName = BndUtil.getInstruction(
					project, Constants.BUNDLE_SYMBOLICNAME);
				String bundleVersion = BndUtil.getInstruction(
					project, Constants.BUNDLE_VERSION);

				StringBuilder sb = new StringBuilder();

				sb.append("work/");
				sb.append(bundleSymbolicName);
				sb.append("-");
				sb.append(bundleVersion);

				final String pathName = sb.toString();

				deployFastTask.from(
					compileJSPTaskProvider,
					new Closure<Void>(project) {

						@SuppressWarnings("unused")
						public void doCall(CopySpec copySpec) {
							copySpec.into(pathName);
						}

					});

				deployFastTask.from(
					processResourcesTaskProvider,
					new Closure<Void>(project) {

						@SuppressWarnings("unused")
						public void doCall(CopySpec copySpec) {
							Action<FileCopyDetails> action = details -> {
								RelativePath relativePath =
									details.getRelativePath();

								String[] segments = relativePath.getSegments();

								if ((segments.length > 4) &&
									segments[2].equals("META-INF") &&
									segments[3].equals("resources")) {

									List<String> list = new ArrayList<>();

									list.add(segments[0]);
									list.add(segments[1]);

									for (int i = 4; i < segments.length; i++) {
										String segment = segments[i];

										if (!segment.equals(".sass-cache")) {
											list.add(segment);
										}
									}

									segments = list.toArray(new String[0]);
								}

								details.setRelativePath(
									new RelativePath(true, segments));
							};

							copySpec.eachFile(action);

							copySpec.include("**/*.css");
							copySpec.include("**/*.css.map");
							copySpec.into(pathName);
						}

					});

				deployFastTask.dependsOn(classesTaskProvider);

				SourceSet mainSourceSet = GradleUtil.getSourceSet(
					project, SourceSet.MAIN_SOURCE_SET_NAME);

				deployFastTask.from(
					mainSourceSet.getOutput(),
					new Closure<Void>(project) {

						@SuppressWarnings("unused")
						public void doCall(CopySpec copySpec) {
							Action<FileCopyDetails> action = details -> {
								RelativePath relativePath =
									details.getRelativePath();

								String[] segments = relativePath.getSegments();

								if ((segments.length > 4) &&
									segments[2].equals("META-INF") &&
									segments[3].equals("resources")) {

									List<String> list = new ArrayList<>();

									list.add(segments[0]);
									list.add(segments[1]);

									for (int i = 4; i < segments.length; i++) {
										list.add(segments[i]);
									}

									segments = list.toArray(new String[0]);
								}

								details.setRelativePath(
									new RelativePath(true, segments));
							};

							copySpec.eachFile(action);

							copySpec.include("**/*.js");
							copySpec.include("**/*.js.map");
							copySpec.into(pathName);
						}

					});
			});
	}

	private void _configureTaskDeployProvider(
		final Project project, final LiferayExtension liferayExtension,
		TaskProvider<Copy> deployTaskProvider,
		final TaskProvider<Jar> jarTaskProvider, boolean lazy) {

		deployTaskProvider.configure(
			deployTask -> {
				final Jar jarTask = jarTaskProvider.get();

				Object sourcePath = jarTask;

				if (lazy) {
					sourcePath = (Callable<File>)() -> jarTask.getArchivePath();
				}

				Closure<Void> copySpecClosure = new Closure<Void>(project) {

					@SuppressWarnings("unused")
					public void doCall(CopySpec copySpec) {
						copySpec.rename(
							new Closure<String>(project) {

								public String doCall(String fileName) {
									Closure<String> closure =
										liferayExtension.
											getDeployedFileNameClosure();

									return closure.call(jarTask);
								}

							});
					}

				};

				deployTask.from(sourcePath, copySpecClosure);
			});
	}

	private void _configureTaskDeployProvider(
		TaskProvider<Copy> deployTaskProvider,
		final TaskProvider<Copy> deployDependenciesTaskProvider) {

		deployTaskProvider.configure(
			deployTask -> deployTask.finalizedBy(
				deployDependenciesTaskProvider));
	}

	private void _configureTaskJarProvider(
		final Project project, TaskProvider<Jar> jarTaskProvider) {

		jarTaskProvider.configure(
			jarTask -> {
				Convention convention = jarTask.getConvention();

				Map<String, Object> plugins = convention.getPlugins();

				final BundleTaskConvention bundleTaskConvention =
					new BundleTaskConvention(jarTask);

				plugins.put("bundle", bundleTaskConvention);

				jarTask.setDescription(
					"Assembles a bundle containing the main classes.");

				jarTask.doFirst(
					task -> {
						Map<String, Object> instructions =
							BndUtil.getInstructions(project);

						instructions.forEach(
							(k, v) -> instructions.put(
								k, GradleUtil.toString(v)));

						Map<String, ?> projectProperties =
							project.getProperties();

						for (Map.Entry<String, ?> entry :
								projectProperties.entrySet()) {

							String key = entry.getKey();
							Object value = entry.getValue();

							Matcher matcher = _keyRegex.matcher(key);

							if (matcher.matches() &&
								(value instanceof String)) {

								instructions.put(key, entry.getValue());
							}
						}

						bundleTaskConvention.setBnd(instructions);
					});

				jarTask.doLast(task -> bundleTaskConvention.buildBundle());

				File bndFile = project.file("bnd.bnd");

				if (!bndFile.exists()) {
					return;
				}

				TaskInputs taskInputs = jarTask.getInputs();

				taskInputs.file(bndFile);
			});
	}

	private void _configureTaskJavaCompile(
		JavaCompile javaCompile, boolean fork) {

		if (GradleUtil.isRunningInsideDaemon()) {
			CompileOptions compileOptions = javaCompile.getOptions();

			compileOptions.setFork(fork);
		}
	}

	private void _configureTaskJavadocProvider(
		Project project, TaskProvider<Javadoc> javadocTaskProvider) {

		String bundleName = BndUtil.getInstruction(
			project, Constants.BUNDLE_NAME);
		String bundleVersion = BndUtil.getInstruction(
			project, Constants.BUNDLE_VERSION);

		if (Validator.isNull(bundleName) || Validator.isNull(bundleVersion)) {
			return;
		}

		javadocTaskProvider.configure(
			javadocTask -> javadocTask.setTitle(
				String.format("%s %s API", bundleName, bundleVersion)));
	}

	private void _configureTaskTest(Test test) {
		test.setDefaultCharacterEncoding(StandardCharsets.UTF_8.name());
	}

	private void _configureTaskTestProvider(
		TaskProvider<Test> testTaskProvider) {

		testTaskProvider.configure(
			testTask -> {
				testTask.jvmArgs(
					"-Djava.net.preferIPv4Stack=true", "-Dliferay.mode=test",
					"-Duser.timezone=GMT");
				testTask.setForkEvery(1L);
			});
	}

	private Map<String, String> _getBuilderProperties(
		Project project, LiferayOSGiExtension liferayOSGiExtension,
		BuildWSDDTask buildWSDDTask) {

		Map<String, String> properties = GradleUtil.toStringMap(
			liferayOSGiExtension.getBundleDefaultInstructions());

		Map<String, ?> projectProperties = project.getProperties();

		for (Map.Entry<String, ?> entry : projectProperties.entrySet()) {
			String key = entry.getKey();

			if (Character.isLowerCase(key.charAt(0))) {
				properties.put(key, GradleUtil.toString(entry.getValue()));
			}
		}

		properties.remove(Constants.DONOTCOPY);
		properties.remove(
			LiferayOSGiExtension.
				BUNDLE_DEFAULT_INSTRUCTION_LIFERAY_SERVICE_XML);

		String bundleName = BndUtil.getInstruction(
			project, Constants.BUNDLE_NAME);

		if (Validator.isNotNull(bundleName)) {
			properties.put(
				Constants.BUNDLE_NAME, bundleName + " WSDD descriptors");
		}

		String bundleSymbolicName = BndUtil.getInstruction(
			project, Constants.BUNDLE_SYMBOLICNAME);

		properties.put(
			Constants.BUNDLE_SYMBOLICNAME, bundleSymbolicName + ".wsdd");
		properties.put(Constants.FRAGMENT_HOST, bundleSymbolicName);

		properties.put(
			Constants.IMPORT_PACKAGE, "javax.servlet,javax.servlet.http");

		StringBuilder sb = new StringBuilder();

		sb.append("WEB-INF/=");
		sb.append(
			FileUtil.getRelativePath(
				project, buildWSDDTask.getServerConfigFile()));
		sb.append(',');
		sb.append(
			FileUtil.getRelativePath(project, buildWSDDTask.getOutputDir()));
		sb.append(";filter:=*.wsdd");

		properties.put(Constants.INCLUDE_RESOURCE, sb.toString());

		return properties;
	}

	private static final String _CACHE_PLUGIN_ID = "com.liferay.cache";

	private static final Logger _logger = Logging.getLogger(
		LiferayOSGiPlugin.class);

	private static final Pattern _keyRegex = Pattern.compile(
		"[a-z][\\p{Alnum}-_.]*");

}