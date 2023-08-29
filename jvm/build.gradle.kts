plugins {
    id("java")
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(8)) }
}

group = "org.example"
version = "unspecified"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openjdk.jol:jol-core:0.16")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}