import java.io.File
import kotlin.random.Random

/*

Question 1
Download the following text file: kargerMinCut.txt

The file contains the adjacency list representation of a simple undirected graph. There are 200 vertices labeled 1 to
200. The first column in the file represents the vertex label, and the particular row (other entries except the first
column) tells all the vertices that the vertex is adjacent to. So for example, the 6th row looks like :
"6 155 56 52 120 ......". This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) the
vertices with labels 155,56,52,120,......,etc

Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on the above
graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions.
Initially, you might want to do this naively, creating a new graph from the old every time there's an edge contraction.
But you should also think about more efficient implementations.) (WARNING: As per the video lectures, please make sure
to run the algorithm many times with different random seeds, and remember the smallest cut that you ever find.) Write
your numeric answer in the space provided. So e.g., if your answer is 5, just type 5 in the space provided.

*/
fun main() {
//    val g: MutableGraph<Int> = readInputFile("easyTest.txt")
//    g.print()
    quickTest()
}

fun readInputFile(pathname: String): MutableUndirectedGraph<Int> {
    val result = AdjacencyListUndirectedGraph<Int>()

    File(pathname).useLines { lines ->
        lines.toList().map { line ->
            val toks = line.split('\t').map(String::toInt)
            val vertex = toks[0]
            val neighbors = toks.subList(1, toks.size)
            for (n in neighbors)
                result.addEdge(vertex, n)
        }
    }

    return result
}

fun randomEdgeTest(r: Random) {
    val g: MutableUndirectedGraph<Char> = AdjacencyListUndirectedGraph()

    g.addEdge('A', 'B')
    g.addEdge('A', 'C')
    g.addEdge('A', 'D')
    g.addEdge('C', 'B')
    g.addEdge('D', 'B')
    g.addEdge('D', 'B')

    g.print()
    println("Selecting random edges:")

    for (i in 1..50) {
        val edge = g.getRandomEdge(r)
        println("(${edge.first}, ${edge.second})")
    }
}

fun collapseTest() {
    val g: MutableUndirectedGraph<Char> = AdjacencyListUndirectedGraph()

    g.addEdge('A', 'B')
    g.addEdge('A', 'C')
    g.addEdge('A', 'D')
    g.addEdge('C', 'B')
    g.addEdge('D', 'B')
    g.addEdge('D', 'B')

    g.print()

    println("Contracting (A, B) into A.")

    g.contractEdge('A', 'B')
    g.print()
}

fun quickTest() {
    val g: MutableUndirectedGraph<Int> = AdjacencyListUndirectedGraph()

    g.addEdge(1, 2)
    g.addEdge(1, 3)
    g.addEdge(2, 3)

    g.print()

    println("Deleting edge (1,3).")

    g.deleteEdge(1, 3)
    g.print()

    println("Deleting node 3.")

    g.deleteNode(3)
    g.print()

    println("Adding node 4.")

    g.addNode(4)
    g.print()

    println("Adding edge (4,1).")

    g.addEdge(4, 1)
    g.print()

    println("Adding parallel edge (4,1).")

    g.addEdge(4, 1)
    g.print()

    println("Deleting node 4.")

    g.deleteNode(4)
    g.print()
}
