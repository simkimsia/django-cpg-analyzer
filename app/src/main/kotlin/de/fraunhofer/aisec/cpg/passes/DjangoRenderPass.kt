package de.fraunhofer.aisec.cpg.passes

import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.graph.Node
import de.fraunhofer.aisec.cpg.graph.statements.expressions.CallExpression
import de.fraunhofer.aisec.cpg.helpers.SubgraphWalker
import de.fraunhofer.aisec.cpg.frontends.python.PythonLanguageFrontend
import de.fraunhofer.aisec.cpg.passes.configuration.RequiredFrontend

@RequiredFrontend(PythonLanguageFrontend::class)
class DjangoRenderPass(ctx: TranslationContext) : TranslationResultPass(ctx) {
    override fun accept(t: TranslationResult) {
        println("\nStarting Django Render Pass Analysis...")

        // Walk through all nodes in the translation units
        for (component in t.components) {
            println("Analyzing component: ${component.name}")
            for (tu in component.translationUnits) {
                println("  Analyzing translation unit: ${tu.name}")
                val nodes = SubgraphWalker.flattenAST(tu)
                nodes.filterIsInstance<CallExpression>().forEach { call ->
                    println("    Found call: ${call.name} at line ${call.location?.region?.startLine}")
                }
            }
        }
        println("Django Render Pass Analysis completed\n")
    }

    override fun cleanup() {
        // Nothing to clean up
    }
}