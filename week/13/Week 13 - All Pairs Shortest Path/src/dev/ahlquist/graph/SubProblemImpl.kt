package dev.ahlquist.graph

import dev.ahlquist.graph.Cost.*

/**
 *  A subproblem for the All Pairs Shortest Path problem.
 *  @param size the maximum oath length
 *  @param dimen the number of vertices in the graph
 */
class SubProblemImpl(
        override val size: Int,
        private val dimen: Int,
): SubProblem {

    private val arr: Array<Array<Cost>> =
            Array(dimen) { Array(dimen) { PositiveInfinity } }

    override fun get(v: Int, w: Int): Cost {
        return arr[v][w]
    }

    override fun set(v: Int, w: Int, value: Cost) {
        arr[v][w] = value
    }

    override fun set(v: Int, w: Int, value: Int) {
        arr[v][w] = Finite(value)
    }
}
