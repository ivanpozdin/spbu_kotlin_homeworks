package homeworks.homework3.task1

fun main() {
    val treeMap = AVLTree<Int, Int>(0,mutableSetOf(), mutableSetOf(), mutableListOf())

    for (i in 1..100){
        treeMap[i] = i
    }
    print("height before removing = ${treeMap.height()}\n")

    for (i in 1..100){
        print("${treeMap[i]}\n")
    }
    print("\n")

    for (i in 1..100){
        if (i%2 == 0){
            treeMap.remove(i)
        }
    }
    for (i in 1..100){
        print("${treeMap[i]}\n")
    }
    print("height after removing = ${treeMap.height()}\n")
}
