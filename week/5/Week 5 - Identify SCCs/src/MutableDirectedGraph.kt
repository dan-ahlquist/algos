
typealias Edge<T> = Pair<Node<T>, Node<T>>

data class Node<T> (
    val data: T,
    var finishingTime: Long = 0L,
    var leaderNode: Node<T>? = null
)

interface MutableDirectedGraph<T> {
    fun hasNode(a: T): Boolean
    fun hasEdge(a: T, b: T): Boolean
    fun getNeighborsFrom(a: T): List<T>
    fun reverse(): MutableDirectedGraph<T>

    fun addNode(a: T)
    fun addEdge(a: T, b: T)
}