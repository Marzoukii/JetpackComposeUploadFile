# Fix Incompatible Gradle JVM Version

The project is currently configured to use Java 25 (bundled with Android Studio) to run Gradle builds. However, Gradle 8.13 only supports Java versions up to 23. This plan updates the Gradle JDK configuration to use a compatible Java 21 JDK found on the system.

## Proposed Changes

### Gradle Configuration

#### [MODIFY] [.gradle/config.properties](file:///C:/Users/user/StudioProjects/JetpackComposeUploadFile/.gradle/config.properties)
Update `java.home` to point to the compatible Java 21 JDK.

#### [MODIFY] [.idea/gradle.xml](file:///C:/Users/user/StudioProjects/JetpackComposeUploadFile/.idea/gradle.xml)
Ensure `gradleJvm` is set to `#GRADLE_LOCAL_JAVA_HOME` to respect the `config.properties` setting, or explicitly set it to a compatible JDK if preferred. (We will stick with `#GRADLE_LOCAL_JAVA_HOME` as it is the project-local way).

## Verification Plan

### Automated Tests
- Run `gradle_sync` to verify that the project synchronizes successfully with the new JDK.

### Manual Verification
- Verify that the "Incompatible Gradle JVM version" error message disappears in the IDE.
