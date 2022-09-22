package homeworks.homework5.task2

fun getSizeAmount(message: String): Int {
    var inputSize = ""
    while (inputSize.toIntOrNull() == null || inputSize.toIntOrNull()!! <= 0) {
        print("Введите кол-во $message: ")
        inputSize = readLine()!!
        if (inputSize.toIntOrNull() == null || inputSize.toIntOrNull()!! <= 0) {
            println("Внимание: вводить можно только натуральные числа. Попробуйте снова.")
        }
    }
    return inputSize.toInt()
}

fun getMatrix(rawAmount: Int, columnAmount: Int): Array<IntArray> {
    require(rawAmount > 0 && columnAmount > 0) { "Количество стобцов и строк у матрицы должно быть положительным!" }
    val matrix = Array(rawAmount) { _ ->
        var isInputNotOk = true
        var raw = IntArray(columnAmount) { 0 }
        while (isInputNotOk) {
            try {
                raw = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
                if (raw.size == columnAmount) {
                    isInputNotOk = false
                }
            } catch (e: NumberFormatException) {
                isInputNotOk = true
                println("Что-то не так с этой строкой. Введите её снова.")
            }
        }
        raw
    }
    return matrix
}

fun getMatrixMultiplication(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(rawAmountOfFirstMatrix == columnAmountOfSecondMatrix)
    {
        "Размеры матриц некорректны"
    }
    val resultMatrix = Array(rawAmountOfFirstMatrix) { IntArray(columnAmountOfSecondMatrix) }

    TODO("Сделать корутинное скалярное произведение для каждого элемента новой матрицы")
    return resultMatrix
}

fun main() {
    val rawAmountOfFirstMatrix: Int = getSizeAmount("строк в 1й матрице")
    val columnAmountOfFirstMatrix: Int = getSizeAmount("столбцов в 1й матрице")

    val rawAmountOfSecondMatrix: Int = getSizeAmount("строк во 2й матрице")
    val columnAmountOfSecondMatrix: Int = getSizeAmount("столбцов во 2й матрице")

    val firstMatrix = getMatrix(rawAmountOfFirstMatrix, columnAmountOfFirstMatrix)
    val secondMatrix = getMatrix(rawAmountOfSecondMatrix, columnAmountOfSecondMatrix)

    val resultMatrix = getMatrixMultiplication(firstMatrix, secondMatrix)

}