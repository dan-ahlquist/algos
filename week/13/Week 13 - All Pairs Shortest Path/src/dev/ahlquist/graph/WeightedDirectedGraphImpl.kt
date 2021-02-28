package dev.ahlquist.graph

class WeightedDirectedGraphImpl: WeightedDirectedGraph {

    private val edgeLookup = mutableMapOf<Pair<Int,Int>, Edge>()

    private val vertices = mutableSetOf<Int>()
    override val n: Int
        get() = vertices.size

    private val edges = mutableSetOf<Edge>()
    override val m: Int
        get() = edges.size

    override fun hasEdge(from: Int, to: Int): Boolean {
        return edgeLookup.contains(Pair(from, to))
    }

    override fun getEdge(from: Int, to: Int): Edge? {
        return try {
            edgeLookup[Pair(from, to)]
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun addEdge(from: Node, to: Node, weight: Int) {
        val newEdge = Edge(from, to, weight)
        edges.add(Edge(from, to, weight))
        edgeLookup[Pair(from.label, to.label)] = newEdge

        vertices.add(from.label)
        vertices.add(to.label)
    }

    override fun hasNode(label: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getNode(label: Int): Node? {
        TODO("Not yet implemented")
    }

    override fun getEdgesInto(label: Int): Set<Edge> {
        TODO("Not yet implemented")
    }
}
