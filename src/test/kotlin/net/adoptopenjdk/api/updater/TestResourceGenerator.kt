package net.adoptopenjdk.api.updater

import kotlinx.coroutines.runBlocking
import net.adoptopenjdk.api.persistence.JsonMapper
import net.adoptopenjdk.api.models.models.Variants
import java.io.File
import java.util.zip.GZIPOutputStream

class TestResourceGenerator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val mock = BaseTest.mockkHttpClient()
                HttpClientFactory.client = mock

                val variantData = this.javaClass.getResource("/JSON/variants.json").readText()
                val variants = JsonMapper.mapper.readValue(variantData, Variants::class.java)

                val repo = AdoptReposBuilder.build(variants.versions)

                File("adoptopenjdk-api-v3-updater/src/test/resources/example-data.json.gz").delete()

                GZIPOutputStream(File("adoptopenjdk-api-v3-updater/src/test/resources/example-data.json.gz").outputStream()).use { out ->
                    JsonMapper.mapper.writerWithDefaultPrettyPrinter().writeValues(out).write(repo)
                }
            }
        }
    }
}