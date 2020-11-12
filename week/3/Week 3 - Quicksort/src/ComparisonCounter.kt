
class ComparisonCounter (list: List<Int>) {

    private val comparisonCountResult by lazy { countComparisons(list) }

    val comparisons: Long
        get() = comparisonCountResult.comparisons

    val sortedList: List<Int>
        get() = comparisonCountResult.sortedList

    private fun countComparisons(list: List<Int>): ComparisonCountResult {
        TODO()
    }
}
