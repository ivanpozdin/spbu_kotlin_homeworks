package homework1.task2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MainTest {
    @ParameterizedTest(name = "case {index}")
    @MethodSource("getPrimesInputData")
    fun testGetPrimesUpTo(expected: MutableList<Int>, testNumber: Int) {
        assertEquals(expected, getPrimesUpTo(testNumber))
    }

    companion object {
        @JvmStatic
        fun getPrimesInputData() = listOf(
                Arguments.of(mutableListOf(2, 3, 5, 7), 10),
                Arguments.of(mutableListOf(2, 3, 5, 7, 11, 13, 17, 19, 23), 23),
                Arguments.of(mutableListOf<Int>(), 0),
                Arguments.of(mutableListOf<Int>(), -500)
        )
    }
}
