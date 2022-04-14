package homeworks.homework1.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class PerformedCommandStorageTest {
    @Test
    fun insertInEndTest() {
        val expected: List<Int> = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { processInsertionInEnd(it, performedCommandStorage) }
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun insertInBeginningTest() {
        val expected = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (5 downTo 1).forEach { processInsertionInBeginning(it, performedCommandStorage) }
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun moveFromToTest() {
        val expected = listOf(1, 3, 4, 2, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { processInsertionInEnd(it, performedCommandStorage) }
        processMovingFromTo(1, 3, performedCommandStorage)
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun cancelLastActionForInsertInEndTest() {
        val expected: List<Int> = listOf(1, 2, 3, 4)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { processInsertionInEnd(it, performedCommandStorage) }
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun cancelLastActionForInsertInBeginningTest() {
        val expected = listOf(2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (5 downTo 1).forEach { processInsertionInBeginning(it, performedCommandStorage) }
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun cancelLastActionForMoveFromToTest() {
        val expected = listOf(1, 2, 3, 4, 5)
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { processInsertionInEnd(it, performedCommandStorage) }
        processMovingFromTo(1, 3, performedCommandStorage)
        performedCommandStorage.cancelLastAction()
        assertEquals(expected, performedCommandStorage.innerListOfNumbers)
    }

    @Test
    fun moveFromToFailTest() {
        val performedCommandStorage = PerformedCommandStorage()
        (1..5).forEach { processInsertionInEnd(it, performedCommandStorage) }
        val action = ActionMoveFromTo(performedCommandStorage.innerListOfNumbers, 0, 100)
        val exception = assertFailsWith<IllegalArgumentException> { action.performAction() }
        assertEquals("Indices are not valid", exception.message)
    }

    @Test
    fun moveCancelLastActionFailTest() {
        val performedCommandStorage = PerformedCommandStorage()
        val exception = assertFailsWith<IllegalArgumentException> { performedCommandStorage.cancelLastAction() }
        assertEquals("There was not last action", exception.message)
    }
}
