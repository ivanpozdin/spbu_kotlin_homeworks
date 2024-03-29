package homeworks.retest

const val MAX_INDEX = UByte.MAX_VALUE
const val EXAMPLE_SIZE = 16

/**
 * Функция сжимает данный ByteArra так,
 * что на чётных местах в новом массиве стоят кол-во повторений следующего элемента.
 * A на нечётных соответственно сам повторяющийся Byte(кол-во повторений может быть и 1).
 */
fun ByteArray.zip(): ByteArray {
    val zippedListOfBytes = mutableListOf<Byte>()
    var counter: UByte = 0u
    for (i in 0 until this.lastIndex) {
        counter++
        if (this[i] != this[i + 1] || counter == MAX_INDEX) {
            zippedListOfBytes.add(counter.toByte())
            zippedListOfBytes.add(this[i])
            counter = 0u
        }
    }
    if (this.isNotEmpty()) {
        counter++
        zippedListOfBytes.add(counter.toByte())
        zippedListOfBytes.add(this.last())
    }

    return zippedListOfBytes.toByteArray()
}

/**
 * Фукция распаковывает сжатый функцией zip() массив в исходный.
 */
fun ByteArray.unZip(): ByteArray {
    val unZippedListOfBytes = mutableListOf<Byte>()
    for (index in this.indices step 2) {
        repeat(this[index].toUByte().toInt()) {
            require(index + 1 <= this.lastIndex) { "Not zipped array was given!!!" }
            unZippedListOfBytes.add(this[index + 1])
        }
    }
    return unZippedListOfBytes.toByteArray()
}

fun main() {
    val byteArray = ByteArray(EXAMPLE_SIZE) {
        it.toByte()
    }
    println("Данный массив:")
    for (i in byteArray) {
        print("${i.toInt()} ")
    }
    println()
    println("Zipped массив:")
    val zippedArray = byteArray.zip()
    for (i in zippedArray) {
        print("${i.toInt()} ")
    }
    println()
    val unzippedArray = zippedArray.unZip()
    println("Unzipped массив:")
    for (j in unzippedArray) {
        print("${j.toInt()} ")
    }
}
