package kdTree

import kotlin.math.abs
import kotlin.math.sqrt

data class Point (
    val label: Int,
    val x: Double,
    val y: Double,
)

fun euclideanDistance(a: Point, b: Point): Double {
    val dx = abs(a.x - b.x)
    val dy = abs(a.y - b.y)
    return sqrt(dx*dx + dy*dy)
}
