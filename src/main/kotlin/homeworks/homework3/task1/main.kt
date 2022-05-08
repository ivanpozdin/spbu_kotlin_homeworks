package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>()
    for (i in 1..1024){
        treeMap.put(i, i)
    }
    for (i in 1..100){
        print(treeMap.hasKey(i))
    }
    print("\nHeight of the tree is ${treeMap.height()}\n")
}