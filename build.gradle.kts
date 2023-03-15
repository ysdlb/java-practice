description = "Learn or test java itself or java utils"

plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("org.junit:junit-bom:5.8.1")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}