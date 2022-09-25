package homeworks.homework5.task1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class MergeSorterTest {

    @Test
    fun mergeSortWithCoroutines() {
        val size: Int = Random.nextInt(200)
        val amountOfThreads: Int = Random.nextInt(100)
        val list = IntArray(size) { Random.nextInt(2 * size - 1) - (size - 1) }
        val listForStdSort = list.copyOf()
        val listForMySort = list.copyOf()
        listForStdSort.sort()
        MergeSorter.mergeSortWithCoroutines(listForMySort, amountOfThreads)
        assertArrayEquals(listForStdSort, listForMySort)
    }
}
