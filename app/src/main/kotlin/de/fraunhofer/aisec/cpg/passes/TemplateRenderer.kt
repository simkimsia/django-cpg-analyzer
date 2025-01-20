package de.fraunhofer.aisec.cpg.passes

import de.fraunhofer.aisec.cpg.graph.OverlayNode

/**
 * Represents a Django template rendering operation in the code.
 * This overlay node is used to track template usage and potential data flow
 * between views and templates.
 */
class TemplateRenderer : OverlayNode() {
    /** The template file/path being rendered */
    var templatePath: String = ""

    /** The context variables being passed to the template */
    var contextVariables: MutableMap<String, String> = mutableMapOf()
}