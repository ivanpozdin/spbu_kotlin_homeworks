package homeworks.homework1.task3

import java.util.Stack

class PerformedCommandStorage {
    private var listOfNumbers: MutableList<Int> = mutableListOf()
    private val insertionInBeginning = 1
    private val insertionInEnd = 2
    private val movingFromTo = 3
    private var performedCommands: Stack<Triple<Int, Int, Int>> = Stack()
    fun insertInBeginning(x: Int) {
        listOfNumbers.add(0, x)
        performedCommands.push(Triple(insertionInBeginning, -1, -1))
    }

    fun insertInEnd(x: Int) {
        listOfNumbers.add(x)
        performedCommands.push(Triple(insertionInEnd, -1, -1))
    }

    fun moveFromTo(i: Int, j: Int) {
        require((i in 0..listOfNumbers.lastIndex) and (j in 0..listOfNumbers.lastIndex)) {
            "Indices are not valid"
        }
        val temp = listOfNumbers[i]
        for (k in i until j) {
            listOfNumbers[k] = listOfNumbers[k + 1]
        }
        listOfNumbers[j] = temp
        performedCommands.push(Triple(movingFromTo, i, j))
    }

    fun cancelLastAction() {
        require(!performedCommands.isEmpty()) { "There was not last action" }
        val (command, i, j) = performedCommands.pop()
        when (command) {
            1 -> listOfNumbers.removeAt(0)
            2 -> listOfNumbers.removeAt(listOfNumbers.lastIndex)
            3 -> run {
                val temp = listOfNumbers[j]
                for (k in j downTo i + 1) {
                    listOfNumbers[k] = listOfNumbers[k - 1]
                }
                listOfNumbers[i] = temp
            }
            else -> run {
                throw IllegalArgumentException("Commands can only be from 1 to 3")
            }
        }
    }

    fun printListOfNumbers() {
        print(listOfNumbers.joinToString(", ", "[", "]\n"))
    }
}
