package net.adoptopenjdk.api.packages

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import net.adoptopenjdk.api.v3.models.Architecture
import net.adoptopenjdk.api.v3.models.HeapSize
import net.adoptopenjdk.api.v3.models.ImageType
import net.adoptopenjdk.api.v3.models.JvmImpl
import net.adoptopenjdk.api.v3.models.OperatingSystem
import net.adoptopenjdk.api.v3.models.Project
import net.adoptopenjdk.api.v3.models.ReleaseType
import net.adoptopenjdk.api.v3.models.Vendor
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

@QuarkusTest
class BinaryPathTest : PackageEndpointTest() {

    override fun getPath(): String {
        return "/v3/binary"
    }

    @Test
    fun latestDoesRedirectToBinary() {
        val path = getLatestPath(8, ReleaseType.ga, OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)
        performRequest(path)
            .then()
            .statusCode(307)
            .header("Location", Matchers.startsWith("https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/"))
    }

    @Test
    fun latestDoesRedirectToBinaryNoProject() {
        val path = "${getPath()}/latest/11/ga/linux/x64/jdk/openj9/normal/adoptopenjdk"
        performRequest(path)
            .then()
            .statusCode(307)
            .header("Location", Matchers.startsWith("https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/"))
    }

    @Test
    fun latestDoesNotRedirectToBinary() {
        val path = getLatestPath(8, ReleaseType.ga, OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.valhalla)
        performRequest(path)
            .then()
            .statusCode(404)
    }

    @Test
    fun nonExistantLatestRequestGives404() {
        val path = getLatestPath(4, ReleaseType.ga, OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.valhalla)
        performRequest(path)
            .then()
            .statusCode(404)
    }

    @Test
    fun versionRequestRedirects() {
        val path = getVersionPath("jdk8u212-b04", OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)
        performRequest(path)
            .then()
            .statusCode(307)
            .header("Location", Matchers.startsWith("https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u212-b04"))
    }

    @Test
    fun nonExistantVersionRequestGives404() {
        val path = getVersionPath("fooBar", OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)
        performRequest(path)
            .then()
            .statusCode(404)
    }

    @Test
    fun gradleHeadRequestToVersionGives200() {
        val path = getVersionPath("jdk8u212-b04", OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)

        RestAssured.given()
            .`when`()
            .header("user-agent", "Gradle")
            .head(path)
            .then()
            .statusCode(200)
            .header("size", Matchers.equalTo("104366847"))
            .header("content-disposition", Matchers.equalTo("""attachment; filename="OpenJDK8U-jdk_x64_linux_hotspot_8u212b04.tar.gz"; filename*=UTF-8''OpenJDK8U-jdk_x64_linux_hotspot_8u212b04.tar.gz"""))
    }

    @Test
    fun nonGradleHeadRequestToVersionGives307() {
        val path = getVersionPath("jdk8u212-b04", OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)

        RestAssured.given()
            .`when`()
            .redirects().follow(false)
            .head(path)
            .then()
            .statusCode(307)
    }

    @Test
    fun nonExistantHeadVersionRequestGives404() {
        val path = getVersionPath("fooBar", OperatingSystem.linux, Architecture.x64, ImageType.jdk, JvmImpl.hotspot, HeapSize.normal, Vendor.adoptopenjdk, Project.jdk)

        RestAssured.given()
            .`when`()
            .header("user-agent", "Gradle")
            .head(path)
            .then()
            .statusCode(404)
    }
}
