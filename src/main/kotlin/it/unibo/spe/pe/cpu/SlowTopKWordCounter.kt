package it.unibo.spe.pe.cpu

/**
 * Slow implementation of [TopKWordCounter].
 * There are many inefficiencies here, most notably:
 * - it wastes a lot of memory by storing the whole input text, split into words, in a list
 * - it iterates over the whole list of words multiple times to count occurrences of each word
 */
class SlowTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int> =
        mostFrequentWords(lines.joinToString(), k)

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
