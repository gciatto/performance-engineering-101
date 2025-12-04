package it.unibo.spe.pe.cpu

import java.net.URL

object Resources {
    private fun url(name: String): URL = javaClass.getResource(name)
        ?: throw IllegalArgumentException("Resource not found: $name")

    /**
     * Function to load the inferno.txt resource file, LAZILY, as a single string containing the whole file.
     * Usage example:
     * ```kotlin
     * Resources.inferno {
     *     // do something with text in variable `it`
     * }
     * ```
     */
    fun <T> inferno(action: (String) -> T): T = url("inferno.txt")
        .readText()
        .let { action(it) }
}
