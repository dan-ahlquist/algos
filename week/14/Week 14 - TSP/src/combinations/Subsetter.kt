package combinations

import java.lang.IllegalArgumentException

class Subsetter<T> {
    fun getSubsets(superset: Set<T>, cardinality: Int): Set<Set<T>> {
        val n = superset.size
        val r = cardinality
        val superList = superset.toList()

        if (n < r) throw IllegalArgumentException("Cardinality must be <= superset size.")

        if (r == 0) return setOf(emptySet())
        if (r == n) return setOf(superset)

        // Initialize the n=1 row
        var prev = Array(2) { setOf( emptySet<T>() ) }
        prev[1] = setOf(setOf(superList.first()))

        for (i in 2..n) {
            // Initialize this row
            val curr = Array(i+1) { setOf( emptySet<T>() ) }

            val newItem = superList[i-1]

            val w = i.coerceAtMost(r)

            for (j in 1..w) {

                if (j == i) {
                    // special case where prev[i-1, j] is undefined
                    // but we know it's {{m_1 .. m_i}}
                    curr[j] = setOf(superList.take(i).toSet())
                    continue
                }

                val a = mutableSetOf<Set<T>>()
                prev[j-1].forEach {
                    a.add(it.union(setOf(newItem)))
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
