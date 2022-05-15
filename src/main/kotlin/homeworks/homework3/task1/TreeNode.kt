package homeworks.homework3.task1

class TreeNode<K : Comparable<K>, V>(var key: K, var value: V) {
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
        if (getBalanceFactor() == 2) {
            if (rightChild?.getBalanceFactor() == -1) {
                rightChild = rightChild?.rotateRight()
            }
            return rotateLeft()
        }
        if (getBalanceFactor() == -2) {
            if (leftChild?.getBalanceFactor() == 1) leftChild = leftChild?.rotateLeft()
            return rotateRight()
        }
        return this
    }

    fun findNodeWithGivenKey(givenKey: K, rootNode: TreeNode<K, V>? = this): TreeNode<K, V>? {
        if (rootNode == null) {
            return null
        }
        if (givenKey > rootNode.key) {
            return findNodeWithGivenKey(givenKey, rootNode.rightChild)
        } else if (givenKey < rootNode.key) {
            return findNodeWithGivenKey(givenKey, rootNode.leftChild)
        }
        return rootNode
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
        if (node == null) {
            return Pair(null, null)
        }
        if (node.rightChild != null) {
            val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?> = extractMax(node.rightChild)
            node.rightChild = pair.second
            return Pair<TreeNode<K, V>?, TreeNode<K, V>?>(pair.first, node)
        }
        return Pair<TreeNode<K, V>?, TreeNode<K, V>?>(node, node.leftChild)
    }

    fun deleteNode(
        key: K?,
        specialArgumentForOldValue: ValueOrNull<V>,
        rootNode: TreeNode<K, V>? = this
    ): TreeNode<K, V>? {
        if (rootNode == null || key == null) return null
        if (key < rootNode.key) {
            rootNode.leftChild = deleteNode(key, specialArgumentForOldValue, rootNode.leftChild)
        } else if (key > rootNode.key) {
            rootNode.rightChild = deleteNode(key, specialArgumentForOldValue, rootNode.rightChild)
        } else {
            specialArgumentForOldValue.value = rootNode.value

            val newRoot: TreeNode<K, V>?
            if (rootNode.leftChild == null) {
                newRoot = rootNode.rightChild
            } else if (rootNode.rightChild == null) {
                newRoot = rootNode.leftChild
            } else {
                val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?>? = rootNode.leftChild?.extractMax()
                newRoot = pair?.first
                newRoot?.leftChild = pair?.second
                newRoot?.rightChild = rootNode.rightChild
            }
            return newRoot?.balance()
        }
        return rootNode.balance()
    }
}
