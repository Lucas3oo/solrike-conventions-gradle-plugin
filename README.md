# solrike-conventions-gradle-plugin
Gradle convention plugins for Java projects.

A Gradle
[convention plugin](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins)
is like setting new default values on plugins. Those new default values can be easily shared between a number of
projects by just depend on this plugin.
The plugin contains *.gradle files and it is what Gradle calls
[precompiled script plugin](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:precompiled_plugins).

The following plugins are applied and configured when using this plugin:
* checkstyle
* eclipse
* jacoco
* java
* com.diffplug.spotless
* com.github.spotbugs
* org.owasp.dependencycheck
* se.solrike.sonarlint

Also the Maven Central is configured as repository.

## Usage
### Apply to your project
Apply the plugin to your project.

```groovy
plugins {
  id 'se.solrike.conventions.java-conventions' version '1.0.0-beta.7'
}
```

## Description
See [the gradle file](./src/main/groovy/se.solrike.conventions.java-conventions.gradle) for the exact configuration for
each plugin.

## Customisation
Typically the configuration can be overridden in a project.

To override the spotbugs exclude filter:

```groovy
spotbugsMain {
  excludeFilter = rootProject.file('plugin-config/spotbugs/exclude.xml')
}
```

To configure plugins for the SonarLint plugin:

```groovy
sonarlint {
  dependencies {
    sonarlintPlugins 'org.sonarsource.html:sonar-html-plugin:3.6.0.3106'
    sonarlintPlugins 'org.sonarsource.java:sonar-java-plugin:7.17.0.31219'
    sonarlintPlugins 'org.sonarsource.javascript:sonar-javascript-plugin:8.8.0.17228' // both JS and TS
    sonarlintPlugins 'org.sonarsource.typescript:sonar-typescript-plugin:2.1.0.4359'
    sonarlintPlugins 'org.sonarsource.xml:sonar-xml-plugin:2.6.1.3686'
    // include a plugin not in Maven repo
    sonarlintPlugins files("${System.getProperty('user.home')}/.p2/pool/plugins/org.sonarlint.eclipse.core_7.2.1.42550/plugins/sonar-secrets-plugin-1.1.0.36766.jar")
  }
}
```


## Release notes
### 1.0.0-beta.5
Enable SARIF format for the reports from Checkstyle, Sonarlint, Sprotbugs and OWASP Dependency Check so that
modern CI:s like Github actions and AWS CodeCatalyst can display issues using their standard support.

### 1.0.0-beta.3
Update versions on plugins and adding a util class for build numbers.

### 1.0.0-beta.2
Add eclipse plugin.

### 1.0.0-beta.1
First version with support for generic Java projects.


