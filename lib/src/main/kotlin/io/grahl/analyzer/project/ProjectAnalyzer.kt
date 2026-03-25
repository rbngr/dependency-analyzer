package io.grahl.analyzer.project

import com.sun.nio.sctp.Association
import java.nio.file.Path

class ProjectAnalyzer {

    internal val ignoreFiles = listOf(
        ".git"
    )

    internal val ignoreDirectories = listOf(
        "node_modules", "dist", "src", "www", ".stencil", ".git", "build", ".m2", "src"
    )

    fun find(path: Path): List<Project> {
        return path.toFile().walkTopDown()
            .onEnter { file ->
                !ignoreDirectories.contains(file.name) && !ignoreFiles.contains(file.name)
            }
            .mapNotNull { file ->
                val type = ProjectType.entries.find { type ->
                    type.associatedBy.contains(file.name)
                }

                if (type != null) {
                    Project(
                        title = file.parentFile.name,
                        type = type,
                        path = file.toPath()
                    )
                } else {
                    null
                }
            }
            .toList()
    }
}

data class Project(
    val title: String,
    val type: ProjectType,
    val path: Path
)

enum class ProjectType(val type: String, val associatedBy: List<String>) {
    GRADLE("gradle", listOf("gradle.build.kts", "gradle.build.groovy")),
    MAVEN("maven", listOf("pom.xml")),
    NODEJS("nodejs", listOf("package.json"))
}