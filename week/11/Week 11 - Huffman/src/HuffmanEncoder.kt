import HuffmanTree.Parent
import HuffmanTree.Symbol
import java.lang.IllegalStateException
import java.util.*
import kotlin.Comparator

typealias Node = Pair<String, HuffmanTree>

const val PREFIX_LEFT = "0"
const val PREFIX_RIGHT = "1"

class HuffmanEncoder {
    private val heap = TreeSet(TreeComparator)
    private var root: HuffmanTree? = null

    fun encode (symbols: List<Symbol>): HuffmanTree {
        heap.clear()
        heap.addAll(symbols)

        while (heap.size > 1) {
            val a = heap.pollFirst()!!
            val b = heap.pollFirst()!!
            val new = HuffmanTree.join(b, a)
            heap.add(new)
        }

        root = heap.pollFirst()!!
        return root!!
    }

    /**
     *  Return the Huffman Encoding as a Map from label to code.
     */
    fun getEncoding(): Map<String, String> {
        if (root == null) return emptyMap()

        val result = mutableMapOf<String, String>()
        bfs(root!!, result)
        return result
    }

    private fun bfs(tree: HuffmanTree, encoding: MutableMap<String, String>) {
        val queue = LinkedList<Node>()

        if (tree is Parent) {
            queue.addLast(Node(PREFIX_LEFT, tree.left))
            queue.addLast(Node(PREFIX_RIGHT, tree.right))
        } else {
            throw IllegalStateException("Root must be a Parent!")
        }

        while (queue.isNotEmpty()) {
            val pair = queue.removeFirst()
            val prefix = pair.first
            val next = pair.second
            when (next) {
                is Symbol -> encoding[next.label] = prefix
                is Parent -> {
                    queue.addLast(Node(prefix + PREFIX_LEFT, next.left))
                    queue.addLast(Node(prefix + PREFIX_RIGHT, next.right))
                }
            }
        }
    }
}

object TreeComparator : Comparator<HuffmanTree> {
    override fun compare(o1: HuffmanTree, o2: HuffmanTree): Int {
        return o1.frequency - o2.frequency
    }
}
