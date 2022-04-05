package homeworks.homework1.task3

import java.util.Stack

const val insertionInBeginning = 1
const val insertionInEnd = 2
const val movingFromTo = 3
const val meaninglessValue = 0
const val startIndex = 0

class PerformedCommandStorage {
    private var listOfNumbers: MutableList<Int> = mutableListOf()

    private var performedCommands: Stack<Triple<Int, Int, Int>> = Stack()
    fun insertInBeginning(x: Int) {
        listOfNumbers.add(startIndex, x)
        performedCommands.push(Triple(insertionInBeginning, meaninglessValue, meaninglessValue))
    }

    fun insertInEnd(x: Int) {
        listOfNumbers.add(x)
        performedCommands.push(Triple(insertionInEnd, meaninglessValue, meaninglessValue))
    }

    fun moveFromTo(i: Int, j: Int) {
        require((i in 0..listOfNumbers.lastIndex) and (j in 0..listOfNumbers.lastIndex)) {
            "Indices are not valid"
        }
        val temp = listOfNumbers[i]
        (i until j).forEach { listOfNumbers[it] = listOfNumbers[it + 1] }
        listOfNumbers[j] = temp
        performedCommands.push(Triple(movingFromTo, i, j))
    }

    fun cancelLastAction() {
        require(!performedCommands.isEmpty()) { "There was not last action" }
        val (command, i, j) = performedCommands.pop()
        when (command) {
            insertionInBeginning -> listOfNumbers.removeAt(0)
            insertionInEnd -> listOfNumbers.removeAt(listOfNumbers.lastIndex)
            movingFromTo -> run {
                val temp = listOfNumbers[j]
                (j downTo i + 1).forEach { listOfNumbers[it] = listOfNumbers[it - 1] }
                listOfNumbers[i] = temp
            }
            else -> run {
                throw IllegalArgumentException("Commands can only be from 1 to 3")
            }
        }
    }

    fun getListOfNumbers(): List<Int> {
        return listOfNumbers
    }
}
