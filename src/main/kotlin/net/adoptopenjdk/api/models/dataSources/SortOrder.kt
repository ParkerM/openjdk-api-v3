package net.adoptopenjdk.api.models.dataSources

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Schema


@Schema(type = SchemaType.STRING, enumeration = ["ASC", "DESC"], example = "DESC")
enum class SortOrder {
    ASC,
    DESC;
}