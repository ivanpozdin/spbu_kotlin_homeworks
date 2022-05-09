package homeworks.homework3.task1

import java.lang.Integer.max
import java.util.*


class AVLTree<K : Comparable<K>, V>(
    override val size: Int,
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>,
    override val keys: MutableSet<K>,
    override val values: MutableCollection<V>
) : MutableMap<K, V> {
    private var root: TreeNode<K, V>? = null
    private fun getHeight(node: TreeNode<K, V>?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: TreeNode<K, V>?) {
        node?.height = max(getHeight(node?.leftChild), getHeight(node?.rightChild)) + 1
    }

    private fun rotateLeft(node: TreeNode<K, V>?): TreeNode<K, V>? {
        val child: TreeNode<K, V>? = node?.rightChild
        node?.rightChild = child?.leftChild
        child?.leftChild = node
        updateHeight(node)
        updateHeight(child)
        return child
    }

    private fun rotateRight(node: TreeNode<K, V>?): TreeNode<K, V>? {
        val child: TreeNode<K, V>? = node?.leftChild
        node?.leftChild = child?.rightChild
        child?.rightChild = node
        updateHeight(node)
        updateHeight(child)
        return child
    }


    private fun getBalanceFactor(node: TreeNode<K, V>?): Int {
        if (node == null) {
            return 0
        }
        val left: Int = node.leftChild?.height ?: 0
        val right: Int = node.rightChild?.height ?: 0
        return right - left

    }

    private fun balance(node: TreeNode<K, V>?): TreeNode<K, V>? {
        updateHeight(node)
        if (getBalanceFactor(node) == 2) {
            if (getBalanceFactor(root?.rightChild) == -1) {
                node?.rightChild = rotateRight(node?.rightChild)
            }
            return rotateLeft(node)
        }
        if (getBalanceFactor(node) == -2) {
            if (getBalanceFactor(node?.leftChild) == 1)
                node?.leftChild = rotateLeft(node?.leftChild)
            return rotateRight(node)
        }
        return node
    }

    private fun findNodeWithGivenKey(rootNode: TreeNode<K, V>?, key: K): TreeNode<K, V>? {
        if (rootNode == null) {
            return null
        }
        if (key > rootNode.key) {
            return findNodeWithGivenKey(rootNode.rightChild, key)
        } else if (key < rootNode.key) {
            return findNodeWithGivenKey(rootNode.leftChild, key)
        }
        return rootNode
    }

    private fun insert(
        node: TreeNode<K, V>?,
        key: K,
        value: V,
        specialNodeForOldValue: TreeNode<K, V?>
    ): TreeNode<K, V>? {
        if (node == null)
            return TreeNode(key, value)
        if (key < node.key) {
            node.leftChild = insert(node.leftChild, key, value, specialNodeForOldValue)
        } else if (key > node.key) {
            node.rightChild = insert(node.rightChild, key, value, specialNodeForOldValue)
        } else {
            specialNodeForOldValue.value = node.value
            node.value = value
        }
        return balance(node)
    }

    override fun put(key: K, value: V): V? {
        val specialNodeForOldValue = TreeNode<K, V?>(key, null)
        root = insert(root, key, value, specialNodeForOldValue)
        return specialNodeForOldValue.value
    }

    private fun extractMax(node: TreeNode<K, V>?): Pair<TreeNode<K, V>?, TreeNode<K, V>?> {
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

    private fun deleteNodeWithoutBalance(rootNode: TreeNode<K, V>?, key: K): TreeNode<K, V>? {
        if (rootNode == null)
            return null
        if (key < rootNode.key) {
            rootNode.leftChild = deleteNodeWithoutBalance(rootNode.leftChild, key)
        } else if (key > rootNode.key) {
            rootNode.rightChild = deleteNodeWithoutBalance(rootNode.rightChild, key)
        } else {
            val newRoot: TreeNode<K, V>?
            if (rootNode.leftChild == null) {
                newRoot = rootNode.rightChild
            } else if (rootNode.rightChild == null) {
                newRoot = rootNode.leftChild
            } else {
                val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?> = extractMax(rootNode.leftChild)
                newRoot = pair.first
                newRoot?.leftChild = pair.second
                newRoot?.rightChild = rootNode.rightChild
            }
            return balance(newRoot)
        }
        return balance(rootNode)
    }

    private fun deleteNode(node: TreeNode<K, V>?) {
        if (node == null)
            return
        root = deleteNodeWithoutBalance(root, node.key)
    }

    override fun remove(key: K): V? {
        val node: TreeNode<K, V> = findNodeWithGivenKey(root, key) ?: return null
        val valueToReturn: V? = node.value
        deleteNode(node)
        return valueToReturn
    }

    override fun containsKey(key: K): Boolean {
        return findNodeWithGivenKey(root, key) != null
    }

    override fun containsValue(value: V): Boolean {
        val stack: Stack<TreeNode<K, V>?> = Stack<TreeNode<K, V>?>()
        var current: TreeNode<K, V>? = root
        while (current != null || stack.size > 0) {
            while (current != null) {
                stack.push(current)
                current = current.leftChild
            }
            current = stack.pop()
            if (current?.value == value) {
                return true
            }
            current = current?.rightChild
        }
        return false
    }

    override fun get(key: K): V? {
        return findNodeWithGivenKey(root, key)?.value
    }

    override fun clear() {
        root = null
    }

    override fun isEmpty(): Boolean {
        return root == null
    }

    override fun putAll(from: Map<out K, V>) {
        from.iterator().forEach { put(it.key, it.value) }
    }
}
