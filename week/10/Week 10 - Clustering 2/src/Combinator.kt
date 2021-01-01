private typealias Pair = kotlin.Pair<Int, Int>
private typealias Triple = kotlin.Triple<Int, Int, Int>

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
            highIndex++
        }

        return result
    }

    /**
     *  Generate all triple combinations from the given range
     */
    fun choose3(range: IntRange): List<Triple> {
        val result = mutableListOf<Triple>()

        var highIndex = 2
        var midIndex = 1
        var lowIndex = 0

        while (highIndex <= range.last) {
            while (midIndex < highIndex) {
                while (lowIndex < midIndex) {
                    result.add(Triple(lowIndex, midIndex, highIndex))
                    lowIndex++
                }
                midIndex++
            }
            highIndex++
        }

        return result
    }
}