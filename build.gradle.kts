plugins {
    kotlin("jvm") version "1.5.0"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.ajoberstar.organize-imports") version "1.0.3"
}
group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
    testImplementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}