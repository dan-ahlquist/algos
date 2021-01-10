class Clusterizer {

    fun clusterize(g: WeightedUndirectedGraph, k: Int): Int {
        val edges = g.getEdges()
        val nodes = g.getNodes()
        val uf = UnionFind(nodes.size)

        val sortedEdges = edges.sortedBy { it.weight }
        val iter = sortedEdges.iterator()
        var spacing = Int.MAX_VALUE

        while (uf.partitionCount() >= k) {
            val curr = iter.next()
            println("Partitions left = ${uf.partitionCount()}, joining edge with weight=${curr.weight}")
            uf.union(curr.a.label, curr.b.label)
            spacing = curr.weight // Purposely trails behind the iterator, which will go one edge too far
        }

        return spacing
    }
}