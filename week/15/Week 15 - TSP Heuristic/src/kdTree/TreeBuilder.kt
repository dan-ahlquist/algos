package kdTree

import kdTree.Axis.*
import kdTree.Direction.*
import java.lang.IllegalArgumentException
import kotlin.math.min

class TreeBuilder {

    var medianSampleSize = 3

    fun build(points: List<Point>, depth: Int, axis: Axis = X): KDTree {
        if (depth < 0) throw IllegalArgumentException("Depth must be positive! depth = $depth")
        if (depth == 0) {
            println("Leaf node size ${points.size} created.")
            return KDTree.Leaf(points)
        }

        val nextAxis = if (axis == X) Y else X
        val nextPredicate = getNextPredicate(points, nextAxis)
        val leftPoints = points.filter { nextPredicate.invoke(it) == Left }
        val rightPoints = points.filter { nextPredicate.invoke(it) == Right }
        val left = build(leftPoints, depth - 1)
        val right = build(rightPoints, depth - 1)
        return KDTree.Internal(nextAxis, nextPredicate, left, right)
    }

    private fun getNextPredicate(points: List<Point>, axis: Axis): (Point) -> Direction {
        return when(axis) {
            X -> getXPredicate(points)
            Y -> getYPredicate(points)
        }
    }

    private fun getXPredicate(points: List<Point>): (Point) -> Direction {
        val sample = sample(points, medianSampleSize)
        val index = (sample.size/2) % medianSampleSize // For safety
        val median = sample.sortedBy { it.x } [index]

        return { point ->
            if (point.x <= median.x)
                Left
            else
                Right
        }
    }

    private fun getYPredicate(points: List<Point>): (Point) -> Direction {
        val sample = sample(points, medianSampleSize)
        val index = (sample.size/2) //% medianSampleSize // For safety
        val median = sample.sortedBy { it.y } [index]

        return { point ->
            if (point.y <= median.y)
                Left
            else
                Right
        }
    }

    private fun sample(points: List<Point>, n: Int): Set<Point> {
        val result = mutableSetOf<Point>()
        val resultSize = min(n, result.size)

        while (result.size < resultSize) {
            result.add(points.random())
        }

        return result
    }
}
