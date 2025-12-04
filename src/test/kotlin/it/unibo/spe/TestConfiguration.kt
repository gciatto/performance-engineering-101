package it.unibo.spe

import kotlin.reflect.KClass

val implementationsUnderTest = System.getenv("IMPLEMENTATIONS_UNDER_TEST")
    ?.lowercase()
    ?.takeIf { it.isNotBlank() }
    ?: "all"

val classesUnderTest = buildSet<KClass<*>> {
    if (listOf("all", "slow").any { it in implementationsUnderTest }) {
        add(SlowTopKWordCounter::class)
    }
    if (listOf("all", "lazy").any { it in implementationsUnderTest }) {
        add(LazyTopKWordCounter::class)
    }
    if (listOf("all", "optimized").any { it in implementationsUnderTest }) {
        add(OptimizedTopKWordCounter::class)
    }
}

fun implementations(minimumWordLength: Int): Set<TopKWordCounter> {
    fun skipShortWords(word: String): Boolean = word.length <= minimumWordLength

    return classesUnderTest.map { kClass ->
        val constructor = kClass.constructors.first { it.parameters.size == 1 }
        constructor.call(::skipShortWords) as TopKWordCounter
    }.toSet()
}
