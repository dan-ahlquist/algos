package planarGraph

import java.security.InvalidKeyException
import kotlin.math.abs
import kotlin.math.sqrt

class LazyPlanarGraph(points: Set<Point>) : PlanarGraph {

    private val pointLookup = points.map { it.label to it }.toMap()
    private val distLookup = mutableMapOf<String, Double>()

    init {
        if (points.size != pointLookup.size)
            throw duplicateLabel()
    }

    override fun distance(a: Int, b: Int): Double {
        val pointA = pointLookup[a] ?: throw noSuchPoint(a)
        val pointB = pointLookup[b] ?: throw noSuchPoint(b)

        val key = key(pointA, pointB)
        return distLookup.getOrPut(key) {
            euclideanDistance(pointA, pointB)
        }
    }

    override val size: Int = points.size

    override val labels: IntArray = points.map { it.label }.toIntArray()

    private fun key(a: Point, b: Point): String = "${a.label},${b.label}"

    private fun euclideanDistance(a: Point, b: Point): Double {
        val dx = abs(a.x - b.x)
        val dy = abs(a.y - b.y)
        return sqrt(dx*dx + dy*dy)
    }

    companion object {
        private fun noSuchPoint(a: Int) = InvalidKeyException("No point $a in graph!")
        private fun duplicateLabel() = IllegalStateException("Multiple points with same label! Check your data!")
    }
}