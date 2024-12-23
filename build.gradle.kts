plugins {
    id("net.neoforged.gradle.userdev") version "7.0+"
}

project.version = "0.2.0"
project.group = "net.ctrlaltmilk"

runs {
    all {
        systemProperty("terminal.jline", "true")
    }
}

repositories {
    maven("https://maven.blamejared.com/") // JEI
    maven("https://maven.octo-studios.com/releases/") // Curios API Continuation
}

dependencies {
    implementation("net.neoforged:neoforge:21.1.90")
}

tasks {
    withType<ProcessResources> {
        exclude("**/*.kra") // Original Krita textures
        exclude(".cache/**") // Resource generation cache
    }
}
