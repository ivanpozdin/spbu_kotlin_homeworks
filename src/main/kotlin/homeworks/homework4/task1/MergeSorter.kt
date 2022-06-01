package homeworks.homework4.task1

import java.util.Random
import kotlin.system.measureNanoTime

object MergeSorter {
    private class SortThreads(array: IntArray, begin: Int, end: Int) :
        Thread(Runnable { mergeSort(array, begin, end) }) {
        init {
            start()
        }
    }

    private fun mergeSort(array: IntArray, begin: Int, end: Int) {
        if (begin < end) {
            val mid = (begin + end) / 2
            mergeSort(array, begin, mid)
            mergeSort(array, mid + 1, end)
            merge(array, begin, mid, end)
        }
    }

    fun threadedSort(array: IntArray, maxAmountOfThreads: Int) = measureNanoTime {
        val exact = array.size % maxAmountOfThreads == 0
        var maxLim = if (exact) array.size / maxAmountOfThreads else array.size / (maxAmountOfThreads - 1)

        maxLim = if (maxLim < maxAmountOfThreads) maxAmountOfThreads else maxLim
        val threads = ArrayList<SortThreads>()

        var i = 0
        while (i < array.size) {
            val beg = i
            val remain = array.size - i
            val end = if (remain < maxLim) i + (remain - 1) else i + (maxLim - 1)
            val t = SortThreads(array, beg, end)
            threads.add(t)
            i += maxLim
        }

        for (t in threads) {
            try {
                t.join()
            } catch (ignored: InterruptedException) {
            }
        }

        i = 0
        while (i < array.size) {
            val mid = if (i == 0) 0 else i - 1
            val remain = array.size - i
            val end = if (remain < maxLim) i + (remain - 1) else i + (maxLim - 1)
            merge(array, 0, mid, end)
            i += maxLim
        }
    }

    private fun merge(array: IntArray, begin: Int, mid: Int, end: Int) {
        val temp = Array(end - begin + 1) { 0 }
        var i = begin
        var j = mid + 1
        var k = 0

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k] = array[i]
                i += 1
            } else {
                temp[k] = array[j]
                j += 1
            }
            k += 1
        }

        while (i <= mid) {
            temp[k] = array[i]
            i += 1
            k += 1
        }

        while (j <= end) {
            temp[k] = array[j]
            j += 1
            k += 1
        }

        for (t in begin..end) {
            array[t] = temp[t - begin]
        }
    }
}

object SortAndDraw {
    @JvmStatic
    fun getDataForPlotThreadsMicroseconds(maxAmountOfThreads: Int, size: Int): Map<String, Any> {
        val list = IntArray(size) { Random().nextInt(size + (size - 1)) - (size - 1) }
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        for (i in 1..maxAmountOfThreads) {
            val arr = list.copyOf()
            listOfXs.add(i)
            listOfYs.add(MergeSorter.threadedSort(arr, i))
        }
        return mapOf<String, Any>("threads" to listOfXs, "microseconds" to listOfYs)
    }

    @JvmStatic
    fun getDataForPlotElementsMicroseconds(amountOfThreads: Int, maxSize: Int): Map<String, Any> {
        val listOfXs = mutableListOf<Int>()
        val listOfYs = mutableListOf<Long>()
        for (size in 1..maxSize) {
            val list = IntArray(size) { Random().nextInt(size + (size - 1)) - (size - 1) }
            for (i in 0 until size) {
                listOfXs.add(i)
                listOfYs.add(MergeSorter.threadedSort(list, amountOfThreads))
            }
        }
        return mapOf<String, Any>("elements" to listOfXs, "microseconds" to listOfYs)
    }
}
