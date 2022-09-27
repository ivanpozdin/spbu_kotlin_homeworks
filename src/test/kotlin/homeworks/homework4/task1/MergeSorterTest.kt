package homeworks.homework4.task1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class MergeSorterTest {
    companion object {
        @JvmStatic
        fun mergeSortTestInputData() = mutableListOf(
            Arguments.of(
                IntArray(1000) { it + 1 }, IntArray(1000) { 1000 - it }, 0
            ),
            Arguments.of(
                IntArray(1000) { it + 1 }, IntArray(1000) { 1000 - it }, 2
            ),
            Arguments.of(
                IntArray(1000) { it + 1 }, IntArray(1000) { 1000 - it }, 4
            ),
            Arguments.of(
                IntArray(1000) { it + 1 }, IntArray(1000) { 1000 - it }, 15
            ),
            Arguments.of(
                IntArray(9999) { it + 1 }, IntArray(9999) { 9999 - it }, 15
            )
        ) + MutableList(15) {
            val array = IntArray(Random.nextInt(1, 100000)) { Random.nextInt(-1000, 1000) }
            val arrayForStdSort = array.copyOf()
            val threadsAmount = Random.nextInt(0, 2048)
            arrayForStdSort.sort()
            Arguments.of(arrayForStdSort, array, threadsAmount)
        }
    }

    @ParameterizedTest(name = "case {index}")
    @MethodSource("mergeSortTestInputData")
    fun sortTest(expected: IntArray, array: IntArray, threadsAmount: Int) {
        MergeSorter.mergeSort(array, threadsAmount)
        assertArrayEquals(expected, array)
    }
}
