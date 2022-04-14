package homeworks.homework1.task3

class ActionInsertInBeginning(private val value: Int, private val listOfNumbers: MutableList<Int>) : Action {
    override fun performAction() {
        listOfNumbers.add(0, value)
    }

    override fun undo() {
        listOfNumbers.removeAt(0)
    }
}
