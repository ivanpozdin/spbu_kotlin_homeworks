package homeworks.homework1.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PerformedCommandStorageTest {
    @Test
    fun insertInEndTest() {
        val expected: List<Int> = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { performedCommandStorage.insertInEnd(it) }
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }

    @Test
    fun insertInBeginningTest() {
        val expected = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (5 downTo 1).forEach { performedCommandStorage.insertInBeginning(it) }
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }

    @Test
    fun moveFromToTest() {
        val expected = listOf(1, 3, 4, 2, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { performedCommandStorage.insertInEnd(it) }
        performedCommandStorage.moveFromTo(1, 3)
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }

    @Test
    fun cancelLastActionForInsertInEndTest() {
        val expected: List<Int> = listOf(1, 2, 3, 4)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { performedCommandStorage.insertInEnd(it) }
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }

    @Test
    fun cancelLastActionForInsertInBeginningTest() {
        val expected = listOf(2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (5 downTo 1).forEach { performedCommandStorage.insertInBeginning(it) }
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }

    @Test
    fun cancelLastActionForMoveFromToTest() {
        val expected = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { performedCommandStorage.insertInEnd(it) }
        performedCommandStorage.moveFromTo(1, 3)
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.getListOfNumbers())
    }
}
