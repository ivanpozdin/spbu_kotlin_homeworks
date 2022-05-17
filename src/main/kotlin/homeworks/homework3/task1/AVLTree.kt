package homeworks.homework3.task1

import java.util.Stack

class AVLTree<K : Comparable<K>, V>(
    override val size: Int,
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>,
    override val keys: MutableSet<K>,
    override val values: MutableCollection<V>
) : MutableMap<K, V> {
    init {
        val iterator = entries.iterator()
        iterator.forEach { put(it.key, it.value) }
    }

    private var root: TreeNode<K, V>? = null

    fun getTreeHeight(): Int {
        return root?.getHeight() ?: 0
    }

    override fun put(key: K, value: V): V? {
        val oldValue = get(key)
        root = root?.insert(key, value) ?: TreeNode(key, value)
        return oldValue
    }

    override fun remove(key: K): V? {
        val oldValue = get(key)
        root = root?.deleteNode(key)
        return oldValue
    }

    override fun containsKey(key: K): Boolean = (root?.findNodeWithGivenKey(key) != null)

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

    override fun get(key: K): V? = root?.findNodeWithGivenKey(key)?.value

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
