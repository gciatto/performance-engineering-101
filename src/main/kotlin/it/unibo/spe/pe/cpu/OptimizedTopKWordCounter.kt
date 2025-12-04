package it.unibo.spe.pe.cpu

class OptimizedTopKWordCounter(skipWord: (String) -> Boolean = { false }) : AbstractTopKWordCounter(skipWord) {
    override fun mostFrequentWords(input: String, k: Int): Map<String, Int> = TODO(
        "Copy-paste the code of ${SlowTopKWordCounter::class.simpleName}, then make it efficient. " +
            "Use the profiler to understand the bottlenecks.",
    )
}
