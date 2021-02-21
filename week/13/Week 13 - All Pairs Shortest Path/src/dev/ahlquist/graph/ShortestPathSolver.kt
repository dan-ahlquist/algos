package dev.ahlquist.graph

interface ShortestPathSolver {
    /**
     *  Find the shortest shortest path
     *  @return cost of the shortest shortest path
     */
    fun solve(g: WeightedDirectedGraph): Path
}