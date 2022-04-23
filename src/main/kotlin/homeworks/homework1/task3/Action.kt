package homeworks.homework1.task3

interface Action {
    fun performAction(listOfNumbers: MutableList<Int>)
    fun undo(listOfNumbers: MutableList<Int>)
}
