package kdTree

import kdTree.Axis.*
import kdTree.Direction.*
import java.lang.IllegalArgumentException
import kotlin.math.min

class TreeBuilder {

    var medianSampleSize = 3

    fun build(points: List<Point>, depth: Int, axis: Axis = X): KDTree {
        if (depth < 0) throw IllegalArgumentException("Depth must be positive! depth = $depth")
        if (depth == 0 || points.isEmpty()) {
            println("Leaf node size ${points.size} created.")
            return KDTree.Leaf(points)
        }

        val nextAxis = if (axis == X) Y else X
        val median = getMedian(points, nextAxis)
        val leftPoints = points.filter { it.compare(median, nextAxis) == Left }
        val rightPoints = points.filter { it.compare(median, nextAxis) == Right }
        val left = build(leftPoints, depth - 1)
        val right = build(rightPoints, depth - 1)
        return KDTree.Internal(nextAxis, median, left, right)
    }

    private fun getMedian(points: List<Point>, axis: Axis): Point {
        return when(axis) {
            X -> getXMedian(points)
            Y -> getYMedian(points)
        }
    }

    private fun getXMedian(points: List<Point>): Point {
        val sample = sample(points, medianSampleSize)
        val index = (sample.size/2) //% medianSampleSize // For safety
        return sample.sortedBy { it.x } [index]
    }

    private fun getYMedian(points: List<Point>): Point {
        val sample = sample(points, medianSampleSize)
        val index = (sample.size/2) //% medianSampleSize // For safety
        return sample.sortedBy { it.y } [index]
    }

    private fun sample(points: List<Point>, n: Int): Set<Point> {
        val result = mutableSetOf<Point>()
        val resultSize = min(n, points.size)

        while (result.size < resultSize) {
            result.add(points.random())
        }

        return result
    }
}
