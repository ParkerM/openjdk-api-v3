package net.adoptopenjdk.api.persistence.dataSources.persitence

import net.adoptopenjdk.api.models.dataSources.models.AdoptRepos
import net.adoptopenjdk.api.models.dataSources.models.FeatureRelease

interface ApiPersistence {
    suspend fun updateAllRepos(repos: AdoptRepos)
    suspend fun readReleaseData(featureVersion: Int): FeatureRelease
}
