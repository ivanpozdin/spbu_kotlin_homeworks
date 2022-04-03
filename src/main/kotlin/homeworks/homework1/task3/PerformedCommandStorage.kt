package homeworks.homework1.task3

class PerformedCommandStorage {
    var listOfNumbers: MutableList<Int> = mutableListOf()
    var previousListOfNumbers: MutableList<Int> = mutableListOf()

    fun insetElementInTheBeginningOfList(x: Int) {
        previousListOfNumbers = listOfNumbers.toMutableList()
        listOfNumbers.add(0, x)
    }

    fun insetElementInTheEndOfList(x: Int) {
        previousListOfNumbers = listOfNumbers.toMutableList()
        listOfNumbers.add(x)
    }

    fun moveAnElement(i: Int, j: Int) {
        if ((i < 0) or (j < 0) or (i > listOfNumbers.size) or (j > listOfNumbers.size)) {
            println("Wrongs indexes")
            return
        }
        previousListOfNumbers = listOfNumbers.toMutableList()
        val temp = listOfNumbers[i]
        listOfNumbers[i] = listOfNumbers[j]
        listOfNumbers[j] = temp
    }

    fun cancelLastAction() {
        require(previousListOfNumbers != emptyList<Int>()) { ("There was not last action") }
        val tempList: MutableList<Int> = listOfNumbers.toMutableList()
        listOfNumbers = previousListOfNumbers.toMutableList()
        previousListOfNumbers = tempList.toMutableList()
    }

}