package it.unibo.spe.pe.cpu

import kotlin.text.iterator

abstract class AbstractTopKWordCounter(private val skipWord: (String) -> Boolean) : TopKWordCounter {
    override fun skipWord(word: String): Boolean = skipWord.invoke(word)

    abstract override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int>

    private val word = Regex("\\w+")

    private val accents = mapOf(
        'à' to 'a',
        'è' to 'e',
        'é' to 'e',
        'ì' to 'i',
        'ò' to 'o',
        'ù' to 'u',
    )

    protected fun removeAccents(input: String): String {
        val builder = StringBuilder()
        for (char in input) {
            builder.append(accents.getOrDefault(char, char))
        }
        return builder.toString()
    }

    protected fun wordify(input: String): Sequence<String> = word.findAll(input).map { it.value }
}
