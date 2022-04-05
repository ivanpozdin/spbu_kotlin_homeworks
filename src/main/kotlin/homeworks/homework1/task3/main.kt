package homeworks.homework1.task3

const val QUIT_PROGRAM = 0
const val CANCEL_LAST_ACTION = 4
const val NOT_DEFINED_COMMAND = 5

fun main() {
    println("Есть 5 комманд:")
    println("0: остановить ввод")
    println("1: вставить элемент x в начало списка,")
    println("2: вставить элемент x в конец списка,")
    println("3: переместить элемент с i на j позицию,")
    println("4: отменить последнее действие")

    val performedCommandStorage = PerformedCommandStorage()
    var command = NOT_DEFINED_COMMAND
    while (command != QUIT_PROGRAM) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            INSERTION_IN_BEGINNING -> {
                var x: Int? = null
                while (x == null) {
                    print("Введите число, которое нужно добавить в начало списка: ")
                    x = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                performedCommandStorage.insertInBeginning(x)
                print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
            }
            INSERTION_IN_END -> {

                var x: Int? = null
                while (x == null) {
                    print("Введите число, которое нужно добавить в конец списка: ")
                    x = readLine()?.toIntOrNull() ?: run {
                        println("   Неверный аргумент, попробуйте ещё раз")
                        null
                    }
                }
                performedCommandStorage.insertInEnd(x)
                print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
            }
            MOVING_FROM_TO -> {
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
                print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
            }
            CANCEL_LAST_ACTION -> {
                try {
                    performedCommandStorage.cancelLastAction()
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
                print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
            }
            QUIT_PROGRAM -> {
                println("Завершение работы")
            }
            else -> {
                println("Неверная комманда")
            }
        }
    }
}
