package homeworks.homework1.task3

val performedCommandStorage = PerformedCommandStorage()
const val quitProgram = 0
const val cancelingLastAction = 4
const val notDefinedCommand = 5

fun main() {
    println(
        """Есть 5 комманд:
0: остановить ввод
1: вставить элемент x в начало списка, 
2: вставить элемент x в конец списка, 
3: переместить элемент с i на j позицию, 
4: отменить последнее действие"""
    )
    var command = notDefinedCommand
    while (command != quitProgram) {
        print("Введите номер комманды: ")
        command = readLine()?.toIntOrNull() ?: -1
        when (command) {
            insertionInBeginning -> {
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
            insertionInEnd -> {

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
            movingFromTo -> {
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
            cancelingLastAction -> {
                try {
                    performedCommandStorage.cancelLastAction()
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
                print(performedCommandStorage.getListOfNumbers().joinToString(", ", "[", "]\n"))
            }
            quitProgram -> {
                println("Завершение работы")
            }
            else -> {
                println("Неверная комманда")
            }
        }
    }
}
