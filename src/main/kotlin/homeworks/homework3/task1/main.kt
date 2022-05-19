package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>()

    for (i in 1..64) {
        treeMap[i] = i
    }
    print(treeMap.toString())
}