import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.1"
}

val pluginGroup: String by project
val pluginName_: String by project
val pluginVersion: String by project

val pluginSinceVersion: String by project
val pluginUntilVersion: String by project

val platformType: String by project
val platformVersion: String by project
val platformPlugins: String by project

group = pluginGroup
version = pluginVersion

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create(platformType, platformVersion)
        bundledPlugins(platformPlugins.split(",").map(String::trim).filter(String::isNotEmpty))
    }
}

intellijPlatform {
    pluginConfiguration {
        name = pluginName_
        ideaVersion {
            sinceBuild = pluginSinceVersion
            untilBuild = provider { null }
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }
}
