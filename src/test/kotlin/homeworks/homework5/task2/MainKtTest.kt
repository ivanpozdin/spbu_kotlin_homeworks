package homeworks.homework5.task2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class MainKtTest {
    @Test
    fun multiplyMatricesInOneThread() {
        val matrix1 = arrayOf(
            intArrayOf(12, 8, 4), intArrayOf(3, 17, 14), intArrayOf(9, 8, 10)
        )
        val matrix2 = arrayOf(
            intArrayOf(5, 19, 3), intArrayOf(6, 15, 9), intArrayOf(7, 8, 16)
        )
        val expectedArray = arrayOf(
            intArrayOf(136, 380, 172), intArrayOf(215, 424, 386), intArrayOf(163, 371, 259)
        )
        assertArrayEquals(expectedArray, multiplyMatricesInOneThread(matrix1, matrix2))
    }

    @Test
    fun multiplyMatricesWithCoroutines() {
        val rowsAmountMatrix1 = Random.nextInt(1, 2000)
        val columnAmountMatrix2 = Random.nextInt(1, 2000)
        val columnAmountMatrix1 = Random.nextInt(1, 2000)

        val matrix1 = generateMatrix(rowsAmountMatrix1, columnAmountMatrix1)
        val matrix2 = generateMatrix(columnAmountMatrix1, columnAmountMatrix2)

        assertArrayEquals(
            multiplyMatricesInOneThread(matrix1, matrix2), multiplyMatricesWithCoroutines(matrix1, matrix2)
        )
    }
}
