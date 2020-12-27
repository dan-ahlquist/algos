interface WeightedUndirectedGraph {

    val nodes: Map<Int, Node>
    val edges: Set<Edge>

    fun addEdge(a: Int, b: Int, weight: Int)

    fun hasNode(s: Node): Boolean
    fun getNode(label: Int): Node
}