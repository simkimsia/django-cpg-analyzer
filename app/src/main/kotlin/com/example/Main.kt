package com.example

import de.fraunhofer.aisec.cpg.TranslationConfiguration
import de.fraunhofer.aisec.cpg.TranslationManager
import de.fraunhofer.aisec.cpg.InferenceConfiguration
import de.fraunhofer.aisec.cpg.passes.DjangoRenderPass
import de.fraunhofer.aisec.cpg.passes.Pass
import java.io.File
import kotlin.reflect.KClass

// Toggle between test file and actual Django project
const val USE_TEST_FILE = true  // Set to false to use actual Django project
const val DJANGO_PROJECT_PATH = "/path/to/your/django/project"
const val TEST_RESOURCES_PATH = "app/src/test/resources/python"

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
        .build()

    // Create and use TranslationManager
    val analyzer = TranslationManager.builder()
        .config(config)
        .build()

    try {
        val result = analyzer.analyze().get()
        println("Analysis completed")
        println("Source: ${if (USE_TEST_FILE) "Test File" else "Django Project"}")

        // Access translation units instead of findings
        result.translationUnits.forEach { unit ->
            println("Analyzing unit: ${unit.name}")
        }
    } catch (e: Exception) {
        println("Analysis failed: ${e.message}")
        e.printStackTrace()
    }
}

private fun setupTestFile(): File {
    val testDir = File(TEST_RESOURCES_PATH)
    testDir.mkdirs()
    return testDir
}