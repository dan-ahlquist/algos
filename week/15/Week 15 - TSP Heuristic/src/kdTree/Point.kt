package kdTree

import kotlin.math.abs
import kotlin.math.sqrt

data class Point (
    val label: Int,
    val x: Double,
    val y: Double,
) {
    fun euclideanDistance(b: Point): Double {
        val dx = abs(this.x - b.x)
        val dy = abs(this.y - b.y)
        return sqrt(dx * dx + dy * dy)
    }

    fun compare(median: Point, axis: Axis): Direction {
        val componentThis = this.component(axis)
        val componentMedian = median.component(axis)

        val diff = componentThis - componentMedian
        return if (diff <= 0) Direction.Left else Direction.Right
    }

    fun component(axis: Axis): Double {
        return when (axis) {
            Axis.X -> this.x
            Axis.Y -> this.y
        }
    }
}