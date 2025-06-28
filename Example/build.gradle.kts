plugins {
    java
	eclipse
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.16"
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.jackson30007.example"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
	
	sourceSets {
        main {
            java.setSrcDirs(listOf("src"))
            resources.setSrcDirs(listOf("src"))
        }
    }
}

repositories {
    mavenCentral()
	
	// paper
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {

	// paper
	paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
	compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION