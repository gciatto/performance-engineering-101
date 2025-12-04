package it.unibo.spe

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StressTestTopKWordCounter : FunSpec({
    val topK = 15

    val expectedDummyResult = mapOf(
        "quando" to 277,
        "perch" to 256,
        "tanto" to 249,
        "quella" to 227,
        "altro" to 225,
        "occhi" to 212,
        "disse" to 208,
        "questo" to 188,
        "tutto" to 175,
        "esser" to 161,
        "questa" to 157,
        "altra" to 156,
        "prima" to 153,
        "ancor" to 150,
        "mondo" to 143,
    )

    fun skipShortWords(word: String): Boolean = word.length <= 4

    val implementations = listOf(
        DummyTopKWordCounter(::skipShortWords),
        LazyTopKWordCounter(::skipShortWords),
    )

    implementations.forEach { impl ->
        test("Testing ${impl::class.simpleName} on Dante's Inferno") {
            Resources.inferno {
                val result = impl.mostFrequentWords(it, topK)
                result.shouldBe(expectedDummyResult)
            }
        }
    }
})
