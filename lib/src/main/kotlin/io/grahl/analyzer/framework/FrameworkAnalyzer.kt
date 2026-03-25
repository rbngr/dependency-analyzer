package io.grahl.analyzer.framework

import io.grahl.analyzer.dependencies.Dependency

class FrameworkAnalyzer {

    private val frameworks = listOf(
        Framework("spring.io", "org.springframework", "Spring",),
        Framework("nextjs.com", "@next-js", "next js")
    )

    internal fun findFramework(dependencies: List<Dependency>): List<Framework> {
        return frameworks.filter { framework ->
            dependencies.map { it.identifier }.contains(framework.identifier)
        }
    }

}

data class Framework(
    val url: String,
    val identifier: String,
    val name: String,
)