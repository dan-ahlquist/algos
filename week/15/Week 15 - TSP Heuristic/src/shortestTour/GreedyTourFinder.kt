package shortestTour

import kdTree.*
import kotlin.math.log2
import kotlin.math.sqrt

class GreedyTourFinder(
        val searcher: TreeSearcher = TreeSearcher(),
        val pruner: TreePruner = TreePruner(),
) {
    /** Find the total cost of a near-optimal tour */
    fun getAShortTour(points: List<Point>): Double {

        // This gives sqrt(n) leaf nodes, of size sqrt(n).
        // Therefore, brute search within a leaf node is O(n).
        val depth = log2(sqrt(points.size.toDouble())).toInt() + 1
        println("Depth of tree will be $depth")

        val builder = TreeBuilder().apply { medianSampleSize = 5 }
        val tree = builder.build(points, depth)

        val first = points.first()
        var result = 0.0
        var curr = first
        for (n in 1 until points.size) { //TODO check until vs ..
            pruner.remove(curr, tree)
            val next = searcher.getNearestNeighbor(curr, tree)
            val dist = curr.euclideanDistance(next)
            result += dist

            curr = next
        }

        val lastLeg = curr.euclideanDistance(first)
        println("Adding $lastLeg to account for the last leg.")
        result += lastLeg

        return result
    }
}