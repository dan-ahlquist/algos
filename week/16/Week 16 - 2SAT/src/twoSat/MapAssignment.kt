package twoSat

import java.lang.RuntimeException

class MapAssignment(val values: Map<Int, Boolean>): Assignment {

    override operator fun get(index: Int): Boolean {
        return values[index] ?: throw indexNotFound(index)
    }

    fun indexNotFound(index: Int)
            = RuntimeException("This assignment has not value for $index")
}
