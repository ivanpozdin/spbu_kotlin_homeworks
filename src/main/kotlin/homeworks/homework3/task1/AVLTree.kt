package homeworks.homework3.task1

import java.lang.Integer.max

class AVLTree<K : Comparable<K>, V> {
    private var root: TreeNode<K, V>? = null
    private fun getHeight(node: TreeNode<K, V>?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: TreeNode<K, V>?) {
        node?.height = max(getHeight(node?.leftChild), getHeight(node?.rightChild))
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
        var newRoot: TreeNode<K, V>? = node
        if (getBalanceFactor(node) == 2) {
            if (getBalanceFactor(root?.rightChild) == 1) {
                node?.rightChild = rotateRight(node?.rightChild)
            }
            newRoot = rotateLeft(node)
        } else if (getBalanceFactor(node) == -2) {
            if (getBalanceFactor(node?.leftChild) == 1)
                node?.leftChild = rotateLeft(node?.leftChild)
            newRoot = rotateRight(root)
        }
        return newRoot
    }

    private fun findNodeWithGivenKey(node: TreeNode<K, V>?, key: K): TreeNode<K, V>? {
        if (node == null) {
            return null
        }
        if (key > node.key) {
            return findNodeWithGivenKey(node.rightChild, key)
        } else if (key < node.key) {
            return findNodeWithGivenKey(node.leftChild, key)
        }
        return node
    }

    private fun insertWithoutBalance(node: TreeNode<K, V>?, key: K, value: V): TreeNode<K, V> {
        if (node == null) {
            return TreeNode(value, key)
        }
        if (node.key > key) {
            node.leftChild = insertWithoutBalance(node.leftChild, key, value)
        } else if (node.key < key) {
            node.rightChild = insertWithoutBalance(node.rightChild, key, value)
        } else if (node.key == key) {
            node.value = value
        }
        return node
    }

    fun put(key: K, value: V) {
        insertWithoutBalance(root, key, value)
        root = balance(root)
    }

    fun hasKey(key: K): Boolean {
        var node: TreeNode<K, V>? = root
        while (node != null) {
            node = if (key > node.key) {
                node.rightChild
            } else if (key < node.key) {
                node.leftChild
            } else {
                return true
            }
        }
        return false
    }

    fun getValueFromKey(key: K): V? {
        val node: TreeNode<K, V>? = findNodeWithGivenKey(root, key)
        return node?.value
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
            return rootNode
        } else if (key > rootNode.key) {
            rootNode.rightChild = deleteNodeWithoutBalance(rootNode.rightChild, key)
            return rootNode
        }
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
        return newRoot
    }

    private fun deleteNode(node: TreeNode<K, V>?) {
        if (node == null)
            return
        root = deleteNodeWithoutBalance(root, node.key)
    }

    fun remove(key: K): V? {
        val node: TreeNode<K, V> = findNodeWithGivenKey(root, key) ?: return null
        val valueToReturn: V? = node.value
        deleteNode(node)
        return valueToReturn
    }
}
