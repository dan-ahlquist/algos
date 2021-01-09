class NeighborFinder {
    fun findNeighbors(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        val n1 = getNeighbors1(node)
        val n2 = getNeighbors2(node)
        result.addAll(n1)
        result.addAll(n2)

        return result
    }

    private fun getNeighbors1(node: Node): Set<Node> {
        val result = mutableSetOf<Node>()

        val combinations = 0 until node.bits.size
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

        val combinations = combinator.choose2(0 until node.bits.size)
        combinations.forEach { pair ->
            val neighbor = node.copy()
            neighbor.flip(pair.first)
            neighbor.flip(pair.second)
            result.add(neighbor)
        }

        return result
    }
}