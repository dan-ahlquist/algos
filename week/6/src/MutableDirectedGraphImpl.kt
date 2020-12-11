import java.lang.IllegalStateException

class MutableDirectedGraphImpl<T, W>(
        private val nodes: MutableMap<T, Node<T>> = mutableMapOf(),
        private val edges: MutableList<Edge<T>> = mutableListOf()
): MutableDirectedGraph<T> {

    val edgesFrom = mutableMapOf<Node<T>, MutableSet<Edge<T>>>()

    override fun getNodes(): Set<Node<T>> {
        return nodes.values.toSet()
    }

    override val nodeCount: Int
        get() = nodes.size

    override fun hasNode(a: T): Boolean {
        return nodes.containsKey(a)
    }

    override fun getNode(a: T): Node<T> {
        return nodes[a]!!
    }

    override fun hasEdge(a: T, b: T): Boolean {
        return edges.any { it.from.data == a && it.to.data == b }
    }

    override fun getEdgesFrom(a: Node<T>): Set<Edge<T>> {
        return edgesFrom[a] ?: emptySet()
    }

    override fun addNode(a: T) {
        if (hasNode(a)) throw IllegalStateException("This graph already has a node $a")
        nodes[a] = Node(a)
    }

    override fun addEdge(a: T, b: T, weight: Int) {
        if (!nodes.contains(a)) { nodes[a] = Node(a) }
        if (!nodes.contains(b)) { nodes[b] = Node(b) }

        val aNode = nodes[a]
        val bNode = nodes[b]
        val newEdge = Edge(aNode!!, bNode!!, weight, Int.MAX_VALUE)
        edges.add(newEdge)

        val aEdges = edgesFrom.getOrPut(aNode, { mutableSetOf() })
        aEdges.add(newEdge)
    }

    override fun resetExplored() {
        nodes.values.forEach { n ->
            n.isExplored = false
        }
    }

    private fun resetNeighbors() {
        nodes.values.forEach { n ->
            edgesFrom.clear()
        }
    }

    override fun reverse() {
        resetNeighbors()

        val oldEdges = edges.toMutableList() //shallow copy
        edges.clear()
        oldEdges.forEach {
            addEdge(it.to.data, it.from.data, it.weight)
        }
    }

    override fun print() {
        println("${nodes.size} nodes, ${edges.size} edges.")
        for (node in nodes.values) {
            val sb = StringBuffer("${node.data} -> ")
            for (e in edgesFrom[node] ?: emptySet()) {
                sb.append("${e.to.data} ")
            }
            println(sb.toString())
        }
    }
}