package planarGraph

interface TourFinder {
    fun findShortestTourLength(graph: PlanarGraph): Float
}