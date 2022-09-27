package homeworks.homework4.task1

import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.letsPlot
import java.awt.Desktop
import java.io.File

/**
 * Объект содержит функции учавствующие в обработке графиков, одна сохраняет график, две другие создают.
 */
object DrawPlot {
    /**
     * Создаётся график зависимости времени от количества потоков.
     */
    fun createPlotThreadsTimes(data: Map<String, Any>): Plot {
        return letsPlot(data) + geomPoint(
            color = "red",
            size = 2.0
        ) { x = "threads"; y = "nanoseconds" }
    }

    /**
     * Создаётся график зависимости времени от количества элементов в массиве.
     */
    fun createPlotElementsTimes(data: Map<String, Any>): Plot {
        return letsPlot(data) + geomPoint(
            color = "red",
            size = 2.0
        ) { x = "elements"; y = "nanoseconds" }
    }

    /**
     * Переданный график сохраняется в html файл и открывается в браузере.
     */
    fun openInBrowser(content: String, fileName: String) {
        val dir = File(System.getProperty("user.dir"), "lets-plot-images")
        dir.mkdir()
        val file = File(dir.canonicalPath, "$fileName.html")
        file.createNewFile()
        file.writeText(content)
        Desktop.getDesktop().browse(file.toURI())
    }
}
