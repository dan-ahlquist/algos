package planarGraph

interface PlanarGraph {
    fun distance(a: Int, b: Int): Double
    val size: Int
}