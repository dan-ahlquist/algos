package kdTree

import kdTree.KDTree.*
import java.lang.IllegalStateException

class TreeSearcher {

    fun getNearestNeighbor(point: Point, tree: KDTree): Point {
        val reversePath = getPathTo(point, tree).reversed()

        var alreadyDidLeaf = false
        var nearest: Point? = null
        var nearestDistance = Double.MAX_VALUE
        reversePath.forEach { node ->
            when (node) {
                is Leaf -> { //The first entry must be
                    if (alreadyDidLeaf) throw IllegalStateException("Why are we hitting two leafs in the path??")
                    alreadyDidLeaf = true
                    val nn = getNearestNeighbor(point, node.points)
                    nearest = nn
                    nearestDistance = point.euclideanDistance(nn)
                }
                is Internal -> {
                    val distToBorder = point.distToBorder(node.point, node.axis)
                    if (distToBorder < nearestDistance) {
                        //TODO optimize to just left or right half
                        val newNeighborhood = getNeighborhood(node)
                        val nn = getNearestNeighbor(point, newNeighborhood)
                        nearest = nn
                        nearestDistance = point.euclideanDistance(nn)
                    }
                    // Otherwise just skip to the next higher node
                }
            }
        }

        return nearest!!
    }

    fun getLeafContaining(point: Point, tree: KDTree): Leaf {
        return getPathTo(point, tree).last() as Leaf
    }

    private fun getPathTo(point: Point, tree: KDTree): List<KDTree> {
        val result = mutableListOf<KDTree>()

        var curr = tree
        while (curr is Internal) {
            result.add(curr)
            val direction = point.compare(curr.point, curr.axis)
            curr = when (direction) {
                Direction.Left -> curr.left
                Direction.Right -> curr.right
            }
        }
        result.add(curr as Leaf) // The leaf node

        if (result.last() !is Leaf) throw IllegalStateException("Path must end with a Leaf!")
        return result
    }

    private fun getNeighborhood(tree: KDTree): List<Point> {
        return when (tree) {
            is Internal -> getNeighborhood(tree)
            is Leaf -> getNeighborhood(tree)
        }
    }

    private fun getNeighborhood(node: Internal): List<Point> {
        val leftNeighborhood = getNeighborhood(node.left)
        val rightNeighborhood = getNeighborhood(node.right)
        return leftNeighborhood + rightNeighborhood
    }

    private fun getNeighborhood(node: Leaf): List<Point> {
        return node.points
    }

    /** Very force, much broot */
    private fun getNearestNeighbor(point: Point, list: List<Point>): Point {
        var nearest = list.first()
        var nearestDist = point.euclideanDistance(nearest)
        list.forEach { candidate ->
            val distance = point.euclideanDistance(candidate)
            if (distance < nearestDist) {
                nearest = candidate
                nearestDist = distance
            }
        }
        return nearest
    }
}
