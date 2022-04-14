package homeworks.homework1.task3

class ActionInsertInEnd(private val value: Int, private val listOfNumbers: MutableList<Int>) : Action {
    override fun performAction() {
        listOfNumbers.add(value)
    }

    override fun undo() {
        listOfNumbers.removeAt(listOfNumbers.lastIndex)
    }
}
