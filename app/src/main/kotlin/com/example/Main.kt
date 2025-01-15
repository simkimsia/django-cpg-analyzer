package com.example

import de.fraunhofer.aisec.cpg.TranslationConfiguration
import de.fraunhofer.aisec.cpg.TranslationManager
import de.fraunhofer.aisec.cpg.InferenceConfiguration
import de.fraunhofer.aisec.cpg.passes.DjangoRenderPass
import de.fraunhofer.aisec.cpg.frontends.python.PythonLanguage
import java.io.File

// Toggle between test file and actual Django project
const val USE_TEST_FILE = true  // Set to false to use actual Django project
const val DJANGO_PROJECT_PATH = "/path/to/your/django/project"
const val PROJECT_ROOT = "django-cpg-analyzer"
const val TEST_RESOURCES_PATH = "/src/test/resources/python"

fun main() {
    val sourceLocation = if (USE_TEST_FILE) {
        setupTestFile()
    } else {
        File(DJANGO_PROJECT_PATH)
    }

    // Configure CPG
    val inferenceConfig = InferenceConfiguration.builder()
        .inferRecords(true)
        .build()

    val config = TranslationConfiguration.builder()
        .sourceLocations(listOf(sourceLocation))
        .inferenceConfiguration(inferenceConfig)
        .defaultPasses()
        .registerPass(DjangoRenderPass::class)
        .registerLanguage(PythonLanguage())
        .build()

    // Create and use TranslationManager
    val analyzer = TranslationManager.builder()
        .config(config)
        .build()

    try {
        val result = analyzer.analyze().get()
        println("Analysis completed")
        println("Source: ${if (USE_TEST_FILE) "Test File" else "Django Project"}")

        // Access components and their translation units
        result.components.forEach { component ->
            component.translationUnits.forEach { unit ->
                println("Analyzing unit: ${unit.name}")
            }
        }
    } catch (e: Exception) {
        println("Analysis failed: ${e.message}")
        e.printStackTrace()
    }
}

private fun setupTestFile(): File {
    val workspaceRoot = System.getProperty("user.dir")
    val testFile = File(workspaceRoot, "$TEST_RESOURCES_PATH/test_django_view.py")
    if (!testFile.exists()) {
        throw IllegalStateException("Test file not found at ${testFile.absolutePath}")
    }
    return testFile
}