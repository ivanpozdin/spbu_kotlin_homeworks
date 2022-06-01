import homeworks.homework4.task1.MergeSorter
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.Random

internal class MergeSorterTest {
    @Test
    fun threadedSort() {
        val size = Random().nextInt(200)
        val amountOfThreads = Random().nextInt(100)
        val list = IntArray(size, { Random().nextInt(size + (size - 1)) - (size - 1) })
        val listForStdSort = list.copyOf()
        val listForMySort = list.copyOf()
        listForStdSort.sort()
        MergeSorter.threadedSort(listForMySort, amountOfThreads)
        assertArrayEquals(listForStdSort, listForMySort)
    }
}
