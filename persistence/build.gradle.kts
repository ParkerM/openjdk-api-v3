dependencies {
    implementation(project(":adoptopenjdk-api-v3-models"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${rootProject.ext.get("jacksonVersion")}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${rootProject.ext.get("jacksonVersion")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext.get("kotlinxCoroutinesVersion")}")
    implementation("org.litote.kmongo:kmongo-coroutine:${rootProject.ext.get("kmongoCoroutineVersion")}")
}
