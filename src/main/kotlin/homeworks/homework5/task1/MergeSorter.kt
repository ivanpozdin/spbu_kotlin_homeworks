package homeworks.homework5.task1

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

const val MIN_THRESHOLD = 128

const val RANGE = 1000

object MergeSorter {
    fun getSortTime(array: IntArray, maxAmountOfCoroutines: Int) = measureNanoTime {
        mergeSortWithCoroutines(array, maxAmountOfCoroutines)
    }

    fun mergeSortWithCoroutines(array: IntArray, maxAmountOfCoroutines: Int) = measureTimeMillis {
        runBlocking { mergeSort(array, 0, array.lastIndex, maxAmountOfCoroutines) }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun mergeSort(array: IntArray, begin: Int, end: Int, coroutinesRemains: Int) {
        if (end - begin < 1) {
            return
        }

        val coroutinesAmount = coroutinesRemains - coroutinesRemains % 2
        val mid = (begin + end) / 2
        if (coroutinesAmount >= 2 && end - begin > MIN_THRESHOLD) {
            val job1 = GlobalScope.async {
                mergeSort(array, begin, mid, coroutinesRemains - 2)
            }
            val job2 = GlobalScope.async {
                mergeSort(array, mid + 1, end, coroutinesRemains - 2)
            }
            job1.await()
            job2.await()
        } else {
            mergeSort(array, begin, mid, coroutinesAmount - 2)
            mergeSort(array, mid + 1, end, coroutinesAmount - 2)
        }
        merge(array, begin, mid, end)
    }

    private fun merge(array: IntArray, begin: Int, mid: Int, end: Int) {
        val temp = Array(end - begin + 1) { 0 }
        var i = begin
        var j = mid + 1
        var k = 0

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k] = array[i]
                i++
            } else {
                temp[k] = array[j]
                j++
            }
            k++
        }

        while (i <= mid) {
            temp[k] = array[i]
            k++
            i++
        }

        while (j <= end) {
            temp[k] = array[j]
            k++
            j++
        }

        for (t in begin..end) {
            array[t] = temp[t - begin]
        }
    }
}

object DataPreparationForGraphs {
    private fun getAverageDataForExperiments(amountOfCoroutines: Int, sizeOfArray: Int, repetitions: Int): Long {
        var timeSum: Long = 0
        repeat(repetitions) {
            timeSum += MergeSorter.getSortTime(generateRandomArray(sizeOfArray), amountOfCoroutines)
        }
        return ((timeSum * 1.0) / repetitions.toDouble()).toLong()
    }

    private fun generateRandomArray(size: Int): IntArray {
        return IntArray(size) { kotlin.random.Random.nextInt(-RANGE, RANGE) }
    }

    /**
     * Функция готовит данные для создания графика зависимости времени от количества корутин при фиксированном размере
     * массива.
     */
    @JvmStatic
    fun getDataForPlotCoroutinesTime(maxAmountOfCoroutines: Int, size: Int, repetitionsAmount: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var coroutinesAmount = 0
        while (coroutinesAmount <= maxAmountOfCoroutines) {
            listOfXs.add(coroutinesAmount)
            listOfYs.add(getAverageDataForExperiments(coroutinesAmount, size, repetitionsAmount))
            if (coroutinesAmount == 0) {
                coroutinesAmount++
            } else {
                coroutinesAmount *= 2
            }
        }
        return mapOf<String, Any>("coroutines" to listOfXs, "nanoseconds" to listOfYs)
    }

    /**
     * Функция готовит данные для создания графика зависимости времени от размера массива при фиксированном количестве
     * корутин.
     */
    @JvmStatic
    fun getDataForPlotSizeTime(amountOfCoroutines: Int, maxSize: Int, repetitionsAmount: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var arraySize = 1
        while (arraySize <= maxSize) {
            listOfXs.add(arraySize)
            listOfYs.add(getAverageDataForExperiments(amountOfCoroutines, arraySize, repetitionsAmount))
            arraySize *= 2
        }
        return mapOf<String, Any>("elements" to listOfXs, "nanoseconds" to listOfYs)
    }
}
