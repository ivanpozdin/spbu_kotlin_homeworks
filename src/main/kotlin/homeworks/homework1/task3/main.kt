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
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            1 -> {
                var x: Int? = null
                while (x == null) {
                    print("Введите число, которое нужно добавить в начало списка: ")
                    x = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                performedCommandStorage.insertInBeginning(x)
                performedCommandStorage.printListOfNumbers()

            }
            2 -> {

                var x: Int? = null
                while (x == null) {
                    print("Введите число, которое нужно добавить в конец списка: ")
                    x = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                performedCommandStorage.insertInEnd(x)
                performedCommandStorage.printListOfNumbers()
            }
            3 -> {
                var i: Int? = null
                while (i == null) {
                    print("Введите индекс элемента, который нужно переместить: ")
                    i = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                var j: Int? = null
                while (j == null) {
                    print("Введите индекс на который нужно переместить: ")
                    j = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                try {
                    performedCommandStorage.moveFromTo(i, j)
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
                performedCommandStorage.printListOfNumbers()
            }
            4 -> {
                performedCommandStorage.cancelLastAction()
                performedCommandStorage.printListOfNumbers()
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
