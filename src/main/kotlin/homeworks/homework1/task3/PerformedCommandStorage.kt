package homeworks.homework1.task3

class PerformedCommandStorage {
    var listOfNumbers: MutableList<Int> = mutableListOf()
    private var previousListOfNumbers: MutableList<Int> = mutableListOf()

    fun insetElementInTheBeginningOfList(x: Int){
        previousListOfNumbers = listOfNumbers
        listOfNumbers.add(0, x)
    }
    fun insetElementInTheEndOfList(x: Int){
        previousListOfNumbers = listOfNumbers
        listOfNumbers.add(x)

    }
    fun moveAnElement(i: Int, j: Int){
        if ((i < 0) or (j < 0) or (i > listOfNumbers.size) or (j > listOfNumbers.size)){
            println("Wrongs indexes")
            return
        }
        previousListOfNumbers = listOfNumbers
        val temp = listOfNumbers[i]
        listOfNumbers[i] = listOfNumbers[j]
        listOfNumbers[j] = temp

    }
    fun cancelLastAction(){
        if (previousListOfNumbers == mutableListOf<Int>()){
            println("There was not last action")
            return
        }
        val tempList: MutableList<Int> = listOfNumbers
        listOfNumbers = previousListOfNumbers
        previousListOfNumbers = tempList
    }

}