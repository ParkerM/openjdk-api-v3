package net.adoptopenjdk.api.v3.models

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(type = SchemaType.STRING, enumeration = ["hotspot", "openj9"])
enum class JvmImpl : FileNameMatcher {
    hotspot, openj9;

    override lateinit var names: List<String>

    constructor(vararg alternativeNames: String) {
        setNames(this.name, alternativeNames.toList())
    }
}
