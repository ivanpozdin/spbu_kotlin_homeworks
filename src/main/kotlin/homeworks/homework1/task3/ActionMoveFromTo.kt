package homeworks.homework1.task3

class ActionMoveFromTo(
    private val listOfNumbers: MutableList<Int>,
    private val fromIndex: Int,
    private val toIndex: Int
) : Action {
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
