package it.unibo.spe.pe.cpu

import java.net.URL

object Resources {
    private fun url(name: String): URL = javaClass.getResource(name)
        ?: throw IllegalArgumentException("Resource not found: $name")

    fun <T> inferno(action: (Sequence<String>) -> T): T = url("inferno.txt")
        .openStream()
        .bufferedReader()
        .useLines { action(it) }
}
