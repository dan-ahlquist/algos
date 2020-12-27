import java.lang.IllegalStateException

class SpanningTree {

    val nodes = mutableMapOf<Int, Node>()
    val edges = mutableSetOf<Edge>()

    val size: Int
        get() = nodes.size

    fun addNode(n: Node) {
        nodes[n.label] = n
    }

    fun addEdge(a: Int, b: Int, weight: Int) {
        val aNode = nodes.getOrPut(a, { Node(a) })
        val bNode = nodes.getOrPut(b, { Node(b) })

        val newEdge = Edge(aNode, bNode, weight)

        edges.add(newEdge)
    }

    fun print() {
        println("Spanning Tree with ${nodes.size} nodes, ${edges.size} edges, " +
                "and total edge weight of ${edges.sumBy { it.weight }}")

        edges.forEach {
            println("${it.a}->${it.b} (${it.weight})")
        }
    }

    companion object {
        fun findMst(g: WeightedUndirectedGraph): SpanningTree {
            val result = SpanningTree()

            val edges = g.edges
            val gSize = g.nodes.size

            val firstNode = g.nodes.values.random()
            result.addNode(firstNode)

            while (result.size < gSize) {
                val nextEdge = edges
                    .filter { crossesFrontier(it, result) }
                    .minBy { it.weight }
                    ?: throw IllegalStateException("Ran out of frontier-crossing edges before spanning G. Is G connected?")

                result.addEdge(nextEdge.a.label, nextEdge.b.label, nextEdge.weight)
            }

            return result
        }

        private fun crossesFrontier(e: Edge, st: SpanningTree): Boolean {
            return (st.nodes.contains(e.a.label) && !st.nodes.contains(e.b.label))
                || (st.nodes.contains(e.b.label) && !st.nodes.contains(e.a.label))
        }
    }
}