import homeworks.homework3.task1.AVLTree
import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class AVLTreeTest {
    @Test
    fun insertionTest() {
        val expected = mutableSetOf<Int>()
        val real = mutableSetOf<Int>()
        val treeMap = AVLTree<Int, Int>()
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

    @ParameterizedTest(name = "case {index}")
    @MethodSource("getInputData")
    fun containsValueTest(expected: Boolean, testNumber: Int) {
        val treeMap = AVLTree<Int, Int>()
        for (i in 1..1500) {
            treeMap[i] = i * 10
        }
        assertEquals(expected, treeMap.containsValue(testNumber))
    }

    companion object {
        @JvmStatic
        fun getInputData() = listOf(
            Arguments.of(true, 500), Arguments.of(false, -500), Arguments.of(true, 1500), Arguments.of(false, 1501)
        )
    }


    @Test
    fun isHeightCorrectTest() {
        var n = 1500
        val treeMap = AVLTree<Int, Int>()
        for (i in 1..n) {
            treeMap[i] = i * 10
        }
        var isSmallerThanMaxPossibleHeight = treeMap.getTreeHeight <= floor(1.44 * log2(n + 2.0) - 0.328)
        var isBiggerThanMinPossibleHeight = treeMap.getTreeHeight >= ceil(log2(n + 1.0))
        var real = isBiggerThanMinPossibleHeight && isSmallerThanMaxPossibleHeight
        assertEquals(true, real)
        // проверка высоты после удаления элементов
        for (i in 901..1100) {
            treeMap.remove(i)
        }
        n -= 200
        isSmallerThanMaxPossibleHeight = treeMap.getTreeHeight <= floor(1.44 * log2(n + 2.0) - 0.328)
        isBiggerThanMinPossibleHeight = treeMap.getTreeHeight >= ceil(log2(n + 1.0))
        real = isBiggerThanMinPossibleHeight && isSmallerThanMaxPossibleHeight
        assertEquals(true, real)
    }

    @Test
    fun isEmptyTest() {
        val treeMap = AVLTree<Int, Int>()
        assertEquals(true, treeMap.isEmpty())
        treeMap[4] = 40
        assertEquals(false, treeMap.isEmpty())
        treeMap.clear()
        assertEquals(true, treeMap.isEmpty())
    }

    @Test
    fun putAllTest() {
        val treeMap = AVLTree<Int, Int>()
        val map = mapOf(1 to 10, 2 to 20, 3 to 30, 100 to 1000)
        treeMap.putAll(map)
        val expected = mutableSetOf<Int?>(10, 20, 30, 1000)
        val real = mutableSetOf<Int?>()
        for (i in -2000..2000) {
            if (treeMap.containsKey(i)) {
                real.add(treeMap[i])
            }
        }
        assertEquals(expected, real)
    }

    @Test
    fun removeNotExistedKey() {
        val treeMap = AVLTree<Int, Int>()
        val n = 1500
        for (i in 1..n) {
            treeMap[i] = i * 10
        }
        val expected: Int? = null
        val real: Int? = treeMap.remove(-1000)
        assertEquals(expected, real)
    }

    @Test
    fun removeOldValueTest() {
        val treeMap = AVLTree<Int, Int>()
        val n = 1500
        for (i in 1..n) {
            treeMap[i] = i * 10
        }
        val expected = 10000
        val real: Int? = treeMap.remove(1000)
        assertEquals(expected, real)
    }

    @Test
    fun insertOldValueTest() {
        val treeMap = AVLTree<Int, Int>()
        val n = 1500
        for (i in 1..n) {
            treeMap[i] = i * 10
        }

        val expected = 6000
        val real: Int? = treeMap.put(600, 6)
        assertEquals(expected, real)
    }
}
