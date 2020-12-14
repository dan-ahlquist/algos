import java.util.*

class MedianMaintainer {

    val left: PriorityQueue<Int> = PriorityQueue { a, b -> b.compareTo(a) }
    val right: PriorityQueue<Int> = PriorityQueue()

    var median: Int? = null

    fun insert(n: Int) {
        when {
            median == null -> median = n
            n <= median!! -> leftInsert(n)
            else -> rightInsert(n)
        }
    }

    private fun leftInsert(n: Int) {
        println("Median = $median. Left-inserting n = $n.")

        if (left.size >= right.size) {
            right.add(median)
            left.add(n)
            median = left.poll()
        } else {
            left.add(n)
        }
    }

    private fun rightInsert(n: Int) {
        println("Median = $median. Right-inserting n = $n.")

        if (right.size > left.size) {
            left.add(median)
            right.add(n)
            median = right.poll()
        } else {
            right.add(n)
        }
    }
}
