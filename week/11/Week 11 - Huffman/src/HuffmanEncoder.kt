import HuffmanTree.Parent
import HuffmanTree.Symbol
import java.util.*
import kotlin.Comparator

class HuffmanEncoder {

    fun encode (symbols: List<Symbol>): HuffmanTree {
        val sortedSymbols = TreeSet(SymbolComparator)
        sortedSymbols.addAll(symbols)

        var root: Parent? = null



        return root!!
    }
}

//TODO relocate
object SymbolComparator : Comparator<Symbol> {
    override fun compare(o1: Symbol, o2: Symbol): Int {
        return o1.frequency - o2.frequency
    }
}
