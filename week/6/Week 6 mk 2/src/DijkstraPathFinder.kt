import java.lang.IllegalStateException
import java.util.*

class DijkstraPathFinder: PathFinder {

    override fun findPaths(g: WeightedUndirectedGraph, s: Node) {
        if (!g.hasNode(s)) throw IllegalStateException("Graph does not have a node ${s.label}")

        // Nodes for which we already know the shortest path
        val X: MutableSet<Node> = mutableSetOf()

        // Edges crossing from X to `X, aka the 'frontier'
        val F: TreeSet<Edge> = TreeSet { a, b -> a.score.compareTo(b.score) }

        // What we know already
        s.shortestPath = 0
        X.add(s)
        s.edgesFrom.forEach { edge ->
            edge.score = s.shortestPath + edge.weight
//            F.removeIf { sameEdge(edge, it) }
            F.add(edge)
        }

        while (F.isNotEmpty()) {
            val vw = F.pop()
            val (v, w) = vw.getFrontierOrderedNodes(X) // Such that v in X, w not in X
            X.add(w)
            w.shortestPath = v.shortestPath + vw.weight
            w.edgesFrom.forEach { edge ->
                if (edge.crossesFrontier(X, w)) {
                    edge.score = w.shortestPath + edge.weight
//                    F.removeIf { sameEdge(edge, it) }
                    F.add(edge)
                } else {
                    F.remove(edge)
                }
            }
        }
    }
}

private fun sameEdge(a: Edge, b: Edge): Boolean {
    return (a.a == b.a && a.b == b.b)
            || (a.b == b.a && a.a == b.b)
}

private fun Edge.crossesFrontier(X: MutableSet<Node>, from: Node): Boolean {
    val toNode = when {
        b == from && a != from -> a
        a == from && b != from -> b
        else -> throw IllegalStateException("WTF")
    }

    val fromNodeInX = X.contains(from)
    val toNodeInX = X.contains(toNode)

    return fromNodeInX && !toNodeInX
}

private fun Edge.getFrontierOrderedNodes(X: MutableSet<Node>): Pair<Node, Node> {
    val aInX = X.contains(a)
    val bInX = X.contains(b)
    return when {
        aInX && !bInX -> Pair(a, b)
        bInX && !aInX -> Pair(b, a)
        else -> throw IllegalStateException(
            "This edge does not cross the frontier!\n" +
            "Node a (label=${a.label} in X? $aInX" +
            "Node b (label=${b.label} in X? $bInX"
        )
    }
}

fun TreeSet<Edge>.pop(): Edge {
    val first = this.first()
    this.remove(first)
    return first
}
