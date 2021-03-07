package planarGraph

interface PlanarGraph {
    fun distance(a: Int, b: Int): Float
    val size: Int
    val labels: IntArray
}