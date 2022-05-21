package homeworks.homework3.task1

import java.util.*
import java.util.AbstractMap.SimpleEntry

class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    override var size: Int = 0
        private set
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = getListOfKeyValuePairs().map { SimpleEntry(it.first, it.second) }.toMutableSet()
    override val keys: MutableSet<K>
        get() = getListOfKeyValuePairs().map { it.first }.toMutableSet()
    override val values: MutableCollection<V>
        get() = getListOfKeyValuePairs().map { it.second }.toMutableList()

    private var root: TreeNode<K, V>? = null

    val height: Int get() = root?.height ?: 0

    override fun put(key: K, value: V): V? {
        val oldValue = get(key)
        root = insert(key, value, root)
        if (oldValue == null) {
            size += 1
        }
        return oldValue
    }

    override fun remove(key: K): V? {
        val oldValue = get(key)
        root = deleteNode(key, root)
        if (oldValue != null) {
            size -= 1
        }
        return oldValue
    }

    override fun containsKey(key: K): Boolean = (root?.findNodeWithGivenKey(key) != null)

    override fun containsValue(value: V) = value in values

    private fun getListOfKeyValuePairs(): MutableList<Pair<K, V>> {
        val pairs = mutableListOf<Pair<K, V>>()
        val stack: Stack<TreeNode<K, V>?> = Stack<TreeNode<K, V>?>()
        var current: TreeNode<K, V>? = root
        while (current != null || stack.size > 0) {
            while (current != null) {
                stack.push(current)
                current = current.leftChild
            }
            current = stack.pop()
            if (current != null) {
                pairs.add(Pair(current.key, current.value))
            }
            current = current?.rightChild
        }
        return pairs
    }

    override fun get(key: K): V? = root?.findNodeWithGivenKey(key)?.value

    override fun clear() {
        size = 0
        root = null
    }

    override fun isEmpty(): Boolean = (root == null)

    override fun putAll(from: Map<out K, V>) {
        from.iterator().forEach { put(it.key, it.value) }
    }

    override fun toString(): String = (root?.getTreeDiagram() ?: "")

    companion object {
        private fun <K : Comparable<K>, V> insert(key: K, value: V, node: TreeNode<K, V>?): TreeNode<K, V>? {
            when {
                node == null -> return TreeNode(key, value).balance()
                key < node.key -> node.leftChild = insert(key, value, node.leftChild)
                key > node.key -> node.rightChild = insert(key, value, node.rightChild)
                else -> node.value = value
            }
            return node.balance()
        }

        private fun <K : Comparable<K>, V> deleteNode(key: K, node: TreeNode<K, V>?): TreeNode<K, V>? = when {
            node == null -> null
            key < node.key -> {
                node.leftChild = deleteNode(key, node.leftChild)
                node.balance()
            }
            key > node.key -> {
                node.rightChild = deleteNode(key, node.rightChild)
                node.balance()
            }
            else -> {
                val pair: Pair<TreeNode<K, V>?, TreeNode<K, V>?>? = node.leftChild?.extractMax()
                val newRoot = pair?.first
                newRoot?.leftChild = pair?.second
                newRoot?.rightChild = node.rightChild
                newRoot?.balance()
            }
        }
    }
}
