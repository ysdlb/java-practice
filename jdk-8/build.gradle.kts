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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}