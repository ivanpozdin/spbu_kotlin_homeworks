package homeworks.homework1.task3

class PerformedCommandStorage {
    private val listOfNumbers: MutableList<Int> = mutableListOf()
    val innerListOfNumbers: MutableList<Int> get() = listOfNumbers
    private var performedCommands: ArrayDeque<Action> = ArrayDeque()

    fun addCommand(action: Action) {
        performedCommands.add(action)
    }

    fun cancelLastAction() {
        require(performedCommands.isNotEmpty()) { "There was not last action" }
        performedCommands.removeLast().undo()
    }
}
