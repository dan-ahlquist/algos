package planarGraph

data class Point (
    val label: Int,
    val x: Double,
    val y: Double
) {
    override fun toString() = "Point $label: ($x, $y)"
}