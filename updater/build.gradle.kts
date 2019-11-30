dependencies {
    implementation(project(":adoptopenjdk-api-v3-models"))
    implementation(project(":adoptopenjdk-api-v3-persistence"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${rootProject.ext["jacksonVersion"]}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${rootProject.ext["jacksonVersion"]}")
    implementation("io.aexp.nodes.graphql:nodes:${rootProject.ext["graphqlNodesVersion"]}")
    implementation("org.codehaus.groovy:groovy-xml:${rootProject.ext["groovyXmlVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext["kotlinxCoroutinesVersion"]}")

    testImplementation("io.mockk:mockk:${rootProject.ext["mockKVersion"]}")
    testImplementation("org.awaitility:awaitility:${rootProject.ext["awaitilityVersion"]}")
    testImplementation("org.litote.kmongo:kmongo-flapdoodle:${rootProject.ext["kmongoVersion"]}")
    testImplementation("org.skyscreamer:jsonassert:${rootProject.ext["jsonAssertVersion"]}")
}
