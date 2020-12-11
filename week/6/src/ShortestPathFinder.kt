interface ShortestPathFinder {
    /**
     *  Preconditions: `g` is a directed graph with non-negative weighted edges. `s` is a node in `g`
     *
     *  Postconditions: All nodes of `g` which are connected to `s` have their shortest path length set as the shortest
     *  path length from `s` to that node.
     */
    fun <T> findShortestPaths (g: MutableDirectedGraph<T>, s: Node<T>)
}