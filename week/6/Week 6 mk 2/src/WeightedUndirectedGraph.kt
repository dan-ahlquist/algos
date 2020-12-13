interface WeightedUndirectedGraph {
    fun addEdge(a: Int, b: Int, weight: Int)

    fun getEdgesFrom(label: Int): Set<Edge>
    fun hasNode(s: Node): Boolean
    fun getNode(label: Int): Node
    fun printNode(n: Node)
}