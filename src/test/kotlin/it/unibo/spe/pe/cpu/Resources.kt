package it.unibo.spe.pe.cpu

import java.net.URL

object Resources {
    private fun url(name: String): URL = javaClass.getResource(name)
        ?: throw IllegalArgumentException("Resource not found: $name")

    /**
     * Function to load the inferno.txt resource file, LAZILY, as a sequence of lines.
     * Usage example:
     * ```kotlin
     * Resources.inferno {
     *     // do something with lines in variable `it`
     * }
     * ```
     * after all lines are managed in the block, the file is closed.
     */
    fun <T> inferno(action: (Sequence<String>) -> T): T = url("inferno.txt")
        .openStream()
        .bufferedReader()
        .useLines { action(it) }
}
