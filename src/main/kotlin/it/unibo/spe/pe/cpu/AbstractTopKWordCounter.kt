package it.unibo.spe.pe.cpu

import kotlin.text.iterator

/**
 * Abstract class containing shared facilities for all implementations of inferface [TopKWordCounter].
 * Forces sub-classes to specify logic for skipping words, via lambda function passed to constructor.
 * @param skipWord lambda function used to skip words (returning `true` for a word to be skipped)
 */
abstract class AbstractTopKWordCounter(private val skipWord: (String) -> Boolean) : TopKWordCounter {

    override fun skipWord(word: String): Boolean = skipWord.invoke(word)

    abstract override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int>

    /**
     * Regex used to match a contiguous sequence of word characters (i.e. letters, digits, underscores, etc.)
     * This is needed to extract words from a text, ignoring punctuation and other non-word characters.
     */
    private val word = Regex("\\w+")

    /**
     * Map containing accented characters and their non-accented equivalents.
     */
    private val accents = mapOf(
        'à' to 'a',
        'è' to 'e',
        'é' to 'e',
        'ì' to 'i',
        'ò' to 'o',
        'ù' to 'u',
    )

    /**
     * Removes accented characters from a string.
     * @param input the string to remove accents from
     * @return the input string with accents removed
     */
    protected fun removeAccents(input: String): String {
        val builder = StringBuilder()
        for (char in input) {
            builder.append(accents.getOrDefault(char, char))
        }
        return builder.toString()
    }

    /**
     * Splits a string into words, ignoring punctuation and other non-word characters.
     * @param input the string to split into words
     * @return a sequence of words extracted from the input string
     */
    protected fun wordify(input: String): Sequence<String> = word.findAll(input).map { it.value }
}
