import java.lang.IllegalArgumentException

class DijkstraPathFinder: ShortestPathFinder {

    override fun <T, W> findShortestPaths(g: MutableDirectedGraph<T, W>, s: Node<T>) {
        if (!g.hasNode(s.data)) throw IllegalArgumentException("Specified starting node (${s.data}) does not exist in this graph.")

        g.getNodes().forEach {
            it.isExplored = false
            it.shortestPathLength = Int.MAX_VALUE
        }

        val X = mutableSetOf(s)
        s.shortestPathLength = 0


    }
}