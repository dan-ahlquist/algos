import java.lang.IllegalStateException

class MutableDirectedGraphImpl<T>(
        private val nodes: MutableMap<T, Node<T>> = mutableMapOf(),
        private val edges: MutableList<Edge<T>> = mutableListOf()
): MutableDirectedGraph<T> {

    override fun hasNode(a: T): Boolean {
        return nodes.containsKey(a)
    }

    override fun hasEdge(a: T, b: T): Boolean {
        return edges.any { it.first.data == a && it.second.data == b }
    }

    override fun getNeighborsFrom(a: T): List<T> {
        return edges.filter { it.first.data == a }.map { it.second.data }
    }

    override fun reverse(): MutableDirectedGraph<T> {
        val reversedEdges = edges.map { it.reverse() }.toMutableList()
        return MutableDirectedGraphImpl(this.nodes, reversedEdges)
    }

    override fun addNode(a: T) {
        if (hasNode(a)) throw IllegalStateException("This graph already has a node $a")
        nodes[a] = Node(a)
    }

    override fun addEdge(a: T, b: T) {
        val aNode = nodes.getOrDefault(a, Node(a))
        val bNode = nodes.getOrDefault(b, Node(b))

        edges.add(Edge(aNode, bNode))
    }
}

fun <T> Edge<T>.reverse(): Edge<T> {
    return Edge(this.second, this.first)
}