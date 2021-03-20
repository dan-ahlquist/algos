package twoSat

import MutableDirectedGraph
import MutableDirectedGraphImpl

class SatChecker {
    fun isSatisfiable(instance: Instance): Boolean {
        // Create an implication graph for the instance
        val g = implicationGraph(instance)

        // Find SCCs of the implication graph
        TODO()

        // Look for conflicts in the graph
        TODO()
    }

    fun implicationGraph(instance: Instance): MutableDirectedGraph<Int> {
        val result = MutableDirectedGraphImpl<Int>()

        instance.clauses.forEach {
            TODO()
        }

        return result
    }
}