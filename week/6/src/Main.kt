import java.io.File
import java.lang.Exception
import java.lang.StringBuilder

/*

The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to 200.
Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge. For
example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. The next
entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length 8200. The
rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the corresponding edges.

Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex,
and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between a
vertex vv and vertex 1, we'll define the shortest-path distance between 1 and vv to be 1000000.

You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,197.
You should encode the distances as a comma-separated string of integers. So if you find that all ten of these vertices
except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should be 1000,1000,
1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string should be in the
same order in which the above ten vertices are given. The string should not contain any spaces. Please type your answer
in the space provided.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn)O(mn) time implementation of Dijkstra's
algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based
version. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping
between vertices and their positions in the heap.

 */

const val fileName = "dijkstraData.txt"
//const val fileName = "easy.txt"

fun main() {
    val g = readInput(fileName)

    val spf: ShortestPathFinder = DijkstraPathFinder()

    val s = g.getNode(1)

    spf.findShortestPaths(g, s)

    report(g)
}

private fun report(g: MutableDirectedGraphImpl<Int, Int>) {
    val queries = listOf(7, 37, 59, 82, 99, 115, 133, 165, 188, 197)
//    val queries = listOf(1,2,3,4)
    val sb = StringBuilder()
    for (q in queries) {
        val n = g.getNode(q)
        sb.append("${n.shortestPathLength},")
//        println("Node $q has shortest path = ${n.shortestPathLength}")
        g.printNode(n)
    }
    println("${sb.toString()}")
}

fun readInput(filename: String): MutableDirectedGraphImpl<Int, Int> {
    val g = MutableDirectedGraphImpl<Int, Int>()
    File(filename).useLines {
        it.forEach { line ->
            val toks = line.split('\t')
            val from = toks[0]
            toks.drop(1).forEach {
                try {
                    val (to, weight) = it.split(',')
                    g.addEdge(from.toInt(), to.toInt(), weight.toInt())
                    g.addEdge(to.toInt(), from.toInt(), weight.toInt())
                } catch (e: Exception) {}
            }
        }
    }
    return g
}
