plugins {
    id("net.neoforged.gradle") version "6.0+"
    id("org.spongepowered.mixin") version "0.7+"
}

project.version = "0.1.0"
project.group = "net.ctrlaltmilk"

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

sourceSets.main.get().resources {
    srcDir("src/generated/resources")
}

minecraft {
    mappings("official", "1.20.1")

    runs {
        all {
            property("terminal.jline", "true")

            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "$projectDir/build/createSrgToMcp/output.srg")

            mods.create("mtm").sources(sourceSets.main.get())
        }

        register("client") {}

        register("server") {}

        register("data") {
            args(
                    "--mod", "mtm",
                    "--all",
                    "--output", file("src/generated/resources/").absolutePath,
                    "--existing", file("src/main/resources/").absolutePath,
                    "--existing-mod", "forge",
                    "--existing-mod", "tfc"
            )
        }
    }
}

mixin {
    config("mtm.mixins.json")
    add(sourceSets.main.get(), "mtm.refmap.json")
}

repositories {
    maven("https://api.modrinth.com/maven/") // TFC
    maven("https://maven.blamejared.com/") // Patchouli
}

dependencies {
    minecraft("net.neoforged:forge:1.20.1-47.1.105")

    implementation(fg.deobf("maven.modrinth:terrafirmacraft:3.2.2"))
    implementation(fg.deobf("vazkii.patchouli:Patchouli:1.20.1-84-FORGE"))

    compileOnly(fg.deobf("mezz.jei:jei-1.20.1-common-api:15.3.0.4"))
    compileOnly(fg.deobf("mezz.jei:jei-1.20.1-forge-api:15.3.0.4"))
    runtimeOnly(fg.deobf("mezz.jei:jei-1.20.1-forge:15.3.0.4"))

    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")?.let { compileOnly(it) }
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

tasks {
    processResources {
        val props = mapOf(
                "version" to project.version
        )

        inputs.properties(props)

        filesMatching("META-INF/mods.toml") {
            expand(props)
        }


        exclude("**/*.kra") // Original Krita textures
        exclude(".cache/**") // Resource generation cache
    }

    jar {
        archiveClassifier = "forge"
    }
}
