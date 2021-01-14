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
            override val frequency: Int,
            var encoding: String = ""
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

    /**
     *  Join this tree and another, under a new parent Node, with this
     *  being the left child, and the other being the right child.
     */
    fun join (sibling: HuffmanTree): HuffmanTree {
        val combinedFrequency = this.frequency + sibling.frequency
        return Parent(this, sibling, combinedFrequency)
    }

}