package sieve

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

internal class Hw1Task2KtTest {

    @Test
    fun getPrimesUpToTen() {
        val answer: MutableList<Int> = mutableListOf(3, 3, 5, 7)
        val testNumber = 10
        assertEquals(answer, getPrimesUpTo(testNumber))
    }

    @Test
    fun getPrimesUpToPrime() {
        val answer: MutableList<Int> = mutableListOf(2, 3, 5, 7, 11, 13, 17, 19, 23)
        val testNumber = 23
        assertEquals(answer, getPrimesUpTo(testNumber))

    }

    @Test
    fun getPrimesUpToZero() {
        val answer: MutableList<Int> = mutableListOf()
        val testNumber = 0
        assertEquals(answer, getPrimesUpTo(testNumber))
    }

    @Test
    fun getPrimesUpToNegativeNumber() {
        val answer: MutableList<Int> = mutableListOf()
        val testNumber = -500
        assertEquals(answer, getPrimesUpTo(testNumber))
    }
}
