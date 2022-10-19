import homeworks.retest.unZip
import homeworks.retest.zip
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertTrue

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
            val array5 = ByteArray(0)
            return listOf(
                Arguments.of(array1),
                Arguments.of(array2),
                Arguments.of(array3),
                Arguments.of(array4),
                Arguments.of(array5)
            )
        }
    }

    @ParameterizedTest(name = "case {index}")
    @MethodSource("inputDataForCorrectZipUnzipTest")
    fun zipUnzipCorrectTest(array: ByteArray) {
        assertArrayEquals(array, array.zip().unZip())
    }

    @Test
    fun sizeOfZipArrayTest() {
        val array = ByteArray(100) { Byte.MAX_VALUE }
        for (i in array.lastIndex / 2..array.lastIndex) {
            array[i] = Byte.MIN_VALUE
        }
        assertTrue(array.zip().size < array.size)
    }
}
