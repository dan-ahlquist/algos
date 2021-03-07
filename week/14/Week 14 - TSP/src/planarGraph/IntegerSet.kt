package planarGraph

import combinations.getSubsets
import java.lang.IllegalArgumentException
import java.util.*

class IntegerSet {

    private val base: SortedSet<Int>
    val size: Int

    constructor(vararg members: Int) {
        this.base = members.toSortedSet()
        this.size = base.size
    }

    constructor(base: Set<Int>) {
        this.base = base.toSortedSet() // shallow copy
        this.size = base.size
    }

    fun toSet(): Set<Int> {
        return base
    }

    fun without(n: Int): IntegerSet {
        val copy = base.toMutableSet()
        copy.remove(n)
        return IntegerSet(copy)
    }

    fun getAllSubsets(size: Int, mustInclude: Int? = null): Set<IntegerSet> {
        if (size < 1) throw IllegalArgumentException("Size must be > 0!")

        val subsetsOfBase = base.getSubsets(size)

        return subsetsOfBase
                .filter { mustInclude == null || it.contains(mustInclude) }
                .map { IntegerSet(it) }
                .toSet()
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

    fun toInt(superSet: IntegerSet): Int {
        return toBitSet(superSet).toInt()
    }

    private fun BitSet.toInt(): Int {
        var result = 0
        for (i in 0..size())
            result += if (get(i)) (1 shl i) else 0
        return result
    }
}