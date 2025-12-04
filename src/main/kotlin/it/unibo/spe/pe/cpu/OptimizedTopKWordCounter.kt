package it.unibo.spe.pe.cpu

class OptimizedTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(lines: Sequence<String>, k: Int): Map<String, Int> {
        val words = lines // lazily iterating over all the input lines
            .filterNot { it.isBlank() } // remove empty lines
            .map { it.trim() } // remove leading and trailing whitespace
            .map { it.lowercase() } // convert each non-empty line to lowercase
            .map(this::removeAccents) // remove accents
            .flatMap(this::wordify) // split each line into words
            .filterNot(this::skipWord) // skip words that match the skip predicate

        val result = mutableMapOf<String, Int>() // initially empty list to be filled with the most frequent words

        // for each word in input...
        for (word in words) {
            // ... add the word to the result map, incrementing its count if it already exists
            result[word] = result.getOrDefault(word, 0) + 1
        }

        // get the k-th largest occurrence count
        val last = result.values.sortedByDescending { it }.take(k).last()
        val iter = result.iterator() // build an iterator to iterate over the result map
        while (iter.hasNext()) { // while iterating...
            val entry = iter.next() // ... let entry be the current map entry
            if (entry.value < last) {
                // remove the current entry if its value is less than the k-th largest occurrence count
                iter.remove()
            }
        }
        // so now the result map only contains the k-th largest words
        return result
    }
}
