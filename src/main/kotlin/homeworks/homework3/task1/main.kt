package homeworks.homework3.task1

fun main() {
    val tree = AVLTree<Int, Int>()
    for (i in 1..9) tree.put(i, i)
    print(tree.toString())
}