package homeworks.homework5.task2

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

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

fun getMatrix(rawAmount: Int, columnAmount: Int, message: String): Array<IntArray> {
    require(rawAmount > 0 && columnAmount > 0) { "Количество стобцов и строк у матрицы должно быть положительным!" }
    println(message)
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

fun getMatrixMultiplicationOneThread(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(columnAmountOfFirstMatrix == rawAmountOfSecondMatrix)
    {
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

fun getMatrixMultiplicationAsync(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(columnAmountOfFirstMatrix == rawAmountOfSecondMatrix)
    {
        "Размеры матриц некорректны"
    }
    val resultMatrix = Array(rawAmountOfFirstMatrix) { IntArray(columnAmountOfSecondMatrix) }
    runBlocking {
        val resultMatrixAsync = Array<Deferred<Int>>(rawAmountOfFirstMatrix * columnAmountOfSecondMatrix) { idx ->
            async() {
                val i = idx / columnAmountOfSecondMatrix
                val j = idx % columnAmountOfSecondMatrix
                var resultToReturn: Int = 0
                for (k in secondMatrix.indices) {
                    resultToReturn += firstMatrix[i][k] * secondMatrix[k][j]
                }
                resultToReturn
            }
        }
        for (coroutineNumber in resultMatrixAsync.indices) {
            resultMatrix[coroutineNumber / columnAmountOfSecondMatrix][coroutineNumber % columnAmountOfSecondMatrix] =
                resultMatrixAsync[coroutineNumber].await()
        }
    }
    return resultMatrix
}

fun getMatrixMultiplicationLaunch(firstMatrix: Array<IntArray>, secondMatrix: Array<IntArray>): Array<IntArray> {
    val rawAmountOfFirstMatrix = firstMatrix.size
    val columnAmountOfFirstMatrix = firstMatrix[0].size
    val rawAmountOfSecondMatrix = secondMatrix.size
    val columnAmountOfSecondMatrix = secondMatrix[0].size
    require(columnAmountOfFirstMatrix == rawAmountOfSecondMatrix)
    {
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

fun generateMatrix(raws: Int, columns: Int): Array<IntArray> {
    return Array<IntArray>(raws) {
        IntArray(columns) {
            kotlin.random.Random.nextInt(0, 3)
        }
    }
}

fun main() {
    val m = 1500
    val firstMatrix = generateMatrix(m, m)
    val secondMatrix = generateMatrix(m, m)
    val time1 = measureTimeMillis {
        val resultMatrix1 = getMatrixMultiplicationOneThread(firstMatrix, secondMatrix)
        println("результат 1 ${resultMatrix1[0][0]}")
    }
    println(time1)
    val time2 = measureTimeMillis {
        val resultMatrix2 = getMatrixMultiplicationAsync(firstMatrix, secondMatrix)
        println("результат 2 ${resultMatrix2[0][0]}")
    }
    println(time2)
    val time3 = measureTimeMillis {
        val resultMatrix3 = getMatrixMultiplicationLaunch(firstMatrix, secondMatrix)
        println("результат 3 ${resultMatrix3[0][0]}")
    }
    println(time3)
}


//    val rawAmountOfFirstMatrix: Int = getSizeAmount("строк в 1й матрице")
//    val columnAmountOfFirstMatrix: Int = getSizeAmount("столбцов в 1й матрице")
//
//    val rawAmountOfSecondMatrix: Int = getSizeAmount("строк во 2й матрице")
//    val columnAmountOfSecondMatrix: Int = getSizeAmount("столбцов во 2й матрице")
//
//    val firstMatrix = getMatrix(
//        rawAmountOfFirstMatrix,
//        columnAmountOfFirstMatrix,
//        "Введите первую матрицу построчно, разделяя элементы пробелами."
//    )
//    val secondMatrix = getMatrix(
//        rawAmountOfSecondMatrix,
//        columnAmountOfSecondMatrix,
//        "Введите вторую матрицу построчно, разделяя элементы пробелами."
//    )


//    for (i in resultMatrix) {
//        for (j in i) {
//            print("$j ")
//        }
//        println()
//    }
//}

//for (i in firstMatrix.indices) {
//    for (j in secondMatrix[0].indices) {
//        resultMatrixAsync[i * j] = async() {
//            var resultToReturn: Int = 0
//            for (k in secondMatrix.indices) {
//                resultToReturn += firstMatrix[i][k] * secondMatrix[k][j]
//            }
//            resultToReturn
//        }
//
//    }
//}