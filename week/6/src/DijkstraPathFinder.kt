import java.lang.IllegalArgumentException
import java.util.*

class DijkstraPathFinder: ShortestPathFinder {

    override fun <T> findShortestPaths(g: MutableDirectedGraph<T>, s: Node<T>) {
        if (!g.hasNode(s.data)) throw IllegalArgumentException("Specified starting node (${s.data}) does not exist in this graph.")

        g.getNodes().forEach {
            it.isExplored = false
            it.shortestPathLength = Int.MAX_VALUE
        }

        val X = mutableSetOf(s)
        s.shortestPathLength = 0

        val F = TreeSet<Edge<T>> { a, b -> a.score.compareTo(b.score) }

        for (e in g.getEdgesFrom(s)) {
            e.score = s.shortestPathLength + e.weight
            F.add(e)
        }

        while (F.isNotEmpty()) {
            val vw = F.first()
            F.remove(vw)

            val v = vw.from
            val w = vw.to
            X.add(w)

            w.shortestPathLength = v.shortestPathLength + vw.weight

            F.removeIf { it.to == w }

            // Add to the frontier all edges from w, which end outside X
            val newFrontierEdges = g.getEdgesFrom(w).filter { !X.contains(it.to) }
            newFrontierEdges.forEach {
                it.score = w.shortestPathLength + it.weight
                F.add(it)
            }
        }
    }
}