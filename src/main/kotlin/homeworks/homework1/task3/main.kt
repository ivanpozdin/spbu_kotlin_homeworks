package homeworks.homework1.task3

enum class Command(val number: Int) {
    QUIT_PROGRAM(0),
    INSERTION_IN_BEGINNING(1),
    INSERTION_IN_END(2),
    MOVING_FROM_TO(3),
    CANCEL_LAST_ACTION(4),
    PRINT_LIST(5),
    NOT_DEFINED_COMMAND(6)
}

fun inputParameter(message: String): Int {
    var parameter: Int? = null
    while (parameter == null) {
        print(message)
        parameter = readLine()?.toIntOrNull() ?: run {
            println("   Неверный аргумент, попробуйте ещё раз")
            null
        }
    }
    return parameter
}

fun processInsertionInBeginning(performedCommandStorage: PerformedCommandStorage) {
    val x: Int = inputParameter("Введите число, которое нужно добавить в начало списка: ")
    performedCommandStorage.insertInBeginning(x)
}

fun processInsertionInEnd(performedCommandStorage: PerformedCommandStorage) {
    val x: Int = inputParameter("Введите число, которое нужно добавить в конец списка: ")
    performedCommandStorage.insertInEnd(x)
}

fun processMovingFromTo(performedCommandStorage: PerformedCommandStorage) {
    val fromIndex = inputParameter("Введите индекс элемента, который нужно переместить: ")
    val toIndex = inputParameter("Введите индекс на который нужно переместить: ")
    try {
        performedCommandStorage.moveFromTo(fromIndex, toIndex)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun processCancelingLastAction(performedCommandStorage: PerformedCommandStorage) {
    try {
        performedCommandStorage.cancelLastAction()
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun processPrintingList(performedCommandStorage: PerformedCommandStorage) {
    performedCommandStorage.printListOfNumbers()
}

fun printRules() {
    println("Есть 5 комманд:")
    println("0: остановить ввод")
    println("1: вставить элемент x в начало списка,")
    println("2: вставить элемент x в конец списка,")
    println("3: переместить элемент с i на j позицию,")
    println("4: отменить последнее действие")
    println("5: напечатать список")
}

fun processCommands() {
    val performedCommandStorage = PerformedCommandStorage()
    var command = Command.NOT_DEFINED_COMMAND.number
    while (command != Command.QUIT_PROGRAM.number) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            Command.INSERTION_IN_BEGINNING.number -> {
                processInsertionInBeginning(performedCommandStorage)
            }
            Command.INSERTION_IN_END.number -> {
                processInsertionInEnd(performedCommandStorage)
            }
            Command.MOVING_FROM_TO.number -> {
                processMovingFromTo(performedCommandStorage)
            }
            Command.CANCEL_LAST_ACTION.number -> {
                processCancelingLastAction(performedCommandStorage)
            }
            Command.PRINT_LIST.number -> {
                processPrintingList(performedCommandStorage)
            }
            Command.QUIT_PROGRAM.number -> {
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
