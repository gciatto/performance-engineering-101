package it.unibo.spe

abstract class AbstractTopKWordCounter(private val skipWord: (String) -> Boolean) : TopKWordCounter {
    override fun skipWord(word: String): Boolean = skipWord.invoke(word)

    abstract override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int>

    private val word = Regex("\\w+")

    protected fun wordify(input: String): Sequence<String> = word.findAll(input).map { it.value }
}
