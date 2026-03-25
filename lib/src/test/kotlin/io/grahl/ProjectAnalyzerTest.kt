package io.grahl

import io.grahl.analyzer.project.ProjectAnalyzer
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path
import kotlin.io.path.absolute

internal class ProjectAnalyzerTest {

    @Test
    fun `should find projects`() {
        val result = ProjectAnalyzer().find(Path.of(""))
        result.forEach {
            println("Found ${it.type.name} project at ${it.path.absolute()}")
        }
    }

}