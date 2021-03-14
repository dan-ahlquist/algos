package kdTree

sealed class KDTree {

    data class Leaf (
        val points: List<Point>,
    ) : KDTree()

    data class Internal (
        val axis: Axis,
        val point: Point,
        var left: KDTree? = null,
        var right: KDTree? = null,
    ) : KDTree()
}

enum class Direction { Left, Right }

enum class Axis { X, Y }
