package it.unibo.spe.pe.cpu

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

/**
 * Stress test to ensure that all implementations are effective and efficient.
 *
 * Which implementations are actually tested depends on the environment variable `IMPLEMENTATIONS_UNDER_TEST`.
 * Admissible values are `all` (default), `slow`, `lazy`, and `optimized` (as per the names of the implementation classes).
 * Lack of such variable defaults to `all`.
 * See file `TestConfiguration.kt` for more details.
 */
class StressTestTopKWordCounter : FunSpec({
    val topK = 15

    val expectedResult = mapOf(
        "quando" to 277,
        "tanto" to 249,
        "quella" to 227,
        "altro" to 225,
        "occhi" to 212,
        "disse" to 208,
        "perche" to 207,
        "questo" to 188,
        "tutto" to 175,
        "esser" to 161,
        "questa" to 157,
        "altra" to 156,
        "prima" to 153,
        "ancor" to 150,
        "mondo" to 143,
    )

    val implementations = implementations(minimumWordLength = 4)

    implementations.forEach { impl ->
        test("Testing ${impl::class.simpleName} on Dante's Inferno") {
            Resources.inferno {
                val result = impl.mostFrequentWords(it, topK)
                result.shouldBe(expectedResult)
            }
        }
    }
})
