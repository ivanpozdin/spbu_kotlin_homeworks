package homeworks.homework5.task1

import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.letsPlot.intern.toSpec

fun main() {
    println("Это программа может выводить графики зависимости времени сортировки от количества корутин и элементов.")
    println("Первым будет график зависмости времени от количества корутин при фиксированном размере массива.")
    print("Введите максимальное количество корутин: ")
    val maxAmountOfCoroutines: Int = readLine()?.toInt() ?: 1
    print("Введите размер массива: ")
    val amountOfElements: Int = readLine()?.toInt() ?: 1
    print("Введите кол-во повторений эксперемента для каждого набора данных: ")
    var repetitionsAmount: Int = readLine()?.toInt() ?: 1
    println("График можно посмотреть в открывшемся браузере.")
    val plotFirst = DrawPlot.createPlotCoroutinesTimes(
        DataPreparationForGraphs.getDataForPlotCoroutinesTime(
            maxAmountOfCoroutines, amountOfElements, repetitionsAmount
        )
    )
    val contentFirst = PlotSvgExport.buildSvgImageFromRawSpecs(plotFirst.toSpec())
    DrawPlot.openInBrowser(contentFirst, "firstPlot")

    println()

    println("Вторым будет график зависмости времени от размера массива при фиксированном количестве корутин.")
    print("Введите число корутин: ")
    val amountOfCoroutines: Int = readLine()?.toInt() ?: 1
    print("Введите максимальное число элементов в массиве: ")
    val maxAmountOfElements: Int = readLine()?.toInt() ?: 1
    print("Введите кол-во повторений эксперемента для каждого набора данных: ")
    repetitionsAmount = readLine()?.toInt() ?: 1
    println("График можно посмотреть в открывшемся браузере.")
    val plotSecond = DrawPlot.createPlotElementsTimes(
        DataPreparationForGraphs.getDataForPlotSizeTime(amountOfCoroutines, maxAmountOfElements, repetitionsAmount)
    )
    val contentSecond = PlotSvgExport.buildSvgImageFromRawSpecs(plotSecond.toSpec())
    DrawPlot.openInBrowser(contentSecond, "secondPlot")
}
