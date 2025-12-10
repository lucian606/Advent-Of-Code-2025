
plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/data2viz/p/maven/public") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.data2viz.geojson:core:0.6.6")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}