/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.performance.generator

import groovy.transform.CompileStatic
import org.gradle.test.fixtures.language.Language

import static org.gradle.test.fixtures.dsl.GradleDsl.KOTLIN

/**
 * NOTE: Each subproject contains of 3 modules (main and test) + project itself
 */
@CompileStatic
enum JavaTestProjectGenerator {
    MODULES_15_JAVA_MULTI_PROJECT_WITH_DEPENDENCIES(new TestProjectGeneratorConfigurationBuilder("modules15WithDependenciesProject")
        .withSourceFiles(10000) // N on input will result in 2N output (N src + N test)
    // + configure manually in [TestProjectGeneratorConfiguration] how many dependencies to include (4200 ?)
        .withSubProjects(5)
        .withDaemonMemory('2G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .create()
    ),

    MONOLITH_4_000_000_WITH_DEPENDENCIES_PROJECT(new TestProjectGeneratorConfigurationBuilder("monolith4000000WithDependenciesProject")
        .withSourceFiles(2000000) // N on input will result in 2N output (N src + N test)
    // + configure manually in [TestProjectGeneratorConfiguration] how many dependencies to include (4200 ?)
        .withSubProjects(1)
        .withDaemonMemory('16G')
        .withCompilerMemory('4G')
        .withBuildSrc(true)
        .create()
    ),
    MONOLITH_2_000_000_WITH_DEPENDENCIES_PROJECT(new TestProjectGeneratorConfigurationBuilder("monolith2000000WithDependenciesProject")
        .withSourceFiles(1000000) // N on input will result in 2N output (N src + N test)
    // + configure manually in [TestProjectGeneratorConfiguration] how many dependencies to include (4200 ?)
        .withSubProjects(1)
        .withDaemonMemory('10G')
        .withCompilerMemory('4G')
        .withBuildSrc(true)
        .create()
    ),

    MODULES_15000_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules15000CompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(5000)
        .withDaemonMemory('10G')
        .withCompilerMemory('2G')
        .withBuildSrc(true)
        .composite(true)
        .create()
    ),
    MODULES_5000_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules5000CompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(1666)
        .withDaemonMemory('8G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .composite(true)
        .create()
    ),
    MODULES_4377_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules4377NOTCompositeJavaMultiProject")
        .withSourceFiles(100) // N src + N test = 2N
        .withSubProjects(1450)
        .withDaemonMemory('8G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .create()
    ),
    MODULES_1000_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules1000NOTCompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(333)
        .withDaemonMemory('6G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .create()
    ),
    MODULES_1000_COMPOSITE_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules1000CompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(333)
        .withDaemonMemory('6G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .composite(true)
        .create()
    ),
    MODULES_500_COMPOSITE_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules500CompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(167)
        .withDaemonMemory('4G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .composite(true)
        .create()
    ),
    MODULES_500_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("modules500NOTCompositeJavaMultiProject")
        .withSourceFiles(100) // N on input will result in 2N output (N src + N test)
        .withSubProjects(167)
        .withDaemonMemory('4G')
        .withCompilerMemory('1G')
        .withBuildSrc(true)
        .create()
    ),

    HUGE_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder('hugeJavaMultiProject')
        .withSourceFiles(100)
        .withSubProjects(500)
        .withDaemonMemory('10G')
        .withCompilerMemory('10g')
        .assembleChangeFile()
        .testChangeFile(450, 2250, 45000)
        .create()
    ),
    LARGE_MONOLITHIC_JAVA_PROJECT(new TestProjectGeneratorConfigurationBuilder("largeMonolithicJavaProject")
        .withSourceFiles(50000)
        .withSubProjects(0)
        .withDaemonMemory('1536m')
        .withCompilerMemory('4g')
        .assembleChangeFile(-1)
        .testChangeFile(-1)
        .create()),
    LARGE_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("largeJavaMultiProject")
        .withSourceFiles(10)
        .withSubProjects(5000)
        .withDaemonMemory('6G')
        .withCompilerMemory('6G')
        .assembleChangeFile()
        .testChangeFile(450, 2250, 45000).create()),
    LARGE_MONOLITHIC_GROOVY_PROJECT(new TestProjectGeneratorConfigurationBuilder("largeMonolithicGroovyProject", Language.GROOVY)
        .withSourceFiles(50000)
        .withSubProjects(0)
        .withDaemonMemory('3g')
        .withCompilerMemory('6g')
        .withSystemProperties(['org.gradle.groovy.compilation.avoidance': 'true'])
        .withFeaturePreviews('GROOVY_COMPILATION_AVOIDANCE')
        .assembleChangeFile(-1)
        .testChangeFile(-1).create()),
    LARGE_GROOVY_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("largeGroovyMultiProject", Language.GROOVY)
        .withSourceFiles(100)
        .withSubProjects(500)
        .withDaemonMemory('1536m')
        .withCompilerMemory('256m')
        .withSystemProperties(['org.gradle.groovy.compilation.avoidance': 'true'])
        .withFeaturePreviews('GROOVY_COMPILATION_AVOIDANCE')
        .assembleChangeFile()
        .testChangeFile(450, 2250, 45000).create()),
    LARGE_JAVA_MULTI_PROJECT_NO_BUILD_SRC(
        new TestProjectGeneratorConfigurationBuilder("largeJavaMultiProjectNoBuildSrc", "largeJavaMultiProject")
            .withBuildSrc(false)
            .withSourceFiles(100)
            .withSubProjects(500)
            .withDaemonMemory('1536m')
            .withCompilerMemory('256m')
            .assembleChangeFile()
            .testChangeFile(450, 2250, 45000)
            .create()
    ),
    LARGE_JAVA_MULTI_PROJECT_KOTLIN_DSL(new TestProjectGeneratorConfigurationBuilder("largeJavaMultiProjectKotlinDsl", "largeJavaMultiProject")
        .withSourceFiles(100)
        .withSubProjects(500)
        .withDaemonMemory('1536m')
        .withCompilerMemory('256m')
        .assembleChangeFile()
        .testChangeFile(450, 2250, 45000)
        .withDsl(KOTLIN)
        .create()),

    MEDIUM_MONOLITHIC_JAVA_PROJECT(new TestProjectGeneratorConfigurationBuilder("mediumMonolithicJavaProject")
        .withSourceFiles(10000)
        .withSubProjects(0)
        .withDaemonMemory('512m')
        .withCompilerMemory('1g')
        .assembleChangeFile(-1)
        .create()),
    MEDIUM_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("mediumJavaMultiProject")
        .withSourceFiles(100)
        .withSubProjects(100)
        .withDaemonMemory('512m')
        .withCompilerMemory('256m')
        .assembleChangeFile()
        .create()),
    MEDIUM_JAVA_COMPOSITE_BUILD(new TestProjectGeneratorConfigurationBuilder("mediumJavaCompositeBuild", "mediumJavaMultiProject")
        .withSourceFiles(100)
        .withSubProjects(100)
        .withDaemonMemory('768m')
        .withCompilerMemory('256m')
        .assembleChangeFile()
        .composite(false)
        .create()),
    MEDIUM_JAVA_PREDEFINED_COMPOSITE_BUILD(new TestProjectGeneratorConfigurationBuilder("mediumJavaPredefinedCompositeBuild", "mediumJavaMultiProject")
        .withSourceFiles(100)
        .withSubProjects(100)
        .withDaemonMemory('768m')
        .withCompilerMemory('256m')
        .assembleChangeFile()
        .composite(true)
        .create()),
    MEDIUM_JAVA_MULTI_PROJECT_WITH_TEST_NG(new TestProjectGeneratorConfigurationBuilder("mediumJavaMultiProjectWithTestNG")
        .withSourceFiles(100)
        .withSubProjects(100)
        .withDaemonMemory('512m')
        .withCompilerMemory('256m')
        .assembleChangeFile()
        .testChangeFile(50, 250, 5000)
        .withUseTestNG(true)
        .create()),
    SMALL_JAVA_MULTI_PROJECT(new TestProjectGeneratorConfigurationBuilder("smallJavaMultiProject")
        .withSourceFiles(50)
        .withSubProjects(10)
        .withDaemonMemory("256m")
        .withCompilerMemory("64m")
        .assembleChangeFile()
        .create()),
    SMALL_JAVA_MULTI_PROJECT_NO_BUILD_SRC(new TestProjectGeneratorConfigurationBuilder('smallJavaMultiProjectNoBuildSrc')
        .withSourceFiles(50)
        .withSubProjects(10)
        .withDaemonMemory("256m")
        .withCompilerMemory("64m")
        .assembleChangeFile()
        .withBuildSrc(false).create())

    private TestProjectGeneratorConfiguration config

    JavaTestProjectGenerator(TestProjectGeneratorConfiguration config) {
        this.config = config
    }

    TestProjectGeneratorConfiguration getConfig() {
        return config
    }

    String getProjectName() {
        return config.projectName
    }

    String getDaemonMemory() {
        return config.daemonMemory
    }

    def getParallel() {
        return config.parallel
    }

    def getMaxWorkers() {
        return config.maxWorkers
    }

    @Override
    String toString() {
        return config.projectName
    }
}
