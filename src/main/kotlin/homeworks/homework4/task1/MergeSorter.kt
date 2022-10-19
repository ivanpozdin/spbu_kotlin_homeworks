package homeworks.homework4.task1

import kotlin.system.measureNanoTime

const val MIN_THRESHOLD = 128

const val RANGE = 1000

/**
 * В объекте содержатся функции, позволяющие отсортировать массив с помощью многопоточной merge sort
 * и измерить время сортировки.
 */
object MergeSorter {
    /**
     * Функция измеряет время на сортировку данного массива.
     */
    fun getSortTime(array: IntArray, maxAmountOfThreads: Int) = measureNanoTime {
        mergeSort(array, maxAmountOfThreads)
    }

    /**
     * Функция сортируют данный массив, используя не больше данного количества потоков.
     */
    fun mergeSort(array: IntArray, threadsRemains: Int, begin: Int = 0, end: Int = array.lastIndex) {
        if (end - begin < 1) {
            return
        }

        val threadsAmount = threadsRemains - threadsRemains % 2
        val mid = (begin + end) / 2
        if (threadsAmount >= 2 && end - begin > MIN_THRESHOLD) {
            val thread1 = Thread {
                mergeSort(array, threadsRemains - 2, begin, mid)
            }
            val thread2 = Thread {
                mergeSort(array, threadsRemains - 2, mid + 1, end)
            }
            thread1.start()
            thread2.start()
            thread1.join()
            thread2.join()
        } else {
            mergeSort(array, threadsAmount - 2, begin, mid)
            mergeSort(array, threadsAmount - 2, mid + 1, end)
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

/**
 * Функции объекта подготавливают данные для создания графиков зависимости времени сортировки от разных параметров.
 */
object DataPreparationForGraphs {
    private fun getAverageDataForExperiments(amountOfThreads: Int, sizeOfArray: Int, repetitions: Int): Long {
        var timeSum: Long = 0
        repeat(repetitions) {
            timeSum += MergeSorter.getSortTime(generateRandomArray(sizeOfArray), amountOfThreads)
        }
        return ((timeSum * 1.0) / repetitions.toDouble()).toLong()
    }

    private fun generateRandomArray(size: Int): IntArray {
        return IntArray(size) { kotlin.random.Random.nextInt(-RANGE, RANGE) }
    }

    /**
     * Функция готовит данные для создания графика зависимости времени от количества потоков при фиксированном размере
     * массива.
     */
    @JvmStatic
    fun getDataForPlotThreadsTime(maxAmountOfThreads: Int, size: Int, repetitionsAmount: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var threadsAmount = 0
        while (threadsAmount <= maxAmountOfThreads) {
            listOfXs.add(threadsAmount)
            listOfYs.add(getAverageDataForExperiments(threadsAmount, size, repetitionsAmount))
            if (threadsAmount == 0) {
                threadsAmount++
            } else {
                threadsAmount *= 2
            }
        }
        return mapOf<String, Any>("threads" to listOfXs, "nanoseconds" to listOfYs)
    }

    /**
     * Функция готовит данные для создания графика зависимости времени от размера массива при фиксированном количестве
     * потоков.
     */
    @JvmStatic
    fun getDataForPlotSizeTime(amountOfThreads: Int, maxSize: Int, repetitionsAmount: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var arraySize = 1
        while (arraySize <= maxSize) {
            listOfXs.add(arraySize)
            listOfYs.add(getAverageDataForExperiments(amountOfThreads, arraySize, repetitionsAmount))
            arraySize *= 2
        }
        return mapOf<String, Any>("elements" to listOfXs, "nanoseconds" to listOfYs)
    }
}
