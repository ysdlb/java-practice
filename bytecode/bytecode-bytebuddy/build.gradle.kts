plugins {
    id("java")
}

group = "org.ysdlb"
version = "0.0.1"

dependencies {
    // https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy
    implementation("net.bytebuddy:byte-buddy:1.14.1")
    implementation("net.bytebuddy:byte-buddy-dep:1.14.1")

    implementation("net.bytebuddy:byte-buddy-agent:1.14.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation(project(mapOf("path" to ":java-util")))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}