package dev.ahlquist.graph

interface WeightedDirectedGraph {
    val n: Int
    val m: Int

    fun hasEdge(from: Int, to: Int): Boolean
    fun getEdge(from: Int, to: Int): Edge?
    fun addEdge(from: Node, to: Node, weight: Int)

    fun hasNode(label: Int): Boolean
    fun getNode(label: Int): Node?
    fun getEdgesInto(label: Int): Set<Edge>
}
