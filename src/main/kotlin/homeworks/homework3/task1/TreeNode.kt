package homeworks.homework3.task1

class TreeNode<K, V>(var key: K, var value: V) {
    var leftChild: TreeNode<K, V>? = null
    var rightChild: TreeNode<K, V>? = null
    var height: Int = 1
}