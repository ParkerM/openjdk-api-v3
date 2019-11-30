package net.adoptopenjdk.api.updater.dataSources.github

import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.GHRelease
import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.GHRepository
import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.summary.GHRepositorySummary

interface GitHubApi {
    suspend fun getRepository(repoName: String): GHRepository
    suspend fun getRepositorySummary(repoName: String): GHRepositorySummary
    suspend fun getReleaseById(id: String): GHRelease?
}
