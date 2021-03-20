import java.lang.IllegalStateException

class MutableDirectedGraphImpl<T>(
        private val nodes: MutableMap<T, Node<T>> = mutableMapOf(),
        private val edges: MutableList<Edge<T>> = mutableListOf()
): MutableDirectedGraph<T> {

    override fun getNodes(): Set<Node<T>> {
        return nodes.values.toSet()
    }

    override val nodeCount: Int
        get() = nodes.size

    override fun hasNode(a: T): Boolean {
        return nodes.containsKey(a)
    }

    override fun hasEdge(a: T, b: T): Boolean {
        return edges.any { it.first.data == a && it.second.data == b }
    }

    override fun getNeighborsFrom(a: Node<T>): Set<Node<T>> {
        return a.neighborsFrom
    }

    override fun addNode(a: T) {
        if (hasNode(a)) throw IllegalStateException("This graph already has a node $a")
        nodes[a] = Node(a)
    }

    override fun addEdge(a: T, b: T) {
        if (!nodes.contains(a)) { nodes[a] = Node(a) }
        if (!nodes.contains(b)) { nodes[b] = Node(b) }

        val aNode = nodes[a]
        val bNode = nodes[b]
        edges.add(Edge(aNode!!, bNode!!))

        aNode.neighborsFrom.add(bNode)
    }

    override fun resetExplored() {
        nodes.values.forEach { n ->
            n.isExplored = false
        }
    }

    private fun resetNeighbors() {
        nodes.values.forEach { n ->
            n.neighborsFrom.clear()
        }
    }

    override fun reverse() {
        resetNeighbors()

        val oldEdges = edges.toMutableList() //shallow copy
        edges.clear()
        oldEdges.forEach {
            addEdge(it.second.data, it.first.data)
        }
    }

    override fun print() {
        println("${nodes.size} nodes, ${edges.size} edges.")
        for (node in nodes.values) {
            val sb = StringBuffer("${node.data} -> ")
            for (neighbor in getNeighborsFrom(node)) {
                sb.append("${neighbor.data} ")
            }
            println(sb.toString())
        }
    }
}