class NeighborFinder {
    fun findNeighbors(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        result.union(getNeighbors1(node))
        result.union(getNeighbors2(node))

        return result
    }

    private fun getNeighbors1(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        val combinations = 0 until node.length()
        combinations.forEach { index ->
            val neighbor = node.copy()
            neighbor.flip(index)
        }

        return result
    }

    private fun getNeighbors2(node: Node): Set<Node> {
        val combinator = Combinator()
        val result = mutableSetOf<Node>()

        val combinations = combinator.choose2(0 until node.length())
        combinations.forEach { pair ->
            val neighbor = node.copy()
            neighbor.flip(pair.first)
            neighbor.flip(pair.second)
        }

        return result
    }
}