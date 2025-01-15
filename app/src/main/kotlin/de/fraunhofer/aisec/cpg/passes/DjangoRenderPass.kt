package de.fraunhofer.aisec.cpg.passes

import de.fraunhofer.aisec.cpg.TranslationResult
import de.fraunhofer.aisec.cpg.TranslationContext
import de.fraunhofer.aisec.cpg.graph.statements.expressions.CallExpression
import de.fraunhofer.aisec.cpg.helpers.SubgraphWalker
import de.fraunhofer.aisec.cpg.frontends.python.PythonLanguageFrontend
import de.fraunhofer.aisec.cpg.passes.configuration.RequiredFrontend

@RequiredFrontend(PythonLanguageFrontend::class)
class DjangoRenderPass(ctx: TranslationContext) : TranslationResultPass(ctx) {
    override fun accept(t: TranslationResult) {
        println("Running Django render pass")

        // Walk through all nodes in the translation units
        for (tu in t.translationUnits) {
            SubgraphWalker.flattenAST(tu).forEach { node ->
                if (node is CallExpression) {
                    println("Found call: ${node.name}")
                }
            }
        }
    }

    override fun cleanup() {
        // Nothing to clean up
    }
}