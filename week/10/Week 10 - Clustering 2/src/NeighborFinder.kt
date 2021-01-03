class NeighborFinder {
    fun findNeighbors(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        result.addAll(getNeighbors1(node))
        result.addAll(getNeighbors2(node))

        return result
    }

    private fun getNeighbors1(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        val combinations = 0 .. node.length()
        combinations.forEach { index ->
            val neighbor = node.copy()
            neighbor.flip(index)
            result.add(neighbor)
        }

        return result
    }

    private fun getNeighbors2(node: Node): Set<Node> {
        val combinator = Combinator()
        val result = mutableSetOf<Node>()

        val combinations = combinator.choose2(0 .. node.length())
        combinations.forEach { pair ->
            val neighbor = node.copy()
            neighbor.flip(pair.first)
            neighbor.flip(pair.second)
            result.add(neighbor)
        }

        return result
    }
}