import java.io.File

/*

The file [SCC.txt] contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714.
Every row indicates an edge, the vertex label in first column is the tail and the vertex label in second column is the
head (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex).
So for example, the 11th row looks like : "2 47646". This just means that the vertex with label 2 has an outgoing edge
to the vertex with label 47646.

Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), and to
run this algorithm on the given graph.

Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes,
separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500, 400,
300, 200 and 100, then your answer should be "500,400,300,200,100" (without the quotes). If your algorithm finds less
than 5 SCCs, then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300,
and 100, then your answer should be "400,300,100,0,0" (without the quotes). (Note also that your answer should not have
any spaces in it.)

WARNING: This is the most challenging programming assignment of the course. Because of the size of the graph you may
have to manage memory carefully. The best way to do this depends on your programming language and environment, and we
strongly suggest that you exchange tips for doing this on the discussion forums.

 */

/**
 *  IMPORTANT: Increase stack size for this recursive solution! JVM option `-Xss1g` was used successfully.
 */
fun main() {

    println("Reading input")
    val g = readInput("SCC.txt")
//    val g = readInput("book-quiz-8-6.txt")

    println("Reversing g - > gRev")
    g.reverse()

    println("Sorting gRev")
    TopoSorterImpl().sort(g)

    println("Reversing gRev -> gPrime")
    g.reverse() // original edge directions, now marked with topo ordering
    g.resetExplored()

    println("Finding SCCs in gPrime")
    SCCCollectorImpl().findSCCs(g)

    val resultMap = mutableMapOf<Int, Int>() // SCC# -> count

    g.getNodes()
        .sortedBy {
            it.containingScc
        }
        .forEach {
            val count = resultMap.getOrPut(it.containingScc, {0})
            resultMap[it.containingScc] = count + 1
        }

    val sortedResults = resultMap.toList().sortedByDescending { (_, value) -> value}.toMap()
    sortedResults.keys.take(100).forEach { k ->
        println("SCC # $k has ${sortedResults[k]} nodes.")
    }
}

fun readInput(filename: String): MutableDirectedGraphImpl<Int> {
    val result = MutableDirectedGraphImpl<Int>()

    File(filename).forEachLine {
        val (u, v) = it.split(' ')
        result.addEdge(u.toInt(), v.toInt())
    }

    return result
}