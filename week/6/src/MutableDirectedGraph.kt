import org.graalvm.compiler.graph.Edges

data class Edge<T> (val from: Node<T>, val to: Node<T>, val weight: Int, var score: Int)

class Node<T> (
    val data: T,
    var isExplored: Boolean = false,
    var shortestPathLength: Int = Int.MAX_VALUE
)

interface MutableDirectedGraph<T> {
    val nodeCount: Int
    fun getNodes(): Set<Node<T>>
    fun hasNode(a: T): Boolean
    fun getNode(a: T): Node<T>
    fun hasEdge(a: T, b: T): Boolean
    fun getEdgesFrom(a: Node<T>): Set<Edge<T>>
    fun addNode(a: T)

    fun addEdge(a: T, b: T, weight: Int)

    fun resetExplored()
    fun reverse()

    fun print()
}