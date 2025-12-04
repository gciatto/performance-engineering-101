package it.unibo.spe.pe.cpu

/**
 * Functional interface for counting the most frequent words in a text (capped by a given number).
 *
 * For instance:
 * ```kotlin
 * mostFrequentWords("a b c a d e a b", k = 2)
 * ```
 * will return a map containing the two most frequent words, `a` (3 occurrences) and `b` (2 occurrences).
 */
fun interface TopKWordCounter {
    /**
     * By default, no word should be skipped, but this method can be overridden to customize the behavior.
     *
     * @return `true` if the given word should be skipped when counting the most frequent words, `false` otherwise.
     */
    fun skipWord(word: String): Boolean = false

    /**
     * Actual implementation of the K-most-frequent-words-counting algorithm,
     * operating on a sequence (i.e. a lazy stream) of lines of text
     * (e.g. a long file, read line by line).
     * @param lines the sequence of lines to count the most frequent words in
     * @param k the maximum number of most frequent words to return
     * @return a map containing the k most frequent words and their occurrences
     */
    fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int>

    /**
     * Convenience method for counting the most frequent words in a single string.
     * @param input the text to count the most frequent words in
     * @param k the maximum number of most frequent words to return
     * @return a map containing the k most frequent words and their occurrences
     */
    fun mostFrequentWords(input: String, k: Int): Map<String, Int> = mostFrequentWords(input.lineSequence(), k)
}
