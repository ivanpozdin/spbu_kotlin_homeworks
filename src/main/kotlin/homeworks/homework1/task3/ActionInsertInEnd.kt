package homeworks.homework1.task3

class ActionInsertInEnd(private val value: Int) : Action {
    override fun performAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.add(value)
    }

    override fun undo(listOfNumbers: MutableList<Int>) {
        listOfNumbers.removeAt(listOfNumbers.lastIndex)
    }
}
