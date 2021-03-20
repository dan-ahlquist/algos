package twoSat

import Edge
import MutableDirectedGraph
import MutableDirectedGraphImpl
import SCCCollectorImpl
import TopoSorterImpl

class SatChecker {

    fun isSatisfiable(instance: Instance): Boolean {
        // Create an implication graph for the instance
        val g = implicationGraph(instance)

        // Find SCCs of the implication graph
//        println("Reversing g - > gRev")
        g.reverse()

//        println("Sorting gRev")
        TopoSorterImpl().sort(g)

//        println("Reversing gRev -> gPrime")
        g.reverse() // original edge directions, now marked with topo ordering
        g.resetExplored()

//        println("Finding SCCs in gPrime")
        SCCCollectorImpl().findSCCs(g)

        // Look for conflicts in the graph
        return !isConflicted(instance, g)
    }

    private fun isConflicted(instance: Instance, g: MutableDirectedGraph<Int>): Boolean {
        return instance.vars.any {
            (g.hasNode(it) && g.hasNode((-1 * it)))
            &&
            (g.getNode(it)?.containingScc == g.getNode((-1 * it))?.containingScc)
        }
    }

    private fun implicationGraph(instance: Instance): MutableDirectedGraph<Int> {
        val result = MutableDirectedGraphImpl<Int>()

        instance.clauses.forEach {
            // Reinflate the variables as neg/pos integers
            val v1 = it.var1 * (if (it.cond1) 1 else -1)
            val v2 = it.var2 * (if (it.cond2) 1 else -1)

            // ~v1 -> v2
            result.addEdge((-1 * v1), v2)
            // ~v2 -> v1
            result.addEdge((-1 * v2), v1)
        }

        return result
    }
}