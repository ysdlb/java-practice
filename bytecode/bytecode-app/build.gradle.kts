plugins {
    id("java")
}

group = "org.ysdlb"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.ow2.asm/asm
    implementation("ch.qos.logback:logback-classic:1.4.5")
}
java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}