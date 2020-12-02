class SCCCollectorImpl: SCCCollector {

    private var currentSCC = 0

    override fun <T> findSCCs(g: MutableDirectedGraph<T>) {
//        println("Finding SCCs.")

        val orderToExplore = g.getNodes().sortedBy {
            it.finishingTime
        }

        for (n in orderToExplore) {
            if (!n.isExplored) {
                currentSCC++
                findSCCs(g, n)
            }
        }
    }

    private fun <T> findSCCs(g: MutableDirectedGraph<T>, v: Node<T>) {
        v.isExplored = true
        v.containingScc = currentSCC
//        println("Exploring node ${v.data} in SCC #$currentSCC")

        for (n in g.getNeighborsFrom(v)) {
            if (!n.isExplored) {
                findSCCs(g, n)
            }
        }
    }
}