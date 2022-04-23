package homeworks.homework1.task3

enum class Command(val type: String) {
    STOP_ENTERING_COMMANDS("STOP"),
    INSERTION_IN_BEGINNING("INS_BEG"),
    INSERTION_IN_END("INS_END"),
    MOVING_FROM_TO("MOVE"),
    CANCEL_LAST_ACTION("CANCEL"),
    PRINT_LIST("PRINT"),
    NOT_DEFINED_COMMAND("NOT_DEF_COM")
}
