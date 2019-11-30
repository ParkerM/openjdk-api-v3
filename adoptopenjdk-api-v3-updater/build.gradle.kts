dependencies {
    implementation(project(":adoptopenjdk-api-v3-models"))
    implementation(project(":adoptopenjdk-api-v3-persistence"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${rootProject.ext.get("jacksonVersion")}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${rootProject.ext.get("jacksonVersion")}")
    implementation("io.aexp.nodes.graphql:nodes:${rootProject.ext.get("graphqlNodesVersion")}")
    implementation("org.codehaus.groovy:groovy-xml:${rootProject.ext.get("groovyXmlVersion")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.ext.get("kotlinxCoroutinesVersion")}")
}
