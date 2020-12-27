import java.lang.IllegalStateException

class WeightedUndirectedGraphImpl: WeightedUndirectedGraph {

    override val nodes = mutableMapOf<Int, Node>()
    override val edges = mutableSetOf<Edge>()

    override fun addEdge(a: Int, b: Int, weight: Int) {
        val aNode = nodes.getOrPut(a, { Node(a) })
        val bNode = nodes.getOrPut(b, { Node(b) })

        val newEdge = Edge(aNode, bNode, weight)

        edges.add(newEdge)
    }

    override fun hasNode(s: Node): Boolean {
        return nodes.contains(s.label)
    }

    override fun getNode(label: Int): Node {
        return nodes[label]!!
    }
}

private fun Edge.otherNode(from: Node): Node {
    return when {
        b == from && a != from -> a
        a == from && b != from -> b
        else -> throw IllegalStateException("WTF")
    }
}
