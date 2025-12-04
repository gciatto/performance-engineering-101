package it.unibo.spe

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DummyTestTopKWordCounter : FunSpec({
    val dummyText = """
        |This is a dummy text for the sake of testing.
        |This text is meant to contain some words that repeat.
        |Some words repeat more than once, some words repeat only once.
        |Testing is essential to ensure correctness to some extent.
    """.trimMargin()

    val topK = 3

    val expectedDummyResult = mapOf(
        "some" to 4,
        "words" to 3,
        "repeat" to 3,
    )

    val implementations = implementations(minimumWordLength = 2)

    implementations.forEach { impl ->
        test("Testing ${impl::class.simpleName} on dummy text") {
            val result = impl.mostFrequentWords(dummyText, topK)
            result.shouldBe(expectedDummyResult)
        }
    }
})
