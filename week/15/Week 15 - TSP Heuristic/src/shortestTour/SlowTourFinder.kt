package shortestTour

import kdTree.Point
import java.lang.IllegalStateException

class SlowTourFinder {

    fun getAShortTour(points: List<Point>): Double {
        var result = 0.0
        val pts = points.sortedBy { it.label }.toMutableList()

        val first = pts.first()
        var curr = first
        while (pts.size > 1) {
            println("${pts.size} points remaining")
            pts.remove(curr)
            val next = getNN(curr, pts)

            result += curr.euclideanDistance(next)

            curr = next
        }

        result += curr.euclideanDistance(first)

        return result
    }

    private fun getNN(point: Point, list: MutableList<Point>): Point {
        var nearest = list.first()
        var nearestDist = point.euclideanDistance(nearest)
        list.forEach { candidate ->
            val dist = point.euclideanDistance(candidate)
            if (dist < nearestDist) { //Strictly < for the tie-break by label
                nearest = candidate
                nearestDist = dist
            }
        }
        if (nearest.label == point.label) throw IllegalStateException("NN = self!")
        return nearest
    }
}