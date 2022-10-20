package homeworks.retest

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertFails

internal class MainKtTest {

    companion object {
        @JvmStatic
        fun inputDataForCorrectZipUnzipTest(): List<Arguments> {
            val array1 = ByteArray(16) { it.toByte() }
            val array2 = ByteArray(100) { Byte.MAX_VALUE }
            for (i in array2.lastIndex / 2..array2.lastIndex) {
                array2[i] = Byte.MIN_VALUE
            }
            val array3 = ByteArray((100..10000).random()) { (-128..127).random().toByte() }
            val array4 = ByteArray((100..10000).random()) { (-128..127).random().toByte() }
            val array5 = ByteArray(1) { 10.toByte() }
            val array6 = ByteArray(UByte.MAX_VALUE.toInt()) { 20.toByte() }
            return listOf(
                Arguments.of(array1),
                Arguments.of(array2),
                Arguments.of(array3),
                Arguments.of(array4),
                Arguments.of(array5),
                Arguments.of(array6)
            )
        }
    }

    @ParameterizedTest(name = "case {index}")
    @MethodSource("inputDataForCorrectZipUnzipTest")
    fun zipUnzipCorrectTest(array: ByteArray) {
        assertArrayEquals(array, array.zip().unZip())
        assertFails { assertArrayEquals(array, array.zip()) }
    }

    @Test
    fun sizeOfZipTest() {
        val array = ByteArray(UByte.MAX_VALUE.toInt()) { 20.toByte() }
        assertEquals(2, array.zip().size)
    }

    @Test
    fun sizeOfZipTest2() {
        val array = ByteArray(UByte.MAX_VALUE.toInt() + 1) { 20.toByte() }
        assertEquals(4, array.zip().size)
    }
}
