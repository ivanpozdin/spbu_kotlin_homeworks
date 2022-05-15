package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>(0, mutableSetOf(), mutableSetOf(), mutableListOf())

    for (i in 1..1) {
        treeMap[i] = i * 10
    }

    for (i in 1..1) {
        print("${treeMap[i]}\n")
    }
    print("\n")
    print("${treeMap.containsKey(1)}\n")
    print("${treeMap.containsKey(2)}\n")
    print("${treeMap.containsValue(10)}\n")
    print("${treeMap.remove(1)}\n")
    print("${treeMap.containsKey(1)}\n")
    print("${treeMap.containsKey(2)}\n")
    print("${treeMap.containsValue(10)}\n")
    print("${treeMap[1]}\n")
    print("${treeMap.isEmpty()}\n")

    for (i in 1..1024) {
        treeMap[i] = i * 10
    }
    print("${treeMap[100]}\n")
}
