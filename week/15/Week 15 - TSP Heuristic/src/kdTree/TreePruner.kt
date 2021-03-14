package kdTree

class TreePruner(
        val searcher: TreeSearcher = TreeSearcher()
) {
    /** Remove a point by its label */
    fun remove(point: Point, tree: KDTree): Boolean {
        val searcher = TreeSearcher()
        val leaf = searcher.getLeafContaining(point, tree)
        return leaf.points.removeIf { it.label == point.label }
    }
}