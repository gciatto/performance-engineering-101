package it.unibo.spe

class DummyTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int> =
        mostFrequentWords(lines.joinToString(), k)

    override fun mostFrequentWords(input: String, k: Int): Map<String, Int> {
        val wordsList = wordify(input.lowercase()).toList()
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
