dependencies {
    implementation(project(":adoptopenjdk-api-v3-models"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${rootProject.ext["jacksonVersion"]}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${rootProject.ext["jacksonVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext["kotlinxCoroutinesVersion"]}")
    implementation("org.litote.kmongo:kmongo-coroutine:${rootProject.ext["kmongoVersion"]}")

    testImplementation("org.litote.kmongo:kmongo-flapdoodle:${rootProject.ext["kmongoVersion"]}")
}
