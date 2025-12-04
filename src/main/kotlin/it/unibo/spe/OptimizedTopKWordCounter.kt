package it.unibo.spe

class OptimizedTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int> {
        val words = lines
            .filterNot { it.isBlank() }
            .map { it.trim() }
            .map { it.lowercase() }
            .map(this::removeAccents)
            .flatMap(this::wordify)
            .filterNot(this::skipWord)

        val result = mutableMapOf<String, Int>()

        for (word in words) {
            result[word] = result.getOrDefault(word, 0) + 1
        }

        val last = result.values.sortedByDescending { it }.take(k).last()
        val iter = result.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            if (entry.value < last) {
                iter.remove()
            }
        }
        return result
    }
}
