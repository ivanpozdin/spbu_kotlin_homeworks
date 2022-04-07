package homeworks.homework1.task3

import java.util.ArrayDeque

open class Action {
    open fun performAction() {
    }

    open fun undo() {
    }
}

class ActionInsertionInBeginning(private val value: Int, private val listOfNumbers: MutableList<Int>) : Action() {
    override fun performAction() {
        listOfNumbers.add(0, value)
    }

    override fun undo() {
        listOfNumbers.removeAt(0)
    }
}

class ActionInsertionInEnd(private val value: Int, private val listOfNumbers: MutableList<Int>) : Action() {
    override fun performAction() {
        listOfNumbers.add(value)
    }

    override fun undo() {
        listOfNumbers.removeAt(listOfNumbers.lastIndex)
    }
}

class ActionMoveFromTo(
    private val listOfNumbers: MutableList<Int>,
    private val fromIndex: Int,
    private val toIndex: Int
) : Action() {
    override fun performAction() {
        require((fromIndex in 0..listOfNumbers.lastIndex) and (toIndex in 0..listOfNumbers.lastIndex)) {
            "Indices are not valid"
        }
        val temp = listOfNumbers[fromIndex]
        listOfNumbers.removeAt(fromIndex)
        listOfNumbers.add(toIndex, temp)
    }

    override fun undo() {
        val temp = listOfNumbers[toIndex]
        listOfNumbers.removeAt(toIndex)
        listOfNumbers.add(fromIndex, temp)
    }
}

class PerformedCommandStorage {
    private val listOfNumbers: MutableList<Int> = mutableListOf()
    val innerListOfNumbers: List<Int> get() = listOfNumbers
    private var performedCommands: ArrayDeque<Action> = ArrayDeque()
    fun insertInBeginning(x: Int) {
        val actionInsertInBeginning = ActionInsertionInBeginning(x, listOfNumbers)
        actionInsertInBeginning.performAction()
        performedCommands.push(ActionInsertionInBeginning(x, listOfNumbers))
    }

    fun insertInEnd(x: Int) {
        val actionInsertInEnd = ActionInsertionInEnd(x, listOfNumbers)
        actionInsertInEnd.performAction()
        performedCommands.push(actionInsertInEnd)
    }

    fun moveFromTo(fromIndex: Int, toIndex: Int) {
        val actionMoveFromTo = ActionMoveFromTo(listOfNumbers, fromIndex, toIndex)
        actionMoveFromTo.performAction()
        performedCommands.push(actionMoveFromTo)
    }

    fun cancelLastAction() {
        require(performedCommands.isNotEmpty()) { "There was not last action" }
        performedCommands.pop().undo()
    }

    fun printListOfNumbers() {
        print(listOfNumbers.joinToString(", ", "[", "]\n"))
    }
}
