package dev.ahlquist.graph

import dev.ahlquist.graph.Cost.*
import java.lang.IllegalStateException

/**
 *  A subproblem for the All Pairs Shortest Path problem.
 *  @param length the maximum oath length
 *  @param dimen the number of vertices in the graph
 */
class SubProblemImpl(
        override val length: Int,
        private val dimen: Int,
): SubProblem {

    private val arr: Array<Array<Cost>> =
            Array(dimen) { Array(dimen) { PositiveInfinity } }

    override fun get(v: Int, w: Int): Cost {
        return arr[v][w]
    }

    override fun set(v: Int, w: Int, cost: Cost) {
        if (v == w && cost is Finite && cost.value < 0) {
            throw NegativeCycleException(v)
        }
        arr[v][w] = cost
    }

    override fun set(v: Int, w: Int, value: Int) {
        set(v, w, Finite(value))
    }

    class NegativeCycleException(label: Int): IllegalStateException(
            "Negative cycle detected, involving node $label."
    )
}
