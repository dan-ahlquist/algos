
class InversionCounter (list: List<Int>) {

    private val inversionCountResult by lazy { countInversions(list) }

    val inversions: Long
        get() = inversionCountResult.inversions

    val sortedList: List<Int>
        get() = inversionCountResult.sortedList

    // Number of inversions in this region of the list
    // a inclusive
    // b exclusive
    private fun countInversions(list: List<Int>): InversionCountResult {
        // Base case of length 0
        if(list.isEmpty()) return InversionCountResult(emptyList(),0)
        // Base Case of length 1
        if(list.size == 1) return InversionCountResult(list,0)

        val m = list.size / 2

        val l = countInversions(list.subList(0, m))
        val r = countInversions(list.subList(m, list.size))
        val s = countSplitInversions(l.sortedList, r.sortedList)

        val totalInversions = l.inversions + r.inversions + s.inversions
        return InversionCountResult(s.sortedList, totalInversions)
    }

    // Number of split inversions between the regions [a,  m) and [m, b)
    // E.g. a=0, m=2, b=5 for a region of length 5:
    // |_ _|_ _ _|
    // 0   2     5
    private fun countSplitInversions(left: List<Int>, right: List<Int>): InversionCountResult {
        val sortedList = mutableListOf<Int>()
        var inversions = 0L

        var i = 0
        var j = 0
        val m = left.size
        val b = right.size
        while (i<m && j<b) {
            // No inversion, advance i
            if(left[i] < right[j]) {
                sortedList.add(left[i])
                i++
            } else { // Inversion, advance j
                sortedList.add(right[j])
                inversions += (m-i) // How many left-items this right-item is inverted with
                j++
            }
        }

        if(j == b) { // Right side emptied first; remaining left side is all inversions.
            val remainingLeft = left.subList(i,m)
            sortedList.addAll(remainingLeft)
        } else {
            val remainingRight = right.subList(j,b)
            sortedList.addAll(remainingRight)
        }

        return InversionCountResult(sortedList, inversions)
    }
}
