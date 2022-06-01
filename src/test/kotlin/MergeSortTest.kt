import homeworks.homework4.task1.MergeSort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Random

internal class MergeSortTest {
    @Test
    fun threadedSort() {
        val size = Random().nextInt(200)
        val amountOfThreads = Random().nextInt(100)
        val list = Array(size){ Random().nextInt(size + (size - 1)) - (size - 1) }
        val listForStdSort = list.copyOf()
        val listForMySort = list.copyOf()
        listForStdSort.sort()
        MergeSort.threadedSort(listForMySort, amountOfThreads)
        assertArrayEquals(listForStdSort, listForMySort)
    }
}
