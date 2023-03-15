plugins {
    id("java")
}

group = "org.ysdlb"
version = "0.0.1"

dependencies {
    // https://mvnrepository.com/artifact/org.ow2.asm/asm
    implementation("org.javassist:javassist:3.29.0-GA")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
