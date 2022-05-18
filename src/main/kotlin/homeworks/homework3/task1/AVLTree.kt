package homeworks.homework3.task1

import java.util.AbstractMap.SimpleEntry
import java.util.Stack

class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    override val size: Int
        get() = amountOfElements
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() {
            val entriesToGet: MutableSet<MutableMap.MutableEntry<K, V>> = mutableSetOf()
            val keyValuePairs = getListOfKeyValuePairs()
            for (pair in keyValuePairs) {
                entriesToGet.add(SimpleEntry(pair.first, pair.second))
            }
            return entriesToGet
        }
    override val keys: MutableSet<K>
        get() {
            val keysToGet = mutableSetOf<K>()
            val keyValuePairs = getListOfKeyValuePairs()
            for (pair in keyValuePairs) {
                keys.add(pair.first)
            }
            return keysToGet
        }
    override val values: MutableCollection<V>
        get() {
            val valuesToGet = mutableListOf<V>()
            val keyValuePairs = getListOfKeyValuePairs()
            for (pair in keyValuePairs) {
                valuesToGet.add(pair.second)
            }
            return valuesToGet
        }

    private var root: TreeNode<K, V>? = null
    private var amountOfElements = 0

    val getTreeHeight: Int get() = root?.getHeight ?: 0

    override fun put(key: K, value: V): V? {
        val oldValue = get(key)
        root = root?.insert(key, value) ?: TreeNode(key, value)
        if (oldValue == null) {
            amountOfElements += 1
        }
        return oldValue
    }

    override fun remove(key: K): V? {
        val oldValue = get(key)
        root = root?.deleteNode(key)
        if (oldValue != null) {
            amountOfElements -= 1
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
                pairs.add(Pair(current.getKey, current.getValue))
            }
            current = current?.rightChild
        }
        return pairs
    }

    override fun get(key: K): V? = root?.findNodeWithGivenKey(key)?.value

    override fun clear() {
        amountOfElements = 0
        root = null
    }

    override fun isEmpty(): Boolean {
        return root == null
    }

    override fun putAll(from: Map<out K, V>) {
        from.iterator().forEach { put(it.key, it.value) }
    }

    override fun toString(): String {
        return root?.getTreeDiagram() ?: ""
    }
}
