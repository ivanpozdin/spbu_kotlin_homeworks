package homeworks.homework1.task3

import java.util.Stack

const val INSERTION_IN_BEGINNING = 1
const val INSERTION_IN_END = 2
const val MOVING_FROM_TO = 3
const val MEANINGLESS_VALUE = 0
const val START_INDEX = 0

class PerformedCommandStorage {
    private var listOfNumbers: MutableList<Int> = mutableListOf()

    private var performedCommands: Stack<Triple<Int, Int, Int>> = Stack()
    fun insertInBeginning(x: Int) {
        listOfNumbers.add(START_INDEX, x)
        performedCommands.push(Triple(INSERTION_IN_BEGINNING, MEANINGLESS_VALUE, MEANINGLESS_VALUE))
    }

    fun insertInEnd(x: Int) {
        listOfNumbers.add(x)
        performedCommands.push(Triple(INSERTION_IN_END, MEANINGLESS_VALUE, MEANINGLESS_VALUE))
    }

    fun moveFromTo(i: Int, j: Int) {
        require((i in 0..listOfNumbers.lastIndex) and (j in 0..listOfNumbers.lastIndex)) {
            "Indices are not valid"
        }
        val temp = listOfNumbers[i]
        (i until j).forEach { listOfNumbers[it] = listOfNumbers[it + 1] }
        listOfNumbers[j] = temp
        performedCommands.push(Triple(MOVING_FROM_TO, i, j))
    }

    fun cancelLastAction() {
        require(!performedCommands.isEmpty()) { "There was not last action" }
        val (command, i, j) = performedCommands.pop()
        when (command) {
            INSERTION_IN_BEGINNING -> listOfNumbers.removeAt(0)
            INSERTION_IN_END -> listOfNumbers.removeAt(listOfNumbers.lastIndex)
            MOVING_FROM_TO -> run {
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
