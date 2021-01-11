import java.lang.IllegalStateException

class HuffmanTree (
        val symbol: Symbol? = null,
        var left: HuffmanTree? = null,
        var right: HuffmanTree? = null
) {

    fun getSymbols (): List<Symbol> {
        val result = mutableListOf<Symbol>()
        getSymbols(result)
        return result
    }

    private fun getSymbols(result: MutableList<Symbol>) {
        if (this.symbol != null) {
            if (this.left != null || this.right != null)
                throw IllegalStateException ("Node has both a symbol and children. $this")
            result.add(this.symbol)

        } else if (this.left != null && this.right != null) {
            result.addAll(this.left!!.getSymbols())
            result.addAll(this.right!!.getSymbols())

        } else {
            throw IllegalStateException("Node has neither symbol nor children. $this")
        }
    }

    fun mergeAsLeftSiblingOf (rightSibling: HuffmanTree): HuffmanTree {
        return HuffmanTree(null, this, rightSibling)
    }
}