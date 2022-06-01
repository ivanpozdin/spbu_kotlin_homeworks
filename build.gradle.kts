plugins {
    kotlin("jvm") version "1.6.21"
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
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.20.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.10")
    implementation("org.jetbrains.lets-plot:lets-plot-common:2.3.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.3.0")
    implementation("org.slf4j:slf4j-simple:1.7.36")
}
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
