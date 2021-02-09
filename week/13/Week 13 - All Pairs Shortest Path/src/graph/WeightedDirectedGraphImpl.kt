package graph

class WeightedDirectedGraphImpl: WeightedDirectedGraph {
    override val n: Int
        get() = TODO("Not yet implemented")

    override val m: Int
        get() = TODO("Not yet implemented")

    override fun hasEdge(from: Int, to: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEdge(from: Int, to: Int): Edge? {
        TODO("Not yet implemented")
    }

    override fun addEdge(from: Node, to: Node, weight: Int) {
        TODO("Not yet implemented")
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