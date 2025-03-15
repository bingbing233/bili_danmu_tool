import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("plugin.serialization") version "1.9.0" // 应用序列化插件
}

group = "com.bing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    val ktorVersion = "3.1.0"
    // Ktor-core
    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    // Logging
    implementation("io.ktor:ktor-client-logging:${ktorVersion}")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    // Negotiation
    implementation("io.ktor:ktor-client-serialization:$ktorVersion") // 序列化支持
    implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio:${ktorVersion}")


}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "bili_danmu_tool"
            packageVersion = "1.0.0"
        }
    }
}
