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
    companion object {
        @JvmStatic
        fun insertionTestInputData() = listOf(
            Arguments.of(true, 500), Arguments.of(false, -500), Arguments.of(true, 1500), Arguments.of(false, 1501)
        )

        @JvmStatic
        fun testHeightInputData(): List<Arguments> {
            val treeMapFirst = AVLTree<Int, Int>()
            for (i in 1..1500) {
                treeMapFirst[i] = i * 10
            }
            val treeMapSecond = AVLTree<Int, Int>()
            for (i in 1..1500) {
                treeMapSecond[i] = i * 10
            }
            for (i in 901..1100) {
                treeMapSecond.remove(i)
            }
            return listOf(
                Arguments.of(treeMapFirst), Arguments.of(treeMapSecond)
            )
        }

        @JvmStatic
        fun emptyTestInputData(): List<Arguments> {
            val emptyTree = AVLTree<Int, Int>()
            val filledTree = AVLTree<Int, Int>()
            for (i in 1..400) {
                filledTree[i] = i * 10
            }
            val clearedTree = AVLTree<Int, Int>()
            for (i in 1..400) {
                clearedTree[i] = i * 10
            }
            clearedTree.clear()
            return listOf(
                Arguments.of(true, emptyTree), Arguments.of(false, filledTree), Arguments.of(true, clearedTree)
            )
        }
    }

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
    @MethodSource("insertionTestInputData")
    fun containsValueTest(expected: Boolean, testNumber: Int) {
        val treeMap = AVLTree<Int, Int>()
        for (i in 1..1500) {
            treeMap[i] = i * 10
        }
        assertEquals(expected, treeMap.containsValue(testNumber))
    }


    @ParameterizedTest(name = "case {index}")
    @MethodSource("testHeightInputData")
    fun isHeightCorrect(treeMap: AVLTree<Int, Int>) {
        val isSmallerThanMaxPossibleHeight = treeMap.getTreeHeight <= floor(1.44 * log2(treeMap.size + 2.0) - 0.328)
        val isBiggerThanMinPossibleHeight = treeMap.getTreeHeight >= ceil(log2(treeMap.size + 1.0))
        val real = isBiggerThanMinPossibleHeight && isSmallerThanMaxPossibleHeight
        assertEquals(true, real)
    }

    @ParameterizedTest(name = "case {index}")
    @MethodSource("emptyTestInputData")
    fun isEmptyTest(expected: Boolean, treeMap: AVLTree<Int, Int>) {
        assertEquals(expected, treeMap.isEmpty())
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
