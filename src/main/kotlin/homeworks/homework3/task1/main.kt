package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>(0,mutableSetOf(), mutableSetOf(), mutableListOf())
    print(treeMap.containsKey(50))
    for (i in 1..100){
        treeMap[i] = i
    }
    print(treeMap.containsValue(50))
}
