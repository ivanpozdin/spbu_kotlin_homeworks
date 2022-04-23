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
    val action = ActionInsertInBeginning(x)
    action.performAction(performedCommandStorage.innerListOfNumbers)
    performedCommandStorage.addCommand(action)
}

fun processInsertionInEnd(x: Int, performedCommandStorage: PerformedCommandStorage) {
    val action = ActionInsertInEnd(x)
    action.performAction(performedCommandStorage.innerListOfNumbers)
    performedCommandStorage.addCommand(action)
}

fun processMovingFromTo(fromIndex: Int, toIndex: Int, performedCommandStorage: PerformedCommandStorage) {
    val action = ActionMoveFromTo(fromIndex, toIndex)
    try {
        action.performAction(performedCommandStorage.innerListOfNumbers)
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
        STOP: остановить ввод
        INS_BEG: вставить элемент x в начало списка,
        INS_END: вставить элемент x в конец списка,"
        MOVE: переместить элемент с i на j позицию,
        CANCEL: отменить последнее действие",
        PRINT: напечатать список
    """.trimIndent()
    println(message)
}

fun processCommands() {
    val performedCommandStorage = PerformedCommandStorage()
    var command = Command.NOT_DEFINED_COMMAND.type
    while (command != Command.STOP_ENTERING_COMMANDS.type) {
        print("Введите команду: ")
        command = readLine() ?: Command.NOT_DEFINED_COMMAND.type
        when (command) {
            Command.INSERTION_IN_BEGINNING.type -> {
                val x: Int = inputParameter("Введите число, которое нужно добавить в начало списка: ")
                processInsertionInBeginning(x, performedCommandStorage)
            }
            Command.INSERTION_IN_END.type -> {
                val x: Int = inputParameter("Введите число, которое нужно добавить в конец списка: ")
                processInsertionInEnd(x, performedCommandStorage)
            }
            Command.MOVING_FROM_TO.type -> {
                val fromIndex = inputParameter("Введите индекс элемента, который нужно переместить: ")
                val toIndex = inputParameter("Введите индекс на который нужно переместить: ")
                processMovingFromTo(fromIndex, toIndex, performedCommandStorage)
            }
            Command.CANCEL_LAST_ACTION.type -> {
                processCancelingLastAction(performedCommandStorage)
            }
            Command.PRINT_LIST.type -> {
                processPrintingList(performedCommandStorage)
            }
            Command.STOP_ENTERING_COMMANDS.type -> {
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
