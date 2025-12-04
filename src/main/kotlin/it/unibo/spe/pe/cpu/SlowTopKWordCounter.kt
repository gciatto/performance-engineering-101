package it.unibo.spe.pe.cpu

/**
 * Slow implementation of [TopKWordCounter].
 * TODO: spot the many inefficiencies and bugs in this implementation, possibly using the debugger and the profiler.
 */
class SlowTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(input: String, k: Int): Map<String, Int> {
        val text = removeAccents(input.lowercase())
        val wordsList = wordify(text).toList()
        val uniqueWords = wordsList.distinct()
        val wordCount = mutableMapOf<String, Int>()
        for (word in uniqueWords) {
            if (!skipWord(word)) {
                wordCount[word] = wordsList.count { it == word }
            }
        }
        return wordCount.entries
            .sortedByDescending { it.value }
            .take(k)
            .associate { it.toPair() }
    }
}
