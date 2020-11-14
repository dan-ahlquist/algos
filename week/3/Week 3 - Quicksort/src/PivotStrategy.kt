import java.lang.IllegalArgumentException

sealed class PivotStrategy {
    abstract fun getPivot(list: List<Int>, a: Int, b: Int): Int
}

class LeftmostPivotStrategy: PivotStrategy() {
    override fun getPivot(list: List<Int>, a: Int, b: Int): Int {
        return a
    }
}

class RightmostPivotStrategy: PivotStrategy() {
    override fun getPivot(list: List<Int>, a: Int, b: Int): Int {
        return b
    }
}

class Median3PivotStrategy: PivotStrategy() {
    override fun getPivot(list: List<Int>, a: Int, b: Int): Int {
        val aVal = list[a]
        val bVal = list[b]
        val m = (a+b)/2
        val mVal = list[m]
        return when {
            (mVal in aVal..bVal) -> m
            (bVal in aVal..mVal) -> b
            (aVal in bVal..mVal) -> a
            (mVal in bVal..aVal) -> m
            (bVal in mVal..aVal) -> b
            (aVal in mVal..bVal) -> a
            else -> throw IllegalArgumentException("WTF")
        }
    }
}
