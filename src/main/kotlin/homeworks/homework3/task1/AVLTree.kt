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
        if (getBalanceFactor(node) == 2) {
            if (getBalanceFactor(root?.rightChild) == 1) {
                node?.rightChild = rotateRight(node?.rightChild)
            }
            return rotateLeft(node)
        }
        if (getBalanceFactor(node) == -2) {
            if (getBalanceFactor(node?.leftChild) == 1)
                node?.leftChild = rotateLeft(node?.leftChild);
            return rotateRight(root);
        }
        return node
    }

    private fun findNodeWithGivenKey(node: TreeNode<K, V>?, key: K): TreeNode<K, V>? {
        if (node == null) {
            return null
        }
        if (key > node.key) {
            return findNodeWithGivenKey(node.rightChild, key)
        } else if (key < node.key){
            return findNodeWithGivenKey(node.leftChild, key)
        }
        return node
    }

    private fun insertWithoutBalance(root: TreeNode<K, V>?, key: K, value: V): TreeNode<K, V> {
        if (root == null) {
            return TreeNode(value, key)
        }
        if (root.key > key) {
            root.leftChild = insertWithoutBalance(root.leftChild, key, value);
        } else if (root.key < key) {
            root.rightChild = insertWithoutBalance(root.rightChild, key, value)
        } else if (root.key == key) {
            root.value = value
        }
        return root
    }


    fun put(key: K, value: V) {
        if (root == null) {
            root = insertWithoutBalance(root, key, value)
        } else {
            insertWithoutBalance(root, key, value)
        }
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

    private fun getNodeWithMaxKey(): TreeNode<K, V>? {
        var node: TreeNode<K, V>? = root
        while (node?.rightChild != null) {
            node = node.rightChild
        }
        return node
    }

    private fun getNodeWithMinKey(): TreeNode<K, V>? {
        var node: TreeNode<K, V>? = root
        while (node?.leftChild != null) {
            node = node.leftChild
        }
        return node
    }

    private fun getLowerBound(key: K): K? {
        var node: TreeNode<K, V>? = root ?: return null
        var lowerBound: K = root?.key ?: return null
        while (node != null) {
            if (node.key >= key) {
                lowerBound = node.key
                node = node.leftChild
            } else {
                if (node.rightChild == null) {
                    if (lowerBound >= key) {
                        return lowerBound
                    }
                    return null
                }
                node = node.rightChild
            }
        }
        return lowerBound
    }

    private fun extractMax(node: TreeNode<K, V>?): Pair<K?, V?>{
        }
    }

    private fun deleteNodeWithoutBalance(rootNode: TreeNode<K, V>?, key: K):TreeNode<K, V>?{
        if (rootNode == null)
            return null
        if (key < rootNode.key){
            rootNode.leftChild = deleteNodeWithoutBalance(rootNode.leftChild, key)
            return rootNode
        } else if (key > rootNode.key){
            rootNode.rightChild = deleteNodeWithoutBalance(rootNode.rightChild, key)
            return rootNode
        }
        var newRoot: TreeNode<K, V>? = null
        if (rootNode.leftChild == null){
            newRoot = rootNode.rightChild
        }
        else if (rootNode.rightChild == null){
            newRoot = rootNode.leftChild
        }
        else{

        }
    }

    fun remove(key: K): V?{
        var node: TreeNode<K, V>? = findNodeWithGivenKey(root, key) ?: return null
        TODO()
    }
}
