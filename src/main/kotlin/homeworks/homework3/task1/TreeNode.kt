package homeworks.homework3.task1

class TreeNode<K : Comparable<K>, V>(private var key: K, var value: V) {
    var leftChild: TreeNode<K, V>? = null
    var rightChild: TreeNode<K, V>? = null
    private var height: Int = 1

    private fun updateHeight() {
        height = Integer.max((leftChild?.height ?: 0), (rightChild?.height ?: 0)) + 1
    }

    private fun rotateLeft(): TreeNode<K, V>? {
        val child: TreeNode<K, V>? = rightChild
        rightChild = child?.leftChild
        child?.leftChild = this
        updateHeight()
        child?.updateHeight()
        return child
    }

    private fun rotateRight(): TreeNode<K, V>? {
        val child: TreeNode<K, V>? = leftChild
        leftChild = child?.rightChild
        child?.rightChild = this
        updateHeight()
        child?.updateHeight()
        return child
    }

    private fun getBalanceFactor(): Int {
        val left: Int = leftChild?.height ?: 0
        val right: Int = rightChild?.height ?: 0
        return right - left
    }

    private fun balance(): TreeNode<K, V>? {
        updateHeight()
        val nodeToReturn: TreeNode<K, V>?
        if (getBalanceFactor() == 2) {
            if (rightChild?.getBalanceFactor() == -1) {
                rightChild = rightChild?.rotateRight()
            }
            nodeToReturn = rotateLeft()
        } else if (getBalanceFactor() == -2) {
            if (leftChild?.getBalanceFactor() == 1) leftChild = leftChild?.rotateLeft()
            nodeToReturn = rotateRight()
        } else {
            nodeToReturn = this
        }
        return nodeToReturn
    }

    fun findNodeWithGivenKey(givenKey: K, rootNode: TreeNode<K, V>? = this): TreeNode<K, V>? {
        var nodeToReturn: TreeNode<K, V>? = rootNode
        if (rootNode != null && givenKey > rootNode.key) {
            nodeToReturn = findNodeWithGivenKey(givenKey, rootNode.rightChild)
        } else if (rootNode != null && givenKey < rootNode.key) {
            nodeToReturn = findNodeWithGivenKey(givenKey, rootNode.leftChild)
        }
        return nodeToReturn
    }

    fun insert(
        key: K, value: V, specialArgumentForOldValue: ValueOrNull<V>, node: TreeNode<K, V>? = this
    ): TreeNode<K, V>? {
        if (node == null) return TreeNode(key, value)
        if (key < node.key) {
            node.leftChild = insert(key, value, specialArgumentForOldValue, node.leftChild)
        } else if (key > node.key) {
            node.rightChild = insert(key, value, specialArgumentForOldValue, node.rightChild)
        } else {
            specialArgumentForOldValue.value = node.value
            node.value = value
        }
        return node.balance()
    }

    private fun extractMax(node: TreeNode<K, V>? = this): Pair<TreeNode<K, V>?, TreeNode<K, V>?> {
        val pairToReturn: Pair<TreeNode<K, V>?, TreeNode<K, V>?>
        if (node?.rightChild != null) {
            val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?> = extractMax(node.rightChild)
            node.rightChild = pair.second
            pairToReturn = Pair<TreeNode<K, V>?, TreeNode<K, V>?>(pair.first, node)
        } else {
            pairToReturn = Pair(node, node?.leftChild)
        }
        return pairToReturn
    }

    fun deleteNode(
        key: K?, specialArgumentForOldValue: ValueOrNull<V>, rootNode: TreeNode<K, V>? = this
    ): TreeNode<K, V>? {
        var newRoot: TreeNode<K, V>? = rootNode
        if (newRoot == null || key == null) {
            newRoot = null
        } else if (key < newRoot.key) {
            newRoot.leftChild = deleteNode(key, specialArgumentForOldValue, newRoot.leftChild)
        } else if (key > newRoot.key) {
            newRoot.rightChild = deleteNode(key, specialArgumentForOldValue, newRoot.rightChild)
        } else {
            specialArgumentForOldValue.value = newRoot.value
            if (newRoot.leftChild == null) {
                newRoot = newRoot.rightChild
            } else if (newRoot.rightChild == null) {
                newRoot = newRoot.leftChild
            } else {
                val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?>? = newRoot.leftChild?.extractMax()
                newRoot = pair?.first
                newRoot?.leftChild = pair?.second
                newRoot?.rightChild = rootNode?.rightChild
            }
        }
        return newRoot?.balance()
    }
}
