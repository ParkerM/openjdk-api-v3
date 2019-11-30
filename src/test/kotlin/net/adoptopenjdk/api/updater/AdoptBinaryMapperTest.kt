package net.adoptopenjdk.api.updater

import kotlinx.coroutines.runBlocking
import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.GHAsset
import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.GHMetaData
import net.adoptopenjdk.api.updater.dataSources.github.graphql.models.GHVersion
import net.adoptopenjdk.api.updater.mapping.adopt.AdoptBinaryMapper
import net.adoptopenjdk.api.models.models.Architecture
import net.adoptopenjdk.api.models.models.Binary
import net.adoptopenjdk.api.models.models.ImageType
import net.adoptopenjdk.api.models.models.JvmImpl
import net.adoptopenjdk.api.models.models.OperatingSystem
import net.adoptopenjdk.api.models.models.Project
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class AdoptBinaryMapperTest {

    val jdk = GHAsset(
            "OpenJDK8U-jre_x64_linux_hotspot-jfr_2019-11-21-10-26.tar.gz",
            1L,
            "",
            1L,
            "2013-02-27T19:35:32Z")

    val assets = listOf(jdk, GHAsset(
            "OpenJDK8U-jdk_x64_linux_hotspot_2019-11-22-16-01.tar.gz",
            1L,
            "",
            1L,
            "2013-02-27T19:35:32Z"))

    @Test
    fun oldChecksumIsFound() {
        runBlocking {
            val binaryList = AdoptBinaryMapper.toBinaryList(listOf(GHAsset(
                    "OpenJDK9-OPENJ9_ppc64le_Linux_jdk-9.0.4.12_openj9-0.9.0.tar.gz",
                    1L,
                    "",
                    1L,
                    "2013-02-27T19:35:32Z"),
                    GHAsset(
                            "OpenJDK9-OPENJ9_ppc64le_Linux_jdk-9.0.4.12_openj9-0.9.0.sha256.txt",
                            1L,
                            "a-download-link",
                            1L,
                            "2013-02-27T19:35:32Z")), emptyMap())

            assertEquals("a-download-link", binaryList.get(0).`package`!!.checksum_link)
        }
    }

    @Test
    fun parsesOldOpenj9() {
        runBlocking {
            val binaryList = AdoptBinaryMapper.toBinaryList(listOf(GHAsset(
                    "OpenJDK9-OPENJ9_ppc64le_Linux_jdk-9.0.4.12_openj9-0.9.0.tar.gz",
                    1L,
                    "",
                    1L,
                    "2013-02-27T19:35:32Z")), emptyMap())

            assertEquals(JvmImpl.openj9, binaryList.get(0).jvm_impl)
            assertEquals(Architecture.ppc64le, binaryList.get(0).architecture)
            assertEquals(OperatingSystem.linux, binaryList.get(0).os)
            assertEquals(Project.jdk, binaryList.get(0).project)
        }
    }

    @Test
    fun parsesJfrFromName() {
        runBlocking {
            val binaryList = AdoptBinaryMapper.toBinaryList(assets, emptyMap())
            assertParsedHotspotJfr(binaryList)
        }
    }

    @Test
    fun projectDefaultsToJdk() {
        runBlocking {
            val binaryList = AdoptBinaryMapper.toBinaryList(assets, emptyMap())
            assertEquals(Project.jdk, binaryList.get(1).project)
        }
    }

    @Test
    fun parsesJfrFromMetadata() {

        runBlocking {
            val metadata = GHMetaData("", OperatingSystem.linux, Architecture.x64, "hotspot-jfr",
                    GHVersion(0, 1, 2, "", 4, "", 6, "", ""),
                    "",
                    "",
                    ImageType.jdk,
                    ""
            )
            val binaryList = AdoptBinaryMapper.toBinaryList(assets, mapOf(Pair(jdk, metadata)))
            assertParsedHotspotJfr(binaryList)
        }
    }

    private fun assertParsedHotspotJfr(binaryList: List<Binary>) {
        assertEquals(JvmImpl.hotspot, binaryList.get(0).jvm_impl)
        assertEquals(Project.jfr, binaryList.get(0).project)
    }
}