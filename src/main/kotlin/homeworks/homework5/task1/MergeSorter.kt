package homeworks.homework5.task1

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.system.measureTimeMillis

const val MIN_THRESHOLD = 128

object MergeSorter {

    fun getSortTime(array: IntArray, maxAmountOfCoroutines: Int) = measureTimeMillis {
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
                temp[k++] = array[i++]
            } else {
                temp[k++] = array[j++]
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++]
        }

        while (j <= end) {
            temp[k++] = array[j++]
        }

        for (t in begin..end) {
            array[t] = temp[t - begin]
        }
    }

}

object SortAndDraw {
    @JvmStatic
    fun getDataForPlotCoroutinesTime(maxAmountOfThreads: Int, size: Int): Map<String, Any> {
        val list = IntArray(size) { Random().nextInt(size + (size - 1)) - (size - 1) }
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var threadAmount = 0
        val arr0 = list.copyOf()
        listOfXs.add(0)
        listOfYs.add(MergeSorter.getSortTime(arr0, threadAmount++))
        while (threadAmount <= maxAmountOfThreads) {
            val arr = list.copyOf()
            listOfXs.add(threadAmount)
            listOfYs.add(MergeSorter.getSortTime(arr, threadAmount))
            threadAmount *= 2
        }
        return mapOf<String, Any>("coroutines" to listOfXs, "milliseconds" to listOfYs)
    }

    @JvmStatic
    fun getDataForPlotElementsTime(amountOfThreads: Int, maxSize: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        var size = 1
        while (size <= maxSize) {
            val list = IntArray(size) { Random().nextInt(size + (size - 1)) - (size - 1) }
            listOfXs.add(size)
            listOfYs.add(MergeSorter.getSortTime(list, amountOfThreads))
            size *= 2
        }
        return mapOf<String, Any>("elements" to listOfXs, "milliseconds" to listOfYs)
    }
}