package it.unibo.spe.pe.cpu

/**
 * Lazy implementation of [TopKWordCounter], heavily relying on lazy higher-order functions.
 * Efficient, but there's room for improvement.
 */
class LazyTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int> = lines
        .filterNot { it.isBlank() }
        .map { it.trim() }
        .map { it.lowercase() }
        .map(this::removeAccents)
        .flatMap(this::wordify)
        .filterNot(this::skipWord)
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { (_, count) -> count }
        .take(k)
        .toMap()
}
