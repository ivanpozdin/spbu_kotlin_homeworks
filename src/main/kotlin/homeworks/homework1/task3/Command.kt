package homeworks.homework1.task3

enum class Command(val number: Int) {
    STOP_ENTERING_COMMANDS(0),
    INSERTION_IN_BEGINNING(1),
    INSERTION_IN_END(2),
    MOVING_FROM_TO(3),
    CANCEL_LAST_ACTION(4),
    PRINT_LIST(5),
    NOT_DEFINED_COMMAND(6)
}
