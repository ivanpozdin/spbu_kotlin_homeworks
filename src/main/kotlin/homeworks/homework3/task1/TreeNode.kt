package homeworks.homework3.task1

class TreeNode<K : Comparable<K>, V>(private var key: K, var value: V) {
    companion object {
        const val LEFT_CASE = 2
        const val RIGHT_CASE = -2
        const val LEFT_RIGHT_CASE = -1
        const val RIGHT_LEFT_CASE = 1
        const val COUNT = 5
    }

    var leftChild: TreeNode<K, V>? = null
    var rightChild: TreeNode<K, V>? = null
    private var height: Int = 1
    val getHeight: Int get() = height

    private fun updateHeight() {
        height = Integer.max((leftChild?.height ?: 0), (rightChild?.height ?: 0)) + 1
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
        givenKey: K,
        givenValue: V
    ): TreeNode<K, V>? {
        when {
            givenKey < key -> {
                leftChild = leftChild?.insert(givenKey, givenValue) ?: TreeNode(givenKey, givenValue)
            }
            givenKey > key -> {
                rightChild = rightChild?.insert(givenKey, givenValue) ?: TreeNode(givenKey, givenValue)
            }
            else -> value = givenValue
        }
        return balance()
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
        givenKey: K?
    ): TreeNode<K, V>? {
        return when {
            givenKey == null -> null
            givenKey < key -> {
                leftChild = leftChild?.deleteNode(givenKey)
                this.balance()
            }
            givenKey > key -> {
                rightChild = rightChild?.deleteNode(givenKey)
                this.balance()
            }
            leftChild == null -> rightChild
            rightChild == null -> leftChild
            else -> { // case, when (key == givenKey) and (this) has all children
                val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?>? = leftChild?.extractMax()
                val newRoot = pair?.first
                newRoot?.leftChild = pair?.second
                newRoot?.rightChild = rightChild
                newRoot?.balance()
            }
        }
    }

    fun getTreeDiagram(space: Int = 0): String {
        var str = ""
        var localSpace = space
        localSpace += COUNT
        str += rightChild?.getTreeDiagram(localSpace) ?: ""
        str += "\n"
        repeat(localSpace - COUNT) { str += " " }
        str += key.toString() + "\n"
        str += leftChild?.getTreeDiagram(localSpace) ?: ""
        return str
    }
}
