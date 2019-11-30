package net.adoptopenjdk.api.updater.dataSources.github.graphql.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GHAssets @JsonCreator constructor(
	@JsonProperty("nodes") val assets: List<GHAsset>,
	@JsonProperty("pageInfo") val pageInfo: PageInfo
)