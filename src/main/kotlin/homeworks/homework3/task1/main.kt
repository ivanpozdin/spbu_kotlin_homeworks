package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>()
    for (i in 1..9) {
        treeMap.put(i, i * 10)
    }
    for (i in 0..10) {
        print("${treeMap.hasKey(i)}\n")
        print("${treeMap.getValueFromKey(i)}\n")
    }
    print(treeMap.remove(1))
    for (i in 0..10) {
        print("${treeMap.hasKey(i)}\n")
        print("${treeMap.getValueFromKey(i)}\n")
    }
    print("\n")
    print(treeMap.remove(-1))
}
