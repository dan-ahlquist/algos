import java.io.File

/*
The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 3 lecture on heap
applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this as
a stream of numbers, arriving one by one. Letting x_i  denote the iith number of the file, the kth median m_k is defined
as the median of the numbers x_1 ... x_k. (So, if k is odd, then m_k  is ((k+1)/2)((k+1)/2)th smallest number among
x_1 ... x_k; if k is even, then m_k is the (k/2)(k/2)th smallest number among x_1 ... x_kx.)

In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That is,
you should compute (m_1+m_2+m_3 + ... + m_10000) mod 10000

OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the algorithm.
 */

fun main() {
    val mm = MedianMaintainer()

    val filename = "Median.txt"

    var total = 0

    File(filename).useLines {
        it.forEach { line ->
            val n = line.toInt()
            mm.insert(n)
            total += mm.median!!
        }
    }

    println("$total (mod 10000 = ${total % 10000})")

    println("Final median = ${mm.median}")

    println("Queue size = ${mm.left.size} / ${mm.right.size}")

    println("${mm.left.peek()} | ${mm.median} | ${mm.right.peek()}")
}
