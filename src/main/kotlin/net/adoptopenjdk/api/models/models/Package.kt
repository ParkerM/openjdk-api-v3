package net.adoptopenjdk.api.models.models

class Package(name: String, link: String, size: Long, checksum: String?, checksum_link: String?) : Asset(name, link, size, checksum, checksum_link)