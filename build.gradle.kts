plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.31"
    id("io.quarkus") version "1.0.1.Final"
}

allprojects {
    apply(plugin = "idea")
    apply(plugin = "jacoco")
    apply(plugin = "eclipse")
    apply("$rootDir/dependencyVersions.gradle.kts")

    repositories {
        mavenLocal()
        mavenCentral()
    }

    group = "net.adoptopenjdk.api.v3"
    version = "3.0.0-SNAPSHOT"

    dependencies {
        kotlin("stdlib-jdk8")
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.quarkus")

    dependencies {
        implementation(enforcedPlatform("io.quarkus:quarkus-universe-bom:${rootProject.ext.get("quarkusVersion")}"))
        implementation("com.google.guava:guava")
        implementation("io.quarkus:quarkus-kotlin")
        implementation("io.quarkus:quarkus-resteasy")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.slf4j:slf4j-api:${rootProject.ext.get("slf4jVersion")}")
        implementation("ch.qos.logback:logback-classic:${rootProject.ext.get("logbackVersion")}")
        implementation("ch.qos.logback:logback-core:${rootProject.ext.get("logbackVersion")}")

        testImplementation("io.quarkus:quarkus-junit5")
        testImplementation("io.rest-assured:rest-assured")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${rootProject.ext.get("kotlinVersion")}")

        nativeTestImplementation("io.quarkus:quarkus-junit5")
        nativeTestImplementation("io.rest-assured:rest-assured")
    }

    configurations.all {
        tasks {
            compileKotlin {
                kotlinOptions.jvmTarget = "11"
            }
            compileTestKotlin {
                kotlinOptions.jvmTarget = "11"
            }
            test {
                useJUnitPlatform()
            }
        }
    }
}
