package homeworks.homework5.task2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun getSizeAmount(message: String): Int {
    var inputSize = ""
    while (inputSize.toIntOrNull() == null || inputSize.toIntOrNull()!! <= 0) {
        print("Введите $message: ")
        inputSize = readLine()!!
        if (inputSize.toIntOrNull() == null || inputSize.toIntOrNull()!! <= 0) {
            println("Внимание: вводить можно только натуральные числа. Попробуйте снова.")
        }
    }
    return inputSize.toInt()
}

fun multiplyMatricesInOneThread(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(columnAmountOfFirstMatrix == rawAmountOfSecondMatrix) {
        "Размеры матриц некорректны"
    }
    val resultMatrix = Array(rawAmountOfFirstMatrix) { IntArray(columnAmountOfSecondMatrix) }

    for (i in firstMatrix.indices) {
        for (j in secondMatrix[0].indices) {
            for (k in secondMatrix.indices) {
                resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j]
            }
        }
    }
    return resultMatrix
}

fun multiplyMatricesWithCoroutines(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(columnAmountOfFirstMatrix == rawAmountOfSecondMatrix) {
        "Размеры матриц некорректны"
    }
    val resultMatrix = Array(rawAmountOfFirstMatrix) { IntArray(columnAmountOfSecondMatrix) }
    val jobList = mutableListOf<Job>()
    runBlocking {
        for (i in 0 until rawAmountOfFirstMatrix) {
            for (j in 0 until columnAmountOfSecondMatrix) {
                jobList.add(launch(Dispatchers.Default) {
                    var resultToReturn: Int = 0
                    for (k in secondMatrix.indices) {
                        resultToReturn += firstMatrix[i][k] * secondMatrix[k][j]
                    }
                    resultMatrix[i][j] = resultToReturn
                })
            }
        }

        for (job in jobList) {
            job.join()
        }
    }
    return resultMatrix
}

fun generateMatrix(raws: Int, columns: Int): Array<IntArray> = Array<IntArray>(raws) {
    IntArray(columns) {
        kotlin.random.Random.nextInt(-5, 5)
    }
}


fun main() {
    println("Введите размерность квадратных матриц, которые будут случайно сгенерированы и перемножены с помощью корутин и без них:")
    val m = getSizeAmount("размерность")
    val firstMatrix = generateMatrix(m, m)
    val secondMatrix = generateMatrix(m, m)
    val timeWithCoroutines = measureTimeMillis {
        val resultMatrixWithCoroutines = multiplyMatricesWithCoroutines(firstMatrix, secondMatrix)
    }
    println("Время умножения матриц с помощью корутин: $timeWithCoroutines мс")
    val timeWithOneThread = measureTimeMillis {
        val resultMatrixWithOneThread = multiplyMatricesInOneThread(firstMatrix, secondMatrix)
    }
    println("Время умножения матриц без корутин: $timeWithOneThread мс")
}
