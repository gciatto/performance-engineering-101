package it.unibo.spe

interface TopKWordCounter {
    fun skipWord(word: String): Boolean = false

    fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int>

    fun mostFrequentWords(input: String, k: Int): Map<String, Int> = mostFrequentWords(input.lineSequence(), k)
}
