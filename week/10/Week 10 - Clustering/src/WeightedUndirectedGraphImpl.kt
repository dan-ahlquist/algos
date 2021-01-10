import java.lang.IllegalStateException

class WeightedUndirectedGraphImpl: WeightedUndirectedGraph {

    private val nodes = mutableMapOf<Int, Node>()
    private val edges = mutableListOf<Edge>()

    override fun addEdge(a: Int, b: Int, weight: Int) {
        val aNode = nodes.getOrPut(a, { Node(a) })
        val bNode = nodes.getOrPut(b, { Node(b) })

        val newEdge = Edge(aNode, bNode, weight)

        aNode.edgesFrom.add(newEdge)

        edges.add(newEdge)
    }

    override fun getEdges(): List<Edge> {
        return edges
    }

    override fun getEdgesFrom(label: Int): Set<Edge> {
        if (nodes.contains(label))
            return nodes[label]!!.edgesFrom.toSet()
        else
            throw IllegalStateException("Graph does not contain a node <$label>.")
    }

    override fun hasNode(s: Node): Boolean {
        return nodes.contains(s.label)
    }

    override fun getNode(label: Int): Node {
        return nodes[label]!!
    }

    override fun getNodes(): List<Node> {
        return nodes.values.toList()
    }

    override fun printNode(n: Node) {
        val sb = StringBuilder("${n.label}\t")
        for (e in n.edgesFrom) {
            sb.append("${e.otherNode(n).label},${e.weight}\t")
        }
        println(sb.toString())
    }
}

private fun Edge.otherNode(from: Node): Node {
    return when {
        b == from && a != from -> a
        a == from && b != from -> b
        else -> throw IllegalStateException("WTF")
    }
}
