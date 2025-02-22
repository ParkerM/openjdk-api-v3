package net.adoptopenjdk.api.v3.filters

import net.adoptopenjdk.api.v3.models.VersionData
import net.adoptopenjdk.api.v3.parser.VersionParser
import net.adoptopenjdk.api.v3.parser.maven.VersionRange
import java.util.function.Predicate

class VersionRangeFilter(range: String?) : Predicate<VersionData> {

    private val rangeMatcher: VersionRange?
    private val exactMatcher: VersionData?

    init {
        // default range behaviour of a solid version is stupid:
        // https://cwiki.apache.org/confluence/display/MAVENOLD/Dependency+Mediation+and+Conflict+Resolution#DependencyMediationandConflictResolution-DependencyVersionRanges
        // so if it is not a range treat ias an exact match
        if (range == null) {
            rangeMatcher = null
            exactMatcher = null
        } else if (!range.startsWith("(") && !range.startsWith("[")) {
            rangeMatcher = null
            exactMatcher = VersionParser.parse(range)
        } else {
            rangeMatcher = VersionRange.createFromVersionSpec(range)
            exactMatcher = null
        }
    }

    override fun test(version: VersionData): Boolean {
        return when {
            exactMatcher != null -> {
                exactMatcher.compareVersionNumber(version)
            }
            rangeMatcher != null -> {
                rangeMatcher.containsVersion(version)
            }
            else -> {
                true
            }
        }
    }
}
