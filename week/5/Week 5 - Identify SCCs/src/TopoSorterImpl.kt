import java.util.*

class TopoSorterImpl: TopoSorter {

    var finishingTime = Int.MAX_VALUE

    override fun <T> sort(g: MutableDirectedGraph<T>) {
        println("Sorting graph.")
        finishingTime = g.nodeCount

        for (n in g.getNodes()) {
            if (!n.isExplored) {
                sort(g, n)
            }
        }
    }

    private fun <T> sort(g: MutableDirectedGraph<T>, v: Node<T>) {
        v.isExplored = true
        println("Exploring node ${v.data}")

        for (n in g.getNeighborsFrom(v)) {
            if (!n.isExplored) {
                sort(g, n)
            }
        }

        println("Setting node ${v.data} finishing time = $finishingTime")
        v.finishingTime = finishingTime
        finishingTime--
    }
}