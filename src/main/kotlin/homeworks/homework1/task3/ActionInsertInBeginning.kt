package homeworks.homework1.task3

class ActionInsertInBeginning(private val value: Int) : Action {
    override fun performAction(listOfNumbers: MutableList<Int>) {
        listOfNumbers.add(0, value)
    }

    override fun undo(listOfNumbers: MutableList<Int>) {
        listOfNumbers.removeAt(0)
    }
}
