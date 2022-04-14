package homeworks.homework1.task3

fun inputParameter(message: String): Int {
    var parameter: Int? = null
    while (parameter == null) {
        print(message)
        parameter = readLine()?.toIntOrNull()
        if (parameter == null) {
            println("Неверный аргумент, попробуйте ещё раз")
        }
    }
    return parameter
}

fun processInsertionInBeginning(x: Int, performedCommandStorage: PerformedCommandStorage) {
    val action = ActionInsertInBeginning(x, performedCommandStorage.innerListOfNumbers)
    action.performAction()
    performedCommandStorage.addCommand(action)
}

fun processInsertionInEnd(x: Int, performedCommandStorage: PerformedCommandStorage) {
    val action = ActionInsertInEnd(x, performedCommandStorage.innerListOfNumbers)
    action.performAction()
    performedCommandStorage.addCommand(action)
}

fun processMovingFromTo(fromIndex: Int, toIndex: Int, performedCommandStorage: PerformedCommandStorage) {
    val action = ActionMoveFromTo(performedCommandStorage.innerListOfNumbers, fromIndex, toIndex)
    try {
        action.performAction()
        performedCommandStorage.addCommand(action)
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
    print(performedCommandStorage.innerListOfNumbers.joinToString(", ", "[", "]\n"))
}

fun printRules() {
    val message = """
        Есть 5 комманд:
        0: остановить ввод
        1: вставить элемент x в начало списка,
        2: вставить элемент x в конец списка,"
        3: переместить элемент с i на j позицию,
        4: отменить последнее действие",
        5: напечатать список
    """.trimIndent()
    println(message)
}

fun processCommands() {
    val performedCommandStorage = PerformedCommandStorage()
    var command = Command.NOT_DEFINED_COMMAND.ordinal
    while (command != Command.STOP_ENTERING_COMMANDS.ordinal) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            Command.INSERTION_IN_BEGINNING.ordinal -> {
                val x: Int = inputParameter("Введите число, которое нужно добавить в начало списка: ")
                processInsertionInBeginning(x, performedCommandStorage)
            }
            Command.INSERTION_IN_END.ordinal -> {
                val x: Int = inputParameter("Введите число, которое нужно добавить в конец списка: ")
                processInsertionInEnd(x, performedCommandStorage)
            }
            Command.MOVING_FROM_TO.ordinal -> {
                val fromIndex = inputParameter("Введите индекс элемента, который нужно переместить: ")
                val toIndex = inputParameter("Введите индекс на который нужно переместить: ")
                processMovingFromTo(fromIndex, toIndex, performedCommandStorage)
            }
            Command.CANCEL_LAST_ACTION.ordinal -> {
                processCancelingLastAction(performedCommandStorage)
            }
            Command.PRINT_LIST.ordinal -> {
                processPrintingList(performedCommandStorage)
            }
            Command.STOP_ENTERING_COMMANDS.ordinal -> {
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