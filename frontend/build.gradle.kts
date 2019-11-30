dependencies {
    implementation(project(":adoptopenjdk-api-v3-models"))
    implementation(project(":adoptopenjdk-api-v3-persistence"))
    implementation(project(":adoptopenjdk-api-v3-updater"))
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext.get("kotlinxCoroutinesVersion")}")

    testImplementation("org.awaitility:awaitility:${rootProject.ext.get("awaitilityVersion")}")
}
