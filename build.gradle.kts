plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("io.micronaut.library") version "3.7.4"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

version = "0.1"
group = "net.raquezha.gtn"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-events:3.11.0")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.micronaut.aws:micronaut-function-aws")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.micronaut:micronaut-http-client")

}


java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
micronaut {
    runtime("lambda_java")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("net.raquezha.gtn.*")
    }
}



