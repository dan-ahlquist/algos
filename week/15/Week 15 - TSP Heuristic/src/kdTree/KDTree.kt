package kdTree

sealed class KDTree {

    data class Leaf (
        val points: MutableList<Point>,
    ) : KDTree()

    data class Internal (
        val axis: Axis,
        val point: Point,
        val left: KDTree,
        val right: KDTree,
    ) : KDTree()
}

enum class Direction { Left, Right }

enum class Axis { X, Y }
