package homeworks.homework3.task1

class TreeNode<K : Comparable<K>, V>(private var key: K, var value: V) {
    companion object {
        const val LEFT_CASE = 2
        const val RIGHT_CASE = -2
        const val LEFT_RIGHT_CASE = -1
        const val RIGHT_LEFT_CASE = 1
    }

    var leftChild: TreeNode<K, V>? = null
    var rightChild: TreeNode<K, V>? = null
    private var height: Int = 1

    private fun updateHeight() {
        height = Integer.max((leftChild?.height ?: 0), (rightChild?.height ?: 0)) + 1
    }

    fun getHeight(): Int {
        return height
    }

    private fun rotateLeft(): TreeNode<K, V>? {
        val child = rightChild
        rightChild = child?.leftChild
        child?.leftChild = this
        updateHeight()
        child?.updateHeight()
        return child
    }

    private fun rotateRight(): TreeNode<K, V>? {
        val child = leftChild
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
        if (getBalanceFactor() == LEFT_CASE) {
            if (rightChild?.getBalanceFactor() == LEFT_RIGHT_CASE) {
                rightChild = rightChild?.rotateRight()
            }
            nodeToReturn = rotateLeft()
        } else if (getBalanceFactor() == RIGHT_CASE) {
            if (leftChild?.getBalanceFactor() == RIGHT_LEFT_CASE) leftChild = leftChild?.rotateLeft()
            nodeToReturn = rotateRight()
        } else {
            nodeToReturn = this
        }
        return nodeToReturn
    }

    fun findNodeWithGivenKey(givenKey: K): TreeNode<K, V>? {
        return when {
            givenKey > key -> rightChild?.findNodeWithGivenKey(givenKey)
            givenKey < key -> leftChild?.findNodeWithGivenKey(givenKey)
            else -> this
        }
    }

    fun insert(
        key: K,
        value: V,
        node: TreeNode<K, V>? = this
    ): TreeNode<K, V>? {
        if (node == null) return TreeNode(key, value)
        if (key < node.key) {
            node.leftChild = insert(key, value, node.leftChild)
        } else if (key > node.key) {
            node.rightChild = insert(key, value, node.rightChild)
        } else {
            node.value = value
        }
        return node.balance()
    }

    private fun extractMax(): Pair<TreeNode<K, V>?, TreeNode<K, V>?> {
        return if (rightChild != null) {
            val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?> = rightChild?.extractMax() ?: Pair(null, null)
            rightChild = pair.second
            Pair<TreeNode<K, V>?, TreeNode<K, V>?>(pair.first, this)
        } else {
            Pair(this, leftChild)
        }
    }

    fun deleteNode(
        key: K?,
        rootNode: TreeNode<K, V>? = this
    ): TreeNode<K, V>? {
        var newRoot: TreeNode<K, V>? = rootNode
        if (newRoot == null || key == null) {
            newRoot = null
        } else if (key < newRoot.key) {
            newRoot.leftChild = deleteNode(key, newRoot.leftChild)
        } else if (key > newRoot.key) {
            newRoot.rightChild = deleteNode(key, newRoot.rightChild)
        } else {
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
