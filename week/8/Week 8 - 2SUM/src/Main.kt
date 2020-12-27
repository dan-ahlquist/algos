import java.io.File

/*
The goal of this problem is to implement a variant of the 2-SUM algorithm covered in this week's lectures.

The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your array
of integers, with the ith row of the file specifying the ith entry of the array.

Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there are
distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line
addition to the algorithm from lecture.)

Write your numeric answer (an integer between 0 and 20001) in the space provided.

OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you
could compare performance under the chaining and open addressing approaches to resolving collisions.
 */

const val filename = "2sum.txt"

fun main() {
    val inputs = readInputFile(filename)
    println("Read in ${inputs.size} distinct values.")

    val queries = (-10000L..10000L)
    var count = 0
    outer@ for (query in queries) {
        println("Query = $query    Count = $count")
        for (current in inputs) {
            val diff = query - current
            if (query != diff && inputs.contains(diff)) {
                count++
                continue@outer
            }
        }
    }

    println("$count hits")
}

fun readInputFile(filename: String): Set<Long> {
    val result = mutableSetOf<Long>()
    File(filename).forEachLine { line ->
        result.add(line.toLong())
    }
    return result
}
