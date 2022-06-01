package homeworks.homework4.task1
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.letsPlot
import java.awt.Desktop
import java.io.File

object DrawPlot {
    fun createPlotThreadsTimes(data: Map<String, Any>): Plot {
        return letsPlot(data) + geomPoint(
            color = "red",
            size = 1.0
        ) { x = "threads"; y = "microseconds" }
    }
    fun createPlotElementsTimes(data: Map<String, Any>): Plot {
        return letsPlot(data) + geomPoint(
            color = "red",
            size = 1.0
        ) { x = "elements"; y = "microseconds" }
    }

    fun openInBrowser(content: String, fileName: String) {
        val dir = File(System.getProperty("user.dir"), "lets-plot-images")
        dir.mkdir()
        val file = File(dir.canonicalPath, fileName + ".html")
        file.createNewFile()
        file.writeText(content)
        Desktop.getDesktop().browse(file.toURI())
    }
}
