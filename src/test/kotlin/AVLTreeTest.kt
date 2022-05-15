import homeworks.homework3.task1.AVLTree
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AVLTreeTest {
    @Test
    fun insertionTest() {
        val expected = mutableSetOf<Int>()
        val real = mutableSetOf<Int>()
        val treeMap = AVLTree<Int, Int>(0, mutableSetOf(), mutableSetOf(), mutableListOf())
        for (i in 1..1024) {
            expected.add(i * 10)
        }
        for (i in 1..1024) {
            treeMap[i] = i * 10
        }
        for (i in -2000..2000) {
            if (treeMap.containsKey(i)) {
                real.add(i * 10)
            }
        }
        assertEquals(expected, real)
    }
}
