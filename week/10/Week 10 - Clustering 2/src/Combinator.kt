private typealias Pair = kotlin.Pair<Int, Int>

class Combinator {
    /**
     *  Generate all pair combinations from the given range
     */
    fun choose2(range: IntRange): List<Pair> {
        val result = mutableListOf<Pair>()

        var highIndex = 1
        var lowIndex = 0

        while (highIndex <= range.last) {
            while (lowIndex < highIndex) {
                result.add(Pair(lowIndex, highIndex))
                lowIndex++
            }
            lowIndex = 0
            highIndex++
        }

        return result
    }
}