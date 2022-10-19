package homeworks.homework4.task1

import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.letsPlot.intern.toSpec

fun main() {
    println("Это программа может выводить графики зависимости времени сортировки от количества потоков и элементов.")
    println("Первым будет график зависмости времени от количества потоков при фиксированном размере массива.")
    print("Введите максимальное количество потоков: ")
    val maxAmountOfThreads: Int = readLine()?.toInt() ?: 1
    print("Введите размер массива: ")
    val amountOfElements: Int = readLine()?.toInt() ?: 1
    print("Введите кол-во повторений эксперимента для каждого набора данных: ")
    var repetitionsAmount: Int = readLine()?.toInt() ?: 1
    println("График можно посмотреть в открывшемся браузере.")
    val plotFirst = DrawPlot.createPlotThreadsTimes(
        DataPreparationForGraphs.getDataForPlotThreadsTime(
            maxAmountOfThreads, amountOfElements, repetitionsAmount
        )
    )
    val contentFirst = PlotSvgExport.buildSvgImageFromRawSpecs(plotFirst.toSpec())
    DrawPlot.openInBrowser(contentFirst, "firstPlot")

    println()

    println("Вторым будет график зависимости времени от размера массива при фиксированном количестве потоков.")
    print("Введите число потоков: ")
    val amountOfThreads: Int = readLine()?.toInt() ?: 1
    print("Введите максимальное число элементов в массиве: ")
    val maxAmountOfElements: Int = readLine()?.toInt() ?: 1
    print("Введите кол-во повторений эксперемента для каждого набора данных: ")
    repetitionsAmount = readLine()?.toInt() ?: 1
    println("График можно посмотреть в открывшемся браузере.")
    val plotSecond = DrawPlot.createPlotElementsTimes(
        DataPreparationForGraphs.getDataForPlotSizeTime(amountOfThreads, maxAmountOfElements, repetitionsAmount)
    )
    val contentSecond = PlotSvgExport.buildSvgImageFromRawSpecs(plotSecond.toSpec())
    DrawPlot.openInBrowser(contentSecond, "secondPlot")
}
