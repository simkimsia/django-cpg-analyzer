package de.fraunhofer.aisec.cpg.passes

import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.graph.Node
import de.fraunhofer.aisec.cpg.graph.statements.expressions.CallExpression
import de.fraunhofer.aisec.cpg.graph.statements.expressions.Literal
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
                    // Look for render or render_to_response calls
                    if (call.name.lastPartsMatch("render") || call.name.lastPartsMatch("render_to_response")) {
                        println("    Found render call: ${call.name} at line ${call.location?.region?.startLine}")

                        // Create a new TemplateRenderer overlay node
                        val templateRenderer = TemplateRenderer()

                        // Try to extract template path from the first argument
                        if (call.arguments.isNotEmpty()) {
                            val templateArg = call.arguments[if (call.name.lastPartsMatch("render")) 1 else 0]
                            if (templateArg is Literal<*>) {
                                templateRenderer.templatePath = templateArg.value.toString()
                            }
                        }

                        // Try to extract context variables from the context dictionary
                        val contextArg = call.arguments.getOrNull(if (call.name.lastPartsMatch("render")) 2 else 1)
                        // TODO: Implement context variable extraction when we have dictionary analysis

                        // Set the underlying node to connect the overlay
                        templateRenderer.underlyingNode = call
                    }
                }
            }
        }
        println("Django Render Pass Analysis completed\n")
    }

    override fun cleanup() {
        // Nothing to clean up
    }
}