package planarGraph

import kotlin.math.pow

class BellmanHeldKarp: TourFinder {

    override fun findShortestTourLength(graph: PlanarGraph): Long {

        val n = graph.size
        val m = (2.0.pow((n-1).toDouble()) - 1 ).toInt() // 2^(n-1) -1

        val arr = Array(m) { Array(n-1) { Double.POSITIVE_INFINITY } }

        for (j in 2..n) {
            arr[3][j] // 3 is a guess, TODO
        }

        for (s in 3..n) {

        }

        return TODO()
    }
}