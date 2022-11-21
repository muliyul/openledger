import org.jetbrains.kotlin.gradle.tasks.*

plugins {
	kotlin("jvm") version "1.7.10"
	application
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.muliyul"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/ru.vyarus.guicey/guicey-bom
	implementation(platform("ru.vyarus.guicey:guicey-bom:5.6.1-1"))
	implementation("io.dropwizard:dropwizard-auth")
	implementation("io.dropwizard.modules:dropwizard-flyway:2.1.0-1")
	implementation("io.github.dropwizard-jobs:dropwizard-jobs-guice:4.0.0-RELEASE")

	implementation("de.ahus1.keycloak.dropwizard:keycloak-dropwizard:1.2.0")

	// https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jdbi:jdbi3-jackson2")

	implementation("org.jdbi:jdbi3-kotlin-sqlobject")

	// https://mvnrepository.com/artifact/com.github.mtakaki/dropwizard-hikaricp
	implementation("com.github.mtakaki:dropwizard-hikaricp:2.0.20")

	// https://mvnrepository.com/artifact/ru.vyarus/dropwizard-guicey
	implementation("ru.vyarus:dropwizard-guicey")
	// https://mvnrepository.com/artifact/ru.vyarus.guicey/guicey-jdbi3
	implementation("ru.vyarus.guicey:guicey-jdbi3")

	implementation("ru.vyarus.guicey:guicey-admin-rest")

	// https://mvnrepository.com/artifact/org.keycloak/keycloak-admin-client
	implementation("org.keycloak:keycloak-admin-client:19.0.2")

	// https://mvnrepository.com/artifact/org.jdbi/jdbi3-kotlin
	implementation("org.jdbi:jdbi3-kotlin")
	implementation("org.jdbi:jdbi3-kotlin-sqlobject")

	// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")

	// https://mvnrepository.com/artifact/org.flywaydb/flyway-mysql
	implementation("org.flywaydb:flyway-mysql:8.5.13")

	implementation("io.swagger.core.v3:swagger-jaxrs2:2.2.2")

	// https://mvnrepository.com/artifact/mysql/mysql-connector-java
	runtimeOnly("mysql:mysql-connector-java:8.0.30")


	testImplementation(kotlin("test-junit5"))
	// https://mvnrepository.com/artifact/org.mockito.kotlin/mockito-kotlin
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "17"
	}
}

tasks.test {
	useJUnitPlatform()
}

application {
	mainClass.set("com.muliyul.kropwizard.KropwizardServerKt")
}

tasks.shadowJar {
	mergeServiceFiles()
}
