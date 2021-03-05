package combinations

import java.lang.IllegalArgumentException
import java.lang.Math.min

class Subsetter<T> {
    fun getSubsets(superset: Set<T>, cardinality: Int): Set<Set<T>> {
        val n = superset.size
        val r = cardinality
        val superList = superset.toList()

        if (n < r) throw IllegalArgumentException("Cardinality must be <= superset size.")

        if (r == 0) return setOf(emptySet())

        // Initialize the n=1 row
        var prev = Array(2) { setOf( emptySet<T>() ) }
        prev[1] = setOf(setOf(superList.first()))

        for (i in 2..n) {
            // Initialize this row
            val w = min(n-1, r)
            val curr = Array(w) { setOf( emptySet<T>() ) }
            if (n <= r) {
                curr[w] = setOf(superList.take(n).toSet())
            }

            for (j in 1..w) {
                val item = superList[n-1]
                val a = mutableSetOf<Set<T>>()
                prev[j-1].forEach {
                    a.add(it.union(setOf(item)))
                }

                val b = mutableSetOf<Set<T>>()
                prev[j].forEach {
                    b.add(it)
                }

                curr[j] = a.union(b)
            }

            prev = curr
        }

        return prev[r] // curr gets assigned to prev at end of final row
    }
}
