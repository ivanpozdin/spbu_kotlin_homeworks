plugins {
    kotlin("jvm") version "1.5.0"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}
group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}