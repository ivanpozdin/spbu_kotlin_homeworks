package homeworks.homework1.task3

fun main() {
    println(
        "Есть 4 комманды:" +
            "\n1: вставить элемент x в начало списка, \n2: вставить элемент x в конец списка, \n3: переместить элемент" +
            " с i на j позицию, \n4: отменить последнее действие"
    )
    println(
        "Для того, чтобы активировать соответсвующую комманду нужно, сначала ввести номер команды, а затем" +
            " соотвествующие аргументы"
    )

    println("Для того, чтобы завершить ввод, введите \"0\"")
    println("Первая комманда:\"1\" (вместо x нужно подставить ваше число)")
    println("Вторая комманда:\"2\" (вместо x нужно подставить ваше число)")
    println("Третья комманда:\"3\" (вместо i и j нужно подставить ваши индексы)")
    println("Четвёртая комманда:\"4\"")

    val performedCommandStorage = PerformedCommandStorage()
    var command = 5
    while (command != 0) {
        command = readLine()?.toInt() ?: 0
        when (command) {
            1 -> {
                print("Введите число, которое нужно добавить в начало списка: ")
                val x: Int = readLine()?.toInt() ?: 0
                performedCommandStorage.insetElementInTheBeginningOfList(x)
                for (i in 0 until performedCommandStorage.listOfNumbers.size)
                    print("{performedCommandStorage.listOfNumbers[i]} ")
                println()
            }
            2 -> {
                print("Введите число, которое нужно добавить в конец списка: ")
                val x: Int = readLine()?.toInt() ?: 0
                performedCommandStorage.insetElementInTheEndOfList(x)
                for (i in 0 until performedCommandStorage.listOfNumbers.size)
                    print("performedCommandStorage.listOfNumbers[i] ")
                println()
            }
            3 -> {
                print("Введите 2 индекса( перемещение числа с 1ой позиции на 2ю: ")
                val i: Int = readLine()?.toInt() ?: 0
                val j: Int = readLine()?.toInt() ?: 0
                performedCommandStorage.moveAnElement(i, j)
                for (t in 0 until performedCommandStorage.listOfNumbers.size)
                    print("{performedCommandStorage.listOfNumbers[t]} ")
                println()
            }
            4 -> {
                performedCommandStorage.cancelLastAction()
                for (i in 0 until performedCommandStorage.listOfNumbers.size)
                    print("{performedCommandStorage.listOfNumbers[i]} ")
                println()
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


