class Clusterizer {
    fun clusterize(nodes: Map<Int, Node>): Int {
        val uf = UnionFind(nodes.size)

        val labels = nodes.entries.associate { (k, v) -> v.bitString to k }

        val neighborFinder = NeighborFinder()

        nodes.values.forEachIndexed { i, node ->
            val neighbors = neighborFinder.findNeighbors(node)
            neighbors.forEach { neighbor ->
                if (labels.contains(neighbor.bitString)) {
                    val neighborLabel = labels[neighbor.bitString] ?: error("No label entry for node: $node")
                    uf.union(node.label, nodes[neighborLabel]!!.label)
                }
            }
        }

        return uf.partitionCount()
    }
}