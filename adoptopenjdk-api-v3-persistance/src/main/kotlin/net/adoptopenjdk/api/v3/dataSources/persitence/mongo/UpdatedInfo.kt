package net.adoptopenjdk.api.v3.dataSources.persitence.mongo

import java.time.ZonedDateTime

data class UpdatedInfo(val time: ZonedDateTime, val checksum: String) {
    override fun toString(): String {
        return "$time $checksum"
    }
}
