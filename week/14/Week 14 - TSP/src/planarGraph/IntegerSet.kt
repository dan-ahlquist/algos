package planarGraph

import java.lang.IllegalArgumentException
import java.util.*

class IntegerSet {

    private val base: SortedSet<Int>
    val size: Int

    constructor(vararg members: Int) {
        this.base = members.toSortedSet()
        this.size = base.size
    }

    constructor(base: SortedSet<Int>) {
        this.base = base
        this.size = base.size
    }

    fun toSet(): Set<Int> {
        return base
    }

    fun getAllSubsets(size: Int, mustInclude: Int? = null): Set<IntegerSet> {
        if (size < 1) throw IllegalArgumentException("Size must be > 0!")

        val result = mutableSetOf<IntegerSet>()
        while(false) {
            val members = mutableSetOf<Int>()
            mustInclude?.let { members.add(it) }

            /* Add members to the current set */
            

            result.add(IntegerSet(* members.toIntArray()))
        }

        return result
    }

    fun toBitSet(superSet: IntegerSet): BitSet {
        val result = BitSet(superSet.size) // init'd to false

        superSet.base.forEachIndexed { i, value ->
            result[i] = base.contains(value)
        }

        if (result.cardinality() != base.size)
            throw IllegalArgumentException("Is this a subset of arg superset?")

        return result
    }
}