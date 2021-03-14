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

    fun compare(borderPoint: Point, axis: Axis): Direction {
        val diff = distToBorder(borderPoint, axis)
        return if (diff <= 0) Direction.Left else Direction.Right
    }

    fun distToBorder(borderPoint: Point, axis: Axis): Double {
        val componentThis = this.component(axis)
        val componentBorder = borderPoint.component(axis)

        return componentThis - componentBorder
    }

    fun component(axis: Axis): Double {
        return when (axis) {
            Axis.X -> this.x
            Axis.Y -> this.y
        }
    }
}