package planarGraph

import java.lang.IllegalStateException
import kotlin.math.pow

class BellmanHeldKarp: TourFinder {

    override fun findShortestTourLength(graph: PlanarGraph): Float {

        val n = graph.size
        val m = (2.0.pow((n).toDouble())).toInt() // 2^(n-1) -1

        println("Allocating for n = $n, m = $m")

        val arr = Array(m+1) { Array(n+1) { Float.POSITIVE_INFINITY } }

        val points = IntegerSet(* graph.labels)

        println("Base cases")

        for (j in 2..n) {
            val set = IntegerSet(1, j)
            val i = set.toInt(points)
//            println("i, j = $i, $j")
            arr[i][j] = graph.distance(1, j)
        }

        for (s in 3..n) {
//            println("Solving s = $s")

            val subsets = points.getAllSubsets(s, 1)
            subsets.forEach { S ->
                S.without(1).toSet().forEach { j ->
                    val i = S.toInt(points)
//                    println("i = $i = ${Integer.toBinaryString(i)}")

                    val min = S.toSet()
                            .filter { it != 1 && it != j }
                            .map { k ->
                                val sMinusJ = S.without(j).toInt(points)
                                arr[sMinusJ][k] + graph.distance(k, j)
                            }.minOrNull()

                    arr[i][j] = min ?: throw cantFindMin("iteration")
                }
            }
        }

        val V = points.toInt(points)
        val min = (2..n)
                .map { j ->
                    arr[V][j] + graph.distance(j, 1)
                }.minOrNull()

        return min ?: throw cantFindMin("problem")
    }

    private fun cantFindMin(t: String) = IllegalStateException("Min was null for this $t...")
}