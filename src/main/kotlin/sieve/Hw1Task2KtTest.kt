package sieve

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Hw1Task2KtTest {

    @Test
    fun getPrimesUpTo10() {
        assertEquals(mutableListOf(2, 3, 5, 7), getPrimesUpTo(10))
    }

    @Test
    fun getPrimesUpToPrime() {
        assertEquals(mutableListOf(2, 3, 5, 7, 11, 13, 17, 19, 23), getPrimesUpTo(23))
    }

    @Test
    fun getPrimesUpTo0() {
        val emptyPrimesList: MutableList<Int> = mutableListOf()
        assertEquals(emptyPrimesList, getPrimesUpTo(0))
    }

    @Test
    fun getPrimesUpToNegativeNumber() {
        val emptyPrimesList: MutableList<Int> = mutableListOf()
        assertEquals(emptyPrimesList, getPrimesUpTo(-500))
    }
}