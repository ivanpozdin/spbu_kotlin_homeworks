package homeworks.homework1.task3


const val QUIT_PROGRAM = 0
const val CANCEL_LAST_ACTION = 4
const val NOT_DEFINED_COMMAND = 5


fun inputParameter(message: String): Int {
    var x: Int? = null
    while (x == null) {
        print(message)
        x = readLine()?.toIntOrNull() ?: run {
            println("   Неверный аргумент, попробуйте ещё раз")
            null
        }
    }
    return x
}

fun processInsertionInBeginning(performedCommandStorage: PerformedCommandStorage) {
    val x: Int = inputParameter("Введите число, которое нужно добавить в начало списка: ")
    performedCommandStorage.insertInEnd(x)
    print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
}

fun processInsertionInEnd(performedCommandStorage: PerformedCommandStorage) {
    val x: Int = inputParameter("Введите число, которое нужно добавить в конец списка: ")
    performedCommandStorage.insertInEnd(x)
    print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
}

fun processMovingFromTo(performedCommandStorage: PerformedCommandStorage) {
    val i = inputParameter("Введите индекс элемента, который нужно переместить: ")
    val j = inputParameter("Введите индекс на который нужно переместить: ")
    try {
        performedCommandStorage.moveFromTo(i, j)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
    print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
}

fun processCancelingLastAction(performedCommandStorage: PerformedCommandStorage) {
    try {
        performedCommandStorage.cancelLastAction()
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
    print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
}

fun printRules() {
    println("Есть 5 комманд:")
    println("0: остановить ввод")
    println("1: вставить элемент x в начало списка,")
    println("2: вставить элемент x в конец списка,")
    println("3: переместить элемент с i на j позицию,")
    println("4: отменить последнее действие")
}

fun processCommands() {
    val performedCommandStorage = PerformedCommandStorage()
    var command = NOT_DEFINED_COMMAND
    while (command != QUIT_PROGRAM) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            INSERTION_IN_BEGINNING -> {
                processInsertionInBeginning(performedCommandStorage)
            }
            INSERTION_IN_END -> {
                processInsertionInEnd(performedCommandStorage)
            }
            MOVING_FROM_TO -> {
                processMovingFromTo(performedCommandStorage)
            }
            CANCEL_LAST_ACTION -> {
                processCancelingLastAction(performedCommandStorage)
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

fun main() {
    printRules()
    processCommands()
}
