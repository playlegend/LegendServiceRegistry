group = "net.playlegend"
version = "1.0.0"

plugins {
    `java-library`
    `maven-publish`
    checkstyle
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("com.gorylenko.gradle-git-properties") version "2.2.4"
    id("com.github.spotbugs") version "4.6.2"
}

tasks.create<Copy>("copyHooks") {
    from(file("./hooks/"))
    into(file("./.git/hooks/"))
}

tasks.getByPath("prepareKotlinBuildScriptModel").dependsOn("copyHooks")

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "checkstyle")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "com.gorylenko.gradle-git-properties")
    apply(plugin = "com.github.spotbugs")

    checkstyle {
        toolVersion = "8.39"
        config = project.resources.text.fromUri("https://assets.playlegend.net/checkstyle.xml")
    }

    gitProperties {
        gitPropertiesName = "git.properties"
        dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        keys = arrayOf("git.branch", "git.build.host", "git.build.version", "git.commit.id", "git.commit.id.abbrev",
                "git.commit.message.full", "git.commit.message.short", "git.commit.time", "git.commit.user.email",
                "git.commit.user.name", "git.remote.origin.url", "git.total.commit.count").toMutableList()
    }

    spotbugs {
        ignoreFailures.set(true)
        showProgress.set(true)
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains:annotations:20.1.0")
        compileOnly("org.projectlombok:lombok:1.18.20")
        annotationProcessor("org.projectlombok:lombok:1.18.18")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        withJavadocJar()
    }

    tasks.javadoc {
        options.encoding = "UTF-8"
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }

    tasks.withType<JavaCompile> { options.encoding = "UTF-8" }
}
