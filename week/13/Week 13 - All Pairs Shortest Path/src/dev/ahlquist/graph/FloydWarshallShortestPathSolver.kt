package dev.ahlquist.graph

import dev.ahlquist.graph.Cost.*

class FloydWarshallShortestPathSolver: ShortestPathSolver {

    override fun solve(g: WeightedDirectedGraph): Path {
        val n = g.n
        val dimen = n + 1

        println("Base cases...")
        // base cases (k = 0)
        var prev: SubProblem = SubProblemImpl(0, dimen)
        for (v in 1..n)
            for (w in 1..n)
                when {
                    v == w ->
                        prev[v, w] = 0
                    g.hasEdge(v, w) ->
                        prev[v, w] = g.getEdge(v, w)!!.weight
                    else ->
                        prev[v, w] = PositiveInfinity
                }

        println("Solving...")
        // systematically solve all subproblems
        lateinit var curr: SubProblem
        for (k in 1..n) {
            if (k%100 == 0) println("\tk = $k")

            curr = SubProblemImpl(k, dimen)
            for (v in 1..n) {
                for (w in 1..n) {
                    val reuse = prev[v, w]
                    val extend = prev[v, k] + prev[k, w]
                    curr[v, w] = minOf(reuse, extend)
                }
            }
            prev = curr
        }

        println("Finding shortest shortest path...")
        // find the shortest shortest path in the final subproblem
        var shortest = Path(Node(0), Node(0), PositiveInfinity)
        for (v in 1..n)
            for (w in 1..n)
                if (curr[v, w] < shortest.cost)
                    shortest = Path(Node(v), Node(w), curr[v, w])

        return shortest
    }
}