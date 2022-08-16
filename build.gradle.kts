description = "Learn or test java itself or java utils"

plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("org.junit:junit-bom:5.7.0")
    }
}

allprojects {
    repositories {
        mavenCentral()
        // etc...
    }
}