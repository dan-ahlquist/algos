
class ComparisonCounter (list: List<Int>, private val pivotStrategy: PivotStrategy) {

    // Make a mutable copy
    private val listCopy = list.toMutableList()

    var comparisons: Long = 0L

    private val comparisonCountResult = sortAndCount(this.listCopy)

    val sortedList: List<Int>
        get() = comparisonCountResult.sortedList

    private fun sortAndCount(list: MutableList<Int>): ComparisonCountResult {
        if (list.size <= 1) return ComparisonCountResult(list, 0)

        comparisons += (list.size) -1

        var count = 0L
        //TODO can be squashed down to a call to the recursive version
        val m = partition(list, 0, list.size)
        sortAndCount(list, 0,  m)
        sortAndCount(list, m+1, list.size)

        return ComparisonCountResult(list, count)
    }

    private fun sortAndCount(list: MutableList<Int>, a: Int, b: Int): Long {
        if (b-a <= 1) return  0

        comparisons += (b-a) -1

        var count = 0L

        val m = partition(list, a, b)
        sortAndCount(list, a,  m)
        sortAndCount(list, m+1, b)

        return count
    }

    /**
     *  Partition list[a, b)
     */
    private fun partition(list: MutableList<Int>, a: Int, b: Int): Int {
        val p = pivotStrategy.getPivot(list, a, b-1)
        val pivotValue = list[p]
        var i = a+1
        var j = a+1

        list.swap(a, p)

        //TODO this can be a for-loop
        while (j < b){
            val comp = list[j]
            if (comp < pivotValue) {
                list.swap(i, j)
                i++
            }
            j++
        }

        list.swap(a, i-1)
        return i-1
    }
}

fun MutableList<Int>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}
