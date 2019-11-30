package net.adoptopenjdk.api.updater.dataSources.github.graphql.models.summary

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GHRepositorySummary @JsonCreator constructor(@JsonProperty("releases") val releases: GHReleasesSummary)