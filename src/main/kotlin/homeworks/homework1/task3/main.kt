package homeworks.homework1.task3

fun main() {
    println(
        "Есть 5 комманд:" +
            "\n0: остановить ввод" + "\n1: вставить элемент x в начало списка, " +
            "\n2: вставить элемент x в конец списка, \n3: переместить элемент" +
            " с i на j позицию, \n4: отменить последнее действие"
    )

    val performedCommandStorage = PerformedCommandStorage()
    var command = 5
    while (command != 0) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: 0
        when (command) {
            1 -> {
                print("Введите число, которое нужно добавить в начало списка: ")
                val x: Int = readLine()?.toIntOrNull() ?: 0
                performedCommandStorage.insetElementInTheBeginningOfList(x)
                print(performedCommandStorage.listOfNumbers.joinToString(", ", "", "\n"))
            }
            2 -> {
                print("Введите число, которое нужно добавить в конец списка: ")
                val x: Int = readLine()?.toIntOrNull() ?: 0
                performedCommandStorage.insetElementInTheEndOfList(x)
                print(performedCommandStorage.listOfNumbers.joinToString(", ", "", "\n"))
            }
            3 -> {
                print("Введите 2 индекса (перемещение числа с 1ой позиции на 2ю: ")
                val i: Int = readLine()?.toIntOrNull() ?: 0
                val j: Int = readLine()?.toIntOrNull() ?: 0
                performedCommandStorage.moveAnElement(i, j)
                print(performedCommandStorage.listOfNumbers.joinToString(", ", "", "\n"))
            }
            4 -> {
                performedCommandStorage.cancelLastAction()
                print(performedCommandStorage.listOfNumbers.joinToString(", ", "", "\n"))
            }

            0 -> {
                println("Завершение работы")
            }
            else -> {
                println("Неверная комманда")
            }
        }
    }
}


