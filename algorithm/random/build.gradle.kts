plugins {
    `java-library`
}

dependencies {
    api("org.junit:junit-bom:5.9.0")?.let { platform(it) }

    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.projectlombok:lombok:1.18.24")
    implementation("org.openjdk.jmh:jmh-core:1.35")
    implementation("org.openjdk.jmh:jmh-generator-annprocess:1.35")
}
