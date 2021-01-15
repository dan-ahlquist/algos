/**
 *  A recursively defined Huffman Tree.
 *  Each node is either a Parent or a Symbol.
 */
sealed class HuffmanTree {

    /**
     *  Total frequency of all nodes in this tree.
     */
    abstract val frequency: Int

    /**
     *  A leaf node with data.
     *  Frequency is from the original dataset.
     */
    data class Symbol (
            val label: String,
            override val frequency: Int
    ) : HuffmanTree()

    /**
     *  A structural node with no data per se.
     *  Frequency is the total of its descendants.
     */
    data class Parent (
            val left: HuffmanTree,
            val right: HuffmanTree,
            override val frequency: Int
    ) : HuffmanTree()

    companion object {
        /**
         *  Join two trees under a new Parent node.
         *
         *  @return A new Parent node with `left` and `right` as children.
         */
        fun join (left: HuffmanTree, right: HuffmanTree): HuffmanTree {
            val combinedFrequency = left.frequency + right.frequency
            return Parent(left, right, combinedFrequency)
        }
    }
}