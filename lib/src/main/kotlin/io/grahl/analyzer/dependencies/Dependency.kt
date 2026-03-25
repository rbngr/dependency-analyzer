package io.grahl.analyzer.dependencies

data class Dependency(
    val url: String,
    val identifier: String,
    val name: String,
    val version: String
)